package cz.vse.adventurahadz01.logika;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
/**
 * Testovacia trieda PrikazZoberPustTest testuje príkaz zober a pusť.
 *
 * @author Zuzana Hadzimová
 * @version január 2023
 */
public class PrikazZoberPustTest {
    private Hra hra;
    /***************************************************************************
     * Metoda se provede před spuštěním každé testovací metody. Používá se
     * k vytvoření tzv. přípravku (fixture), což jsou datové atributy (objekty),
     * s nimiž budou testovací metody pracovat.
     */
    @BeforeEach
    public void setUp() {
        hra = new Hra();
    }
    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každé testovací metody.
     */
    @AfterEach
    void tearDown() {
        hra = new Hra();
    }

    /**
     * Metóda na testovanie zobratia a pustenia vecí.
     */
    @Test
    void testZobraniaAPusteniaPredmetu() {
        hra.zpracujPrikaz("chod sklad");
        hra.zpracujPrikaz("zober metla");
        assertEquals("metla ", hra.getHerniPlan().getTaska().zoznamVeciVTaske());
        hra.zpracujPrikaz("pust metla");
        assertEquals("Nič sa tu nenachádza", hra.getHerniPlan().getTaska().zoznamVeciVTaske());
        assertNotNull(hra.getHerniPlan().getAktualniProstor().ziskejVec("metla"));
    }

    /**
     * Metóda tetujúca zobratie viac vecí.
     */
    @Test
    void testPokusZobratViaceroVeci() {
        assertEquals("Nie je možné zobrať naraz viac ako jednu vec.", hra.zpracujPrikaz("zober lampa metla"));
    }
    /**
     * Metóda tetujúca zobratie bez parametra.
     */
    @Test
    void testPokusNieZobratNic() {
        assertEquals("Neviem čo mám zobrať, musíš zadať názov veci!", hra.zpracujPrikaz("zober "));
    }
    /**
     * Metóda tetujúca pustenie viac vecí naraz.
     */
    @Test
    void testPokuspustitViaceroVeci() {
        assertEquals("Nie je možné pustiť viac ako jednu vec naraz.", hra.zpracujPrikaz("pust lampa metla"));
    }
    /**
     * Metóda tetujúca pustenie bez parametra.
     */
    @Test
    void testPokusNiePustitNic() {
        assertEquals("Neviem čo mám pustiť, musíš zadať názov veci.", hra.zpracujPrikaz("pust "));
    }
    /**
     * Metóda tetujúca pustenie veci, ktorá nie je v batohu.
     */
    @Test
    void testPustitVecCoNeniVTaske() {
        assertEquals("vec nie je v batohu!", hra.zpracujPrikaz("pust vec"));
    }
}
