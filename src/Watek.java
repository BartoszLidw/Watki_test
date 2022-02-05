public class Watek extends Thread {
 private final int id;
 private final Obraz obraz;

 Watek(int id, Obraz obraz) {
  this.id = id;
  this.obraz = obraz;
 }
 public void run() {
  obraz.calculate_histogramPararrel(id);
  obraz.print_histogramPararrel(id);
 }
}