package erates.realmachines.recipes;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;

public class RecipeSandMixer {
	
	private ItemStack inputSlot1;
	private ItemStack inputSlot2;
	private Fluid fluid;
	private int amount;
	private ItemStack outputSlot;
	
	public RecipeSandMixer(ItemStack input1, ItemStack input2, Fluid fluid, int amountInMilliBuckets, ItemStack output){
		this.inputSlot1 = input1;
		this.inputSlot2 = input2;
		this.fluid = fluid;
		this.amount = amountInMilliBuckets;
		this.outputSlot = output;
	}
	
	public ItemStack getInput1(){
		return inputSlot1;
	}
	
	public ItemStack getInput2(){
		return inputSlot2;
	}
	
	public Fluid getFluid(){
		return fluid;
	}
	
	public int getFluidAmount(){
		return amount;
	}
	
	public ItemStack getOutput(){
		return outputSlot;
	}
	
}
