package cz.vse.adventurahadz01.logika;

/**
 * Trieda Bytost - trieda predstavujúca bytosti v hre
 * @author Zuzana Hadzimová
 * @version január 2023
 */
public class Bytost {
    private final String meno;
    private String dialog;
    private Uloha uloha;

    /**
     * Konštruktor pre bytosti obsahujúci meno a dialóg
     * @param meno meno danej bytosti
     * @param dialog dialóg, ktorý sa zobrazí hráčovi pri prehovorení s postavou
     */
    public Bytost(String meno, String dialog) {
        this.meno = meno;
        this.dialog = dialog;
    }


    /**
     * Metóda vracajúca meno určitej bytosti
     * @return meno bytosti
     */
    public String getMeno() {
        return meno;
    }

    /**
     * Metóda vracajúca dialóg určitej bytosti, využitá pri prehovorení s bytosťou
     * @return dialóg bytosti
     */
    public String getDialog() {
        return dialog;
    }

    /**
     * Metóda vracajúca úlohu priradenú k určitej bytosti
     * @return inštanciu triedy Uloha
     */
    public Uloha getUloha() {
        return uloha;
    }

    /**
     * Metóda na nastavenie úlohy určitej bytosti
     * @param uloha inštancia triedy uloha, nová úloha priradená k bytosti
     */
    public void setUloha(Uloha uloha) {
        this.uloha = uloha;
    }

}
