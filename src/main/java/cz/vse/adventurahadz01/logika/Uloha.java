package cz.vse.adventurahadz01.logika;

/**
 * Trieda Uloha - predstavuje úlohy, ktoré má hlavná postava plniť.
 * @author Zuzana Hadzimová
 * @version január 2023
 */
public class Uloha {
    private final String nazovUlohy;
    private final String popisUlohy;
    private final String uspesnyDialog;
    private final String neuspesnyDialog;
    private boolean zadana;
    private boolean hotova;


    IOcakavanaHodnota ocekavanaHodnota;

    Vec uspesneSpleni;
    ITrest trestZaNesplneni;

    /**
     * Konštruktor triedy Uloha.
     * @param nazovUlohy názov úlohy
     * @param popisUlohy popis danej úlohy
     * @param uspesnyDialog dialóg bytosti, pri splnení úlohy
     * @param neuspesnyDialog dialóg bytosti, pri nesplnení úlohy
     */
    public Uloha(String nazovUlohy, String popisUlohy, String uspesnyDialog, String neuspesnyDialog) {
        this.nazovUlohy = nazovUlohy;
        this.popisUlohy = popisUlohy;
        this.uspesnyDialog = uspesnyDialog;
        this.neuspesnyDialog = neuspesnyDialog;
        uspesneSpleni= new Vec("cast_klucu",true);

    }

    /**
     * Metóda vracia názov úlohy.
     * @return názov úlohy
     */
    public String getNazovUlohy() {
        return nazovUlohy;
    }
    /**
     * Metóda vracia popis úlohy.
     * @return popis úlohy
     */
    public String getPopisUlohy() {
        return popisUlohy;
    }
    /**
     * Metóda vracia úspešný dialóg zobrazený pri splnení úlohy.
     * @return uspesnyDialog
     */
    public String getUspesnyDialog() {
        return uspesnyDialog;
    }

    /**
     * Metóda vracia neúspešný dialóg zobrazený pri nesplnení úlohy.
     * @return neuspesnyDialog
     */
    public String getNeuspesnyDialog() {
        return neuspesnyDialog;
    }

    /**
     * Metóda na nastavenie očakávanej hodnoty - potrebnej na splnenie úlohy.
     * @param ocekavanaHodnota
     */
    public void setOcekavanaHodnota(IOcakavanaHodnota ocekavanaHodnota) {
        this.ocekavanaHodnota = ocekavanaHodnota;
    }

    /**
     * Metóda na nastavenie trestu v prípade nesplnenia.
     * @param trestZaNesplneni
     */
    public void setTrestZaNesplneni(ITrest trestZaNesplneni) {
        this.trestZaNesplneni = trestZaNesplneni;
    }

    /**
     * Metóda vracajúca hodnotuboolean zadana.
     * @return boolean zadana
     */
    public boolean isZadana() {
        return zadana;
    }

    /**
     * Metóda na zmenu hodnoty zadana na true.
     * @param zadana
     */
    public void setZadana(boolean zadana) {
        this.zadana = zadana;
    }

    /**
     * Metóda na zmenu hodnoty hotova na true.
     * @param hotova
     */
    public void setHotova(boolean hotova) {
        this.hotova = hotova;
    }
    /**
     * Metóda vracajúca hodnotu boolean hotova.
     * @return boolean zadana
     */
    public boolean isHotova() {
        return hotova;
    }

    /**
     * Metóda na získanie očakávanej hodnoty úlohy.
     * @return ocakavanaHodnota úlohy
     */
    public IOcakavanaHodnota getOcekavanaHodnota() {
        return ocekavanaHodnota;
    }

    /**
     *
     * @return
     */
    public Vec getUspesneSpleni() {
        return uspesneSpleni;
    }
    /**
     * Metóda na získanie trestu za nesplnenie úlohy.
     * @return trestZaNesplnenie úlohy
     */
    public ITrest getTrestZaNesplneni() {
        return trestZaNesplneni;
    }
}
