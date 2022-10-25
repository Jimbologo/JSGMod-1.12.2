package tauri.dev.jsg.worldgen.structures;

import net.minecraft.block.state.IBlockState;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;
import net.minecraft.world.storage.loot.LootTableList;
import tauri.dev.jsg.JSG;
import tauri.dev.jsg.config.JSGConfig;
import tauri.dev.jsg.config.stargate.StargateSizeEnum;

import javax.annotation.Nonnull;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

import static tauri.dev.jsg.worldgen.StargateGenerator.generateStargate;


public class JSGStructure extends WorldGenerator implements IStructure {

    public String structureName;
    public int yNegativeOffset;
    boolean isStargateStructure;
    boolean isPegasusGate;
    int dimensionToSpawn;

    public int structureSizeX;
    public int structureSizeZ;

    public JSGStructure(String structureName, int yNegativeOffset, boolean isStargateStructure, boolean isPegasusGate, int structureSizeX, int structureSizeZ, int dimensionToSpawn) {
        this.structureName = structureName;
        this.yNegativeOffset = yNegativeOffset;
        this.isStargateStructure = isStargateStructure;
        this.isPegasusGate = isPegasusGate;
        this.structureSizeX = structureSizeX;
        this.structureSizeZ = structureSizeZ;
        this.dimensionToSpawn = dimensionToSpawn;
    }

    @Override
    public boolean generate(@Nonnull World worldIn, @Nonnull Random rand, @Nonnull BlockPos position) {
        generateStructure(worldIn, position, rand);
        return true;
    }

    public void generateStructure(World world, BlockPos pos, Random random) {
        pos = pos.down(yNegativeOffset);
        WorldServer worldToSpawn = Objects.requireNonNull(world.getMinecraftServer()).getWorld(dimensionToSpawn);
        // generate stargate
        if (isStargateStructure) {
            if (JSGConfig.stargateGeneratorConfig.stargateRandomGeneratorEnabled) {
                String size = tauri.dev.jsg.config.JSGConfig.stargateSize == StargateSizeEnum.LARGE ? "_large" : "_small";
                generateStargate(world, pos, "sg_" + structureName + size, isPegasusGate, dimensionToSpawn);
            }
        } else if (tauri.dev.jsg.config.JSGConfig.stargateGeneratorConfig.structuresRandomGeneratorEnabled) {

            // else generate normal structure
            MinecraftServer mcServer = world.getMinecraftServer();
            TemplateManager manager = worldToSpawn.getStructureTemplateManager();
            ResourceLocation resourceLocation = new ResourceLocation(JSG.MOD_ID, structureName);
            Template template = manager.getTemplate(mcServer, resourceLocation);

            IBlockState state = world.getBlockState(pos);
            world.notifyBlockUpdate(pos, state, state, 3);
            template.addBlocksToWorldChunk(world, pos, defaultSettings);

            Map<BlockPos, String> datablocks = template.getDataBlocks(pos, defaultSettings);
            BlockPos lootPos;

            for (BlockPos dataPos : datablocks.keySet()) {
                String name = datablocks.get(dataPos);

                switch (name) {
                    case "structure":
                        // remove structure block
                        world.setBlockToAir(dataPos);
                        world.setBlockToAir(dataPos.down()); // save block
                        break;
                    case "loot_ov":
                    case "loot_nether":
                    case "loot_end":
                        lootPos = dataPos.down();
                        TileEntity entity = world.getTileEntity(lootPos);
                        if (entity instanceof TileEntityChest) {
                            TileEntityChest chest = (TileEntityChest) entity;
                            chest.setLootTable(LootTableList.CHESTS_NETHER_BRIDGE, random.nextLong());
                            chest.fillWithLoot(null);
                        }
                        world.setBlockToAir(dataPos);
                        break;
                    default:
                        break;
                }
            }
        }
    }
}
