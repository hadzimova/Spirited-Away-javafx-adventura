package cz.vse.adventurahadz01.logika;
/**
 *  Trieda PrikazHovor implementuje pre hru príkaz hovor.
 *
 *@author     Zuzana Hadzimová
 *@version    január 2023
 */
public class PrikazHovor implements IPrikaz{

    public static final String NAZEV = "hovor";

    private HerniPlan herniPlan;

    /**
     * Konštruktor príkazu hovor.
     * @param herniPlan prijíma herniPlan
     */
    public PrikazHovor(HerniPlan herniPlan) {
        this.herniPlan = herniPlan;
    }

    /**
     * Metóda pri použití mena bytosti v aktuálnej miestnosti vráti dialóg. V prípade, že hráč zatiaľ neprijal úlohu
     * vráti aj úlohu. Ak úlohu ešte nesplnil tak vráti text rozdielny od dialógu bytosti.
     * @param parametry počet parametrů závisí na konkrétním příkazu.
     * @return vracia text zobrazený v hráčovi
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "S kým mám hovoriť?";
        }
        else if (parametry.length > 1) {
            return "Nebudem hovoriť s viac ako jednou bytosťou naraz!";
        }

        Bytost mistni = herniPlan.getAktualniProstor().vratBytost(parametry[0]);
        if (mistni != null){
            if (mistni.getUloha()==null){
                return mistni.getDialog();
            }
            if (!mistni.getUloha().isZadana()  && !mistni.getUloha().isHotova() ){
                return mistni.getDialog() + "\nMám pre teba úlohu: " + mistni.getUloha().getNazovUlohy() + "\n" + mistni.getUloha().getPopisUlohy();
            }
            if (mistni.getUloha().isZadana()  && !mistni.getUloha().isHotova()){
                return "Na čo čakáš s robením tej úlohy, čo som ti zadal/a?";
            }
            if (mistni.getUloha().isZadana()  && mistni.getUloha().isHotova()) {
                return mistni.getDialog();
            }
        }
        return "Táto bytosť tu nie je, ako by si s ňou chcel hovoriť?";
    }

    /**
     * Metóda vracajúca názov príkazu, používaná na vyvolanie hráčom.
     * @return hovor
     */

    @Override
    public String getNazev() {
        return NAZEV;
    }
}
