package erates.realmachines.tileentities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.ForgeDirection;
import cofh.api.energy.IEnergyHandler;
import cofh.api.energy.IEnergyStorage;
import erates.realmachines.recipes.Recipe;

public class TileMachineSandMixer extends TileEntityMachine {

	public TileMachineSandMixer() {
		super("SandMixer", Recipe.SAND_MIXER);
	}
}
