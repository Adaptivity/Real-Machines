package erates.realmachines.client.interfaces.slots;

import java.util.ArrayList;

import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import erates.realmachines.recipes.RecipeOxidationChamber;
import erates.realmachines.recipes.RecipeHelper;

public class SlotOxidationChamber extends Slot {

	private int id;

	public SlotOxidationChamber(IInventory inventory, int id, int x, int y) {
		super(inventory, id, x, y);
		this.id = id;
	}

	@Override
	public boolean isItemValid(ItemStack stack) {
		return RecipeHelper.isStackValidForOxidationChamber(stack, id);
	}

}
