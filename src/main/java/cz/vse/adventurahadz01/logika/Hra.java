package cz.vse.adventurahadz01.logika;

import javafx.scene.control.Alert;


/**
 *  Třída Hra - třída představující logiku adventury.
 * 
 *  Toto je hlavní třída  logiky aplikace.  Tato třída vytváří instanci třídy HerniPlan, která inicializuje mistnosti hry
 *  a vytváří seznam platných příkazů a instance tříd provádějící jednotlivé příkazy.
 *  Vypisuje uvítací a ukončovací text hry.
 *  Také vyhodnocuje jednotlivé příkazy zadané uživatelem.
 *
 *@author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Zuzana Hadzimová
 *@version    2023 upravená verzia pre účel semestrálnej práce
 */

public class Hra implements IHra {
    private SeznamPrikazu platnePrikazy;    // obsahuje seznam přípustných příkazů
    private HerniPlan herniPlan;
    private boolean konecHry = false;

    private String epilog = "Ďakujem, že ste si zahrali!";

    private static Hra singleton= new Hra();

    public static Hra getSingleton() {
        return singleton;
    }

    public static Hra restartHry() {
        singleton = new Hra();
        return singleton;
    }

    /**
     *  Vytváří hru a inicializuje místnosti (prostřednictvím třídy HerniPlan) a seznam platných příkazů.
     */
    public Hra() {
        herniPlan = new HerniPlan(this);
        platnePrikazy = new SeznamPrikazu();
        platnePrikazy.vlozPrikaz(new PrikazNapoveda(platnePrikazy));
        platnePrikazy.vlozPrikaz(new PrikazChod(this));
        platnePrikazy.vlozPrikaz(new PrikazKonec(this));
        platnePrikazy.vlozPrikaz(new PrikazZober(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazPust(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazTaska(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazPozametaj(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazUmy(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazOdovzdajUlohu(this));
        platnePrikazy.vlozPrikaz(new PrikazUlohy(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazHovor(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazZloz(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazOdomkni(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazPrijmiUlohu(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazIdkfa(herniPlan));
    }

    /**
     *  Vrátí úvodní zprávu pro hráče.
     */
    public String vratUvitani() {
        return "Vitajte v hre Spirited away!\n" +
                "Si Chihiro, 10-ročné japonské dievča, ktoré sa spolu s rodičmi po ceste stratilo a dorazilo do podivného tunela.\n" +
                "Zo zvedavosti ste ním prešli a bez toho, aby ste vedeli ako, dostali ste sa do ríše duchov v tajomných " +
                "čarovných kúpeľoch zlej čarodejnice Yubaby.\n" +
                "Za narušenie pokoja čarodejnica zajala tvojich rodičov. Tebe sa podarilo utiecť, no jediný spôsob akým ich môžeš " +
                "zachrániť je zamestnať sa v čarodejniciných kúpeľoch a získať všetkých 7 častí kľúča k miestnosti, kde sú uväznení tvoji rodičia.\n" +
                "V kúpeľoch sa nachádzajú hostia a zamestnanci, ktorí ti dávajú časti kľúča výmenou za splnenie úloh.\n" +
                "Ale pozor, hostí týchto kúpeľov nechceš nahnevať! Mohlo by to znamenať tvoj definitívny koniec.\n" +"\n"+
                "Použi príkaz \"napoveda\", aby si sa dozvedel viac.\n" +
               "\n" +
               herniPlan.getAktualniProstor().dlouhyPopis() +"\n";
    }
    
    /**
     *  Vrátí závěrečnou zprávu pro hráče.
     */
    public String vratEpilog() {
        return epilog;
    }
    
    /** 
     * Vrací true, pokud hra skončila.
     */
     public boolean konecHry() {
        return konecHry;
    }

    /**
     *  Metoda zpracuje řetězec uvedený jako parametr, rozdělí ho na slovo příkazu a další parametry.
     *  Pak otestuje zda příkaz je klíčovým slovem  např. jdi.
     *  Pokud ano spustí samotné provádění příkazu.
     *
     *@param  radek  text, který zadal uživatel jako příkaz do hry.
     *@return          vrací se řetězec, který se má vypsat na obrazovku
     */
     public String zpracujPrikaz(String radek) {
        String [] slova = radek.split("[ \t]+");
        String slovoPrikazu = slova[0];
        String []parametry = new String[slova.length-1];
        for(int i=0 ;i<parametry.length;i++){
           	parametry[i]= slova[i+1];  	
        }
        String textKVypsani=" .... ";
        if (platnePrikazy.jePlatnyPrikaz(slovoPrikazu)) {
            IPrikaz prikaz = platnePrikazy.vratPrikaz(slovoPrikazu);
            textKVypsani = prikaz.provedPrikaz(parametry);
            if (konecHry == true){
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Koniec Hry");
                a.setHeaderText(vratEpilog());
                a.show();
            }
        }
        else {
            textKVypsani="Neviem čo tým myslíš? Tento príkaz nepoznám. ";
        }
        return textKVypsani;
    }
    
    
     /**
     *  Nastaví, že je konec hry, metodu využívá třída PrikazKonec,
     *  mohou ji použít i další implementace rozhraní Prikaz.
     *  
     *  @param  konecHry  hodnota false= konec hry, true = hra pokračuje
     */
    void setKonecHry(boolean konecHry) {
        this.konecHry = konecHry;
    }
    
     /**
     *  Metoda vrátí odkaz na herní plán, je využita hlavně v testech,
     *  kde se jejím prostřednictvím získává aktualní místnost hry.
     *  
     *  @return     odkaz na herní plán
     */
     public HerniPlan getHerniPlan(){
        return herniPlan;
     }

    /**
     * Metóda zabezpečujúca zmenu/nastavenie Epilógu.
     * @param epilog konečná správa zobrazená hráčovi
     */
    public void setEpilog(String epilog) {
        this.epilog = epilog;
    }
}

