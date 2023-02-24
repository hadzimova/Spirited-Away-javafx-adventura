package cz.vse.adventurahadz01.logika;

/**
 * Trieda TrestSmrt- predstavuje trest, v podobe konca hry.
 * @author Zuzana Hadzimová
 * @version január 2023
 */
public class TrestSmrt implements ITrest{
    private Hra hra;

    /**
     * Konštruktor triedy TrestSmrt.
     * @param hra prijíma parameter hra
     */
    public TrestSmrt(Hra hra) {
        this.hra = hra;
    }

    /**
     * Metóda trestZaNesplnení vráti uživateľovi text a ukončí hru.
     */
    @Override
    public void trestZaNesplneni() {
        hra.setEpilog("Nesplnil si úlohu a týmto hra končí.");
        hra.setKonecHry(true);
    }
}
