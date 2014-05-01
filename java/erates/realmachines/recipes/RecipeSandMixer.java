package erates.realmachines.recipes;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class RecipeSandMixer extends Recipe {

	public RecipeSandMixer(ItemStack input1, ItemStack input2, FluidStack inputFluid, ItemStack output) {
		super(Recipe.SAND_MIXER, new ItemStack[] { input1, input2 }, new FluidStack[] { inputFluid }, output);
	}
}
