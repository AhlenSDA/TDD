import java.math.BigDecimal;

public enum RodzajPaliwa {
    BEZOLOWIOWA_95(new BigDecimal(4.80)),
    DIESEL(new BigDecimal(4.50)),
    GAZ(new BigDecimal(3.50));

    BigDecimal cena;

    RodzajPaliwa(BigDecimal cena) {
        this.cena = cena;
    }
}
