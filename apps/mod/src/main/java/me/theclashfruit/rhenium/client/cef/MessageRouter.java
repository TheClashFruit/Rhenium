package me.theclashfruit.rhenium.client.cef;

import com.google.gson.JsonObject;
import me.theclashfruit.rhenium.util.CommandUtil;
import org.cef.browser.CefBrowser;
import org.cef.browser.CefFrame;
import org.cef.callback.CefQueryCallback;
import org.cef.handler.CefMessageRouterHandlerAdapter;

import java.util.Map;

import static me.theclashfruit.rhenium.Rhenium.logger;

public class MessageRouter extends CefMessageRouterHandlerAdapter {
    @Override
    public boolean onQuery(CefBrowser cefBrowser, CefFrame cefFrame, long l, String s, boolean b, CefQueryCallback cefQueryCallback) {
        logger.debug("CEF Query: {}", s);

        try {
            Map<String, Object> cmd = CommandUtil.parseCommand(s);

            String fn = (String) cmd.get("function");

            if (fn.equals("helloWorld")) {
                cefQueryCallback.success("Hello, World!");
            } else {
                JsonObject data = new JsonObject();

                data.addProperty("error", "fnInvalid");

                cefQueryCallback.failure(0, data.toString());
            }
        } catch (Exception e) {
            logger.error("Failed to handle CEF Query!", e);

            cefQueryCallback.failure(1, e.getMessage());
        }

        return true;
    }
}
