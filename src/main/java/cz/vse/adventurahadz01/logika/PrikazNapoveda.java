package cz.vse.adventurahadz01.logika;

/**
 *  Třída PrikazNapoveda implementuje pro hru příkaz napoveda.
 *  Tato třída je součástí jednoduché textové hry.
 *  
 *@author     Jarmila Pavlickova, Luboš Pavlíček, Zuzana Hadzimová
 *@version    2023 upravená verzia pre účel semestrálnej práce
 *  
 */
class PrikazNapoveda implements IPrikaz {
    
    private static final String NAZEV = "napoveda";
    private SeznamPrikazu platnePrikazy;
    
    
     /**
    *  Konstruktor třídy
    *  
    *  @param platnePrikazy seznam příkazů,
    *                       které je možné ve hře použít,
    *                       aby je nápověda mohla zobrazit uživateli. 
    */    
    public PrikazNapoveda(SeznamPrikazu platnePrikazy) {
        this.platnePrikazy = platnePrikazy;
    }
    
    /**
     *  Vrací základní nápovědu po zadání příkazu "napoveda". Nyní se vypisuje
     *  vcelku primitivní zpráva a seznam dostupných příkazů.
     *  
     *  @return napoveda ke hre
     */
    @Override
    public String provedPrikaz(String... parametry) {
        return "Na vyslobodenie tvojich rodičov potrebuješ získať 7 častí kľuča.\n"
        + "Tie získaš za splnenie úloh od hostí. Následne nimi musíš otvoriť väzenie \n"
        + "v prírodných kúpeloch. \n"
        + "\n"
        + "Pri tvojej ceste k oslobodeniu rodičov môžeš použiť tieto príkazy:\n"
        + platnePrikazy.vratNazvyPrikazu();
    }
    
     /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @return nazev prikazu - napoveda
     */
    @Override
      public String getNazev() {
        return NAZEV;
     }

}
