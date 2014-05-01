package erates.realmachines.tileentities;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.tileentity.TileEntity;
import buildcraft.api.mj.MjBattery;

public class TileEntityMachine extends TileEntity {

	// protected EnergyStorage storage = new EnergyStorage(32000);

	@MjBattery(maxReceivedPerCycle = 25, maxCapacity = 1500, minimumConsumption = 10)
	protected double mjStored = 0;

	protected static int POWER_USAGE = 25;

	protected int workDone;
	public static int MAX_WORK_TICKS = 40;

	protected boolean isWorking = false;

	public TileEntityMachine() {
		workDone = 0;
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
		return workDone;
	}
	
	public void setWorkDone(int workDone){
		this.workDone = workDone;
	}

	public boolean isWorking() {
		return isWorking;
	}

	@SideOnly(Side.CLIENT)
	public int getCookProgressScaled(int scale) {
		return workDone * scale / 200;
	}
}
