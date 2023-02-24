package cz.vse.adventurahadz01.logika;

/**
 * Trieda PrikazZober- Definuje príkaz zobrania veci z miestnosti(priestoru) do inventára.
 *
 * @author Zuzana Hadzimová
 * @version január 2023
 */
public class PrikazZober implements IPrikaz{



    private static final String NAZEV = "zober";

    private HerniPlan herniPlan;

    /**
     * Konštruktor triedy PrikazSeber.
     * @param herniPlan prijíma herní plán
     */
    public PrikazZober(HerniPlan herniPlan){
        this.herniPlan = herniPlan;
    }

    /**
     * Metóda zobratia veci, ošetrenie prípadu použitia nesprávneho parametra.
     * Odoberie vec z miestnosti a pridá ju do tašky
     * @param parametry - názov veci, ktorú chceme zobrať
     * @return text, ktorý sa zobrazí hráčovi
     */
    @Override
    public String provedPrikaz(String... parametry) {  //... 0 až n
        if (parametry.length==0){
            return "Neviem čo mám zobrať, musíš zadať názov veci!";
        } else if (parametry.length > 1) {
            return "Nie je možné zobrať naraz viac ako jednu vec.";
        }
        String nazevVeci = parametry[0];
        Prostor aktualniProstor = herniPlan.getAktualniProstor();

        if(!aktualniProstor.vecExistuje(nazevVeci)){
            return "Vec s názvom " + nazevVeci + " nepoznám.";
        }
        Vec vec = aktualniProstor.ziskejVec(nazevVeci);
        if(vec.isPrenositelna()){
            if (herniPlan.getTaska().vlozVec(vec)){
                aktualniProstor.odstranVec(nazevVeci);
                return "Vec " + nazevVeci + " si zobrala z miestnosti a vložila do tašky.";
            }
            return "Vec " + nazevVeci + " nebolo možné zobrať, pretože máš plnú tašku.";
        }

        return "Túto vec nejde preniesť.";
    }

    /**
     * Vracia názov príkazu.
     * @return názov príkazu - zober
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }
}
