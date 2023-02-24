package cz.vse.adventurahadz01.logika;
/**
 *  Trieda PrikazUmy implementuje pre hru príkaz umy.
 *
 *@author     Zuzana Hadzimová
 *@version    január 2023
 */
public class PrikazUmy implements IPrikaz{

    public static final String NAZEV = "umy";

    private HerniPlan herniPlan;

    public PrikazUmy(HerniPlan herniPlan) {
        this.herniPlan = herniPlan;
    }

    /**
     * Metóda realizujúca zmenu hodnoty upratana v danej miestnosti na true v prípade spravnej
     * miestnosti a existencie mopu a handry v inventári
     * @param parametry počet parametrů závisí na konkrétním příkazu.
     * @return vracia text zobrazený hráčovi
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length==0){
            return "Ako mám vedieť čo mám umyť? Zadaj názov veci!";
        } else if (parametry.length > 1) {
            return "Myslíš si, že mám sto rúk, aby som mohla umyť tolko vecí naraz?";
        }

        if(herniPlan.getAktualniProstor().getNazev().equals(parametry[0])){
            if(herniPlan.getAktualniProstor().getNazev().equals("bahenne_kupele") || herniPlan.getAktualniProstor().getNazev().equals("toalety")) {
                if (herniPlan.getAktualniProstor().isUpratana()) {
                    return "Nevidíš snaď, že je to tu už umyté?";
                }
                if (herniPlan.getTaska().zoznamVeciVTaske().contains("mop") && herniPlan.getTaska().zoznamVeciVTaske().contains("handra")) {
                    herniPlan.getAktualniProstor().setUpratana(true);
                    return "Takáto čisté to tu ešte nebolo! Celé sa to tu leskne!";
                }
                return "Nemáš potrebné veci, ako to chceš umyť?";
            }
            return "Túto miestnosť umyť nemôžeš!";
        }
        return "Si si istý, že si v správnej miestnosti?";
    }
    /**
     * Vracia názov príkazu.
     * @return názov príkazu - umy
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }
}
