package cz.vse.adventurahadz01.logika;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/*******************************************************************************
 * Testovací třída ProstorTest slouží ke komplexnímu otestování
 * třídy Prostor
 *
 * @author    Jarmila Pavlíčková, Zuzana Hadzimová
 * @version   2023 upravená verzia pre účel semestrálnej práce
 */
public class ProstorTest {
    private Prostor prostor1;
    private Prostor prostor2;
    private Prostor prostor3;
    private Vec vec1;
    private Vec vec2;
    private Bytost bytost1;
    /***************************************************************************
     * Metoda se provede před spuštěním každé testovací metody. Používá se
     * k vytvoření tzv. přípravku (fixture), což jsou datové atributy (objekty),
     * s nimiž budou testovací metody pracovat.
     */
    @BeforeEach
    public void setUp() {
        prostor1 = new Prostor("kuchyna", "Kuchyňa v dome");
        prostor2 = new Prostor("obývačka", "Obývačka v dome");
        prostor3 = new Prostor("kúpeľňa", "Kúpeľňa v dome");
        vec1 = new Vec("kost", true);
        vec2 = new Vec("metla", false);
        bytost1 = new Bytost("Bob", "Ahoj, ako sa mas?");
    }

    @Test
    public void testGetNazev() {
        assertEquals("kuchyna", prostor1.getNazev());
    }


    @Test
    public void testVychody() {
        prostor1.setVychod(prostor2);
        prostor1.setVychod(prostor3);
        assertTrue(prostor1.getVychody().contains(prostor2));
        assertTrue(prostor1.getVychody().contains(prostor3));
    }

    @Test
    public void testUpratana() {
        prostor1.setUpratana(true);
        assertTrue(prostor1.isUpratana());
    }

    @Test
    public void testZamknuta() {
        prostor1.zamknutMiestnost();
        assertTrue(prostor1.isZamknuta());
    }


    /**
     * Testuje, zda jsou správně nastaveny průchody mezi prostory hry. Prostory
     * nemusí odpovídat vlastní hře, 
     */
    @Test
    public  void testLzeProjit() {		
        Prostor prostor1 = new Prostor("chodba", "vstupní hala budovy VŠE na Jižním městě");
        Prostor prostor2 = new Prostor("jedalen", "bufet, kam si můžete zajít na svačinku");
        prostor1.setVychod(prostor2);
        prostor2.setVychod(prostor1);
        assertEquals(prostor2, prostor1.vratSousedniProstor("jedalen"));
        assertEquals(null, prostor2.vratSousedniProstor("pokoj"));
    }

}
