package erates.realmachines.inventory;

import erates.realmachines.recipes.Recipe;
import erates.realmachines.recipes.RecipeHelper;
import erates.realmachines.tileentities.TileEntityMachine;
import erates.realmachines.tileentities.TileMachineOxidationChamber;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class MachineContainer extends Container {

	protected InventoryPlayer invPlayer;
	protected TileEntityMachine te;
	private int containerType;

	public MachineContainer(InventoryPlayer invPlayer, TileEntityMachine te, int containerType) {
		this.invPlayer = invPlayer;
		this.te = te;
		this.containerType = containerType;
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return te.isUseableByPlayer(player);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int i) {
		Slot slot = getSlot(i);

		if (slot != null && slot.getHasStack()) {
			ItemStack stack = slot.getStack();
			ItemStack result = stack.copy();

			if (i >= 36) {
				if (!mergeItemStack(stack, 0, 36, false)) { return null; }
			} else {
				if (RecipeHelper.isStackValidForSlot(containerType, stack, 0)) {
					if (!mergeItemStack(stack, 36, 37, false)) {
						if (RecipeHelper.isStackValidForSlot(containerType, stack, 1)) {
							if (!mergeItemStack(stack, 37, 38, false)) {
								if (RecipeHelper.isStackValidForSlot(containerType, stack, 2)) {
									if (!mergeItemStack(stack, 38, 39, false)) { return null; }
								} else {
									return null;
								}
							}
						} else {
							if (RecipeHelper.isStackValidForSlot(containerType, stack, 2)) {
								if (!mergeItemStack(stack, 38, 39, false)) { return null; }
							} else {
								return null;
							}
						}
					} else {
						if (RecipeHelper.isStackValidForSlot(containerType, stack, 1)) {
							if (!mergeItemStack(stack, 37, 38, false)) {
								if (RecipeHelper.isStackValidForSlot(containerType, stack, 2)) {
									if (!mergeItemStack(stack, 38, 39, false)) { return null; }
								}
							} else {
								if (RecipeHelper.isStackValidForSlot(containerType, stack, 2)) {
									if (!mergeItemStack(stack, 38, 39, false)) { return null; }
								} else {
									return null;
								}
							}
						} else {
							if (RecipeHelper.isStackValidForSlot(containerType, stack, 2)) {
								if (!mergeItemStack(stack, 38, 39, false)) { return null; }
							} else {
								return null;
							}
						}
					}
				} else {
					if (RecipeHelper.isStackValidForSlot(containerType, stack, 1)) {
						if (!mergeItemStack(stack, 37, 38, false)) {
							if (RecipeHelper.isStackValidForSlot(containerType, stack, 2)) {
								if (!mergeItemStack(stack, 38, 39, false)) { return null; }
							}
						}
					} else {
						if (RecipeHelper.isStackValidForSlot(containerType, stack, 2)) {
							if (!mergeItemStack(stack, 38, 39, false)) { return null; }
						} else {
							return null;
						}
					}
				}

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

	public TileEntityMachine getTileEntity() {
		return te;
	}
}
