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

public class ContainerOxidationChamber extends Container {

	private TileMachineOxidationChamber oxidationChamber;
	private int lastCookTime;
	private int lastBurnTime;

	public ContainerOxidationChamber(InventoryPlayer invPlayer, TileMachineOxidationChamber te) {
		this.oxidationChamber = te;

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

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return oxidationChamber.isUseableByPlayer(player);
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
				if (RecipeHelper.isStackValidForSlot(Recipe.OXIDATION_CHAMBER, stack, 0)) {
					if (!mergeItemStack(stack, 36, 37, false)) {
						if (RecipeHelper.isStackValidForSlot(Recipe.OXIDATION_CHAMBER, stack, 1)) {
							if (!mergeItemStack(stack, 37, 38, false)) {
								if (RecipeHelper.isStackValidForSlot(Recipe.OXIDATION_CHAMBER, stack, 2)) {
									if (!mergeItemStack(stack, 38, 39, false)) { return null; }
								} else {
									return null;
								}
							}
						} else {
							if (RecipeHelper.isStackValidForSlot(Recipe.OXIDATION_CHAMBER, stack, 2)) {
								if (!mergeItemStack(stack, 38, 39, false)) { return null; }
							} else {
								return null;
							}
						}
					} else {
						if (RecipeHelper.isStackValidForSlot(Recipe.OXIDATION_CHAMBER, stack, 1)) {
							if (!mergeItemStack(stack, 37, 38, false)) {
								if (RecipeHelper.isStackValidForSlot(Recipe.OXIDATION_CHAMBER, stack, 2)) {
									if (!mergeItemStack(stack, 38, 39, false)) { return null; }
								}
							} else {
								if (RecipeHelper.isStackValidForSlot(Recipe.OXIDATION_CHAMBER, stack, 2)) {
									if (!mergeItemStack(stack, 38, 39, false)) { return null; }
								} else {
									return null;
								}
							}
						} else {
							if (RecipeHelper.isStackValidForSlot(Recipe.OXIDATION_CHAMBER, stack, 2)) {
								if (!mergeItemStack(stack, 38, 39, false)) { return null; }
							} else {
								return null;
							}
						}
					}
				} else {
					if (RecipeHelper.isStackValidForSlot(Recipe.OXIDATION_CHAMBER, stack, 1)) {
						if (!mergeItemStack(stack, 37, 38, false)) {
							if (RecipeHelper.isStackValidForSlot(Recipe.OXIDATION_CHAMBER, stack, 2)) {
								if (!mergeItemStack(stack, 38, 39, false)) { return null; }
							}
						}
					} else {
						if (RecipeHelper.isStackValidForSlot(Recipe.OXIDATION_CHAMBER, stack, 2)) {
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

	public TileMachineOxidationChamber getTileEntity() {
		return oxidationChamber;
	}

	public void addCraftingToCrafters(ICrafting par1ICrafting) {
		super.addCraftingToCrafters(par1ICrafting);
		par1ICrafting.sendProgressBarUpdate(this, 0, oxidationChamber.MAX_WORK_TICKS);
		par1ICrafting.sendProgressBarUpdate(this, 1, oxidationChamber.getWorkDone());
	}

	public void detectAndSendChanges() {
		super.detectAndSendChanges();

		for (int i = 0; i < this.crafters.size(); ++i) {
			ICrafting icrafting = (ICrafting) this.crafters.get(i);

			if (lastCookTime != oxidationChamber.MAX_WORK_TICKS) {
				icrafting.sendProgressBarUpdate(this, 0, oxidationChamber.MAX_WORK_TICKS);
			}

			if (lastBurnTime != oxidationChamber.getWorkDone()) {
				icrafting.sendProgressBarUpdate(this, 1, oxidationChamber.getWorkDone());
			}
		}

		lastCookTime = oxidationChamber.MAX_WORK_TICKS;
		lastBurnTime = oxidationChamber.getWorkDone();
	}

	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int par1, int par2) {
		if (par1 == 0) {
			oxidationChamber.MAX_WORK_TICKS = par2;
		}

		if (par1 == 1) {
			oxidationChamber.setWorkDone(par2);
		}
	}

}
