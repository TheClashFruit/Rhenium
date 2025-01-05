package me.theclashfruit.rhenium.client.cef.resource;

import net.fabricmc.loader.api.ModContainer;
import org.cef.callback.CefCallback;
import org.cef.handler.CefResourceHandler;
import org.cef.misc.IntRef;
import org.cef.misc.StringRef;
import org.cef.network.CefRequest;
import org.cef.network.CefResponse;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import static me.theclashfruit.rhenium.Rhenium.logger;
import static me.theclashfruit.rhenium.Rhenium.mods;
import static me.theclashfruit.rhenium.util.MimeTypeUtil.getMimeType;

public class ResourceHandler implements CefResourceHandler {
    private byte[] data;
    private int offset;

    private String url;
    private String path;

    @Override
    public boolean processRequest(CefRequest cefRequest, CefCallback cefCallback) {
        url = cefRequest.getURL();

        path  = url.split("\\?")[0];

        if (path.endsWith("/"))
            path = path.substring(0, path.length() - 1);
        for (ModContainer mod : mods)
            path = path.replace(mod.getMetadata().getId() + "://", "assets/" + mod.getMetadata().getId() + "/");

        try (InputStream is = getClass().getClassLoader().getResourceAsStream(path)) {
            if (is == null) {
                logger.warn("Resource Not Found: {}", path);

                return false;
            }

            data = is.readAllBytes();
            cefCallback.Continue();

            return true;
        } catch (Exception e) {
            logger.error("Failed to load resource!", e);
        }

        return false;
    }

    @Override
    public void getResponseHeaders(CefResponse cefResponse, IntRef intRef, StringRef stringRef) {
        cefResponse.setMimeType(getMimeType(path.substring(path.lastIndexOf(".") + 1)));
        cefResponse.setStatus(200);

        // cors headers just in case
        Map<String, String> headers = new HashMap<>();

        headers.put("Access-Control-Allow-Origin", "*");
        headers.put("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
        headers.put("Access-Control-Allow-Headers", "Content-Type");

        cefResponse.setHeaderMap(headers);
        intRef.set(data.length);
    }

    @Override
    public boolean readResponse(byte[] bytes, int i, IntRef intRef, CefCallback cefCallback) {
        if (data == null) return false;

        int length = Math.min(i, data.length - offset);
        System.arraycopy(data, offset, bytes, 0, length);

        offset += length;
        intRef.set(length);

        if (offset >= data.length) data = null;
        return true;
    }

    @Override
    public void cancel() {
        data = null;
    }
}
