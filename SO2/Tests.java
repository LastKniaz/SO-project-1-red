package SO2;

public class Tests {
    public void Tests(Procesy procesy)
    {
        System.out.println("Ilość procesów: " + procesy.getProcesy().size());

        System.out.println("średni czas oczekiwania, FCFS: " + procesy.FCFS() / procesy.getProcesy().size());
        System.out.println("średni czas oczekiwania, SJF: " + procesy.SJF() / procesy.getProcesy().size());
        System.out.println("średni czas oczekiwania, SRJF: " + procesy.SRJF() / procesy.getProcesy().size());
        System.out.println("średni czas oczekiwania, RR: " + procesy.RR() / procesy.getProcesy().size() + "\n");


    }
}
