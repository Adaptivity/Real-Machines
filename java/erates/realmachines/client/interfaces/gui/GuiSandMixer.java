package erates.realmachines.client.interfaces.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erates.realmachines.client.interfaces.containers.ContainerSandMixer;
import erates.realmachines.tileentities.TileMachineSandMixer;

@SideOnly(Side.CLIENT)
public class GuiSandMixer extends GuiContainer {

	private static final ResourceLocation	textureBackground	= new ResourceLocation("erates_realmachines", "textures/gui/container/sandMixer.png");
	private TileMachineSandMixer sandMixer;
	
	public GuiSandMixer(InventoryPlayer invPlayer, TileMachineSandMixer sandMixer) {
		super(new ContainerSandMixer(invPlayer, sandMixer));
		this.sandMixer = sandMixer;
		
		xSize = 176;
		ySize = 185;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int x, int y) {
		GL11.glColor4f(1, 1, 1, 1);

		Minecraft.getMinecraft().renderEngine.bindTexture(textureBackground);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		
		/*float filled = sandMixer.getEnergyStored();
		int barHeight = (int)(filled * 27);
		if (barHeight > 0){
			int srcX = xSize;
			int srcY = 27 - barHeight;
			
			drawTexturedModalRect(guiLeft, guiTop + 40 + 27 - barHeight, srcX, srcY, 7, barHeight);
		}*/
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int x, int y) {
		fontRendererObj.drawString("Sand Mixer", 8, 6, 0x404040);

	}

	@Override
	public void initGui() {
		super.initGui();

	}
}
