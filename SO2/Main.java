package SO2;

public class Main {

    public static void main(String[] args) {
        Tests tests = new Tests();
        Procesy demonstracja = new Procesy(5);
        System.out.println("Demonstracja:");
        int i = 0;
        for (Proces proces: demonstracja.getProcesy()) {
            System.out.println("Proces " + i + ": " + proces);
            i++;
        }
        tests.Tests(demonstracja);

        tests.Tests(new Procesy(25));
        tests.Tests(new Procesy(100));
        tests.Tests(new Procesy(300));
        tests.Tests(new Procesy(1000));
        tests.Tests(new Procesy(10000));
    }
}
