package cz.vse.adventurahadz01.observer;

public interface Observable {

    void register (Observer observer);
    void notifyObservers();
    void unregistered(Observer observer);
}
