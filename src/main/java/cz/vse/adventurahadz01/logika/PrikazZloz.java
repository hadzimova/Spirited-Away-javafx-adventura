package cz.vse.adventurahadz01.logika;
/**
 *  Trieda PrikazZloz implementuje pre hru príkaz zloz.
 *
 *@author     Zuzana Hadzimová
 *@version    január 2023
 */
public class PrikazZloz implements IPrikaz{

    public static final String NAZEV= "zloz";

    HerniPlan herniPlan;

    /**
     * Konštruktor triedy PrikazZloz.
     * @param herniPlan
     */
    public PrikazZloz(HerniPlan herniPlan) {
        this.herniPlan = herniPlan;
    }

    /**
     * Metóda vytvárajúca kľúč zo 7 častí kľúča. Odoberie 7 častí z tašky a pridá kľúč.
     * @param parametry počet parametrů závisí na konkrétním příkazu.
     * @return text zobrazený hráčovi
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length==0){
            return "Čo mám zložiť? Čím chceš odomknúť miestnosť, kde máš rodičov?";
        } else if (parametry.length > 1) {
            return "Neviem zložiť viac vecí naraz!";
        }
        Vec kluc = new Vec("kluc",true);
         if ("kluc".equals(parametry[0])) {
             int castKlucaCount = herniPlan.getTaska().pocetVeci("cast_klucu");
             if (castKlucaCount < 7) {
                 return "Nemáš dostatok kusov kľúča na zloženie!";
             }
             herniPlan.getTaska().vlozVec(kluc);
             for(int i =0; i <7; i++) {
                 herniPlan.getTaska().odoberVec("cast_klucu");
             }
             return "Zložil si klúč. Teraz môžeš zachrániť rodičov!";
         }
         return "Túto vec zložiť nevieš";
    }
    /**
     * Vracia názov príkazu.
     * @return názov príkazu - zloz
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }
}
