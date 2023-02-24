package cz.vse.adventurahadz01.logika;
/**
 *  Trieda PrikazOdovzdajUlohu implementuje pre hru príkaz odovzdaj.
 *
 *@author     Zuzana Hadzimová
 *@version    január 2023
 */
public class PrikazOdovzdajUlohu implements IPrikaz{

    public static final String NAZEV = "odovzdaj";
    private Hra hra;

    /**
     * Konštruktor triedy PrikazOdovzdajUlohu.
     * @param hra prijíma parameter hra
     */
    public PrikazOdovzdajUlohu(Hra hra) {
        this.hra = hra;
    }

    /**
     * Metóda realizujúca odovzdávanie dokončených úloh.
     * @param parametry počet parametrů závisí na konkrétním příkazu.
     * @return vracia text zobrazený hráčovi
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length==0){
            return "Musíš zadať názov úlohy, ktorú chceš odovzdať!";
        } else if (parametry.length > 1) {
            return "Odovzdať môžes len jednu úlohu!";
        }

        Uloha mistni = null;
        if (hra.getHerniPlan().vratPrijatuUlohu(parametry[0]) != null) {
                mistni = hra.getHerniPlan().vratPrijatuUlohu(parametry[0]);
        }
        if(mistni == null){
           return "Nezadal si platný názov prijatej úlohy!";
        }

        if (hra.getHerniPlan().getAktualniProstor().jeUlohaZadanaVMiestnosti(mistni)) {
            if (mistni.getOcekavanaHodnota().skontrolujSplnenieUlohy()) {
                hra.getHerniPlan().odstranUlohu(mistni);
                mistni.setHotova(true);
                hra.getHerniPlan().getTaska().vlozVec(mistni.getUspesneSpleni());
                return mistni.getUspesnyDialog();
            }

            mistni.getTrestZaNesplneni().trestZaNesplneni();
            return mistni.getNeuspesnyDialog();
        }
        return "Musíš prísť do miestnosti, kde sa nachádza bytosť, ktorá ti úlohu zadala.";
    }
    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání).
     *
     *  @return nazev prikazu - odovzdaj
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }
}
