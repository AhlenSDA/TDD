package cars;

import java.math.BigDecimal;

public class StacjaBenzynowa {

    private int aktualnyStanZbiornika = 150;

    public int getaktualnyStanZbiornika() {
        return aktualnyStanZbiornika;
    }

    public void wlejDoPelna(Samochod samochod) {
        samochod.setAktualnyPoziomPaliwa(samochod.getPojemnoscZbiornika());
    }

    public boolean sprawdzMozliwoscZatankowania(int iloscPaliwaDoNalania) {
        if (getaktualnyStanZbiornika() >= iloscPaliwaDoNalania) {
            return true;
        } else {
            return false;
        }
    }

    public int wlejPaliwo(Samochod samochod, int iloscWlanegoPaliwa) throws ZaDuzoPaliwaException {
        if (sprawdzMozliwoscZatankowania(iloscWlanegoPaliwa)) {

            if ((samochod.getAktualnyPoziomPaliwa() + iloscWlanegoPaliwa) <= samochod.getPojemnoscZbiornika()) {
                samochod.setAktualnyPoziomPaliwa(samochod.getAktualnyPoziomPaliwa() + iloscWlanegoPaliwa);
                System.out.println("Zatankowales");
                System.out.println("Do zapłaty: " + (obliczCeneDoZaplaty(samochod, iloscWlanegoPaliwa)));
                return iloscWlanegoPaliwa;
            } else {
                throw new ZaDuzoPaliwaException("Zamierzasz wlać za dużo paliwa. Wlej mniej paliwa.");
            }
        }
        return 0;
    }

    public BigDecimal obliczCeneDoZaplaty(Samochod samochod, int iloscWlanegoPaliwa) {
        return samochod.getRodzajPaliwa().cena.multiply(new BigDecimal(iloscWlanegoPaliwa));
    }

}


