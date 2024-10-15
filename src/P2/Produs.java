package P2;

import java.time.LocalDate;

public class Produs {
    private String denumire;
    private float pretul;
    private int cantitatea;
    private LocalDate dataExpirarii;
    public static float incasari = 0.0f;

    public Produs(String denumire, float pretul, int cantitatea, LocalDate dataExpirarii) {
        this.denumire = denumire;
        this.pretul = pretul;
        this.cantitatea = cantitatea;
        this.dataExpirarii = dataExpirarii;
    }


    public String getDenumire() {
        return denumire;
    }

    public float getPretul() {
        return pretul;
    }

    public int getCantitatea() {
        return cantitatea;
    }

    public LocalDate getDataExpirarii() {
        return dataExpirarii;
    }


    public void setCantitatea(int cantitatea) {
        this.cantitatea = cantitatea;
    }

    public boolean esteExpirat() {
        return LocalDate.now().isAfter(dataExpirarii);
    }

    @Override
    public String toString() {
        return String.format("%s, %.2f, %d, %s", denumire, pretul, cantitatea, dataExpirarii);
    }

    public boolean vinde(int cantitate) {
        if (cantitate <= cantitatea) {
            cantitatea -= cantitate;
            incasari += cantitate * pretul;
            return true;
        }
        return false;
    }
}