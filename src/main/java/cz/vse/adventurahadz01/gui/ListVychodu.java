package cz.vse.adventurahadz01.gui;

import cz.vse.adventurahadz01.logika.HerniPlan;
import cz.vse.adventurahadz01.logika.Hra;
import cz.vse.adventurahadz01.observer.Observer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
/**
 * Trieda ListVychodu - realizujúca panel východov použitý v grafickom rozhraní.
 * Panel zobrazuje východy z aktuálneho priestoru v hernom pláne.
 * Trieda implementuje rozhranie Observer a prihlási sa na zmenu stavu herného plánu.
 * @author Zuzana Hadzimová
 * @version apríl 2023
 */

public class ListVychodu extends ListView<String> implements Observer {

    private HerniPlan herniPlan;
    private ObservableList<String> itemsList;

    /**
     * Konštruktor ListVychodu - Vytvára nový objekt typu ListVychodu a inicializuje ho.
     * Registruje sa ako observer herného plánu pre zistenie zmien v priestoroch.
     */
    public ListVychodu() {
        this.herniPlan = Hra.getSingleton().getHerniPlan();
        herniPlan.register(this);

        itemsList = FXCollections.observableArrayList();
        setItems(itemsList);

        update();
    }

    /**
     * Metóda update - slúži na aktualizáciu obsahu ListView.
     * Získava aktuálny herný plán a získava z neho zoznam východov z aktuálneho priestoru.
     * Následne aktualizuje položky ListView s východmi.
     */

    @Override
    public void update() {
        herniPlan = Hra.getSingleton().getHerniPlan();
        itemsList.clear();
        herniPlan.getAktualniProstor().getVychody().forEach(prostor -> itemsList.add(prostor.getNazev()));
    }
}