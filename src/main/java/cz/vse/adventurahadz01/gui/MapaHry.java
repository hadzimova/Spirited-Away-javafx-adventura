package cz.vse.adventurahadz01.gui;

import cz.vse.adventurahadz01.logika.HerniPlan;
import cz.vse.adventurahadz01.logika.Hra;
import cz.vse.adventurahadz01.logika.IHra;
import cz.vse.adventurahadz01.observer.Observer;
import javafx.animation.PathTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.util.Duration;

/**
 * Trieda MapaHry - realizujúca mapu hry použitú v grafickom rozhraní.
 * Aktualizuje polohu hlavnej postavy v závislosti na aktuálnom priestore v hernom pláne.
 * @author Zuzana Hadzimová
 * @version apríl 2023
 */

public class MapaHry implements Observer {

    private IHra hra = Hra.getSingleton();
    private AnchorPane anchorPane = new AnchorPane();

    Image hlPostava = new Image(MapaHry.class.getResourceAsStream("hlPostava.gif"), 70.0, 60.0, false, false);
    ImageView hlavnaPostava = new ImageView(hlPostava);

    double povodnaPolohaX = 0.0;
    double povodnaPolohaY = 0.0;


    /**
     * Konštruktor MapaHry
     * Inicializuje herný plán, vkladá obrázok herného plánu a hlavnej postavy do AnchorPane a registruje sa ako Observer.
     */
    public MapaHry() {
        init();
        anchorPane.setTopAnchor(hlavnaPostava,0.0);
        anchorPane.setLeftAnchor(hlavnaPostava, 0.0);
        aktualizuj();
        HerniPlan plan = hra.getHerniPlan();
        plan.register(this);
    }
    /**
     * Metóda init - Inicializuje AnchorPane s obrázkom herného plánu a hlavnou postavou.
     */
    private void init() {
        Image image = new Image(MapaHry.class.getResourceAsStream("herniPlan.png"), 620.0, 380.0, false, false);
        ImageView imageView = new ImageView(image);
        anchorPane.getChildren().addAll(imageView);
        anchorPane.getChildren().addAll(hlavnaPostava);

    }
    /**
     * Metóda aktualizuj - Aktualizuje polohu hlavnej postavy v závislosti na aktuálnom priestore v hernom pláne.
     * Poloha hlavnej postavy sa mení pomocou setTopAnchor a setLeftAnchor v AnchorPane.
     */
    private void aktualizuj(){
        hra = Hra.getSingleton();
        HerniPlan plan= hra.getHerniPlan();
        double posX= plan.getAktualniProstor().getPosLeft();
        double posY= plan.getAktualniProstor().getPosTop();

        Line line = new Line();
        line.setStartY(povodnaPolohaY);
        line.setStartX(povodnaPolohaX);
        line.setEndX(posX);
        line.setEndY(posY);

        PathTransition pathTransition= new PathTransition();
        pathTransition.setNode(hlavnaPostava);
        pathTransition.setPath(line);
        pathTransition.setDuration(Duration.seconds(2));
        pathTransition.play();

        povodnaPolohaX = posX;
        povodnaPolohaY = posY;


    }

    /**
     * Metóda getAnchorPane - vrátenie AnchorPane objektu s grafickou reprezentáciou mapy.
     * @return anchorPane s grafickou reprezentáciou mapy.
     */
    public AnchorPane getAnchorPane() {
        return anchorPane;
    }

    /**
     * Metóda update- reaguje na zmeny v Hernom pláne a aktualizuje pozíciu hlavnej postavy na mape.
     */
    @Override
    public void update() {
        aktualizuj();
    }
}
