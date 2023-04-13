package cz.vse.adventurahadz01.logika;

import cz.vse.adventurahadz01.observer.Observable;
import cz.vse.adventurahadz01.observer.Observer;

import java.util.HashSet;
import java.util.Set;

/**
 *  Class HerniPlan - třída představující mapu a stav adventury.
 * 
 *  Tato třída inicializuje prvky ze kterých se hra skládá:
 *  vytváří všechny prostory,
 *  propojuje je vzájemně pomocí východů 
 *  a pamatuje si aktuální prostor, ve kterém se hráč právě nachází.
 *
 *@author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Zuzana Hadzimová
 *@version    2023 upravená verzia pre účel semestrálnej práce
 */
public class HerniPlan implements Observable {

    private Set<Observer> observers = new HashSet<>();
    private Prostor aktualniProstor;
    private Prostor vyherniProstor;

    private Prostor zakazanyProstor;
    private Taska taska;
    private Set<Uloha> prijateUlohy;

    private Hra hra;


    /**
     *  Konstruktor který vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví halu.
     */
    public HerniPlan(Hra hra) {
        this.hra = hra;
        taska = new Taska(13);
        prijateUlohy = new HashSet<>();

        zalozProstoryHry();
    }

    /**
     *  Vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví domeček.
     */
    private void zalozProstoryHry() {
        // vytvářejí se jednotlivé prostory
        Prostor chodba = new Prostor("chodba","chodba, tu to všetko začína!", 280.0, 55.0);
        Prostor sklad = new Prostor("sklad", "v ktorej nájdeš niečo čo potrebuješ - v sklade.",203.0, 100.0);
        Prostor pracovna = new Prostor("pracovna","práčovňa. Prádla je tu fakt veľa.",46.0, 65.0);
        Prostor toalety = new Prostor("toalety","toalety. Tak tu veľa času stráviť nechceš!",131.0, 30.0);
        Prostor bahenneKupele = new Prostor("bahenne_kupele","bahenné kúpele. Povráva sa, že bahno z tohto miesta vylieči každého!",111.0, 175.0);
        Prostor prirodneKupele = new Prostor("prirodne_kupele","prírodné kúpele. Tak tu je to naozaj nádherné!",335.0, 210.0);
        Prostor jedalen = new Prostor("jedalen","jedalen. Niekto spravil kávy a čaje pre hostí.\nČo to tam je za zvláštne dvere?",497.0, 140.0);
        Prostor kuchyna = new Prostor("kuchyna","kuchyňa. Riad, riad a ďalší riad. Zachraňuje to len výborne vyzerajúce jedlo.",539.0, 30.0);
        Prostor zakazanaMiestnost = new Prostor("zakazana_miestnost","Tu tvoja cesta končí.",458.0, 20.0);
        Prostor vazenie= new Prostor("vazenie","väzenie, v ktorom sú uväznení tvoji rodičia.",351.0, 320.0);

        //vytváranie jednotlivých vecí
        Vec socha = new Vec("socha", false);
        Vec metla = new Vec("metla",true , "broom.gif");
        Vec mop= new Vec("mop",true , "mop.jpg");
        Vec handra= new Vec("handra",true , "handricka.gif");
        Vec pracka= new Vec("pracka",false );
        Vec susicka= new Vec("susicka",false );
        Vec oprataBielizen= new Vec("oprata_bielizen",true , "ciste.jpg");
        Vec spinavaBielizen= new Vec("spinava_bielizen",true, "spinave.gif" );
        Vec praciPrach= new Vec("praci_prach",true, "praci.gif" );
        Vec bahno= new Vec("bahno",true, "mud.png");
        Vec liecivaVoda= new Vec("lieciva_voda", true, "lieciva.gif");
        Vec bahennaVoda= new Vec("bahenna_voda", true, "bahno.gif");
        Vec exotickaRastlina= new Vec("exoticka_rastlina", true, "rastlina.gif");
        Vec posedenie= new Vec("drevenne_posedenie",false);
        Vec kava= new Vec("kava",true , "kava.gif");
        Vec caj= new Vec("caj",true , "caj.gif");
        Vec cukor= new Vec("cukor",true , "cukor.gif");
        Vec cucoriedkovyKolac= new Vec("cucoriedkovy_kolac",true, "cucoriedka.png");
        Vec malinovyKolac= new Vec("malinovy_kolac", true, "malina.png");
        Vec kura= new Vec("kura",true, "kura.png");



        //priradenie vecí do priestorov
        chodba.setVec(socha);
        sklad.setVec(metla);
        sklad.setVec(mop);
        sklad.setVec(handra);
        sklad.setVec(praciPrach);
        pracovna.setVec(pracka);
        pracovna.setVec(susicka);
        pracovna.setVec(oprataBielizen);
        bahenneKupele.setVec(bahno);
        bahenneKupele.setVec(spinavaBielizen);
        bahenneKupele.setVec(bahennaVoda);
        prirodneKupele.setVec(liecivaVoda);
        prirodneKupele.setVec(exotickaRastlina);
        prirodneKupele.setVec(posedenie);
        jedalen.setVec(kava);
        jedalen.setVec(caj);
        jedalen.setVec(cukor);
        kuchyna.setVec(cucoriedkovyKolac);
        kuchyna.setVec(malinovyKolac);
        kuchyna.setVec(kura);



        // přiřazují se průchody mezi prostory (sousedící prostory)
        chodba.setVychod(sklad);
        chodba.setVychod(pracovna);
        chodba.setVychod(jedalen);
        sklad.setVychod(chodba);
        pracovna.setVychod(toalety);
        pracovna.setVychod(chodba);
        pracovna.setVychod(bahenneKupele);
        toalety.setVychod(pracovna);
        bahenneKupele.setVychod(pracovna);
        bahenneKupele.setVychod(prirodneKupele);
        prirodneKupele.setVychod(bahenneKupele);
        prirodneKupele.setVychod(vazenie);
        jedalen.setVychod(kuchyna);
        jedalen.setVychod(chodba);
        jedalen.setVychod(zakazanaMiestnost);
        kuchyna.setVychod(jedalen);

        //pridavanie uloh
        Uloha zametanieChodby = new Uloha("zametanie","Trpaslíkovi vadí prach a špina, ktorá sa rozvíri, keď niekto prejde. \nPozametaj pomocou metly zem na chodbe. ", "No.. Mohla to byť aj lepšia práca ale uznám ti to. Tu máš časť kľúča. ","Nepozametala si!");
        Uloha umytieKupelov = new Uloha("umytie_kupelov","Zdravotníka už ma nebaví ako k nemu chodia bytosti s tým, že sa šmykli a ublížili si. \nUmy dlážku v bahenných kúpeľoch s mopom a pretri to tam handrou.", "Až sa to tu leskne! Časť kľúča ti dám s radosťou!","Tak toto umyté nie je ani zďaleka!");
        Uloha bielizen = new Uloha("bielizen","Prines špinavú bielizeň a niečo s čím ju môže chyžná vyprať.", "Neviem, čo ti tolko trvalo. Ale hlavné je, že môžem dať prať! Vezmi si toto, na tvojej ceste ti to pomôže.","Keby si hosť, chcela by si byť v špinavom?! Potrebujem oprať špinavé prádlo z kúpelov a na to potrebujem prací prach!");
        Uloha umytieToaliet = new Uloha("umytie_toaliet","Na toaletách je potreba premopovať dlážku a umyť s handrou všetky ostatné časti.", "Hmm.. No dobre. Je vidieť, že si sa snažila. Tu máš časť kľúča a pakuj sa!","Ako si sa tu chcela udržať, keď nevieš splniť jednu úlohu?");
        Uloha vodaLieciva = new Uloha("lieciva_voda","Zranený hosť má vážnu ranu. Prines mu liečivú vodu, ktorá mu s ňou pomôže.", "Zachránila si práve jeden život! Verím, že sa ti podarí zachrániť aj tie tvojich rodičov.","Nevidíš, že trpím? A aj tak mi nepomôžeš?");
        Uloha kolac = new Uloha("kolac","Hladný hosť má chuť na niečo sladké. Maliny mu moc nechutia, tak nájdi niečo iné a prines mu to.", "No ešte, že si mi to priniesla. Inak by som musel zjesť teba!","Nič sladké a dobré? vidím to tak že budem musieť zjesť teba...");
        Uloha horuciNapoj = new Uloha("napoj","Prines smädnému hosťovi čaj s cukrom. Pozor aby si nepriniesol kávu!", "Lepší čaj som ešte nemal!","Tak toto čaj s cukrom nebol!");

        //pridavanie bystosti
        Bytost yubaba = new Bytost("Yubaba", "Prečo nepracuješ? Zmizni mi z očí!");
        Bytost straznik = new Bytost("straznik", "Nech už tu robíš čokoľvek, nie že ťa uvidím pri zakázanej miestnosti mladá dáma!");
        Bytost tajomnyHost= new Bytost("tajomny_host", "hmmm?");
        Bytost sefkuchar = new Bytost("sefkuchar", "Hmm ešte chýba trochu soli.. A žabacích nožičiek. Možno trochu muších krídelok?");
        Bytost hladnyHost = new Bytost("hladny_host","Máš jedlo?");
        Bytost zranenyHost = new Bytost("zraneny_host","Au au. To tak bolí!");
        Bytost pracovnikKupelov = new Bytost("pracovnik_kupelov", "Stojíš tu a nič nerobíš. To nevidíš koľko máme práce? A toľko hostí!");
        Bytost zdravotnik = new Bytost("zdravotnik", "Bolí ťa niečo? S tým za mnou nechoď, už bez toho mám veľa práce.");
        Bytost chyzna = new Bytost("chyzna", "Takí nečistotní... Všetci sú takí nečistotní.");
        Bytost smadnyHost = new Bytost("smadny_host", "Zachvílku tu asi uschnem.");
        Bytost trpaslik = new Bytost("trpaslik","Aký som vám ja trpaslík?! Som priemerne vysoký mladý muž!!");

        //priradenie bytosti do miestnosti
        chodba.pridatBytost(yubaba);
        chodba.pridatBytost(trpaslik);
        sklad.pridatBytost(pracovnikKupelov);
        jedalen.pridatBytost(straznik);
        jedalen.pridatBytost(zranenyHost);
        kuchyna.pridatBytost(sefkuchar);
        pracovna.pridatBytost(chyzna);
        bahenneKupele.pridatBytost(hladnyHost);
        bahenneKupele.pridatBytost(zdravotnik);
        prirodneKupele.pridatBytost(smadnyHost);
        prirodneKupele.pridatBytost(tajomnyHost);

        //priradenie očakavaných veci k splneniu úloh
        horuciNapoj.setOcekavanaHodnota(new OcakavanieVeci(taska, caj,cukor));
        kolac.setOcekavanaHodnota(new OcakavanieVeci(taska, cucoriedkovyKolac));
        vodaLieciva.setOcekavanaHodnota(new OcakavanieVeci(taska, liecivaVoda));
        bielizen.setOcekavanaHodnota(new OcakavanieVeci(taska, spinavaBielizen,praciPrach));
        zametanieChodby.setOcekavanaHodnota(new OcakavanieUpratania(chodba));
        umytieToaliet.setOcekavanaHodnota(new OcakavanieUpratania(toalety));
        umytieKupelov.setOcekavanaHodnota(new OcakavanieUpratania(bahenneKupele));

        //priradenie bytostí k úlohám
        smadnyHost.setUloha(horuciNapoj);
        hladnyHost.setUloha(kolac);
        zranenyHost.setUloha(vodaLieciva);
        chyzna.setUloha(bielizen);
        trpaslik.setUloha(zametanieChodby);
        pracovnikKupelov.setUloha(umytieToaliet);
        zdravotnik.setUloha(umytieKupelov);

        //priradenie trestov k úlohám
        horuciNapoj.setTrestZaNesplneni(new TrestSmrt(hra));
        kolac.setTrestZaNesplneni(new TrestSmrt(hra));
        vodaLieciva.setTrestZaNesplneni(new TrestPokarhania());
        bielizen.setTrestZaNesplneni(new TrestPokarhania());
        zametanieChodby.setTrestZaNesplneni(new TrestPokarhania());
        umytieToaliet.setTrestZaNesplneni(new TrestPokarhania());
        umytieKupelov.setTrestZaNesplneni(new TrestPokarhania());


        aktualniProstor = chodba;  // hra začína  v chodbe Lázní
        vyherniProstor = vazenie;  //hra končí v miestnosti, kde sú uväznení rodičia - vo väzení
        zakazanyProstor = zakazanaMiestnost; //hra končí neuspešne v zakazanej miestnosti

        //určenie zamknutej miestnosti
        vazenie.zamknutMiestnost();
    }
    
    /**
     *  Metoda vrací odkaz na aktuální prostor, ve ktetém se hráč právě nachází
     *
     *@return     aktuální prostor
     */
    
    public Prostor getAktualniProstor() {
        return aktualniProstor;
    }
    
    /**
     *  Metoda nastaví aktuální prostor, používá se nejčastěji při přechodu mezi prostory
     *
     *@param  prostor nový aktuální prostor
     */
    public void setAktualniProstor(Prostor prostor) {
       aktualniProstor = prostor;
       notifyObservers();
    }

    /**
     * Metóda vracia odkaz na inventár - v tomto prípade tašku
     * @return inštancia triedy taška
     */
    public Taska getTaska() {
        return taska;
    }


    /**
     * Metóda vracajúca odkaz na výherný priestor
     * @return výherný priestor
     */
    public Prostor getVyherniProstor() {
        return vyherniProstor;
    }

    /**
     * Metóda vracajúca odkaz na zakázanú miestnosť
     * @return zakázaný priestor
     */
    public Prostor getZakazanyProstor() {
        return zakazanyProstor;
    }

    /**
     * Metóda vracajúca prijatú úlohu. Prechádza set prijatých úloh
     * @param nazov úlohy u ktorej sa zistuje či je prijatá
     * @return odkaz na úlohu, v prípade, že taká úloha je prijatá. Null ak taká úloha prijatá nie je
     */
    public Uloha vratPrijatuUlohu(String nazov){
        for (Uloha item : prijateUlohy) {
            if (item.getNazovUlohy().equals(nazov)){
                return item;
            }
        }
        return null;
    }

    /**
     * Metóda zabezpečujúca pridávanie prijatých úloh do Setu prijateUlohy
     * @param nazovUlohy ktorú do setu pridá
     */
    public void pridajPrijatuUlohu (Uloha nazovUlohy){prijateUlohy.add(nazovUlohy);}

    /**
     * Metóda odstraňujúca úlohu zo setu prijateUlohy
     * @param nazovUlohy ktorá sa odstráni zo setu
     */
    public void odstranUlohu(Uloha nazovUlohy){
        prijateUlohy.remove(nazovUlohy);
    }

    /**
     * Metóda vracajúca text s prijatými úlohami a infromácie o nich(popis)
     * @return text s úlohami a popisom úloh
     */
    public String infoKUloham(){
        StringBuilder mistni = new StringBuilder("Prijaté ulohy: \n");
        if ( prijateUlohy.isEmpty()) {
            return "Nemáš prijaté žiadne úlohy.";
        }
        for (Uloha item : prijateUlohy){
            mistni.append(item.getNazovUlohy()).append(": \n");
            mistni.append(item.getPopisUlohy()).append('\n');
        }

        return mistni.toString();
    }

    /**
     * Metóda register - Registrácia observera
     * @param observer
     */
    @Override
    public void register(Observer observer) {
        observers.add(observer);

    }

    /**
     * Metóda notifyObservers - notifikuje (updatuje) observera
     */
    @Override
    public void notifyObservers() {
        for (Observer observer: observers){
            observer.update();
        }

    }

    /**
     * Metóda unregistered.
     * @param observer
     */
    @Override
    public void unregistered(Observer observer) {

    }
}
