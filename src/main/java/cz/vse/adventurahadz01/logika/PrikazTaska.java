package cz.vse.adventurahadz01.logika;
/**
 * Trieda PrikazOtvorTasku - definuje príkaz zobrazenia obsahu tasky.
 * @author Zuzana Hadzimová
 * @version január 2023
 */
public class PrikazTaska implements IPrikaz{

    public static final String NAZEV = "taska";

    private HerniPlan herniPlan;

    /**
     * Kkonštrukor triedy PrikazTaska.
     * @param herniPlan prijíma herni plán
     */
    public PrikazTaska(HerniPlan herniPlan) {
        this.herniPlan = herniPlan;
    }

    /**
     * Metóda realizáciu zobrazenia obsahu tašky(inventáru)
     * @param parametry počet parametrů závisí na konkrétním příkazu.
     * @return vracia text informujúci o obsahu tašky
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Obsah tašky: " + herniPlan.getTaska().zoznamVeciVTaske();
        }
        return "Spomal spomal. Tento príkaz nepoužívaš s inými slovami!";
    }

    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání).
     *
     *  @return nazev prikazu - taska
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }
}
