package erates.realmachines.tileentities;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import buildcraft.core.inventory.SimpleInventory;
import erates.realmachines.recipes.Recipe;
import erates.realmachines.recipes.RecipeHelper;

public class TileMachineOxidationChamber extends TileEntityMachine {

	public TileMachineOxidationChamber() {
		super("OxidationChamber", Recipe.OXIDATION_CHAMBER);
	}

	@Override
	public void updateEntity() {
		super.updateEntity();

		if (worldObj.isRemote) return;

		ItemStack recipeOutputStack = RecipeHelper.getRecipeOutput(Recipe.OXIDATION_CHAMBER, new ItemStack[] { getStackInSlot(0), getStackInSlot(1), getStackInSlot(2) });
		if (recipeOutputStack == null) {
			currentWorkTime = 0;
			return;
		}

		if (RecipeHelper.getValidRecipe(Recipe.OXIDATION_CHAMBER, new ItemStack[] { getStackInSlot(0), getStackInSlot(1), getStackInSlot(2) }) != null) {
			if (getStackInSlot(3) != null) {
				if (getStackInSlot(3).getMaxStackSize() < getStackInSlot(3).stackSize + recipeOutputStack.stackSize) return;
				if (!getStackInSlot(3).getUnlocalizedName().equals(recipeOutputStack.getUnlocalizedName())) return;
			}

			if (mjStored > POWER_USAGE) {
				mjStored -= POWER_USAGE;
			} else {
				return;
			}

			currentWorkTime++;
			if (currentWorkTime == MAX_WORK_TICKS) {

				Recipe recipe = RecipeHelper.getValidRecipe(Recipe.OXIDATION_CHAMBER, new ItemStack[] { getStackInSlot(0), getStackInSlot(1), getStackInSlot(2) });

				decrStackSize(0, recipe.getInputItemStack(0).stackSize);
				decrStackSize(1, recipe.getInputItemStack(1).stackSize);
				decrStackSize(2, recipe.getInputItemStack(2).stackSize);

				ItemStack outputStack = getStackInSlot(3);
				if (outputStack == null) {
					setInventorySlotContents(3, recipeOutputStack);
				} else {
					outputStack.stackSize += recipeOutputStack.stackSize;
					setInventorySlotContents(3, outputStack);
				}
				currentWorkTime = 0;
			}
		} else {
			currentWorkTime = 0;
		}
	}
}
