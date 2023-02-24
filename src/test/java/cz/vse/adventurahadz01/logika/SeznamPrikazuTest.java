package cz.vse.adventurahadz01.logika;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*******************************************************************************
 * Testovací třída SeznamPrikazuTest slouží ke komplexnímu otestování třídy  
 * SeznamPrikazu
 * 
 * @author    Luboš Pavlíček
 * @version   2023 upravená verzia pre účel semestrálnej práce
 */
public class SeznamPrikazuTest
{
    private Hra hra;
    private PrikazKonec prKonec;
    private PrikazChod prChod;

    /***************************************************************************
     * Metoda se provede před spuštěním každé testovací metody. Používá se
     * k vytvoření tzv. přípravku (fixture), což jsou datové atributy (objekty),
     * s nimiž budou testovací metody pracovat.
     */
    
    @BeforeEach
    public void setUp() {
        hra = new Hra();
        prKonec = new PrikazKonec(hra);
        prChod = new PrikazChod(hra);

    }

    @Test
    public void testVlozeniVybrani() {
        SeznamPrikazu seznPrikazu = new SeznamPrikazu();
        seznPrikazu.vlozPrikaz(prKonec);
        seznPrikazu.vlozPrikaz(prChod);
        assertEquals(prKonec, seznPrikazu.vratPrikaz("koniec"));
        assertEquals(prChod, seznPrikazu.vratPrikaz("chod"));
        assertEquals(null, seznPrikazu.vratPrikaz("napoveda"));
    }
    @Test
    public void testJePlatnyPrikaz() {
        SeznamPrikazu seznPrikazu = new SeznamPrikazu();
        seznPrikazu.vlozPrikaz(prKonec);
        seznPrikazu.vlozPrikaz(prChod);
        assertEquals(true, seznPrikazu.jePlatnyPrikaz("koniec"));
        assertEquals(true, seznPrikazu.jePlatnyPrikaz("chod"));
        assertEquals(false, seznPrikazu.jePlatnyPrikaz("nápoveda"));
        assertEquals(false, seznPrikazu.jePlatnyPrikaz("Konec"));
    }
    
    @Test
    public void testNazvyPrikazu() {
        SeznamPrikazu seznPrikazu = new SeznamPrikazu();
        seznPrikazu.vlozPrikaz(prKonec);
        seznPrikazu.vlozPrikaz(prChod);
        String nazvy = seznPrikazu.vratNazvyPrikazu();
        assertEquals(true, nazvy.contains("koniec"));
        assertEquals(true, nazvy.contains("chod"));
        assertEquals(false, nazvy.contains("napoveda"));
        assertEquals(false, nazvy.contains("Koniec"));
    }
    
}
