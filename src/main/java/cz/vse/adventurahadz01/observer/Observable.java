package cz.vse.adventurahadz01.observer;
/**
 * Interface Observable
 * @author Zuzana Hadzimová
 * @version marec 2023
 */
public interface Observable {
    /**
     * Metóda registe - registruje observera s objektom Observable.
     * @param observer pozorovateľ, ktorý má byť zaregistrovaný
     */
    void register (Observer observer);

    /**
     * Metóda notifyObservers - upozorní registrovaných observers, že sa objekt Observable zmenil.
     */
    void notifyObservers();

    /**
     * Metóda unregistered - Odregistruje observera z objektu Observable.
     * @param observer, ktorý má byť odregistrovaný
     */
    void unregistered(Observer observer);
}
