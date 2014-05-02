package erates.realmachines.tileentities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import buildcraft.api.mj.MjBattery;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntityMachine extends TileEntity implements IInventory {

	// protected EnergyStorage storage = new EnergyStorage(32000);

	@MjBattery(maxCapacity = 1500)
	protected double mjStored = 0;

	protected static int POWER_USAGE = 25;

	protected int currentWorkTime;
	public static int MAX_WORK_TICKS = 40;

	protected boolean isWorking = false;

	public TileEntityMachine() {
		currentWorkTime = 0;
	}

	/*
	 * IEnergyHandler
	 */

	/*
	 * @Override public void readFromNBT(NBTTagCompound nbt) {
	 * super.readFromNBT(nbt); storage.readFromNBT(nbt); }
	 * 
	 * @Override public void writeToNBT(NBTTagCompound nbt) {
	 * super.writeToNBT(nbt); storage.writeToNBT(nbt); }
	 * 
	 * 
	 * @Override public int receiveEnergy(ForgeDirection from, int maxReceive,
	 * boolean simulate) { return storage.receiveEnergy(maxReceive, simulate); }
	 * 
	 * @Override public int extractEnergy(ForgeDirection from, int maxExtract,
	 * boolean simulate) { return storage.extractEnergy(maxExtract, simulate); }
	 * 
	 * @Override public boolean canInterface(ForgeDirection from) { return true;
	 * }
	 * 
	 * @Override public int getEnergyStored(ForgeDirection from) { return
	 * storage.getEnergyStored(); }
	 * 
	 * @Override public int getMaxEnergyStored(ForgeDirection from) { return
	 * storage.getMaxEnergyStored(); }
	 */

	public int getWorkDone() {
		return currentWorkTime;
	}

	public void setWorkDone(int workDone) {
		this.currentWorkTime = workDone;
	}

	public boolean isWorking() {
		return isWorking;
	}

	@SideOnly(Side.CLIENT)
	public int getCookProgressScaled(int scale) {
		return currentWorkTime * scale / 200;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return player.getDistanceSq(xCoord + 0.5D, yCoord + 0.5D, zCoord + 0.5D) <= 64;
	}

	@Override
	public int getSizeInventory() {
		return 0;
	}

	@Override
	public ItemStack getStackInSlot(int var1) {
		return null;
	}

	@Override
	public ItemStack decrStackSize(int var1, int var2) {
		return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int var1) {
		return null;
	}

	@Override
	public void setInventorySlotContents(int var1, ItemStack var2) {
	}

	@Override
	public String getInventoryName() {
		return null;
	}

	@Override
	public boolean hasCustomInventoryName() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 0;
	}

	@Override
	public void openInventory() {
	}

	@Override
	public void closeInventory() {
	}

	@Override
	public boolean isItemValidForSlot(int var1, ItemStack var2) {
		return false;
	}
}
