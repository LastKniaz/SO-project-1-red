package SO2;

import java.util.Comparator;

public class ComparatorSJF implements Comparator<Proces> {
    @Override
    public int compare(Proces p1, Proces p2) {
        if (p1.getCzasDoKoncaWykon() == p2.getCzasDoKoncaWykon()) return 0;
        else if(p1.getCzasDoKoncaWykon() < p2.getCzasDoKoncaWykon()) return -1;
        else return 1;
    }
}
