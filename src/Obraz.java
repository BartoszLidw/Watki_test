import  java.util.Random;


class Obraz {

    private int size_n;
    private int size_m;
    private char[][] tab;
    private char[] tab_symb;
    private int[] histogram;
    private int[] histPararrel;
    private int[] histPararrel2;
    private int lSymboli;

    public Obraz(int n, int m, int l_symb) {

        this.size_n = n;
        this.size_m = m;
        lSymboli = l_symb;
        tab = new char[n][m];
        tab_symb = new char[lSymboli];

        final Random random = new Random();

        // for general case where symbols could be not just integers
        for(int k=0;k<lSymboli;k++) {
            tab_symb[k] = (char)(k+33); // substitute symbols
        }

        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                tab[i][j] = tab_symb[random.nextInt(lSymboli)];  // ascii 33-127
                //tab[i][j] = (char)(random.nextInt(94)+33);  // ascii 33-127
                System.out.print(tab[i][j]+" ");
            }
            System.out.print("\n");
        }
        System.out.print("\n\n");

        histogram = new int[lSymboli];
        histPararrel = new int[lSymboli];
        histPararrel2 = new int[lSymboli];
        clear_histogram();
    }

    public void clear_histogram(){

        for(int i=0;i<lSymboli;i++) histogram[i]=0;
        for(int i=0;i<lSymboli;i++) histPararrel[i]=0;
        for(int i=0;i<lSymboli;i++) histPararrel2[i]=0;
    }

    public void calculate_histogram() {

        for (int i = 0; i < size_n; i++) {
            for (int j = 0; j < size_m; j++) {

                // optymalna wersja obliczania histogramu, wykorzystująca fakt, że symbole w tablicy
                // można przekształcić w indeksy tablicy histogramu
                // histogram[(int)tab[i][j]-33]++;

                // wersja bardziej ogólna dla tablicy symboli nie utożsamianych z indeksami
                // tylko dla tej wersji sensowne jest zrównoleglenie w dziedzinie zbioru znaków ASCII
                for (int k = 0; k < lSymboli; k++) {
                    if (tab[i][j] == tab_symb[k]) histogram[k]++;
                    //if(tab[i][j] == (char)(k+33)) histogram[k]++;
                }

            }
        }
    }



    public void print_histogram(){

        for(int i=0;i<lSymboli;i++) {
            System.out.print(tab_symb[i]+" "+histogram[i]+"\n");
            //System.out.print((char)(i+33)+" "+histogram[i]+"\n");
        }

    }
    public void calculate_histogramPararrel(int id){
        for(int i=0;i<size_n;i++) {
            for(int j=0;j<size_m;j++) {
                if(tab[i][j] == tab_symb[id]) incrementHist(id);
            }

        }
    }
    public synchronized void print_histogramPararrel(int id){
        System.out.print(tab_symb[id]+": ");
        for (int i = 0; i < histPararrel[id]; i++) {
            System.out.print("=");
        }
        System.out.print("\n");

    }
    public void calculate_histogramPararrel2(int start, int end){
        for(int i = 0; i <size_n;i++) {
            for(int j=0;j <size_m;j++) {
                for (int k = start; k < end; k++)
                {
                    if (tab[i][j] == tab_symb[k])
                        histPararrel2[k] ++;
                }
            }

        }
    }
    public synchronized void print_histogramPararrel2(int start, int end){
       for(int k = start; k < end; k++){
           System.out.print(tab_symb[k]+": ");
        for (int i = 0; i < histPararrel[k]; i++) {
            System.out.print("=");
        }
           System.out.println();
        }

    }

    public char getTab(int i, int j){
        return tab[i][j];
    }
    public char getSym(int i){
        return tab_symb[i];
    }
    public synchronized void incrementHist(int i) {
        histPararrel[i]++;
    }
    public int getSymbolsAmount(){
        return lSymboli;
    }
    public int getSize_n(){
        return size_n;
    }
    public int getSize_m(){
        return size_m;
    }
    public void checkPoprawnosc(){
        for (int i = 0; i < lSymboli; i++) {
            if (histPararrel[i]!=histogram[i]){
                System.out.println("Błąd obliczeń");
            }
        }
    }
}
