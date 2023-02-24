package cz.vse.adventurahadz01.logika;
/**
 *  Trieda PrikazUlohy implementuje pre hru príkaz ulohy.
 *
 *@author     Zuzana Hadzimová
 *@version    január 2023
 */
public class PrikazUlohy implements IPrikaz{

    private static final String NAZEV = "ulohy";

    private HerniPlan herniPlan;
    /**
     * Konštrukor triedy PrikazUlohy.
     * @param herniPlan prijíma herni plán
     */
    public PrikazUlohy(HerniPlan herniPlan) {
        this.herniPlan = herniPlan;
    }

    /**
     * Metóda vracajúca informácie o prijatých úlohách.
     * @param parametry počet parametrů závisí na konkrétním příkazu.
     * @return vracia text zobrazený hráčovi - úlohy a ich popis
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return herniPlan.infoKUloham();
        }
        return "Spomal spomal. Tento príkaz nepoužívaš s inými slovami!";
    }
    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání).
     *
     *  @return nazev prikazu - ulohy
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }
}
