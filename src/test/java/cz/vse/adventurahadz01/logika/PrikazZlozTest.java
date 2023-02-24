package cz.vse.adventurahadz01.logika;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/**
 * Testovacia trieda PrikazZlozTest testuje príkaz zloz.
 *
 * @author Zuzana Hadzimová
 * @version január 2023
 */
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PrikazZlozTest {
    private HerniPlan herniPlan;
    private Hra hra;
    private  PrikazZloz prikazZloz;

    private Vec castKlucu;
    /***************************************************************************
     * Metoda se provede před spuštěním každé testovací metody. Používá se
     * k vytvoření tzv. přípravku (fixture), což jsou datové atributy (objekty),
     * s nimiž budou testovací metody pracovat.
     */
    @BeforeEach
    public void setUp() {
        herniPlan = new HerniPlan(hra);
        prikazZloz = new PrikazZloz(herniPlan);
        castKlucu = new Vec("cast_klucu",true);

    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každé testovací metody.
     */
    @AfterEach
    public void tearDown() {
    }
    /**
     * Metóda prikazZlozTest testuje príkaz zloz pri nepoužití parametru, použití mnoho parametrov, nepužití správneho parametru,
     * a nedostatočný počet častí kľúča v inventári. Taktiež správne použitie príkazu.
     */
    @Test
    void prikazZlozTest() {
        assertEquals("Čo mám zložiť? Čím chceš odomknúť miestnosť, kde máš rodičov?", prikazZloz.provedPrikaz());
        assertEquals("Neviem zložiť viac vecí naraz!", prikazZloz.provedPrikaz("kluc","skladacku"));
        assertEquals("Túto vec zložiť nevieš", prikazZloz.provedPrikaz("skladacku"));
        herniPlan.getTaska().vlozVec(castKlucu);
        assertEquals("Nemáš dostatok kusov kľúča na zloženie!", prikazZloz.provedPrikaz("kluc"));
        for (int i = 0; i < 6; i++){herniPlan.getTaska().vlozVec(castKlucu);}
        assertEquals("Zložil si klúč. Teraz môžeš zachrániť rodičov!", prikazZloz.provedPrikaz("kluc"));
        assertEquals(0, herniPlan.getTaska().pocetVeci("cast_klucu"));
        assertEquals(1, herniPlan.getTaska().pocetVeci("kluc"));
    }


}
