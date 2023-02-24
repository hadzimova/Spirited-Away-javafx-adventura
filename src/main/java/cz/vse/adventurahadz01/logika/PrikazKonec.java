package cz.vse.adventurahadz01.logika;

/**
 *  Třída PrikazKonec implementuje pro hru příkaz konec.
 *  Tato třída je součástí jednoduché textové hry.
 *  
 *@author     Jarmila Pavlickova, Zuzana Hadzimová
 *@version    2023 upravená verzia pre účel semestrálnej práce
 *  
 */

class PrikazKonec implements IPrikaz {

    private static final String NAZEV = "koniec";

    private Hra hra;

    /**
     *  Konstruktor třídy
     *  
     *  @param hra odkaz na hru, která má být příkazem konec ukončena
     */    
    public PrikazKonec(Hra hra) {
        this.hra = hra;
    }

    /**
     * V případě, že příkaz má jen jedno slovo "konec" hra končí(volá se metoda setKonecHry(true))
     * jinak pokračuje např. při zadání "konec a".
     * 
     * @return zpráva, kterou vypíše hra hráči
     */

    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length > 0) {
            return "Čo chceš ukončiť?";
        }
        else {
            hra.setKonecHry(true);
            return "Hra bola ukončená príkazom koniec";
        }
    }

    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @return nazev prikazu - konec
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }
}
