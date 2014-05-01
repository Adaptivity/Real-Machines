package erates.realmachines.client.interfaces;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import erates.realmachines.RealMachines;
import erates.realmachines.client.interfaces.gui.GuiOxidationChamber;
import erates.realmachines.client.interfaces.gui.GuiSandMixer;
import erates.realmachines.inventory.ContainerOxidationChamber;
import erates.realmachines.inventory.ContainerSandMixer;
import erates.realmachines.lib.Reference;
import erates.realmachines.tileentities.TileMachineOxidationChamber;
import erates.realmachines.tileentities.TileMachineSandMixer;

public class GuiHandler implements IGuiHandler {

	public GuiHandler() {
		NetworkRegistry.INSTANCE.registerGuiHandler(RealMachines.instance, this);
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity te;
		switch (ID) {
			case Reference.TE_NUMBER_SAND_MIXER:
				te = world.getTileEntity(x, y, z);
				if (te != null && te instanceof TileMachineSandMixer) { return new ContainerSandMixer(player.inventory, (TileMachineSandMixer) te); }
				break;
			case Reference.TE_NUMBER_OXIDATION_CHAMBER:
				te = world.getTileEntity(x, y, z);
				if (te != null && te instanceof TileMachineOxidationChamber) { return new ContainerOxidationChamber(player.inventory, (TileMachineOxidationChamber) te); }
				break;
		}

		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity te;
		switch (ID) {
			case Reference.TE_NUMBER_SAND_MIXER:
				te = world.getTileEntity(x, y, z);
				if (te != null && te instanceof TileMachineSandMixer) { return new GuiSandMixer(player.inventory, (TileMachineSandMixer) te); }
				break;
			case Reference.TE_NUMBER_OXIDATION_CHAMBER:
				te = world.getTileEntity(x, y, z);
				if (te != null && te instanceof TileMachineOxidationChamber) { return new GuiOxidationChamber(player.inventory, (TileMachineOxidationChamber) te); }
				break;
		}

		return null;
	}

}
