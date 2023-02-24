package cz.vse.adventurahadz01.logika;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testovacia trieda PrikazZlozTest testuje príkaz zloz.
 *
 * @author Zuzana Hadzimová
 * @version január 2023
 */
public class TestBytost {
    private Bytost bytost;
    private Uloha uloha;
    /***************************************************************************
     * Metoda se provede před spuštěním každé testovací metody. Používá se
     * k vytvoření tzv. přípravku (fixture), což jsou datové atributy (objekty),
     * s nimiž budou testovací metody pracovat.
     */
    @BeforeEach
    public void setUp() {
        bytost = new Bytost("Bob", "Ahoj, ako sa mas?");
        uloha = new Uloha("uloha1", "Opis ulohy", "uspesnyDialog", "neuspesnyDialog");
    }

    /**
     * Metóda vracajúca názov bytosti.
     */

    @Test
    public void testGetMeno() {
        assertEquals("Bob", bytost.getMeno());
    }

    /**
    * Metóda vracajúca dialóg bytosti - test.
     */

    @Test
    public void testGetDialog() {
        assertEquals("Ahoj, ako sa mas?", bytost.getDialog());
    }

    /**
     * Metóda vracajúca úlohu danej bytosti - test.
     */
    @Test
    public void testGetUloha() {
        bytost.setUloha(uloha);
        assertEquals(uloha, bytost.getUloha());
    }

    /**
     * Test metódy priradzujúcej úlohu bytosti.
     */
    @Test
    public void testSetUloha() {
        bytost.setUloha(uloha);
        assertEquals(uloha, bytost.getUloha());
    }
}
