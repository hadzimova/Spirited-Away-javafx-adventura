package cz.vse.adventurahadz01.gui;

import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.stage.Stage;
import javafx.scene.web.WebView;

import java.net.URL;

public class Napoveda extends Stage {
    public Napoveda(){
        WebView web = new WebView();
        WebEngine wEngine = web.getEngine();
        URL url = getClass().getResource("prirucka.html");
        wEngine.load(url.toExternalForm());

        Scene scene = new Scene(web, 850, 500);
        this.setScene(scene);
    }
}
