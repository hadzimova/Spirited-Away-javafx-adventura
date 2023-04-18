package cz.vse.adventurahadz01.observer;
/**
 * Interface Observer - predstavuje časť návrhového vzoru observer.
 * @author Zuzana Hadzimová
 * @version marec 2023
 */
public interface Observer {
    /**
     * Metóda update - volá sa, keď sa Observable objekt zmení.
     */
    void update();
}
