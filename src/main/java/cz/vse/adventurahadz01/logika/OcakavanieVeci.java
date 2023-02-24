package cz.vse.adventurahadz01.logika;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 *Trieda kontrolujúca splnenie úloh s očakávanou hodnotou uprataním miestnosti.
 * @author Zuzana Hadzimová
 * @version január 2023
 */

public class OcakavanieVeci implements IOcakavanaHodnota {
    private List<Vec> ocakavaneVeci = new ArrayList<>();

    private Taska taska;

    /**
     * Konštruktor triedy OcakavaneVeci prijimajúci tašku a očakávané veci.
     * @param taska taška v ktorej sa veci majú nachádzať
     * @param ocakavaneVeci 0 až n vecí z tašky
     */
    public OcakavanieVeci(Taska taska, Vec... ocakavaneVeci) {
        this.ocakavaneVeci.addAll(Arrays.asList(ocakavaneVeci));
        this.taska = taska;
    }
    /**
     * Metóda kontrolujúca splnenie úlohy - určitá vec v taške. Prechádza zoznam očakávaných vecí a ak má dostatočný počet vráti true.
     * Používa pomocný list prijaté veci. V prípade nedostatočného množstva očakávaných vecí vráti tieto veci späť do tašky.
     * @return boolean hodnota (true/false)
     */
    @Override
    public boolean skontrolujSplnenieUlohy() {
        int counter = 0;
        List<Vec> prijateVeci = new ArrayList<>();
        for (Vec vec : ocakavaneVeci){
          if (taska.odoberVec(vec.getNazev())){
              counter++;
              prijateVeci.add(vec);
          }
        }
        if (counter == ocakavaneVeci.size())
        {
            return true;
        }
        for(Vec vec : prijateVeci){
            taska.vlozVec(vec);
        }
        return false;
    }
}
