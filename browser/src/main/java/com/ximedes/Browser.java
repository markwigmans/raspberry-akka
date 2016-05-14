package com.ximedes;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.layout.Region;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 *
 */
class Browser extends Region {

    private final WebView browser = new WebView();
    private final WebEngine webEngine = browser.getEngine();

    public Browser(final String url) {
        //apply the styles
        getStyleClass().add("browser");
        webEngine.load(url);
        getChildren().add(browser);
    }

    @Override
    protected void layoutChildren() {
        double w = getWidth();
        double h = getHeight();
        layoutInArea(browser, 0, 0, w, h, 0, HPos.CENTER, VPos.CENTER);
    }
}