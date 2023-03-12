package cz.vse.adventurahadz01.uiText;


import java.io.*;
import java.util.Scanner;

import cz.vse.adventurahadz01.logika.Hra;
import cz.vse.adventurahadz01.logika.IHra;

/**
 *  Class TextoveRozhrani
 * 
 *  Toto je uživatelského rozhraní aplikace Adventura
 *  Tato třída vytváří instanci třídy Hra, která představuje logiku aplikace.
 *  Čte vstup zadaný uživatelem a předává tento řetězec logice a vypisuje odpověď logiky na konzoli.
 *  
 *  
 *
 *@author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova
 *@version    pro školní rok 2016/2017
 */

public class TextoveRozhrani {
    private IHra hra;

    public static void main(String[] args) {
        Hra hra = Hra.getSingleton();
        new TextoveRozhrani(hra).hraj();
    }

    /**
     *  Vytváří hru.
     */
    public TextoveRozhrani(IHra hra) {
        this.hra = hra;
    }

    /**
     *  Hlavní metoda hry. Vypíše úvodní text a pak opakuje čtení a zpracování
     *  příkazu od hráče do konce hry (dokud metoda konecHry() z logiky nevrátí
     *  hodnotu true). Nakonec vypíše text epilogu.
     */
    public void hraj() {
        System.out.println(hra.vratUvitani());

        // základní cyklus programu - opakovaně se čtou příkazy a poté
        // se provádějí do konce hry.

        while (!hra.konecHry()) {
            String radek = prectiString();
            System.out.println(hra.zpracujPrikaz(radek));
        }

        System.out.println(hra.vratEpilog());
    }

    /**
     *  Metoda přečte příkaz z příkazového řádku
     *
     *@return    Vrací přečtený příkaz jako instanci třídy String
     */
    private String prectiString() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("> ");
        return scanner.nextLine();
    }
    public void hrajZeSouboru(String nazevSouboru){
        try(BufferedReader cteni = new BufferedReader(new FileReader(nazevSouboru));
            PrintWriter zapis = new PrintWriter(new FileWriter("vystup.txt"))) {
            System.out.println(hra.vratUvitani());
            zapis.println(hra.vratUvitani());

            String radek = cteni.readLine();
            while (radek!=null && !hra.konecHry()){
                System.out.println("> "+ radek);
                zapis.println("> "+ radek);

                String vystup = hra.zpracujPrikaz(radek);
                System.out.println(vystup);
                zapis.println(vystup);

                radek = cteni.readLine();
            }
            System.out.println(hra.vratEpilog());
            zapis.println(hra.vratEpilog());

        } catch (FileNotFoundException e) {
            File file = new File(nazevSouboru);
            System.out.println("Soubor nebyl nalezen!");
            System.out.println("Prohledávaná cesta byla "+file.getAbsolutePath());
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("test");
        }

    }

}
