package me.theclashfruit.rhenium.mixin;

import com.google.common.collect.Lists;
import me.theclashfruit.rhenium.client.gui.BrowserScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.QuickPlay;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Mixin(net.minecraft.client.MinecraftClient.class)
public class MinecraftClientMixin {
    @Inject(method = "onInitFinished", at = @At(value = "HEAD"), cancellable = true)
    private void onInitFinished(MinecraftClient.LoadingContext loadingContext, CallbackInfoReturnable<Runnable> cir) {
        List<Function<Runnable, Screen>> list = new ArrayList();

        ((MinecraftClient) ((Object) this)).createInitScreens(list);

        Runnable runnable = () -> {
            if (loadingContext != null && loadingContext.quickPlayData().isEnabled()) {
                QuickPlay.startQuickPlay(((MinecraftClient) ((Object) this)), loadingContext.quickPlayData(), loadingContext.realmsClient());
            } else {
                ((MinecraftClient) ((Object) this)).setScreen(
                    new BrowserScreen(
                        Text.literal("Rhenium"),
                        "rhenium://html/index.html?gui=titlescreen",
                        true
                    )
                );
            }
        };

        for (Function<Runnable, Screen> function : Lists.reverse(list)) {
            Screen screen = function.apply(runnable);
            runnable = () -> ((MinecraftClient) ((Object) this)).setScreen(screen);
        }

        cir.setReturnValue(runnable);
    }
}
