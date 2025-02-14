package tauri.dev.jsg.command;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import tauri.dev.jsg.tileentity.props.DestinyCountDownTile;

import javax.annotation.Nonnull;

import static net.minecraft.command.CommandBase.parseCoordinate;

public class CommandCountdownSet implements IJSGCommand {

    @Nonnull
    @Override
    public String getName() {
        return "countdown";
    }

    @Nonnull
    @Override
    public String getDescription() {
        return "Sets Destiny timer to the value";
    }

    @Nonnull
    @Override
    public String getUsage() {
        return "countdown [set|reset] <ticks> [x y z]";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 2;
    }

    @Override
    public void execute(@Nonnull MinecraftServer server, @Nonnull ICommandSender sender, String[] args) throws CommandException {
        BlockPos pos = sender.getPosition();
        World world = sender.getEntityWorld();
        TileEntity tileEntity = null;

        if (args.length < 1) {
            //notifyCommandListener(sender, this, "Please, insert time in ticks!");
            JSGCommand.sendUsageMess(sender, this);
            return;
        }

        if (args.length > 3) {
            int x1 = (int) parseCoordinate(pos.getX(), args[1], false).getResult();
            int y1 = (int) parseCoordinate(pos.getY(), args[2], 0, 255, false).getResult();
            int z1 = (int) parseCoordinate(pos.getZ(), args[3], false).getResult();
            BlockPos foundPos = new BlockPos(x1, y1, z1);
            tileEntity = world.getTileEntity(foundPos);
        }
        if (tileEntity == null)
            tileEntity = JSGCommands.rayTraceTileEntity((EntityPlayerMP) sender);

        try {
            long ticks = Long.parseLong(args[0]);

            if (tileEntity instanceof DestinyCountDownTile) {
                ((DestinyCountDownTile) tileEntity).setCountDown(sender.getEntityWorld().getTotalWorldTime() + ticks);
                JSGCommand.sendSuccessMess(sender, "Countdown set to " + ticks + " ticks!");
            } else
                JSGCommand.sendErrorMess(sender, "Target block is not a countdown!");
        } catch (NumberFormatException e) {
            JSGCommand.sendUsageMess(sender, this);
        }
    }
}
