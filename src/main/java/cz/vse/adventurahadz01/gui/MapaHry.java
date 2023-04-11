package cz.vse.adventurahadz01.gui;

import cz.vse.adventurahadz01.logika.HerniPlan;
import cz.vse.adventurahadz01.logika.Hra;
import cz.vse.adventurahadz01.logika.IHra;
import cz.vse.adventurahadz01.observer.Observer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class MapaHry implements Observer {

    private IHra hra = Hra.getSingleton();
    private AnchorPane anchorPane = new AnchorPane();

    Image hlPostava = new Image(MapaHry.class.getResourceAsStream("hlPostava.gif"), 70.0, 60.0, false, false);
    ImageView hlavnaPostava = new ImageView(hlPostava);


    public MapaHry() {
        init();
        aktualizuj();
        HerniPlan plan = hra.getHerniPlan();
        plan.register(this);
    }

    private void init() {
        Image image = new Image(MapaHry.class.getResourceAsStream("herniPlan.png"), 620.0, 380.0, false, false);
        ImageView imageView = new ImageView(image);
        anchorPane.getChildren().addAll(imageView);
        anchorPane.getChildren().addAll(hlavnaPostava);

    }

    private void aktualizuj(){
        hra = Hra.getSingleton();
        HerniPlan plan= hra.getHerniPlan();
        double posX= plan.getAktualniProstor().getPosLeft();
        double posY= plan.getAktualniProstor().getPosTop();
        anchorPane.setTopAnchor(hlavnaPostava,posY);
        anchorPane.setLeftAnchor(hlavnaPostava, posX);
    }

    public AnchorPane getAnchorPane() {
        return anchorPane;
    }

    @Override
    public void update() {
        aktualizuj();
    }
}
