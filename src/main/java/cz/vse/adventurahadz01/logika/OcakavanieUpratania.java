package cz.vse.adventurahadz01.logika;
/**
 *Trieda kontrolujúca splnenie úloh s očakávanou hodnotou uprataním miestnosti.
 * @author Zuzana Hadzimová
 * @version január 2023
 */

public class OcakavanieUpratania implements IOcakavanaHodnota {
    private Prostor prostor;

    /**
     * Konštruktor triedy prijíma priestor.
     * @param prostor určitý priestor
     */
    public OcakavanieUpratania(Prostor prostor) {
        this.prostor = prostor;
    }
    /**
     * Metóda kontrolujúca splnenie úlohy - upratania.
     * Kontroluje, či vráti metóda isUpratana() v danom priestore hodnotu true
     * @return boolean hodnota (true/false)
     */
    @Override
    public boolean skontrolujSplnenieUlohy() {
        if (prostor.isUpratana() ){
            return true;
        }
        return false;
    }
}
