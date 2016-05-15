package com.ximedes;

import javafx.application.Preloader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.PropertyPlaceholderAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * See:
 * {@linktourl http://docs.oracle.com/javafx/2/webview/jfxpub-webview.htm}
 * {@linktourl https://github.com/thomasdarimont/spring-labs/tree/master/spring-boot-javafx}
 * {@linktourl http://www.alexecollins.com/spring-boot-performance/}
 */
@Configuration
@Import(PropertyPlaceholderAutoConfiguration.class)
public class App extends AbstractJavaFxApplicationSupport {

    @Value("${app.ui.width:1024}")
    private double width;

    @Value("${app.ui.height:600}")
    private double height;

    @Value("${app.url}")
    private String url;

    @Override
    public void start(Stage stage) throws Exception {
        notifyPreloader(new Preloader.StateChangeNotification(Preloader.StateChangeNotification.Type.BEFORE_START));

        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(new Browser(url), width, height, Color.web("#666970")));
        stage.centerOnScreen();
        stage.show();
    }

    public static void main(String[] args) {
        launchApp(App.class, args);
    }
}