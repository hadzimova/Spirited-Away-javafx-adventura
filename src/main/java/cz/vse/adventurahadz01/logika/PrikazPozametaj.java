package cz.vse.adventurahadz01.logika;
/**
 *  Trieda PrikazPozametaj implementuje pre hru príkaz pozametaj.
 *
 *@author     Zuzana Hadzimová
 *@version    január 2023
 */
public class PrikazPozametaj implements IPrikaz{
    public static final String NAZEV = "pozametaj";

    private HerniPlan herniPlan;
    /**
     * Konštrukor triedy PrikazPozametaj.
     * @param herniPlan prijíma herni plán
     */
    public PrikazPozametaj(HerniPlan herniPlan) {
        this.herniPlan = herniPlan;
    }

    /**
     * Metóda nastavujúca hodnotu Upratana na true v prípade správnej miestnosti a existencie metly v tašky.
     * @param parametry počet parametrů závisí na konkrétním příkazu.
     * @return vracia text zobrazený hráčovi
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Čo mám pozametať?.";
        } else if (parametry.length > 1) {
            return "Ako mám podľa teba pozametať viac miestností naraz?";

        }
        if (herniPlan.getAktualniProstor().getNazev().equals(parametry[0])) {
            if (herniPlan.getAktualniProstor().getNazev().equals("chodba")) {
                if (herniPlan.getAktualniProstor().isUpratana()) {
                    return "Načo to budeš zametať dvakrát?";
                }
                if (herniPlan.getTaska().zoznamVeciVTaske().contains("metla")) {
                    herniPlan.getAktualniProstor().setUpratana(true);
                    return "Miestnosť bola úspešne pozametaná.";
                }
                return "Nemáš metlu, ako chceš zametať?";
            }
            return "Takúto miestnosť pozametať nemôžeš.";
        }
        return "Ako by si to chcel pozametať, keď tam nie si?";
    }
    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání).
     *
     *  @return nazev prikazu - pozametaj
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }
}
