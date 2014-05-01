package erates.realmachines.recipes;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;

public class RecipeOxidationChamber extends Recipe {

	public RecipeOxidationChamber(ItemStack input1, ItemStack input2, ItemStack input3, ItemStack output) {
		super(Recipe.OXIDATION_CHAMBER, new ItemStack[] { input1, input2, input3 }, output);
	}
}
