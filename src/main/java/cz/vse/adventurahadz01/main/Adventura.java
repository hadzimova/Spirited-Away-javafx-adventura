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

public class Adventura extends Application {

    IHra hra = Hra.getSingleton();
    PanelBatohu panelBatohu = new PanelBatohu();
    ListVychodu listVychodu = new ListVychodu();
    MapaHry mapaHry = new MapaHry();
    TextArea textArea = new TextArea();


    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("-text")) {
            TextoveRozhrani.main(args);
        } else {
            Adventura.launch(args);
        }
    }

    @Override
    public void start(Stage stage){
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(textArea);

        Label label = new Label("Zadaj príkaz: ");
        label.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        textArea.setWrapText(true);
        TextField uzivatelskyVstup = new TextField();
        nastavListVychodu(borderPane, textArea, label, uzivatelskyVstup);
        nastavPridaniPaneluTasky(borderPane);

        MenuBar menuBar = new MenuBar();
        nastavMenuBar(menuBar);


        AnchorPane anchorPane = pripravMapuHry();
        VBox vBox = new VBox();
        vBox.getChildren().addAll(menuBar,anchorPane);
        borderPane.setTop(vBox);

        nastavTextAreaAUzivatelskyVstup(textArea, uzivatelskyVstup);


        Scene scene = new Scene(borderPane, 620, 750);
        stage.setScene(scene);
        stage.show();

    }

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
            hra.getHerniPlan().getTaska().register(panelBatohu);
            hra.getHerniPlan().register(mapaHry);
            hra.getHerniPlan().notifyObservers();
            hra.getHerniPlan().getTaska().notifyObservers();
            textArea.clear();
            textArea.setText(hra.vratUvitani());
        });


    }

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

    private void nastavPridaniPaneluTasky(BorderPane borderPane) {
        Label nazevLabel = new Label("Taska:");
        nazevLabel.setStyle("-fx-font-weight: bold;");
        VBox vbox = new VBox();
        vbox.getChildren().addAll(nazevLabel, panelBatohu);
        borderPane.setLeft(vbox);
    }

    private AnchorPane pripravMapuHry() {
        AnchorPane anchorPane = mapaHry.getAnchorPane();
        return anchorPane;
    }

    private void nastavTextAreaAUzivatelskyVstup(TextArea textArea, TextField uzivatelskyVstup) {
        textArea.setEditable(false);
        textArea.setText(hra.vratUvitani());


//        uzivatelskyVstup.setOnAction(actionEvent -> {
//            String prikaz = uzivatelskyVstup.getText();
//            String odpoved = hra.zpracujPrikaz(prikaz);
//            textArea.appendText("\n" + odpoved + "\n");
//            uzivatelskyVstup.setText("");
//        });
        uzivatelskyVstup.setOnAction(actionEvent -> {
            String prikaz = uzivatelskyVstup.getText();
            String odpoved = hra.zpracujPrikaz(prikaz);
            textArea.appendText("\n" + prikaz + "\n"); // pridáme aj text z textového poľa uzivatelskyVstup
            textArea.appendText("\n" +odpoved + "\n");
            uzivatelskyVstup.setText("");

        });




    }
}
