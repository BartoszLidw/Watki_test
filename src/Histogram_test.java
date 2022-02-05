import java.util.Scanner;



public class Histogram_test {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Set image size: n (#rows), m(#kolumns)");
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int liczba_znakow = scanner.nextInt();
        Obraz obraz_1 = new Obraz(n, m, liczba_znakow);
        //Obraz obraz_2 = new Obraz(n, m, liczba_znakow);
        obraz_1.calculate_histogram();
        obraz_1.print_histogram();

        System.out.println("Set number of threads");
        int num_threads2 = scanner.nextInt();
         int num_threads = liczba_znakow;

         Watek[] NewThr = new Watek[liczba_znakow];

         for (int i = 0; i < num_threads; i++) {
             (NewThr[i] = new Watek(i,obraz_1)).start();
         }

         for (int i = 0; i < num_threads; i++) {
             try {
         	NewThr[i].join();
             } catch (InterruptedException e) {}
         }
//System.out.println("Set number of threads");
       // int num_threads2 = scanner.nextInt();

        Thread[] NewThr2 = new Thread[num_threads2];

        int tThread = (liczba_znakow/ num_threads2);
        for(int i = 0; i < num_threads2; i++)
        {
            int start = tThread * i;
            int end = Math.min(((i + 1 ) * tThread), tThread);
            (NewThr2[i] = new Thread(new Watek2(start, end, obraz_1))).start();
        }
        for (int i = 0; i < num_threads2; i++) {
            try {
                NewThr2[i].join();
            } catch (InterruptedException e) {}
        }
    }

}

