package erates.realmachines.client.interfaces.slots;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class SlotSandMixer extends Slot {

	private int	id;

	public SlotSandMixer(IInventory inventory, int id, int x, int y) {
		super(inventory, id, x, y);
		this.id = id;
	}

	@Override
	public boolean isItemValid(ItemStack stack) {
		String validItemName = null;
		switch (id) {
			case 0:
				return stack.getUnlocalizedName().equals("tile.sand.default") || stack.getUnlocalizedName().equals("tile.sand");
			case 1:
				return stack.getUnlocalizedName().equals(Items.clay_ball.getUnlocalizedName());
			case 2:
				return stack.getUnlocalizedName().equals(Items.water_bucket.getUnlocalizedName());
		}
		return false;
	}

}
