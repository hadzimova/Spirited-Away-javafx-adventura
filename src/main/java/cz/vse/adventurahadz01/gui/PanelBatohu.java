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
        setMaxWidth(185);

        hra.getHerniPlan().getTaska().register(this);
        update();
 }


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
