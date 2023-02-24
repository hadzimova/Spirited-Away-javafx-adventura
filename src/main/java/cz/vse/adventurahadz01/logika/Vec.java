package cz.vse.adventurahadz01.logika;
/**
 * Trieda Vec - realizujúca veci v hre.
 * @author Zuzana Hadzimová
 * @version január 2023
 */
public class Vec {

    private final String nazev;
    private final boolean prenositelna;

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
}
