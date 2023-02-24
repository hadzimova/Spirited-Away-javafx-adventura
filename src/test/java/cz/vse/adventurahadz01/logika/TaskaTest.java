package cz.vse.adventurahadz01.logika;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
/**
 * Testovacia trieda TaskaTest testujúca triedu Taska.
 *
 * @author Zuzana Hadzimová
 * @version január 2023
 */
public class TaskaTest {

    Vec metla = new Vec("metla", true);
    Vec flasa = new Vec("flasa", true);
    Vec ucebnica = new Vec("ucebnica", true);
    Taska taska = new Taska(15);
    Taska taska1 = new Taska(2);

    /**
     * Metóda testujúca vloženie vecí do inventára.
     */
    @Test
    void testVlozeniaVeci() {
        assertTrue(taska1.vlozVec(metla));
        assertTrue(taska1.vlozVec(flasa));
        assertFalse(taska1.vlozVec(ucebnica));
    }

    /**
     * Metóda testujúca odobratie veci z inventára.
     */
    @Test
    void testOdobratiaVeci() {
        taska.vlozVec(metla);
        taska.vlozVec(flasa);
        assertTrue(taska.odoberVec("metla"));
        assertFalse(taska.odoberVec("mop"));
    }

    /**
     * Metóda testujúca zoznam vecí v taške.
     */
    @Test
    public void testZoznamVeciVTaske() {
        assertEquals("Nič sa tu nenachádza", taska.zoznamVeciVTaske());

        taska.vlozVec(metla);
        taska.vlozVec(flasa);
        taska.vlozVec(ucebnica);

        assertEquals("metla flasa ucebnica ", taska.zoznamVeciVTaske());
    }
    /**
     * Metóda testujúca vrátenie inštancie vec po vložení String názvu.
     */
    @Test
    public void testVratVec() {

        assertEquals(taska.vratVec("metla"), null);
        taska.vlozVec(ucebnica);
        assertEquals(taska.vratVec("ucebnica"),ucebnica);
    }
    /**
     * Metóda testujúca metódu vracajúcu počet vecí s daným názvom v taške.
     */
    @Test
    public void testPocetVeci() {
        taska.vlozVec(flasa);
        taska.vlozVec(flasa);
        taska.vlozVec(ucebnica);

        assertEquals(2, taska.pocetVeci("flasa"));
        assertEquals(1, taska.pocetVeci("ucebnica"));
        assertEquals(0, taska.pocetVeci("noviny"));

    }
}
