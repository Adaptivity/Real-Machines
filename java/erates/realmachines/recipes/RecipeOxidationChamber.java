package erates.realmachines.recipes;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;

public class RecipeOxidationChamber {
	private ItemStack inputSlot1;
	private ItemStack inputSlot2;
	private ItemStack inputSlot3;
	private ItemStack outputSlot;

	public RecipeOxidationChamber(ItemStack input1, ItemStack input2, ItemStack input3, ItemStack output) {
		this.inputSlot1 = input1;
		this.inputSlot2 = input2;
		this.inputSlot3 = input3;
		this.outputSlot = output;
	}

	public ItemStack getInput1() {
		return inputSlot1;
	}

	public ItemStack getInput2() {
		return inputSlot2;
	}

	public ItemStack getInput3() {
		return inputSlot3;
	}

	public ItemStack getOutput() {
		return outputSlot;
	}
}
