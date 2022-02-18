package mrjake.aunis.crafting.thermalreplace.crystals;

import mrjake.aunis.crafting.thermalreplace.ThermalAbstractRecipe;
import mrjake.aunis.item.AunisItems;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;

import java.util.HashMap;

public class CrystalWhiteRecipe extends ThermalAbstractRecipe {

    // CONFIGURATION
    public static final int MINIMAL_SLOTS = 9;
    public static final int MAXIMAL_SLOTS = 9;
    public static final String NAME = "thermal_crystal_white";
    public static final ItemStack OUTPUT_ITEM = new ItemStack(AunisItems.CRYSTAL_WHITE);

    // items / patterns
    public HashMap<ItemStack, int[]> PATTERN_LIST = new HashMap<ItemStack, int[]>() {
        {
            put(new ItemStack(Items.DYE, 1, EnumDyeColor.WHITE.getDyeDamage()), new int[]{
                    0, 1, 0,
                    1, 0, 1,
                    0, 1, 0
            });
            put(new ItemStack(AunisItems.CRYSTAL_SEED), new int[]{
                    0, 0, 0,
                    0, 1, 0,
                    0, 0, 0
            });
        }
    };

    // REGISTER / CONSTRUCTOR
    public CrystalWhiteRecipe() {
        super(NAME, MINIMAL_SLOTS, MAXIMAL_SLOTS, OUTPUT_ITEM);
        setPatterns(PATTERN_LIST);
    }
}
