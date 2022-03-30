package SO2;

import java.util.Objects;
import java.util.Random;

public class Proces {
    private int czasDoKoncaWykon;
    private final int dlugoscProcesu;
    private int czasPojawSystemie;

    public Proces(int ilosc) {
        Random rndm = new Random();
        this.czasDoKoncaWykon = rndm.nextInt( 1, 12);
        this.dlugoscProcesu = this.czasDoKoncaWykon;
        this.czasPojawSystemie = rndm.nextInt(1, ilosc*2);
    }

    public Proces(Proces proces) {
        this.czasDoKoncaWykon = proces.getCzasDoKoncaWykon();
        this.dlugoscProcesu = this.czasDoKoncaWykon;
        this.czasPojawSystemie = proces.getCzasPojawSystemie();
    }

    public int getCzasDoKoncaWykon() {
        return czasDoKoncaWykon;
    }

    public int getDlugoscProcesu() {
        return dlugoscProcesu;
    }

    public int getCzasPojawSystemie() {
        return czasPojawSystemie;
    }

    public void decreaseCzasDoKoncaWykon(int t) {
        this.czasDoKoncaWykon -= t;
    }

    @Override
    public String toString() {
        return "Czas pojawiania się w systemie: " + czasPojawSystemie +
                ",  czas do końca wykonania: " + czasDoKoncaWykon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Proces proces = (Proces) o;
        return czasDoKoncaWykon == proces.czasDoKoncaWykon && czasPojawSystemie == proces.czasPojawSystemie;
    }

    @Override
    public int hashCode() {
        return Objects.hash(czasDoKoncaWykon, czasPojawSystemie);
    }
}

