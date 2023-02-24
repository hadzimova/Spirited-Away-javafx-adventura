package cz.vse.adventurahadz01.logika;
/**
 *  Trieda PrikazPrijmiUlohu implementuje pre hru príkaz prijmi.
 *
 *@author     Zuzana Hadzimová
 *@version    január 2023
 */
public class PrikazPrijmiUlohu implements IPrikaz{
    public static final String NAZEV = "prijmi";

    HerniPlan herniPlan;
    /**
     * Konštrukor triedy.
     * @param herniPlan prijíma herni plán
     */
    public PrikazPrijmiUlohu(HerniPlan herniPlan) {
        this.herniPlan = herniPlan;
    }

    /**
     * Metóda prijatia úlohy. Zmení hodnotu zadana na true. Postava prijímajúca úlohu sa musí nachádzať v rovnakej miestnosti
     * ako bytosť ku ktorej je táto úloha priradená.
     * @param parametry počet parametrů závisí na konkrétním příkazu.
     * @return vracia text zobrazený hráčovi
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Akú úlohu mám prijať?";
        } else if (parametry.length > 1) {
            return "Nemôžem prijať viac úloh naraz.";
        }

        Uloha mistni = null;
        if (herniPlan.getAktualniProstor().vratUlohuVPriestore(parametry[0]) != null) {
            mistni = herniPlan.getAktualniProstor().vratUlohuVPriestore(parametry[0]);
        }
        if(mistni == null){
            return "Nezadal si platný názov  úlohy!";
        }
        if (herniPlan.getAktualniProstor().jeUlohaZadanaVMiestnosti(mistni)) {
            if (mistni.isZadana()) {
                return "Túto úlohu si už prijala.";
            } else if (!mistni.isZadana()) {
                mistni.setZadana(true);
                herniPlan.pridajPrijatuUlohu(mistni);
                return "Prijal si ulohu: " + mistni.getNazovUlohy();
            }
        }

        return "V tejto miestnosti nemôžeš prijať túto úlohu";
    }

    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání).
     *  @return nazev prikazu - prijmi
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }
}
