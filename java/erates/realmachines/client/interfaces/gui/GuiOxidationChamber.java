package erates.realmachines.client.interfaces.gui;

import org.lwjgl.opengl.GL11;

import erates.realmachines.inventory.ContainerOxidationChamber;
import erates.realmachines.tileentities.TileEntityMachine;
import erates.realmachines.tileentities.TileMachineOxidationChamber;
import erates.realmachines.tileentities.TileMachineSandMixer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GuiOxidationChamber extends GuiContainer {

	private static final ResourceLocation textureBackground = new ResourceLocation("erates_realmachines", "textures/gui/container/oxidationChamber.png");
	private TileMachineOxidationChamber oxidationChamber;

	public GuiOxidationChamber(InventoryPlayer invPlayer, TileMachineOxidationChamber oxidationChamber) {
		super(new ContainerOxidationChamber(invPlayer, oxidationChamber));

		this.oxidationChamber = oxidationChamber;

		xSize = 176;
		ySize = 185;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
		GL11.glColor4f(1, 1, 1, 1);

		Minecraft.getMinecraft().renderEngine.bindTexture(textureBackground);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

		int i1;

		i1 = oxidationChamber.getCookProgressScaled(15);
		drawTexturedModalRect(guiLeft + 61, guiTop + 28, xSize, 0, i1, 8);
		drawTexturedModalRect(guiLeft + 115 - i1, guiTop + 28, xSize + 14 - i1, 8, i1, 8);
		drawTexturedModalRect(guiLeft + 84, guiTop + 45, xSize, 16, 8, i1);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int x, int y) {
		fontRendererObj.drawString("Oxidation Chamber", 8, 6, 0x404040);
	}

}
