package me.theclashfruit.rhenium.client.cef.resource;

import org.cef.browser.CefBrowser;
import org.cef.browser.CefFrame;
import org.cef.handler.CefCookieAccessFilter;
import org.cef.handler.CefResourceHandler;
import org.cef.handler.CefResourceRequestHandler;
import org.cef.misc.BoolRef;
import org.cef.misc.StringRef;
import org.cef.network.CefRequest;
import org.cef.network.CefResponse;
import org.cef.network.CefURLRequest;

public class ResourceRequestHandler implements CefResourceRequestHandler {
    @Override
    public CefResourceHandler getResourceHandler(CefBrowser cefBrowser, CefFrame cefFrame, CefRequest cefRequest) {
        return new ResourceHandler();
    }

    @Override
    public CefCookieAccessFilter getCookieAccessFilter(CefBrowser cefBrowser, CefFrame cefFrame, CefRequest cefRequest) {
        return null;
    }

    @Override
    public boolean onBeforeResourceLoad(CefBrowser cefBrowser, CefFrame cefFrame, CefRequest cefRequest) {
        return false;
    }

    @Override
    public void onResourceRedirect(CefBrowser cefBrowser, CefFrame cefFrame, CefRequest cefRequest, CefResponse cefResponse, StringRef stringRef) {

    }

    @Override
    public boolean onResourceResponse(CefBrowser cefBrowser, CefFrame cefFrame, CefRequest cefRequest, CefResponse cefResponse) {
        return false;
    }

    @Override
    public void onResourceLoadComplete(CefBrowser cefBrowser, CefFrame cefFrame, CefRequest cefRequest, CefResponse cefResponse, CefURLRequest.Status status, long l) {

    }

    @Override
    public void onProtocolExecution(CefBrowser cefBrowser, CefFrame cefFrame, CefRequest cefRequest, BoolRef boolRef) {

    }
}
