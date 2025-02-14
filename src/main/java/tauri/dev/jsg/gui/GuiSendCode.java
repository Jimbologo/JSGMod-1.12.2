package tauri.dev.jsg.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.EnumHand;
import tauri.dev.jsg.config.JSGConfig;
import tauri.dev.jsg.gui.base.JSGGuiBase;
import tauri.dev.jsg.gui.base.JSGGuiButton;
import tauri.dev.jsg.gui.element.NumberOnlyTextField;
import tauri.dev.jsg.item.linkable.gdo.GDOActionEnum;
import tauri.dev.jsg.item.linkable.gdo.GDOActionPacketToServer;
import tauri.dev.jsg.item.linkable.gdo.GDOItem;
import tauri.dev.jsg.item.linkable.gdo.GDOMessages;
import tauri.dev.jsg.packet.JSGPacketHandler;
import tauri.dev.jsg.sound.JSGSoundHelper;
import tauri.dev.jsg.sound.SoundEventEnum;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author matousss
 * @author MineDragonCZ_
 */
public class GuiSendCode extends JSGGuiBase {
    EnumHand hand;

    public GuiSendCode(EnumHand hand) {
        super(260, 200, 4, FRAME_COLOR, BG_COLOR, TEXT_COLOR, 4);
        this.hand = hand;
    }

    String message = null;
    int messageColor = 14876672;
    JSGGuiButton sendButton;
    JSGGuiButton backButton;
    NumberOnlyTextField codeField;
    protected List<JSGGuiButton> keyPadButtons = new ArrayList<>();

    @Override
    public void initGui() {
        super.initGui();
        keyPadButtons.clear();
        codeField = new NumberOnlyTextField(0, Minecraft.getMinecraft().fontRenderer, width / 2 - 80, height / 2 - 75, 160, 20);
        codeField.setMaxStringLength(JSGConfig.Stargate.iris.irisCodeLength);
        codeField.setFocused(true);
        // init num pad
        int i = 0;
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 3; x++) {
                if (y != 3) {
                    keyPadButtons.add(new JSGGuiButton(i + 3,
                            (width / 2 - 48) + (33 * x),
                            (height / 2 - 35) + (33 * y),
                            30,
                            30,
                            "" + (i + 1)));
                    i++;
                } else {
                    if (x == 0)
                        sendButton = new JSGGuiButton(1, (width / 2 - 48), (height / 2 - 35) + (33 * y), 30, 30, "OK");
                    if (x == 1)
                        keyPadButtons.add(new JSGGuiButton(12, (width / 2 - 48) + 33, (height / 2 - 35) + (33 * y), 30, 30, "0"));
                    if (x == 2) {
                        backButton = new JSGGuiButton(13, (width / 2 - 48) + (33 * 2), (height / 2 - 35) + (33 * y), 30, 30, "<-");
                    }
                }
            }
        }
        sendButton.enabled = false;
        backButton.enabled = false;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        GlStateManager.pushMatrix();
        translateToCenter();
        drawBackground();
        drawString(I18n.format("gui.gdo.send_code"), 5, 5, 0x00AA00);
        GlStateManager.popMatrix();
        codeField.drawTextBox();
        sendButton.drawButton(mc, mouseX, mouseY, partialTicks);
        backButton.drawButton(mc, mouseX, mouseY, partialTicks);
// <matousss> to <MineDragonCZ_>: like wtf?
//        for (GuiButton button : jsgButtonList) {
//            button.enabled = true;
//            if(button.id == 13) {
//                button.enabled = codeField.getText().length() > 0;
//                sendButton.enabled = button.enabled;
//            }
//            else{
//                button.enabled = codeField.getText().length() < JSGConfig.irisConfig.irisCodeLength;
//            }
//        }

        // draw num pad
        for (JSGGuiButton guiButton : this.keyPadButtons) {
            guiButton.drawButton(mc, mouseX, mouseY, partialTicks);
        }


        if (message != null) {
            drawCenteredString(mc.fontRenderer, message, width / 2, codeField.y + 25, messageColor);
        }
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        super.keyTyped(typedChar, keyCode);
        refreshButtonEnable();
        codeField.textboxKeyTyped(typedChar, keyCode);
        if (keyCode == 28) sendCode();
        playButtonPressSound();

    }


    private void refreshButtonEnable() {
        sendButton.enabled = !codeField.getText().isEmpty();
        backButton.enabled = sendButton.enabled;

        for (JSGGuiButton button : keyPadButtons) {
            button.enabled = codeField.getText().length() < JSGConfig.Stargate.iris.irisCodeLength;
        }
    }

    private void playButtonPressSound() {
        JSGSoundHelper.playSoundEventClientSide(Minecraft.getMinecraft().world, Minecraft.getMinecraft().player.getPosition(), SoundEventEnum.GUI_SEND_CODE_BUTTON_PRESS);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        codeField.mouseClicked(mouseX, mouseY, mouseButton);
        if (sendButton.mousePressed(this.mc, mouseX, mouseY)) {
            //sendButton.playPressSound(this.mc.getSoundHandler());
            playButtonPressSound();
            sendCode();
        }
        if (backButton.mousePressed(this.mc, mouseX, mouseY) && codeField.getText().length() > 0) {
            codeField.setText(codeField.getText().substring(0, codeField.getText().length() - 1));
            playButtonPressSound();
        }

        // click on num pad
        for (GuiButton guibutton : this.keyPadButtons) {
            if (guibutton.mousePressed(this.mc, mouseX, mouseY)) {
                //guibutton.playPressSound(this.mc.getSoundHandler());
                playButtonPressSound();
                codeField.setText(codeField.getText() + guibutton.displayString);
            }
        }
        codeField.setFocused(true);
        refreshButtonEnable();
    }


    public void sendCode() {
        if (GDOItem.isLinked(this.mc.player.getHeldItem(hand))) {

            if (codeField.getText().isEmpty()) {
                message = GDOMessages.CODE_NOT_SET.textComponent.getFormattedText();
                messageColor = 14876672;
                codeField.setFocused(true);
                return;
            }
            int code = Integer.parseInt(codeField.getText());
            JSGPacketHandler.INSTANCE.sendToServer(new GDOActionPacketToServer(GDOActionEnum.SEND_CODE, hand, code, false));
            this.mc.player.closeScreen();

        }
    }
}
