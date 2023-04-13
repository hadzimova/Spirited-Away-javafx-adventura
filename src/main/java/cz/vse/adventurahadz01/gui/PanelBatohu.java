package cz.vse.adventurahadz01.gui;

import cz.vse.adventurahadz01.logika.Hra;
import cz.vse.adventurahadz01.logika.IHra;
import cz.vse.adventurahadz01.logika.Vec;
import cz.vse.adventurahadz01.observer.Observer;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
/**
 * Trieda PanelBatohu zobrazujúci veci v taške hráča.
 * @author Zuzana Hadzimová
 * @version apríl 2023
 */

public class PanelBatohu extends FlowPane implements Observer {

    private IHra hra= Hra.getSingleton();

    /**
     * Konštruktor panelu batohu- nastavenie rozmerov a registrácia observera tašky hráča.
     */
    public PanelBatohu() {
        setMaxWidth(181);
        setMinWidth(181);

        hra.getHerniPlan().getTaska().register(this);
        update();
 }

    /**
     * Metóda update vymaže existujúce prvky v objekte typu JavaFX kontajner
     * a pridá nové veci, ktoré má hráč v inventári
     */
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
