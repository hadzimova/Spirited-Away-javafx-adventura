package cz.vse.adventurahadz01.logika;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/*******************************************************************************
 * Testovací třída HraTest slouží ke komplexnímu otestování
 * třídy Hra
 *
 * @author    Jarmila Pavlíčková, Zuzana Hadzimová
 * @version  2023 upravená verzia pre účel semestrálnej práce
 */
public class HraTest {
    private Hra hra1;

    /***************************************************************************
     * Metoda se provede před spuštěním každé testovací metody. Používá se
     * k vytvoření tzv. přípravku (fixture), což jsou datové atributy (objekty),
     * s nimiž budou testovací metody pracovat.
     */
    @BeforeEach
    public void setUp() {
        hra1 = new Hra();
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každé testovací metody.
     */
    @AfterEach
    public void tearDown() {
    }

    /***************************************************************************
     * Testuje průběh hry, po zavolání každěho příkazu testuje, zda hra končí
     * a v jaké aktuální místnosti se hráč nachází.
     * Při dalším rozšiřování hry doporučujeme testovat i jaké věci nebo osoby
     * jsou v místnosti a jaké věci jsou v batohu hráče.
     * 
     */
    @Test
    public void testPrubehHry() {
        assertEquals("chodba", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("hovor škriatok");
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("prijmi zametanie");
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("chod sklad");
        assertEquals(false, hra1.konecHry());
        assertEquals("sklad", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("zober metla");
        assertEquals("Obsah tašky: metla ",hra1.zpracujPrikaz("taska"));
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("chod chodba");
        assertEquals("chodba", hra1.getHerniPlan().getAktualniProstor().getNazev());
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("pozametaj chodba");
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("pust metla");
        assertTrue(hra1.getHerniPlan().getAktualniProstor().vecExistuje("metla"));
        assertEquals("Obsah tašky: Nič sa tu nenachádza",hra1.zpracujPrikaz("taska"));
        hra1.zpracujPrikaz("odovzdaj zametanie");
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("chod jedalen");
        assertEquals("jedalen", hra1.getHerniPlan().getAktualniProstor().getNazev());
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("hovor zraneny_host");
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("prijmi lieciva_voda");
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("chod chodba");
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("chod pracovna");
        assertEquals("pracovna", hra1.getHerniPlan().getAktualniProstor().getNazev());
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("hovor chyzna");
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("prijmi bielizen");
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("chod bahenne_kupele");
        assertEquals(false, hra1.konecHry());
        assertEquals("bahenne_kupele", hra1.getHerniPlan().getAktualniProstor().getNazev());
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("hovor hladny_host");
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("prijmi kolac");
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("hovor zdravotnik");
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("prijmi umytie_kupelov");
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("zober spinava_bielizen");
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("chod prirodne_kupele");
        assertEquals("prirodne_kupele", hra1.getHerniPlan().getAktualniProstor().getNazev());
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("zober lieciva_voda");
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("hovor smadny_host");
        hra1.zpracujPrikaz("prijmi napoj");
        hra1.zpracujPrikaz("chod bahenne_kupele");
        assertEquals(false, hra1.konecHry());
        assertEquals("bahenne_kupele", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("chod pracovna");
        hra1.zpracujPrikaz("chod chodba");
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("chod sklad");
        hra1.zpracujPrikaz("zober praci_prach");
        hra1.zpracujPrikaz("zober mop");
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("zober handra");
        hra1.zpracujPrikaz("hovor pracovnik_kupelov");
        hra1.zpracujPrikaz("prijmi umytie_toaliet");
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("odovzdaj bielizen");
        hra1.zpracujPrikaz("chod chodba");
        hra1.zpracujPrikaz("chod jedalen");
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("odovzdaj lieciva_voda");
        hra1.zpracujPrikaz("chod kuchyna");
        hra1.zpracujPrikaz("zober cucoriedkovy_kolac");
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("zober cukor");
        hra1.zpracujPrikaz("chod jedalen");
        hra1.zpracujPrikaz("chod chodba");
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("chod pracovna");
        hra1.zpracujPrikaz("chod toalety");
        hra1.zpracujPrikaz("umy toalety");
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("chod pracovna");
        hra1.zpracujPrikaz("chod chodba");
        hra1.zpracujPrikaz("chod sklad");
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("odovzdaj umytie_toaliet");
        hra1.zpracujPrikaz("chod chodba");
        hra1.zpracujPrikaz("chod pracovna");
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("chod bahenne_kupele");
        hra1.zpracujPrikaz("umy bahenne_kupele");
        hra1.zpracujPrikaz("odovzdaj kolac");
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("odovzdaj zdravotnik");
        hra1.zpracujPrikaz("odovzdaj napoj");
        hra1.zpracujPrikaz("zloz kluc");
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("chod prirodne_kupele");
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("chod vazenie");
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("odomkni vazenie");
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("chod vazenie");
          assertTrue( hra1.konecHry());

    }
}
