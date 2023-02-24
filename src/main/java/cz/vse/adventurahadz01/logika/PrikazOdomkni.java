package cz.vse.adventurahadz01.logika;
/**
 *  Trieda PrikazOdomkni implementuje pre hru príkaz odomkni.
 *
 *@author     Zuzana Hadzimová
 *@version    január 2023
 */
public class PrikazOdomkni implements IPrikaz{

    public static final String NAZEV = "odomkni";

    HerniPlan herniPlan;

    /**
     * konštruktor triedy PrikazOdomkni.
     * @param herniPlan prijíma herný plán
     */
    public PrikazOdomkni(HerniPlan herniPlan) {
        this.herniPlan=herniPlan;
    }

    /**
     * Metoda, ktorá v prípade prítomnosti kľúča a správnej miestnosti odomkne zamknuté dvere.
     * @param parametry počet parametrů závisí na konkrétním příkazu.
     * @return správa, ktorú vypíše hráčovi
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length==0){
            return "Čo mám odomknúť?";
        } else if (parametry.length>1) {
            return "Ešte nech odomknem aj neodomknutelné nie? Vyber si jednu zamknutú miestnosť. Myslím, že by si mala vedieť ktorú.";
        }

        for(Prostor miestnost: herniPlan.getAktualniProstor().vychodyZamknutychList()){
            if (parametry[0].equals(miestnost.getNazev())&& herniPlan.getTaska().zoznamVeciVTaske().contains("kluc")){
                herniPlan.getTaska().odoberVec("kluc");
                miestnost.odomknutMiestnost();
                return "Odomkla si väzenie, kde sa nachádzajú tvoji rodičia!";
            }
            return "Skontroluj či chceš odomknúť správnu miestnosť a či máš kľúč.";
        }
        return "V tejto miestnosti žiadne iné miestnosti odomknúť nemôžeš.";
    }

    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání).
     *
     *  @return nazev prikazu - odomkni
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }
}
