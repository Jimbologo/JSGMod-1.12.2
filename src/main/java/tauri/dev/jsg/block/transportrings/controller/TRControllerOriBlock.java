package tauri.dev.jsg.block.transportrings.controller;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tauri.dev.jsg.raycaster.ringscontroller.RaycasterRingsOriController;
import tauri.dev.jsg.renderer.transportrings.controller.TRControllerOriRenderer;
import tauri.dev.jsg.tileentity.transportrings.controller.TRControllerAbstractTile;
import tauri.dev.jsg.tileentity.transportrings.controller.TRControllerOriTile;
import tauri.dev.jsg.util.main.JSGProps;

import javax.annotation.ParametersAreNonnullByDefault;

public class TRControllerOriBlock extends TRControllerAbstractBlock {

    private static final String BLOCK_NAME = "transportrings_controller_ori_block";

    public TRControllerOriBlock() {
        super(BLOCK_NAME);
    }

    @Override
    public TRControllerAbstractTile createTileEntity(World world, IBlockState state) {
        return new TRControllerOriTile();
    }

    @Override
    @ParametersAreNonnullByDefault
    public boolean canPlaceBlockOnSide(World worldIn, BlockPos pos, EnumFacing side) {
        return canPlaceBlockAt(worldIn, pos);
    }


    @Override
    @ParametersAreNonnullByDefault
    public void neighborChanged(IBlockState state, World world, BlockPos pos, Block block, BlockPos fromPos) {}

    @Override
    public void onRayCasterActivated(World world, BlockPos pos, EntityPlayer player) {
        RaycasterRingsOriController.INSTANCE.onActivated(world, pos, player);
    }

    @Override
    public Class<? extends TileEntity> getTileEntityClass() {
        return TRControllerOriTile.class;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public TileEntitySpecialRenderer<? extends TileEntity> getTESR() {
        return new TRControllerOriRenderer();
    }
}
