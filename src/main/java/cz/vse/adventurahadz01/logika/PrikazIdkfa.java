package cz.vse.adventurahadz01.logika;

public class PrikazIdkfa implements IPrikaz{
    public static final String NAZEV = "idkfa";

    private HerniPlan herniPlan;

    public PrikazIdkfa(HerniPlan herniPlan) {
        this.herniPlan = herniPlan;
    }

    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            herniPlan.getTaska().setKapacitaTasky(herniPlan.getVeciVHre().size()+herniPlan.getUlohyVHre().size()+1);
            for(Vec vec: herniPlan.getVeciVHre()){
                herniPlan.getTaska().vlozVec(vec);
            }
            for (Uloha kluc: herniPlan.getUlohyVHre()){
                herniPlan.getTaska().vlozVec(kluc.getUspesneSpleni());
            }
            return "Zobral si všetky veci z hry.";
        }
        return "Neviem o čo sa tu pokúšaš!";
    }

    @Override
    public String getNazev() {
        return NAZEV;
    }
}
