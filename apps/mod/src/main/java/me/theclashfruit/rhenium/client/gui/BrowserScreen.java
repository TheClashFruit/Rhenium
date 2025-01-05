package me.theclashfruit.rhenium.client.gui;

import com.cinemamod.mcef.MCEF;
import com.cinemamod.mcef.MCEFBrowser;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.render.*;
import net.minecraft.text.Text;

public class BrowserScreen extends Screen {
    private MCEFBrowser browser;

    private final MinecraftClient minecraft = MinecraftClient.getInstance();

    private final String url;
    private final boolean transparent;

    public BrowserScreen(Text title, String url, boolean transparent) {
        super(title);

        this.url = url;
        this.transparent = transparent;
    }

    @Override
    protected void init() {
        super.init();

        if (browser == null) {
            browser = MCEF.createBrowser(this.url, this.transparent);

            resizeBrowser();
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);

        RenderSystem.disableDepthTest();
        RenderSystem.setShader(GameRenderer::getPositionTexColorProgram);
        RenderSystem.setShaderTexture(0, browser.getRenderer().getTextureID());

        if (this.transparent) RenderSystem.enableBlend();

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.begin(
                VertexFormat.DrawMode.QUADS,
                VertexFormats.POSITION_TEXTURE_COLOR
        );

        buffer
                .vertex(0, height, 0)
                .texture(0.0f, 1.0f)
                .color(255, 255, 255, 255);
        buffer
                .vertex(width, height, 0)
                .texture(1.0f, 1.0f)
                .color(255, 255, 255, 255);
        buffer
                .vertex(width, 0, 0)
                .texture(1.0f, 0.0f)
                .color(255, 255, 255, 255);
        buffer
                .vertex(0, 0, 0)
                .texture(0.0f, 0.0f)
                .color(255, 255, 255, 255);

        BufferRenderer.drawWithGlobalProgram(buffer.end());

        RenderSystem.setShaderTexture(0, 0);
        RenderSystem.enableDepthTest();
    }

    @Override
    public void resize(MinecraftClient minecraft, int width, int height) {
        super.resize(minecraft, width, height);

        resizeBrowser();
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        browser.sendMousePress(mouseX(mouseX), mouseY(mouseY), button);
        browser.setFocus(true);

        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        browser.sendMouseRelease(mouseX(mouseX), mouseY(mouseY), button);
        browser.setFocus(true);

        return super.mouseReleased(mouseX, mouseY, button);
    }

    @Override
    public void mouseMoved(double mouseX, double mouseY) {
        browser.sendMouseMove(mouseX(mouseX), mouseY(mouseY));

        super.mouseMoved(mouseX, mouseY);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
        browser.sendMouseWheel(mouseX(mouseX), mouseY(mouseY), verticalAmount, 0);
        browser.sendMouseWheel(mouseX(mouseX), mouseY(mouseY), horizontalAmount, 1);

        return super.mouseScrolled(mouseX, mouseY, horizontalAmount, verticalAmount);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        browser.sendKeyPress(keyCode, scanCode, modifiers);
        browser.setFocus(true);

        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean keyReleased(int keyCode, int scanCode, int modifiers) {
        browser.sendKeyRelease(keyCode, scanCode, modifiers);
        browser.setFocus(true);

        return super.keyReleased(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean charTyped(char codePoint, int modifiers) {
        if (codePoint == (char) 0) return false;

        browser.sendKeyTyped(codePoint, modifiers);
        browser.setFocus(true);

        return super.charTyped(codePoint, modifiers);
    }

    // helpers
    private void resizeBrowser() {
        if (width > 100 && height > 100) {
            browser.resize(scaleX(width), scaleY(height));
        }
    }

    private int mouseX(double x) {
        return (int) (x * minecraft.getWindow().getScaleFactor());
    }

    private int mouseY(double y) {
        return (int) (y * minecraft.getWindow().getScaleFactor());
    }

    private int scaleX(double x) {
        return (int) (x * minecraft.getWindow().getScaleFactor());
    }

    private int scaleY(double y) {
        return (int) (y * minecraft.getWindow().getScaleFactor());
    }
}
