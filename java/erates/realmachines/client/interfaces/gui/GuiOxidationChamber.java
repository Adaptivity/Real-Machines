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

		int size;

		if (oxidationChamber.isWorking()) {
			System.out.println("isWorking");
			size = oxidationChamber.getCookProgressScaled(14);
			System.out.println("size: " + size);
			drawTexturedModalRect(guiLeft + 60, guiTop + 30, xSize, 0, size, 8);
		}
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int x, int y) {
		fontRendererObj.drawString("Oxidation Chamber", 8, 6, 0x404040);
	}

	@Override
	public void initGui() {
		super.initGui();
	}
	
	
}
