package cz.vse.adventurahadz01.gui;

import cz.vse.adventurahadz01.logika.Hra;
import cz.vse.adventurahadz01.logika.IHra;
import cz.vse.adventurahadz01.logika.Vec;
import cz.vse.adventurahadz01.observer.Observer;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

public class PanelBatohu extends FlowPane implements Observer {

    private IHra hra;

    AnchorPane anchorPane= new AnchorPane();

    public PanelBatohu(IHra hra) {
        this.hra = hra;
        this.setPadding(new Insets(10));
        this.setVgap(10);
        this.setHgap(10);
        this.setPrefWrapLength(150);

        hra.getHerniPlan().getTaska().register(this);
        update();
 }
//    private void init() {
//        Image image = new Image(PanelBatohu.class.getResourceAsStream("maliny.jpeg"), 400.0, 250.0, false, false);
//        ImageView imageView = new ImageView(image);
//        anchorPane.getChildren().addAll(imageView);
//        anchorPane.getChildren().addAll();
//
//        //ak dáme len image tak s tým nevie pracovať
//        //  imageView.setFitHeight();
//    }


    @Override
    public void update() {
        this.getChildren().clear();

        Label nazevLabel = new Label("Batoh:");
        nazevLabel.setStyle("-fx-font-weight: bold;");
        this.getChildren().add(nazevLabel);

//        for (Vec predmet : hra.getHerniPlan().getTaska().getMnozinaVeci()) {
//            ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream(Hra.getSingleton().getHerniPlan().getTaska().vratVec(predmet.getNazev())+".gif")));
//            this.getChildren().add(imageView);
//        }
    }
}
