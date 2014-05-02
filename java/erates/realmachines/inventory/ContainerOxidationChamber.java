package erates.realmachines.inventory;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import buildcraft.core.gui.slots.SlotValidated;
import erates.realmachines.recipes.Recipe;
import erates.realmachines.recipes.RecipeHelper;
import erates.realmachines.tileentities.TileMachineOxidationChamber;

public class ContainerOxidationChamber extends ContainerMachine {

	private TileMachineOxidationChamber oxidationChamber;

	public ContainerOxidationChamber(InventoryPlayer invPlayer, TileMachineOxidationChamber oxidationChamber) {
		super(Recipe.OXIDATION_CHAMBER, oxidationChamber);
		this.oxidationChamber = oxidationChamber;

		// Player hotbar
		for (int x = 0; x < 9; x++) {
			addSlotToContainer(new SlotValidated(invPlayer, x, 8 + 18 * x, 161));
		}

		// Player inventory
		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 9; x++) {
				addSlotToContainer(new SlotValidated(invPlayer, x + y * 9 + 9, 8 + 18 * x, 103 + y * 18));
			}
		}

		// Inputslot 1
		addSlotToContainer(new SlotValidated(oxidationChamber, 0, 40, 24));

		// Inputslot 2
		addSlotToContainer(new SlotValidated(oxidationChamber, 1, 80, 24));

		// Inputslot 3
		addSlotToContainer(new SlotValidated(oxidationChamber, 2, 120, 24));

		// Outputslot
		addSlotToContainer(new SlotValidated(oxidationChamber, 3, 80, 64));
	}
}
