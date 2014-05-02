package erates.realmachines.inventory;

import erates.realmachines.recipes.RecipeHelper;
import erates.realmachines.tileentities.TileEntityMachine;
import erates.realmachines.tileentities.TileMachineOxidationChamber;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerMachine extends Container {

	private int recipeType;
	private TileEntityMachine teMachine;

	public ContainerMachine(int recipeType, TileEntityMachine teMachine) {
		this.recipeType = recipeType;
		this.teMachine = teMachine;
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return teMachine.isUseableByPlayer(player);
	}

	public TileEntityMachine getTileEntity() {
		return teMachine;
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slotId) {
		Slot slot = getSlot(slotId);

		if (slot != null && slot.getHasStack()) {
			ItemStack stack = slot.getStack();
			ItemStack result = stack.copy();

			if (slotId >= 36) {
				if (!mergeItemStack(stack, 0, 36, false)) { return null; }
			} else {
				int inventorySize = teMachine.getSizeInventory();
				boolean[] isOk = new boolean[inventorySize];
				for (int i = 0; i < inventorySize; i++) {
					if (RecipeHelper.isStackValidForSlot(recipeType, stack, i)) {
						if (mergeItemStack(stack, 36 + i, 37 + i, false)) {
							isOk[i] = true;
						}
					}
				}

				boolean isOkInList = false;
				for (boolean ok : isOk) {
					if (ok) isOkInList = true;
				}

				if (!isOkInList) return null;
			}

			if (stack.stackSize == 0) {
				slot.putStack(null);
			} else {
				slot.onSlotChanged();
			}

			slot.onPickupFromSlot(player, stack);

			return result;
		}

		return null;
	}
	
	
}
