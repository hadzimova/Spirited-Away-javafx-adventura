package cz.vse.adventurahadz01.logika;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Testovacia trieda PrikazPrijmiTest testuje príkaz prijmi.
 *
 * @author Zuzana Hadzimová
 * @version 2023
 */
public class PrikazPrijmiTest {
    private Hra hra;
    private HerniPlan herniPlan;
    private PrikazPrijmiUlohu prikazPrijmiUlohu;

    /***************************************************************************
     * Metoda se provede před spuštěním každé testovací metody. Používá se
     * k vytvoření tzv. přípravku (fixture), což jsou datové atributy (objekty),
     * s nimiž budou testovací metody pracovat.
     */
    @BeforeEach
    public void setUp() {
        herniPlan = new HerniPlan(hra);
        prikazPrijmiUlohu = new PrikazPrijmiUlohu(herniPlan);

    }
    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každé testovací metody.
     */
    @AfterEach
    void tearDown() {
        hra = new Hra();
    }

    /**
     * Metóda prijatieUlohTest testuje prijatie úloh pri nepoužití parametru, použití mnoho parametrov, nepužití správneho parametru,
     * a pokus o prijatie prijatej úlohy,
     */
    @Test
    void prijatieUlohTest() {

        assertEquals("Akú úlohu mám prijať?", prikazPrijmiUlohu.provedPrikaz());
        assertEquals("Nemôžem prijať viac úloh naraz.", prikazPrijmiUlohu.provedPrikaz("uloha1", "uloha2"));
        assertEquals("Nezadal si platný názov  úlohy!", prikazPrijmiUlohu.provedPrikaz("nespravnaUloha"));
        Uloha prijmiUlohu = new Uloha("ulohaPrijmi", "popis prijmi ulohy", "splnil si..", "nesplnil si..");
        herniPlan.getAktualniProstor().pridatBytost(new Bytost("bytost1", "dialog"));
        herniPlan.getAktualniProstor().vratBytost("bytost1").setUloha(prijmiUlohu);
        assertEquals("Prijal si ulohu: ulohaPrijmi", prikazPrijmiUlohu.provedPrikaz("ulohaPrijmi"));
        assertEquals("Túto úlohu si už prijala.", prikazPrijmiUlohu.provedPrikaz("ulohaPrijmi"));



    }

}
