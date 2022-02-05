public class Watek2 implements Runnable {
    private int start, end;
    private Obraz obraz;


    Watek2(int start, int end, Obraz obraz) {
        this.start = start;
        this.end = end;
        this.obraz = obraz;

    }

    public void run() {
        obraz.calculate_histogramPararrel2(start, end);
        obraz.print_histogramPararrel2(start, end);
    }
}