package erates.realmachines.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erates.realmachines.inventory.slots.SlotOxidationChamber;
import erates.realmachines.recipes.Recipe;
import erates.realmachines.recipes.RecipeHelper;
import erates.realmachines.tileentities.TileMachineOxidationChamber;

public class ContainerOxidationChamber extends MachineContainer {

	private TileMachineOxidationChamber oxidationChamber;

	public ContainerOxidationChamber(InventoryPlayer invPlayer, TileMachineOxidationChamber te) {
		super(invPlayer, te, Recipe.OXIDATION_CHAMBER);

		// Player hotbar
		for (int x = 0; x < 9; x++) {
			addSlotToContainer(new Slot(invPlayer, x, 8 + 18 * x, 161));
		}

		// Player inventory
		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 9; x++) {
				addSlotToContainer(new Slot(invPlayer, x + y * 9 + 9, 8 + 18 * x, 103 + y * 18));
			}
		}

		// Inputslot 1
		addSlotToContainer(new SlotOxidationChamber(oxidationChamber, 0, 40, 24));

		// Inputslot 2
		addSlotToContainer(new SlotOxidationChamber(oxidationChamber, 1, 80, 24));

		// Inputslot 3
		addSlotToContainer(new SlotOxidationChamber(oxidationChamber, 2, 120, 24));

		// Outputslot
		addSlotToContainer(new SlotOxidationChamber(oxidationChamber, 3, 80, 64));
	}
}
