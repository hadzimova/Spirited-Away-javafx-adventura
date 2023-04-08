package cz.vse.adventurahadz01.gui;

import cz.vse.adventurahadz01.logika.HerniPlan;
import cz.vse.adventurahadz01.observer.Observer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

public class ListVychodu extends ListView<String> implements Observer {

    private HerniPlan herniPlan;
    private ObservableList<String> itemsList;

    public ListVychodu(HerniPlan herniPlan) {
        this.herniPlan = herniPlan;
        herniPlan.register(this);

        itemsList = FXCollections.observableArrayList();
        setItems(itemsList);

        update();
    }



    @Override
    public void update() {
        itemsList.clear();
        herniPlan.getAktualniProstor().getVychody().forEach(prostor -> itemsList.add(prostor.getNazev()));
    }
}