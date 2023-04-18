package cz.vse.adventurahadz01.gui;

import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.stage.Stage;
import javafx.scene.web.WebView;

import java.net.URL;
/**
 * Trieda Napoveda - realizujúca zobrazenie nápovedy v HTML podobe.
 * @author Zuzana Hadzimová
 * @version apríl 2023
 */

public class Napoveda extends Stage {

    /**
     * Konštruktor triedy Napoveda, ktorý vytvára okno nápovedy a zobrazuje HTML obsah.
     * Nápoveda je načítaná z lokálneho súboru "prirucka.html".
     */
    public Napoveda(){
        WebView web = new WebView();
        WebEngine wEngine = web.getEngine();
        URL url = getClass().getResource("prirucka.html");
        wEngine.load(url.toExternalForm());

        Scene scene = new Scene(web, 850, 500);
        this.setScene(scene);
    }
}
