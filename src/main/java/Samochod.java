public class Samochod {

    private int aktualnyPoziomPaliwa;
    private int pojemnoscZbiornika = 50;
    private RodzajPaliwa rodzajPaliwa;

    public int getAktualnyPoziomPaliwa() {
        return aktualnyPoziomPaliwa;
    }

    public Samochod() {
    }

    public Samochod(int pojemnoscZbiornika, int aktualnyPoziomPaliwa, RodzajPaliwa rodzajPaliwa) {
        this.pojemnoscZbiornika = pojemnoscZbiornika;
        this.aktualnyPoziomPaliwa = aktualnyPoziomPaliwa;
        this.rodzajPaliwa = rodzajPaliwa;
    }

    public int getPojemnoscZbiornika() {
        return pojemnoscZbiornika;
    }

    public void setAktualnyPoziomPaliwa(int aktualnyPoziomPaliwa) {
        this.aktualnyPoziomPaliwa = aktualnyPoziomPaliwa;
    }

    public RodzajPaliwa getRodzajPaliwa() {
        return rodzajPaliwa;
    }
}

