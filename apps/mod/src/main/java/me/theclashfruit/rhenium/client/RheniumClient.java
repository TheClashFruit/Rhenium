package me.theclashfruit.rhenium.client;

import me.theclashfruit.rhenium.client.cef.AppHandler;
import net.fabricmc.api.ClientModInitializer;
import org.cef.CefApp;

public class RheniumClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        CefApp.addAppHandler(new AppHandler(null));
    }
}
