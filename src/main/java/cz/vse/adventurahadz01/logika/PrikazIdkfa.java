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
            herniPlan.getTaska().setKapacitaTasky(herniPlan.getVeciVHre().size());
            for(Vec vec: herniPlan.getVeciVHre()){
                herniPlan.getTaska().vlozVec(vec);
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
