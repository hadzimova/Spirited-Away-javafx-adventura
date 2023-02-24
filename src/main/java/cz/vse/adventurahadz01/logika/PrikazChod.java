package cz.vse.adventurahadz01.logika;

/**
 *  Třída PrikazChod implementuje pro hru příkaz chod.
 *  Tato třída je součástí jednoduché textové hry.
 *  
 *@author     Jarmila Pavlickova, Luboš Pavlíček, Zuzana Hadzimová
 *@version    2023 upravená verzia pre účel semestrálnej práce
 */
class PrikazChod implements IPrikaz {
    private static final String NAZEV = "chod";
    private Hra hra;
    
    /**
    *  Konstruktor třídy
    *  
    *  @param hra Hra
    */    
    public PrikazChod(Hra hra) {
        this.hra = hra;
    }

    /**
     *  Provádí příkaz "jdi". Zkouší se vyjít do zadaného prostoru. Pokud prostor
     *  existuje, vstoupí se do nového prostoru. Pokud zadaný sousední prostor
     *  (východ) není, vypíše se chybové hlášení.
     *
     *@param parametry - jako  parametr obsahuje jméno prostoru (východu),
     *                         do kterého se má jít.
     *@return zpráva, kterou vypíše hra hráči
     */ 
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            // pokud chybí druhé slovo (sousední prostor), tak ....
            return "Neviem, kam mám ísť. Musíš zadať meno východu";
        }

        String smer = parametry[0];

        // zkoušíme přejít do sousedního prostoru
        Prostor sousedniProstor = hra.getHerniPlan().getAktualniProstor().vratSousedniProstor(smer);

        if (sousedniProstor == null) {
            return "Tam sa odtialto nedostanem!";
        } else if (sousedniProstor.isZamknuta()) {
            return "Táto miestnosť je zamknutá. Pred vstupom ju musíš odomknúť kľúčom.";
        }
            hra.getHerniPlan().setAktualniProstor(sousedniProstor);
            if (sousedniProstor.equals(hra.getHerniPlan().getVyherniProstor())) {
                hra.setKonecHry(true);
                hra.setEpilog("Úžasná práca! Úspešne si vyslobodila rodičov. Gratulujem!");
                return "";
            } else if (sousedniProstor.equals(hra.getHerniPlan().getZakazanyProstor())) {
                hra.setEpilog("Bolo ti treba chodiť do zakázanej miestnosti? Stráždnik ťa odhalil a Yubaba ťa vyhodila z Lázní. \nSvojich rodičov už nikdy nevyslobodíš!");
                hra.setKonecHry(true);
                return "";
            }
            return sousedniProstor.dlouhyPopis();
    }
    
    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání).
     *  
     *  @return nazev prikazu - chod
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }

}
