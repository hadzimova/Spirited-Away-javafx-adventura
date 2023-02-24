package cz.vse.adventurahadz01.logika;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
/**
 * Testovacia trieda PrikazHovorTest testuje príkaz hovor s bytosťami.
 *
 * @author Zuzana Hadzimová
 * @version január 2023
 */

public class PrikazHovorTest {
    private Hra hra;
    private HerniPlan herniPlan;
    private PrikazHovor prikazHovor;
    private PrikazPrijmiUlohu prikazPrijmiUlohu;
    /***************************************************************************
     * Metoda se provede před spuštěním každé testovací metody. Používá se
     * k vytvoření tzv. přípravku (fixture), což jsou datové atributy (objekty),
     * s nimiž budou testovací metody pracovat.
     */
    @BeforeEach
    public void setUp() {
        herniPlan = new HerniPlan(hra);
        prikazHovor = new PrikazHovor(herniPlan);
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
     * Metóda testujúca príkaz hovor. Testuje v prípade, že uživateľ nezadá parameter, zadá príliš veľa parametrov, zadá meno bytosti, ktorá
     * sa v miestnosti nenachádza a zmenu dialógu pri prijatí a dokončení úlohy.
     */

    @Test
    public void testProvedPrikazHovor() {
        assertEquals("S kým mám hovoriť?", prikazHovor.provedPrikaz());
        assertEquals("Nebudem hovoriť s viac ako jednou bytosťou naraz!", prikazHovor.provedPrikaz("zdravotnik", "trpaslik"));
        assertEquals("Táto bytosť tu nie je, ako by si s ňou chcel hovoriť?", prikazHovor.provedPrikaz("tajomna_bytost"));
        herniPlan.getAktualniProstor().pridatBytost(new Bytost("zdravotnik", "zdr dialog"));
        assertEquals("zdr dialog", prikazHovor.provedPrikaz("zdravotnik"));

        Uloha uloha = new Uloha("uloha", "popis ulohy", "splnil si", "nesplnil si");
        herniPlan.getAktualniProstor().pridatBytost(new Bytost("bytost", "bytost dialog"));
        herniPlan.getAktualniProstor().vratBytost("bytost").setUloha(uloha);
        assertEquals("bytost dialog\nMám pre teba úlohu: uloha\npopis ulohy", prikazHovor.provedPrikaz("bytost"));

        prikazPrijmiUlohu.provedPrikaz("uloha");
        assertEquals("Na čo čakáš s robením tej úlohy, čo som ti zadal/a?", prikazHovor.provedPrikaz("bytost"));

        herniPlan.getAktualniProstor().vratBytost("bytost").getUloha().setHotova(true);
        assertEquals("bytost dialog", prikazHovor.provedPrikaz("bytost"));

    }


}


