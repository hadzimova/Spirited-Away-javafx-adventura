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

    private IHra hra= Hra.getSingleton();

    AnchorPane anchorPane= new AnchorPane();

    public PanelBatohu() {
        this.hra = hra;
        setMaxWidth(185);

        hra.getHerniPlan().getTaska().register(this);
        update();
 }
//    private void init() {
//        Image image = new Image(PanelBatohu.class.getResourceAsStream("bahno.gif"), 400.0, 250.0, false, false);
//        ImageView imageView = new ImageView(image);
//        anchorPane.getChildren().addAll(imageView);
//        anchorPane.getChildren().addAll();
//        //imageView.setFitHeight();
//    }


    @Override
    public void update() {
        hra = Hra.getSingleton();
        this.getChildren().clear();


        for (Vec predmet : hra.getHerniPlan().getTaska().getMnozinaVeci()) {
            ImageView imageView = new ImageView(predmet.getObrazok());
            this.getChildren().add(imageView);
        }
    }
}
