package erates.realmachines.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.registry.GameRegistry;
import erates.realmachines.lib.Reference;
import erates.realmachines.tileentities.TileMachineOxidationChamber;
import erates.realmachines.tileentities.TileMachineSandMixer;

public class ModBlocks {

	public static Block machineOxidationChamber;
	public static Block machineSandMixer;
	public static Block machineMolder;
	public static Block oreChromium;

	public static void registerBlocks() {
		machineSandMixer = new BlockMachineSandMixer();
		machineOxidationChamber = new BlockMachineOxidationChamber();
		oreChromium = new BlockChromiumOre();

		register(oreChromium);
		register(machineSandMixer);
		register(machineOxidationChamber);

		GameRegistry.registerTileEntity(TileMachineSandMixer.class, Reference.MACHINE_SANDMIXER_TE_KEY);
		GameRegistry.registerTileEntity(TileMachineOxidationChamber.class, Reference.MACHINE_OXIDATION_CHAMBER_TE_KEY);

		OreDictionary.registerOre("oreChromium", oreChromium);
	}

	private static void register(Block block) {
		GameRegistry.registerBlock(block, "erates_realmachines_" + block.getUnlocalizedName().substring(5));
	}
}
