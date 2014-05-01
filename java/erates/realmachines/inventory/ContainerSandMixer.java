package erates.realmachines.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import erates.realmachines.inventory.slots.SlotSandMixer;
import erates.realmachines.tileentities.TileMachineSandMixer;

public class ContainerSandMixer extends Container {

	private TileMachineSandMixer sandMixer;

	public ContainerSandMixer(InventoryPlayer invPlayer, TileMachineSandMixer sandMixer) {
		this.sandMixer = sandMixer;

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

		// Input slots
		for (int x = 0; x < 3; x++) {
			addSlotToContainer(new SlotSandMixer(sandMixer, x, 62 + 18 * x, 20));
		}

		// Output slot
		addSlotToContainer(new SlotSandMixer(sandMixer, 3, 80, 72));
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return sandMixer.isUseableByPlayer(player);
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
				if (stack.getUnlocalizedName().equals("tile.sand.default")) {
					if (!mergeItemStack(stack, 36, 37, false)) { return null; }
				} else if (stack.getUnlocalizedName().equals("tile.sand")) {
					if (!mergeItemStack(stack, 36, 37, false)) { return null; }
				} else if (stack.getUnlocalizedName().equals(Items.clay_ball.getUnlocalizedName())) {
					if (!mergeItemStack(stack, 37, 38, false)) { return null; }
				} else if (stack.getUnlocalizedName().equals(Items.water_bucket.getUnlocalizedName())) {
					if (!mergeItemStack(stack, 38, 39, false)) { return null; }
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

	public TileMachineSandMixer getTileEntity() {
		return sandMixer;
	}

}
