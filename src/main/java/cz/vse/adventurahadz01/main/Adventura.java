package cz.vse.adventurahadz01.main;

import cz.vse.adventurahadz01.gui.ListVychodu;
import cz.vse.adventurahadz01.gui.MapaHry;
import cz.vse.adventurahadz01.gui.Napoveda;
import cz.vse.adventurahadz01.gui.PanelBatohu;
import cz.vse.adventurahadz01.logika.Hra;
import cz.vse.adventurahadz01.logika.IHra;
import cz.vse.adventurahadz01.uiText.TextoveRozhrani;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
/**
 * Trieda Adventura - vstupný bod aplikácie.
 * Implementuje rozhranie, vytvára inštanciu triedy Hra a jednotlivé panely a komponenty grafického rozhrania.
 * Taktiež implementuje spustenie hry s grafickým rozhraním alebo textovým rozhraním.
 * @author Zuzana Hadzimová
 * @version apríl 2023
 */
public class Adventura extends Application {

    IHra hra = Hra.getSingleton();
    PanelBatohu panelTasky = new PanelBatohu();
    ListVychodu listVychodu = new ListVychodu();
    MapaHry mapaHry = new MapaHry();
    TextArea textArea = new TextArea();

    /**
     * Metóda main - spúšťa aplikáciu. Pokiaľ je prvý argument "-text", spustí hru s textovým rozhraním, inak
     * spustí hru s grafickým rozhraním.
     * @param args argumenty príkazového riadku
     */
    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("-text")) {
            TextoveRozhrani.main(args);
        } else {
            Adventura.launch(args);
        }
    }

    /**
     * Metóda start - volá sa pri spustení aplikácie s grafickým rozhraním.
     * Vytvára hlavné okno s jednotlivými komponentami grafického rozhrania a obsahuje implementáciu ich funkcionality.
     * @param stage hlavné okno aplikácie
     */
    @Override
    public void start(Stage stage){
        BorderPane borderPane = new BorderPane();

        Label label = new Label("Zadaj príkaz: ");
        label.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        textArea.setWrapText(true);
        TextField uzivatelskyVstup = new TextField();
        nastavListVychodu(borderPane, textArea, label, uzivatelskyVstup);
        VBox panelTasky = nastavPridaniePaneluTasky(borderPane);


        MenuBar menuBar = new MenuBar();
        nastavMenuBar(menuBar);

        AnchorPane anchorPane = pripravMapuHry();
        HBox hbox = new HBox();
        VBox vBox = new VBox();

        vBox.getChildren().addAll(anchorPane, textArea);
        hbox.getChildren().addAll(panelTasky, vBox);

        borderPane.setCenter(hbox);
        borderPane.setTop(menuBar);


        nastavTextAreaAUzivatelskyVstup(textArea, uzivatelskyVstup);

        Scene scene = new Scene(borderPane, 1011, 750);
        stage.setScene(scene);
        stage.show();

    }

    /**
     * Metóda nastavMenuBar - vytvára položky menu, novú hru, ukončenie hry a zobrazenie nápovedy v dialógovom okne.
     * @param menuBar
     */

    private void nastavMenuBar(MenuBar menuBar) {
        Menu menuHra = new Menu("Hra");
        Menu menuNapoveda = new Menu("Nápoveda");
        MenuItem novaHra = new MenuItem("Nová hra");
        MenuItem koniecHry = new MenuItem("Koniec hry");
        MenuItem akoHratHru = new MenuItem("Ako hrať hru");
        menuBar.getMenus().addAll(menuHra,menuNapoveda);
        menuHra.getItems().addAll(novaHra, koniecHry);
        menuNapoveda.getItems().addAll(akoHratHru);

        akoHratHru.addEventHandler(ActionEvent.ACTION, event -> {
            Napoveda napoveda = new Napoveda();
            napoveda.show();
        });

        koniecHry.addEventHandler(ActionEvent.ACTION, event -> {
            System.exit(0);
        });

        novaHra.addEventHandler(ActionEvent.ACTION, event -> {
            hra = Hra.restartHry();
            hra.getHerniPlan().register(listVychodu);
            hra.getHerniPlan().getTaska().register(panelTasky);
            hra.getHerniPlan().register(mapaHry);
            hra.getHerniPlan().notifyObservers();
            hra.getHerniPlan().getTaska().notifyObservers();
            textArea.clear();
            textArea.setText(hra.vratUvitani());
        });


    }

    /**
     * Metóda nastavListVychodu - nastavuje zoznam východov.
     * Pridáva sa do BorderPane, ktorý zobrazuje zoznam na pravej strane obrazovky. Pri kliknutí na názov východu sa spracuje príkaz "Chod " + názov danej miestnosti.
     * @param borderPane, do ktorého sa pridáva zoznam východov.
     * @param textArea slúži na výpis textových informácií pre používateľa.
     * @param label obsahuje textový popis poľa pre zadanie príkazu.
     * @param uzivatelskyVstup slúži na zadanie príkazu.
     * @return ListVychodu, zoznam východov.
     */

    private ListVychodu nastavListVychodu(BorderPane borderPane, TextArea textArea, Label label, TextField uzivatelskyVstup) {
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(label, uzivatelskyVstup);
        borderPane.setBottom(hBox);
        borderPane.setRight(listVychodu);
        listVychodu.setMaxWidth(120);
        listVychodu.setOnMouseClicked(mouseEvent -> {
            if(mouseEvent.getClickCount()==2){
                String vybranaPolozka = listVychodu.getSelectionModel().getSelectedItem();
                if(vybranaPolozka != null){
                    String odpoved = hra.zpracujPrikaz("chod " + vybranaPolozka);
                    textArea.appendText("\n" + odpoved + "\n");
                    uzivatelskyVstup.setText("");
                }
            }
        });
        return listVychodu;
    }

    /**
     * Metóda nastavPridaniPaneluTasky - pridáva panel tašky na ľavú stranu BorderPane.
     * Panel obsahuje názov "Taska" a zoznam vecí, ktoré sú v taške hráča.
     * @param borderPane, do ktorého sa pridáva obsah tašky
     */

    private VBox nastavPridaniePaneluTasky(BorderPane borderPane) {
        Label nazovLabel = new Label("Taska:");
        nazovLabel.setStyle("-fx-font-weight: bold;");
        VBox vbox = new VBox();
        vbox.getChildren().addAll(nazovLabel, panelTasky);

        return vbox;

    }

    /**
     * Metóda pripravMapuHry - Táto metóda vráti AnchorPane, ktorý zobrazuje mapu hry.
     * @return anchorPane zobrazujúci mapu hry
     */
    private AnchorPane pripravMapuHry() {
        AnchorPane anchorPane = mapaHry.getAnchorPane();
        return anchorPane;
    }

    /**
     * Metóda nastavTextAreaAUzivatelskyVstup - nastavuje TextArea a TextField pre používateľský vstup.
     * TextArea sa nastavuje tak, aby bolo možné z nej iba čítať a používateľský vstup sa spracováva pomocou metódy zpracujPrikaz s argumentom zadaným používateľom.
     * Následne sa výstup vypíše do TextArea.
     * @param textArea zobrazuje výstup
     * @param uzivatelskyVstup zadaný hráčom
     */

    private void nastavTextAreaAUzivatelskyVstup(TextArea textArea, TextField uzivatelskyVstup) {
        textArea.setEditable(false);
        textArea.setText(hra.vratUvitani());
        textArea.setMinHeight(318);


        uzivatelskyVstup.setOnAction(actionEvent -> {
            String prikaz = uzivatelskyVstup.getText();
            String odpoved = hra.zpracujPrikaz(prikaz);
            textArea.appendText("\n" +odpoved + "\n");
            uzivatelskyVstup.setText("");

        });




    }
}
