package com.blackwhite.sinamn75.base.utils;

/**
 * A simple css for web view.
 */
public class WebViewStyle {
    private static WebViewStyle myInstance;

    private WebViewStyle() {
    }

    public static synchronized WebViewStyle getInstance() {
        if (myInstance == null) {
            myInstance = new WebViewStyle();
        }
        return myInstance;
    }

    public String getHtmlLayout(String content, String hexColor, String hexBackgroundColor) {
        return "<!DOCTYPE html> <html> <head> " + "    <link rel=\"stylesheet\" href=\"style.css\"> " + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"> " + "</head> " + "<body style='color:" + hexColor + " background-color:" + hexBackgroundColor + "'>" + content + "</body></html>";
    }
}