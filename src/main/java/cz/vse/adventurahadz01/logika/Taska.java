package cz.vse.adventurahadz01.logika;

import cz.vse.adventurahadz01.observer.Observable;
import cz.vse.adventurahadz01.observer.Observer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * Trieda Taska - trieda pre realizáciu tašky(inventára) hlavnej postavy v hre.
 *
 * @author Zuzana Hadzimová
 * @version január 2023
 */
public class Taska implements Observable {

    private int kapacitaTasky; //nastavuje kapacitu tasky
    private List<Vec> obsahTasky; //Kolekcia veci co sa nachazaju v inventari

    private Set<Observer> observers= new HashSet<>();
    /**
     * Vytvorenie tašky (inventára) a definovanie jej veľkosti.
     * @param kapacitaTasky veľkost tašky - maximálne množstvo vecí, ktoré môže taška prijať
     */
    public Taska(int kapacitaTasky) {
        this.kapacitaTasky = kapacitaTasky;
        this.obsahTasky = new ArrayList<>();
    }

    /**
     * Vkladanie veci do tašky (inventára).
     * @param vec predstavuje predmet, ktorý sa vkladá
     * @return vráti true ak sa vec pridala yy&do inventára. Ak nie, vráti false.
     */
    public boolean vlozVec(Vec vec) {
        if(obsahTasky.size() < kapacitaTasky) {
            obsahTasky.add(vec);
            notifyObservers();
            return true;
        }
        return false;
    }

    /**
     * Vyberanie vecí z inventára. Prejde obsah a ak sa daná vec nachádza v taške, odstráni.
     * @param nazevVeci názov veci, ktorú vyberáme
     * @return boolean true/false
     */
    public boolean odoberVec(String nazevVeci) {
        for (Vec item : obsahTasky) {
            if (nazevVeci.equals(item.getNazev())){
                obsahTasky.remove(item);
                notifyObservers();
                return true;
            }
        }
        return false;
    }

    /**
     * Metóda prechádza obsah tašky a vracia ich zoznam.
     * @return String zoznam vecí, v prípade, že žiadna vec v taške nie je vráti text "Nič sa tu nenachádza"
     */
    public String zoznamVeciVTaske(){
        StringBuilder mistni = new StringBuilder();
        if (obsahTasky.isEmpty()){
            return "Nič sa tu nenachádza";
        }
        for (Vec item : obsahTasky) {
            mistni.append(item.getNazev()).append(' ');
        }
        return mistni.toString();
    }

    /**
     * Metóda prechádza obsah tašky na základe názvu.
     * @param nazev veci pri ktorej chceme získať inštaciu veci
     * @return inštanciu triedy Vec alebo null
     */
    public Vec vratVec(String nazev){
        for (Vec item: obsahTasky){
            if (nazev.equals(item.getNazev())){
                return item;
            }
        }
        return null;
    }

    /**
     * Metóda vracajúca počet vecí s daným názvom v taške.
     * @param vec názov veci o ktorej chceme vedieť počet
     * @return počet vecí s daným názvom v taške
     */
    public int pocetVeci(String vec) {
        int count = 0;
        for (Vec item : obsahTasky) {
            if (item.getNazev().equals(vec)) {
                count++;
            }
        }
        return count;
    }
    public List<Vec> getMnozinaVeci() {
        return obsahTasky;
    }


    @Override
    public void register(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObservers() {
        for(Observer observer: observers){
            observer.update();
        }


    }

    @Override
    public void unregistered(Observer observer) {

    }


}
