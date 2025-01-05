package me.theclashfruit.rhenium.client.cef;

import net.fabricmc.loader.api.ModContainer;
import org.cef.CefApp;
import org.cef.callback.CefSchemeRegistrar;
import org.cef.handler.CefAppHandlerAdapter;

import static me.theclashfruit.rhenium.Rhenium.logger;
import static me.theclashfruit.rhenium.Rhenium.mods;

public class AppHandler extends CefAppHandlerAdapter {
    public AppHandler(String[] args) {
        super(args);
    }

    @Override
    public void onRegisterCustomSchemes(CefSchemeRegistrar registrar) {
        for (ModContainer mod : mods) {
            if (registrar.addCustomScheme(mod.getMetadata().getId(), true, false, false, false, true, false, true))
                logger.info("Registered Scheme: {}", mod.getMetadata().getId());
        }
    }

    @Override
    public void onContextInitialized() {
        CefApp cefApp = CefApp.getInstance();

        for (ModContainer mod : mods) {
            cefApp.registerSchemeHandlerFactory(
                    mod.getMetadata().getId(),
                    "",
                    (cefBrowser, cefFrame, s, cefRequest) -> null
            );
        }
    }
}
