package cz.vse.adventurahadz01.logika;

/**
 * Trieda PrikazPust - definuje príkaz pustenia vecí - pust.
 * @author Zuzana Hadzimová
 * @version január 2023
 */
public class PrikazPust implements IPrikaz{

    public static final String NAZEV= "pust";

    private HerniPlan herniPlan;

    /**
     * Konštruktor triedy PrikazPust
     * @param herniPlan
     */
    public PrikazPust(HerniPlan herniPlan){
        this.herniPlan = herniPlan;
    }

    /**
     * Metóda pustenia 1 veci z tašky - odstráni sa z tašky a pridá do aktuálneho priestoru.
     * @param parametry počet parametrů závisí na konkrétním příkazu.
     * @return text zobrazený hráčovi
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length==0){
            return "Neviem čo mám pustiť, musíš zadať názov veci.";
        } else if (parametry.length > 1) {
            return "Nie je možné pustiť viac ako jednu vec naraz.";
        }
        Vec mistni=herniPlan.getTaska().vratVec(parametry[0]);
        if (mistni == null){
                return parametry[0] + " nie je v batohu!";
        }

        if(herniPlan.getTaska().odoberVec(parametry[0])){
            herniPlan.getAktualniProstor().setVec(mistni);
            return "Vec " + mistni.getNazev() + " si pustil. Nabudúce ju nájdeš v tejto miestnosti";
        }
        return "Nemôžeš pustiť vec, ktorú v inventári nemáš!";
    }

    /**
     * Metóda vracajúca názov príkazu.
     * @return názov príkazu - pust
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }
}
