import static junit.framework.TestCase.assertEquals;
import static org.assertj.core.api.Assertions.*;

import cars.RodzajPaliwa;
import cars.Samochod;
import cars.StacjaBenzynowa;
import cars.ZaDuzoPaliwaException;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.math.BigDecimal;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

@RunWith(DataProviderRunner.class)
public class StacjaBenzynowaTest {

    Samochod samochod;
    static StacjaBenzynowa stacjaBenzynowa;

    @BeforeClass
    public static void setup() {
        stacjaBenzynowa = new StacjaBenzynowa();
    }

    @Test
    public void kiedyZatankowanoDoPelna() {
        // given
        samochod = new Samochod(50, 20, RodzajPaliwa.DIESEL);

        // when
        stacjaBenzynowa.wlejDoPelna(samochod);

        // then
        assertThat(samochod.getAktualnyPoziomPaliwa()).isEqualTo(samochod.getPojemnoscZbiornika());
    }

    @Test(expected = ZaDuzoPaliwaException.class)
    public void powinienZglosicZeZaDuzoPaliwa() throws ZaDuzoPaliwaException {
        samochod = new Samochod(100, 20, RodzajPaliwa.DIESEL);
        stacjaBenzynowa.wlejPaliwo(samochod, 101);
    }

    @Test
    public void powinienZglosicZeZaDuzoPaliwa2() throws ZaDuzoPaliwaException {
        assertThatThrownBy(() -> { samochod = new Samochod(100, 20, RodzajPaliwa.DIESEL);
            stacjaBenzynowa.wlejPaliwo(samochod, 101); }).isInstanceOf(ZaDuzoPaliwaException.class);

    }

    @Test
    public void powinienZatankowacAleNieDoPelna() throws ZaDuzoPaliwaException {
        // given
        samochod = new Samochod(100, 50, RodzajPaliwa.DIESEL);

        // when
        stacjaBenzynowa.wlejPaliwo(samochod, 49);

        // then
        assertThat(samochod.getAktualnyPoziomPaliwa())
                .isNotEqualTo(0)
                .isLessThan(samochod.getPojemnoscZbiornika());
    }

    @Test
    public void powinienNalewac() {
        assertThat(stacjaBenzynowa.sprawdzMozliwoscZatankowania(50)).isTrue();
    }

    @Test
    public void powinienNieNalewac() {
        assertThat(stacjaBenzynowa.sprawdzMozliwoscZatankowania(500)).isFalse();
    }

    // sprawdzamy czy metoda zwroci wynik ktorego sie spodziewamy
    @Test
    public void czyDobrzeWyliczonoCeneSprzedazy () throws ZaDuzoPaliwaException {
        // given
        samochod = new Samochod(100, 50, RodzajPaliwa.DIESEL);

        // when
        BigDecimal result = stacjaBenzynowa.obliczCeneDoZaplaty(samochod, 1);

        // then
        assertThat(result).isEqualTo(new BigDecimal(4.50));
    }

    @DataProvider
    public static Object[][] dataProviderAdd(){
        return new Object[][] {
      // wprowadzamy rozne wartosci argumentow dla danej metody (tutaj wlejPaliwo)

                // pojemnoscZbiornika | aktualnyPoziomPaliwa | iloscWlanegoPaliwa | spodziewanyWynik
                { 100, 50,  20, 70  },
                { 50,  15,  20, 35  },
                { 110,  55,  50, 105 },
        };
    }

    @Test
    @UseDataProvider("dataProviderAdd")
    public void testDataProvider(int pojemnoscZbiornika, int aktualnyPoziomPaliwa, int iloscWlanegoPaliwa, int spodziewanyWynik) throws ZaDuzoPaliwaException {
        // given
        Samochod samochod = new Samochod(pojemnoscZbiornika, aktualnyPoziomPaliwa, RodzajPaliwa.DIESEL);
        StacjaBenzynowa stacjaBenzynowa = new StacjaBenzynowa();

        // when
        int ileWlano = stacjaBenzynowa.wlejPaliwo(samochod, iloscWlanegoPaliwa);

        // then
        assertEquals(spodziewanyWynik ,(ileWlano + aktualnyPoziomPaliwa));
    }
}
