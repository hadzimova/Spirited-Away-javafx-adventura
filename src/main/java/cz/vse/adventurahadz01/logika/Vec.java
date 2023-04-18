package cz.vse.adventurahadz01.logika;

import javafx.scene.image.Image;

/**
 * Trieda Vec - realizujúca veci v hre.
 * @author Zuzana Hadzimová
 * @version apríl 2023
 */
public class Vec {

    private final String nazev;
    private final boolean prenositelna;

    Image obrazok;
    /**
     * Konštruktor triedy Vec.
     * @param nazev názov veci
     * @param prenositelna boolean hodnota
     * @param nazovObrazku
     */
    public Vec(String nazev, boolean prenositelna, String nazovObrazku) {
        this.nazev = nazev;
        this.prenositelna = prenositelna;
        obrazok = new Image(getClass().getResourceAsStream(nazovObrazku),90,90,true, true);
    }

    /**
     * Konštruktor triedy Vec.
     * @param nazev názov veci
     * @param prenositelna boolean hodnota
     */
    public Vec(String nazev, boolean prenositelna) {
        this.nazev = nazev;
        this.prenositelna = prenositelna;
    }

    /**
     * Metóda na získanie  názvu veci.
     * @return String hodnota názov
     */
    public String getNazev() {
        return nazev;
    }

    /**
     * Metóda na vrátenie boolean hodnoty prenositelna veci.
     * @return boolean prenositelna
     */
    public boolean isPrenositelna() {
        return prenositelna;
    }

    public Image getObrazok() {
        return obrazok;
    }
}
