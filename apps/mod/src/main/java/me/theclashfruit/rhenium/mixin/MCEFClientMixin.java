package me.theclashfruit.rhenium.mixin;

import me.theclashfruit.rhenium.client.cef.MessageRouter;
import me.theclashfruit.rhenium.client.cef.resource.ResourceHandlerAdapter;
import org.cef.CefClient;
import org.cef.browser.CefMessageRouter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

// I would have hoped that this wouldn't be necessary, somehow the message router only works if it's added here.
@Mixin(com.cinemamod.mcef.MCEFClient.class)
public class MCEFClientMixin {
    @Inject(at = @At("TAIL"), method = "<init>")
    private void constructorHead(CefClient cefClient, CallbackInfo ci) {
        CefMessageRouter msgRouter = CefMessageRouter.create(
            new CefMessageRouter.CefMessageRouterConfig("javaQuery", "javaQueryCancel")
        );
        msgRouter.addHandler(new MessageRouter(), true);

        cefClient.addMessageRouter(msgRouter);
        cefClient.addRequestHandler(new ResourceHandlerAdapter());
    }
}
