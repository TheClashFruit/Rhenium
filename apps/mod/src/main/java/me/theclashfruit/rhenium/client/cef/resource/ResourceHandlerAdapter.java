package me.theclashfruit.rhenium.client.cef.resource;

import net.fabricmc.loader.api.ModContainer;
import org.cef.browser.CefBrowser;
import org.cef.browser.CefFrame;
import org.cef.handler.CefRequestHandlerAdapter;
import org.cef.handler.CefResourceRequestHandler;
import org.cef.misc.BoolRef;
import org.cef.network.CefRequest;

import static me.theclashfruit.rhenium.Rhenium.mods;

public class ResourceHandlerAdapter extends CefRequestHandlerAdapter {
    @Override
    public CefResourceRequestHandler getResourceRequestHandler(CefBrowser browser, CefFrame frame, CefRequest request, boolean isNavigation, boolean isDownload, String requestInitiator, BoolRef disableDefaultHandling) {
        for (ModContainer mod : mods) {
            if (request.getURL().startsWith(mod.getMetadata().getId() + "://")) {
                return new ResourceRequestHandler();
            }
        }

        return null;
    }
}
