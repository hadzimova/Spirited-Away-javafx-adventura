package cz.vse.adventurahadz01.logika;
/**
 * Trieda TrestPokarhania - predstavuje trest za nesplnenie úlohy v podobe textu.
 * @author Zuzana Hadzimová
 * @version január 2023
 */
public class TrestPokarhania implements ITrest{
    /**
     * Konštruktor triedy TrestPokarhania.
     */
    public TrestPokarhania() {

    }

    /**
     * Metóda vracia text, ktorý sa zobrazí hráčovi pri nesplnení úlohy s nastaveným trestom TrestPokarhania.
     */
    @Override
    public void trestZaNesplneni() {
        System.out.println("Úlohu si nesplnila! Skús to znova");
    }
}
