package erates.realmachines.recipes;

import java.util.ArrayList;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import erates.realmachines.blocks.ModBlocks;
import erates.realmachines.items.ModItems;

public class RecipeHelper {

	private static ArrayList<RecipeOxidationChamber> oxidationChamberRecipeList = new ArrayList<RecipeOxidationChamber>();
	private static ArrayList<RecipeSandMixer> sandMixerRecipeList = new ArrayList<RecipeSandMixer>();

	public static void registerRecipes() {
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.machineOxidationChamber), new Object[] { "IDI", "IBI", "III", 'I', Items.iron_ingot, 'D', ModItems.itemChromiumDust, 'B', Items.bucket });
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.machineSandMixer), new Object[] { "ISI", "ISI", "III", 'I', Items.iron_ingot, 'S', Items.stick });

		registerOxidationChamberRecipe(new RecipeOxidationChamber(new ItemStack(ModItems.itemChromiumDust), new ItemStack(Items.iron_ingot), new ItemStack(ModItems.itemChromiumDust), new ItemStack(
				ModItems.itemChromiteDust)));
		registerSandMixerRecipe(new RecipeSandMixer(new ItemStack(Blocks.sand, 8), new ItemStack(Items.clay_ball, 1), FluidRegistry.WATER, 1000, new ItemStack(ModItems.itemGreenSand)));
	}

	public static void registerOxidationChamberRecipe(RecipeOxidationChamber recipe) {
		oxidationChamberRecipeList.add(recipe);
	}

	public static void registerSandMixerRecipe(RecipeSandMixer recipe) {
		sandMixerRecipeList.add(recipe);
	}

	public static ArrayList<RecipeOxidationChamber> getOxidationChamberRecipeList() {
		return oxidationChamberRecipeList;
	}

	public static ArrayList<RecipeSandMixer> getSandMixerRecipeList() {
		return sandMixerRecipeList;
	}

	public static boolean isStackValidForOxidationChamber(ItemStack stack, int slotId) {
		switch (slotId) {
			case 0:
				for (RecipeOxidationChamber recipe : oxidationChamberRecipeList) {
					if (stack.getUnlocalizedName().equals(recipe.getInput1().getUnlocalizedName())) return true;
				}
				break;
			case 1:
				for (RecipeOxidationChamber recipe : oxidationChamberRecipeList) {
					if (stack.getUnlocalizedName().equals(recipe.getInput2().getUnlocalizedName())) return true;
				}
				break;
			case 2:
				for (RecipeOxidationChamber recipe : oxidationChamberRecipeList) {
					if (stack.getUnlocalizedName().equals(recipe.getInput3().getUnlocalizedName())) return true;
				}
				break;
		}

		return false;
	}
}
