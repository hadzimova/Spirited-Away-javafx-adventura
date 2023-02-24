package cz.vse.adventurahadz01.logika;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
/**
 * Testovacia trieda PrikazPozametajTest testuje príkaz pozametaj.
 *
 * @author Zuzana Hadzimová
 * @version január 2023
 */
public class PrikazPozametajTest {
    private PrikazPozametaj prikazPozametaj;
    private HerniPlan herniPlan;
    private Vec vec1;
    /***************************************************************************
     * Metoda se provede před spuštěním každé testovací metody. Používá se
     * k vytvoření tzv. přípravku (fixture), což jsou datové atributy (objekty),
     * s nimiž budou testovací metody pracovat.
     */
    @BeforeEach
    public void setUp() {
        herniPlan = new HerniPlan(new Hra());
        prikazPozametaj = new PrikazPozametaj(herniPlan);
        vec1 = new Vec("metla", true);
        herniPlan.getTaska().vlozVec(vec1);
    }

    /**
     * Metóda testujúca správnosť príkazu pozametaj v prípade chýbajúceho parametru, viac parametrov a nesprávnej miestnosti.
     */
    @Test
    public void testPozametajParametre() {
        assertEquals("Čo mám pozametať?.",prikazPozametaj.provedPrikaz());
        assertEquals("Ako mám podľa teba pozametať viac miestností naraz?", prikazPozametaj.provedPrikaz("chodba", "kuchyna"));
        assertEquals("Ako by si to chcel pozametať, keď tam nie si?",  prikazPozametaj.provedPrikaz("kuchyna"));
    }


    /**
     * Metóda testujúca príkaz zametanie v prípade chýbajúcej metly
     */
    @Test
    public void testPozametajMiestnostiBezMetly() {
        herniPlan.getTaska().odoberVec("metla");
        assertEquals("Nemáš metlu, ako chceš zametať?", prikazPozametaj.provedPrikaz("chodba"));
    }
    /**
     * Metóda testujúca príkaz zametanie v prípade upratanej miestnosti
     */
    @Test
    public void testPozametajMiestnostiSUpratanymProstorom() {
        herniPlan.getAktualniProstor().setUpratana(true);
        assertEquals("Načo to budeš zametať dvakrát?", prikazPozametaj.provedPrikaz("chodba"));
    }
}

