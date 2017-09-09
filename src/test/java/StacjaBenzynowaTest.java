import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class StacjaBenzynowaTest {

    Samochod samochod;
    static StacjaBenzynowa stacjaBenzynowa;

    @BeforeClass
    public static void setup() {
        stacjaBenzynowa = new StacjaBenzynowa();
    }

    @Test
    public void kiedyZatankowanoDoPelna() {
        samochod = new Samochod(50, 20, RodzajPaliwa.DIESEL);
        stacjaBenzynowa.wlejDoPelna(samochod);
        assertEquals(samochod.getAktualnyPoziomPaliwa(), samochod.getPojemnoscZbiornika());
    }

    @Test(expected = ZaDuzoPaliwaException.class)
    public void powinienZglosicZeZaDuzoPaliwa() throws ZaDuzoPaliwaException {
        samochod = new Samochod(100, 20, RodzajPaliwa.DIESEL);
        stacjaBenzynowa.wlejPaliwo(samochod, 101);
    }

    @Test
    public void powinienZatankowacAleNieDoPelna() throws ZaDuzoPaliwaException {
        samochod = new Samochod(100, 50, RodzajPaliwa.DIESEL);
        stacjaBenzynowa.wlejPaliwo(samochod, 49);
        assertTrue(samochod.getAktualnyPoziomPaliwa() != 0 && samochod.getAktualnyPoziomPaliwa() < samochod.getPojemnoscZbiornika());
    }

    @Test
    public void powinienNalewac() {
        assertTrue(stacjaBenzynowa.sprawdzMozliwoscZatankowania(50));
    }

    @Test
    public void powinienNieNalewac() {
        assertFalse(stacjaBenzynowa.sprawdzMozliwoscZatankowania(500));
    }

    @Test
    public void czyDobrzeWyliczonoCeneSprzedazy () throws ZaDuzoPaliwaException {
        samochod = new Samochod(100, 50, RodzajPaliwa.DIESEL);
        assertTrue(stacjaBenzynowa.obliczCeneDoZaplaty(samochod, 1));
    }


}
