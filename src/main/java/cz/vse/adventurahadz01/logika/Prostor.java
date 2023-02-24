package cz.vse.adventurahadz01.logika;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Trida Prostor - popisuje jednotlivé prostory (místnosti) hry
 *
 * Tato třída je součástí jednoduché textové hry.
 *
 * "Prostor" reprezentuje jedno místo (místnost, prostor, ..) ve scénáři hry.
 * Prostor může mít sousední prostory připojené přes východy. Pro každý východ
 * si prostor ukládá odkaz na sousedící prostor.
 *
 * @author Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Zuzana Hadzimová
 * @version 2023 upravená verzia pre účel semestrálnej práce
 */
public class Prostor {

    private String nazev;
    private String popis;
    private Set<Prostor> vychody;   // obsahuje sousední místnosti

    private Set<Bytost> seznamBytosti;

    private boolean upratana;

    private boolean zamknuta;

    private final Map<String, Vec> veci = new HashMap<>();   //vec-string= klic, unikatni hodnota(napr klic = instance koste = string koste)



    /**
     * Vytvoření prostoru se zadaným popisem, např. "kuchyň", "hala", "trávník
     * před domem"
     *
     * @param nazev nazev prostoru, jednoznačný identifikátor, jedno slovo nebo
     * víceslovný název bez mezer.
     * @param popis Popis prostoru.
     */
    public Prostor(String nazev, String popis) {
        this.nazev = nazev;
        this.popis = popis;
        vychody = new HashSet<>();
        seznamBytosti = new HashSet<>();
        upratana= false;
        zamknuta = false;
    }

    /**
     * Definuje východ z prostoru (sousední/vedlejsi prostor). Vzhledem k tomu,
     * že je použit Set pro uložení východů, může být sousední prostor uveden
     * pouze jednou (tj. nelze mít dvoje dveře do stejné sousední místnosti).
     * Druhé zadání stejného prostoru tiše přepíše předchozí zadání (neobjeví se
     * žádné chybové hlášení). Lze zadat též cestu ze do sebe sama.
     *
     * @param vedlejsi prostor, který sousedi s aktualnim prostorem.
     *
     */
    public void setVychod(Prostor vedlejsi) {
        vychody.add(vedlejsi);
    }

    /**
     * Metóda priradzujúca ved do určitého priestoru.
     * @param vec
     */
    public void setVec(Vec vec){
        veci.put(vec.getNazev(),vec);
    }

    /**
     * Metoda zisťujúca či vec s daným názvom v hre existuje.
     * @param nazevVeci
     * @return
     */
    public boolean vecExistuje(String nazevVeci){
        return veci.containsKey(nazevVeci);
    }

    /**
     * možnost odstranenia veci z priestoru.
     * @param nazevVeci
     * @return instancia veci
     */
    public Vec odstranVec(String nazevVeci){
        return veci.remove(nazevVeci);
    }

    /**
     * Metóda zobratia veci ale neodstránenia z mapy.
     * @param nazevVeci
     * @return instancia veci
     */
    public Vec ziskejVec(String nazevVeci){
        return veci.get(nazevVeci);
    }

    /**
     * Metóda na nastavenie boolean hodnoty priestoru upratana.
     * @param upratana boolean
     */
    public void setUpratana(boolean upratana) {
        this.upratana = upratana;
    }

    /**
     * Metóda získania boolean hodnoty upratana pre daný priestor.
     * @return hodnotu upratana
     */
    public boolean isUpratana() {
        return upratana;
    }

    /**
     * Metoda equals pro porovnání dvou prostorů. Překrývá se metoda equals ze
     * třídy Object. Dva prostory jsou shodné, pokud mají stejný název. Tato
     * metoda je důležitá z hlediska správného fungování seznamu východů (Set).
     *
     * Bližší popis metody equals je u třídy Object.
     *
     * @param o object, který se má porovnávat s aktuálním
     * @return hodnotu true, pokud má zadaný prostor stejný název, jinak false
     */  
      @Override
    public boolean equals(Object o) {
        // porovnáváme zda se nejedná o dva odkazy na stejnou instanci
        if (this == o) {
            return true;
        }
        // porovnáváme jakého typu je parametr 
        if (!(o instanceof Prostor)) {
            return false;    // pokud parametr není typu Prostor, vrátíme false
        }
        // přetypujeme parametr na typ Prostor 
        Prostor druhy = (Prostor) o;

        //metoda equals třídy java.util.Objects porovná hodnoty obou názvů. 
        //Vrátí true pro stejné názvy a i v případě, že jsou oba názvy null,
        //jinak vrátí false.

       return (Objects.equals(this.nazev, druhy.nazev));
    }

    /**
     * metoda hashCode vraci ciselny identifikator instance, ktery se pouziva
     * pro optimalizaci ukladani v dynamickych datovych strukturach. Pri
     * prekryti metody equals je potreba prekryt i metodu hashCode. Podrobny
     * popis pravidel pro vytvareni metody hashCode je u metody hashCode ve
     * tride Object
     */
    @Override
    public int hashCode() {
        int vysledek = 3;
        int hashNazvu = Objects.hashCode(this.nazev);
        vysledek = 37 * vysledek + hashNazvu;
        return vysledek;
    }
      

    /**
     * Vrací název prostoru (byl zadán při vytváření prostoru jako parametr
     * konstruktoru)
     *
     * @return název prostoru
     */
    public String getNazev() {
        return nazev;       
    }

    /**
     * Vrací "dlouhý" popis prostoru, který může vypadat následovně: Jsi v
     * mistnosti/prostoru vstupni hala budovy VSE na Jiznim meste. vychody:
     * chodba bufet ucebna
     *
     * @return Dlouhý popis prostoru
     */
    public String dlouhyPopis() {
        return "Si v miestnosti " + popis + "\n"
                + popisVychodu() + "\n"
                + popisVeci() + "\n"
                + popisBytosti() ;
    }

    /**
     * Vrací textový řetězec, který popisuje sousední východy, například:
     * "vychody: hala ".
     *
     * @return Popis východů - názvů sousedních prostorů
     */
    private String popisVychodu() {
        String vracenyText = "východy:";
        for (Prostor sousedni : vychody) {
            vracenyText += " " + sousedni.getNazev();
        }
        return vracenyText;
    }

    /**
     * Metóda prechádza všetky veci uložené v mape na základe ich kľúču.
     * @return vracia text obsahujúci veci v miestnosti
     */
    public String popisVeci(){
        String vracenyText = "veci v miestnosti:";
        for (String nazevVeci: veci.keySet()) {
            vracenyText += " " + nazevVeci;
        }
        return vracenyText;
    }

    /**
     * Metóda prechádza zoznam bytostí v danom priestore.
     * @return text obsahujúci zoznam bytostí v priestore
     */
    private String popisBytosti(){
        String vracenyText = "bytosti:";
        for (Bytost bytost : seznamBytosti) {
            vracenyText += " " + bytost.getMeno();
        }
        return vracenyText;
    }

    /**
     * Vrací prostor, který sousedí s aktuálním prostorem a jehož název je zadán
     * jako parametr. Pokud prostor s udaným jménem nesousedí s aktuálním
     * prostorem, vrací se hodnota null.
     *
     * @param nazevSouseda Jméno sousedního prostoru (východu)
     * @return Prostor, který se nachází za příslušným východem, nebo hodnota
     * null, pokud prostor zadaného jména není sousedem.
     */
    public Prostor vratSousedniProstor(String nazevSouseda) {
        List<Prostor>hledaneProstory = 
            vychody.stream()
                   .filter(sousedni -> sousedni.getNazev().equals(nazevSouseda))
                   .collect(Collectors.toList());
        if(hledaneProstory.isEmpty()){
            return null;
        }
        else {
            return hledaneProstory.get(0);
        }
    }

    /**
     * Vrací kolekci obsahující prostory, se kterými tento prostor sousedí.
     * Takto získaný seznam sousedních prostor nelze upravovat (přidávat,
     * odebírat východy) protože z hlediska správného návrhu je to plně
     * záležitostí třídy Prostor.
     *
     * @return Nemodifikovatelná kolekce prostorů (východů), se kterými tento
     * prostor sousedí.
     */
    public Collection<Prostor> getVychody() {
        return Collections.unmodifiableCollection(vychody);
    }


    /**
     * Metóda pridania bytosti alebo viac bytostí do priestoru.
     * @param bytosti, ktoré sa priradia k danému priestoru
     */
    public void pridatBytost(Bytost... bytosti){
        seznamBytosti.addAll(Arrays.asList(bytosti));
    }

    /**
     * Metóda prechádzajúca zoznam bytostí, v prípade, že je daná úloha priradená k bytosti vráti true.
     * @param uloha prijíma úlohu
     * @return boolean hodnotu true/false
     */

    public boolean jeUlohaZadanaVMiestnosti(Uloha uloha){
        for (Bytost item: seznamBytosti){
                if (item.getUloha() != null && (item.getUloha().equals(uloha))) {
                    return true;
                }
        }
        return false;
    }

    /**
     * Metóda prechádza zoznam bytostí v priestore a vracia instanciu triedy Bytosť.
     * Ak sa takáto bytosť v priestore nenáchadza vráti null.
     * @param nazev
     * @return
     */
    public Bytost vratBytost(String nazev){
        for (Bytost item: seznamBytosti){
            if (nazev.equals(item.getMeno())){
                return item;
            }
        }
        return null;
    }

    /**
     * Metóda prechádza zoznam bytostí v priestore a ak sa úloha priradená bytosti v tomto priestore rovná
     * zadanému názvu, vráti to jej inštanciu.
     * @param nazov String názov úlohy
     * @return inštanciu úlohy alebo null
     */
    public Uloha vratUlohuVPriestore(String nazov){
        for (Bytost item : seznamBytosti){
                if (item.getUloha() != null && nazov.equals(item.getUloha().getNazovUlohy())) {
                    return item.getUloha();
                }
        }
        return null;
    }

    /**
     * Metóda prechádza východy v priestore a ak je miestnosť zamknutá, pridá jej vychody na zoznam vychodyZamknutych.
     * @return zoznam východov zamknutých priestorov
     */
    public ArrayList<Prostor> vychodyZamknutychList(){
        ArrayList<Prostor> vychodyZamknutych = new ArrayList<>();
        for (Prostor item : vychody){
            if (item.isZamknuta()) {
                vychodyZamknutych.add(item);
            }
        }
        return vychodyZamknutych;
    }

    /**
     * Metóda, ktorá v prípade, že je miestnosť zamknutá nastavý hodnotu zamknuta na false.
     */
    public void odomknutMiestnost(){
        if (this.zamknuta){
            zamknuta=false;
        }
    }

    /**
     * Metóda, ktorá v prípade, že miestnosť nie je zamknutá nastavý hodnotu zamknuta na true.
     */
    public void zamknutMiestnost(){
        if (!this.zamknuta){
            zamknuta=true;
        }
    }

    /**
     * Metóda na zistenie hodnoty zamknuta.
     * @return boolean zamknuta
     */
    public boolean isZamknuta() {
        return zamknuta;
    }


}
