package erates.realmachines.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import buildcraft.core.IItemPipe;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erates.realmachines.RealMachines;
import erates.realmachines.lib.Reference;
import erates.realmachines.tileentities.TileMachineSandMixer;

public class BlockMachineSandMixer extends BlockContainer {

	private IIcon topTexture;
	private IIcon sideTexture;

	public BlockMachineSandMixer() {
		super(Material.iron);
		setBlockName("rmSandMixer");
		setCreativeTab(RealMachines.tabRealMachines);
		setHardness(3.0F);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {

		if (player.isSneaking()) return false;

		if (player.getCurrentEquippedItem() != null) {
			if (player.getCurrentEquippedItem().getItem() instanceof IItemPipe) return false;
		}

		if (!world.isRemote) FMLNetworkHandler.openGui(player, RealMachines.instance, Reference.TE_NUMBER_SAND_MIXER, world, x, y, z);

		return true;
	}

	@Override
	public IIcon getIcon(int i, int j) {
		if (i == 1 || i == 0) {
			return topTexture;
		} else {
			return sideTexture;
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister registry) {
		topTexture = registry.registerIcon("erates_realmachines:sandMixerTop");
		sideTexture = registry.registerIcon("erates_realmachines:sandMixerSide");
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileMachineSandMixer();
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
		TileEntity te = world.getTileEntity(x, y, z);
		if (te != null && te instanceof IInventory) {
			IInventory inventory = (IInventory) te;

			for (int i = 0; i < inventory.getSizeInventory(); i++) {
				ItemStack stack = inventory.getStackInSlotOnClosing(i);

				if (stack != null) {
					float spawnX = x + world.rand.nextFloat();
					float spawnY = y + world.rand.nextFloat();
					float spawnZ = z + world.rand.nextFloat();

					EntityItem droppedItem = new EntityItem(world, spawnX, spawnY, spawnZ, stack);

					float mult = 0.05f;

					droppedItem.motionX = (-0.5f + world.rand.nextFloat()) * mult;
					droppedItem.motionY = (4f + world.rand.nextFloat()) * mult;
					droppedItem.motionZ = (-0.5f + world.rand.nextFloat()) * mult;

					world.spawnEntityInWorld(droppedItem);
				}
			}
		}

		super.breakBlock(world, x, x, z, block, meta);
	}
}
