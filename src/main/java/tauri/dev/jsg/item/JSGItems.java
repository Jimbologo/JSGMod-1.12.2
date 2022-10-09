package tauri.dev.jsg.item;

import tauri.dev.jsg.config.JSGConfig;
import tauri.dev.jsg.item.linkable.dialer.UniverseDialerItem;
import tauri.dev.jsg.item.linkable.gdo.GDOItem;
import tauri.dev.jsg.item.notebook.NotebookItem;
import tauri.dev.jsg.item.notebook.PageNotebookItem;
import tauri.dev.jsg.item.renderer.CustomModelItemInterface;
import tauri.dev.jsg.item.stargate.PageMysteriousItem;
import tauri.dev.jsg.item.stargate.UpgradeIris;
import tauri.dev.jsg.item.tools.staff.StaffItem;
import tauri.dev.jsg.item.tools.zat.ZatItem;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import tauri.dev.jsg.util.main.loader.JSGCreativeTabsHandler;

@EventBusSubscriber
public class JSGItems {

    /**
     * DHD power/control crystal
     */
    public static final Item CRYSTAL_CONTROL_MILKYWAY_DHD = ItemHelper.createGenericItem("crystal_control_dhd", JSGCreativeTabsHandler.jsgItemsCreativeTab);
    public static final Item CRYSTAL_CONTROL_PEGASUS_DHD = ItemHelper.createGenericItem("crystal_control_pegasus_dhd", JSGCreativeTabsHandler.jsgItemsCreativeTab);
    /**
     * DHD Pegasus control crystal crafting
     */
    public static final Item CRYSTAL_RAW_PEGASUS_DHD = ItemHelper.createGenericItem("raw_pegasus_dhd_crystal", JSGCreativeTabsHandler.jsgItemsCreativeTab);
    public static final Item CRYSTAL_BLUE_PEGASUS = ItemHelper.createGenericItem("crystal_blue_pegasus", JSGCreativeTabsHandler.jsgItemsCreativeTab);

    /**
     * Iris/Shield upgrade
     */
    public static final Item IRIS_BLADE = ItemHelper.createGenericItem("iris_blade", JSGCreativeTabsHandler.jsgItemsCreativeTab);
    public static final Item QUAD_IRIS_BLADE = ItemHelper.createGenericItem("quad_iris_blade", JSGCreativeTabsHandler.jsgItemsCreativeTab);
    public static final Item UPGRADE_IRIS = new UpgradeIris("upgrade_iris", tauri.dev.jsg.config.JSGConfig.irisConfig.titaniumIrisDurability);

    public static final Item IRIS_BLADE_TRINIUM = ItemHelper.createGenericItem("iris_blade_trinium", JSGCreativeTabsHandler.jsgItemsCreativeTab);
    public static final Item QUAD_IRIS_BLADE_TRINIUM = ItemHelper.createGenericItem("quad_iris_blade_trinium", JSGCreativeTabsHandler.jsgItemsCreativeTab);
    public static final Item UPGRADE_IRIS_TRINIUM = new UpgradeIris("upgrade_iris_trinium", JSGConfig.irisConfig.triniumIrisDurability);

    public static final Item UPGRADE_SHIELD = ItemHelper.createGenericItem("upgrade_shield", JSGCreativeTabsHandler.jsgItemsCreativeTab);
    public static final Item SHIELD_EMITTER = ItemHelper.createGenericItem("shield_emitter", JSGCreativeTabsHandler.jsgItemsCreativeTab);

    /**
     * These allow for dialing 8th glyph(cross dimension travel) and show different address spaces
     */
    public static final Item CRYSTAL_GLYPH_DHD = ItemHelper.createGenericItem("crystal_glyph_dhd", JSGCreativeTabsHandler.jsgItemsCreativeTab);
    public static final Item CRYSTAL_GLYPH_STARGATE = ItemHelper.createGenericItem("crystal_glyph_stargate", JSGCreativeTabsHandler.jsgItemsCreativeTab);
    public static final Item CRYSTAL_GLYPH_MILKYWAY = ItemHelper.createGenericItem("crystal_glyph_milkyway", JSGCreativeTabsHandler.jsgItemsCreativeTab);
    public static final Item CRYSTAL_GLYPH_PEGASUS = ItemHelper.createGenericItem("crystal_glyph_pegasus", JSGCreativeTabsHandler.jsgItemsCreativeTab);
    public static final Item CRYSTAL_GLYPH_UNIVERSE = ItemHelper.createGenericItem("crystal_glyph_universe", JSGCreativeTabsHandler.jsgItemsCreativeTab);

    // transportrings
    public static final Item CRYSTAL_GLYPH_GOAULD = ItemHelper.createGenericItem("crystal_glyph_goauld", JSGCreativeTabsHandler.jsgItemsCreativeTab);
    public static final Item CRYSTAL_GLYPH_ORI = ItemHelper.createGenericItem("crystal_glyph_ori", null); // todo(Mine): after ori panel is done, set this to items tab

    /**
     * Diffrent Naquadah(main Stargate building material) stages of purity
     */
    public static final Item NAQUADAH_SHARD = ItemHelper.createGenericItem("naquadah_shard", JSGCreativeTabsHandler.jsgItemsCreativeTab);
    public static final Item NAQUADAH_ALLOY_RAW = ItemHelper.createGenericItem("naquadah_alloy_raw", JSGCreativeTabsHandler.jsgItemsCreativeTab);
    public static final Item NAQUADAH_ALLOY = ItemHelper.createGenericItem("naquadah_alloy", JSGCreativeTabsHandler.jsgItemsCreativeTab);

    /**
     * Titanium & Trinium
     */
    public static final Item TITANIUM_INGOT = ItemHelper.createGenericItem("titanium_ingot", JSGCreativeTabsHandler.jsgItemsCreativeTab);
    public static final Item TRINIUM_INGOT = ItemHelper.createGenericItem("trinium_ingot", JSGCreativeTabsHandler.jsgItemsCreativeTab);

    /**
     * Crafting items
     */
    public static final Item CRYSTAL_SEED = ItemHelper.createGenericItem("crystal_fragment", JSGCreativeTabsHandler.jsgItemsCreativeTab);
    public static final Item CRYSTAL_BLUE = ItemHelper.createGenericItem("crystal_blue", JSGCreativeTabsHandler.jsgItemsCreativeTab);
    public static final Item CRYSTAL_RED = ItemHelper.createGenericItem("crystal_red", JSGCreativeTabsHandler.jsgItemsCreativeTab);
    public static final Item CRYSTAL_ENDER = ItemHelper.createGenericItem("crystal_ender", JSGCreativeTabsHandler.jsgItemsCreativeTab);
    public static final Item CRYSTAL_YELLOW = ItemHelper.createGenericItem("crystal_yellow", JSGCreativeTabsHandler.jsgItemsCreativeTab);
    public static final Item CRYSTAL_WHITE = ItemHelper.createGenericItem("crystal_white", JSGCreativeTabsHandler.jsgItemsCreativeTab);


    public static final Item CIRCUIT_CONTROL_BASE = ItemHelper.createGenericItem("circuit_control_base", JSGCreativeTabsHandler.jsgItemsCreativeTab);
    public static final Item CIRCUIT_CONTROL_CRYSTAL = ItemHelper.createGenericItem("circuit_control_crystal", JSGCreativeTabsHandler.jsgItemsCreativeTab);
    public static final Item CIRCUIT_CONTROL_NAQUADAH = ItemHelper.createGenericItem("circuit_control_naquadah", JSGCreativeTabsHandler.jsgItemsCreativeTab);

    public static final Item STARGATE_RING_FRAGMENT = ItemHelper.createGenericItem("stargate_ring_fragment", JSGCreativeTabsHandler.jsgItemsCreativeTab);
    public static final Item UNIVERSE_RING_FRAGMENT = ItemHelper.createGenericItem("universe_ring_fragment", JSGCreativeTabsHandler.jsgItemsCreativeTab);
    public static final Item TR_RING_FRAGMENT = ItemHelper.createGenericItem("transportrings_ring_fragment", JSGCreativeTabsHandler.jsgItemsCreativeTab);
    public static final Item HOLDER_CRYSTAL = ItemHelper.createGenericItem("holder_crystal", JSGCreativeTabsHandler.jsgItemsCreativeTab);
    public static final Item HOLDER_CRYSTAL_PEGASUS = ItemHelper.createGenericItem("holder_crystal_pegasus", JSGCreativeTabsHandler.jsgItemsCreativeTab);

    public static final Item DHD_BRB = ItemHelper.createGenericItem("dhd_brb", JSGCreativeTabsHandler.jsgItemsCreativeTab);
    public static final Item DHD_BBB = ItemHelper.createGenericItem("dhd_bbb", JSGCreativeTabsHandler.jsgItemsCreativeTab);

    public static final NotebookItem NOTEBOOK_ITEM = new NotebookItem();
    public static final PageNotebookItem PAGE_NOTEBOOK_ITEM = new PageNotebookItem();
    public static final PageMysteriousItem PAGE_MYSTERIOUS_ITEM = new PageMysteriousItem();
    public static final UniverseDialerItem UNIVERSE_DIALER = new UniverseDialerItem();

    public static final GDOItem GDO = new GDOItem();

    public static final Item BEAMER_CRYSTAL_POWER = ItemHelper.createGenericItem("beamer_crystal_power", JSGCreativeTabsHandler.jsgItemsCreativeTab);
    public static final Item BEAMER_CRYSTAL_FLUID = ItemHelper.createGenericItem("beamer_crystal_fluid", JSGCreativeTabsHandler.jsgItemsCreativeTab);
    public static final Item BEAMER_CRYSTAL_ITEMS = ItemHelper.createGenericItem("beamer_crystal_items", JSGCreativeTabsHandler.jsgItemsCreativeTab);

    /**
     * TOOLS
     */
    public static final ZatItem ZAT = new ZatItem();
    public static final StaffItem STAFF = new StaffItem();

    public static final Item[] ITEMS = {
            CRYSTAL_CONTROL_MILKYWAY_DHD,
            CRYSTAL_CONTROL_PEGASUS_DHD,
            CRYSTAL_RAW_PEGASUS_DHD,

            IRIS_BLADE,
            QUAD_IRIS_BLADE,
            UPGRADE_IRIS,
            IRIS_BLADE_TRINIUM,
            QUAD_IRIS_BLADE_TRINIUM,
            UPGRADE_IRIS_TRINIUM,
            UPGRADE_SHIELD,
            SHIELD_EMITTER,

            CRYSTAL_GLYPH_DHD,
            CRYSTAL_GLYPH_STARGATE,
            CRYSTAL_GLYPH_MILKYWAY,
            CRYSTAL_GLYPH_PEGASUS,
            CRYSTAL_GLYPH_UNIVERSE,
            CRYSTAL_GLYPH_GOAULD,
            CRYSTAL_GLYPH_ORI,

            NAQUADAH_SHARD,
            NAQUADAH_ALLOY,
            NAQUADAH_ALLOY_RAW,

            TRINIUM_INGOT,
            TITANIUM_INGOT,

            CRYSTAL_SEED,
            CRYSTAL_BLUE,
            CRYSTAL_RED,
            CRYSTAL_ENDER,
            CRYSTAL_YELLOW,
            CRYSTAL_WHITE,
            CRYSTAL_BLUE_PEGASUS,

            BEAMER_CRYSTAL_POWER,
            BEAMER_CRYSTAL_FLUID,
            BEAMER_CRYSTAL_ITEMS,

            CIRCUIT_CONTROL_BASE,
            CIRCUIT_CONTROL_CRYSTAL,
            CIRCUIT_CONTROL_NAQUADAH,

            STARGATE_RING_FRAGMENT,
            UNIVERSE_RING_FRAGMENT,
            TR_RING_FRAGMENT,

            HOLDER_CRYSTAL,
            HOLDER_CRYSTAL_PEGASUS,

            DHD_BRB,
            DHD_BBB,
            NOTEBOOK_ITEM,
            PAGE_NOTEBOOK_ITEM,
            PAGE_MYSTERIOUS_ITEM,
            UNIVERSE_DIALER,

            GDO,

            ZAT,
            STAFF,
    };

    public static Item[] getItems() {
        return ITEMS;
    }

    @SubscribeEvent
    public static void onRegisterItems(Register<Item> event) {
        for (Item item : ITEMS) {
            if (item instanceof CustomModelItemInterface)
                ((CustomModelItemInterface) item).setTEISR();

            event.getRegistry().register(item);
        }
    }

    @SubscribeEvent
    public static void onModelRegistry(ModelRegistryEvent event) {
        for (Item item : ITEMS) {
            if (item instanceof CustomModelItemInterface)
                ((CustomModelItemInterface) item).setCustomModelLocation();
            else
                ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
        }
    }
}
