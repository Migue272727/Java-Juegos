import java.util.InputMismatchException;
import java.util.Scanner;

public class App {

    public static void inicioFlota(char[][] t) {

        for (int indice = 0; indice < t.length; indice++) {
            for (int recorrido = 0; recorrido < t[indice].length; recorrido++) {
                t[indice][recorrido] = '_';
            }
        }
    }

    public static void mostrarFlota(char[][] t) {
        for (int indice = 0; indice < t.length; indice++) {
            for (int recorrido = 0; recorrido < t[indice].length; recorrido++) {
                System.out.print(t[indice][recorrido] + " ");
            }
            System.out.println();
        }
    }

    public static void rellenarFlota(char[][] t) {
        int incrementador = 0;

        while (incrementador < 10) {
            int fila, columna, filaM, columnaM;
            do {
                do {
                    fila = (int) (Math.random() * 5);
                    columna = (int) (Math.random() * 10);
                } while (t[fila][columna] != '_');
                do {
                    filaM = (int) (Math.random() * 5);
                    columnaM = (int) (Math.random() * 10);
                } while (t[filaM][columnaM] != '_');

            } while (ocupadoBarco(t, fila, columna) && ocupadoMina(t, filaM, columnaM));
            t[fila][columna] = 'b';
            t[filaM][columnaM] = '.';

            incrementador++;
        }
    }

    public static boolean ocupadoBarco(char[][] t, int fila, int columna) {
        boolean estar = false;
        if (t[fila][columna] == 'b') {
            estar = true;
        }
        return estar;
    }

    public static boolean ocupadoMina(char[][] t, int fila, int columna) {
        boolean estar = false;
        if (t[fila][columna] == '.') {
            estar = true;
        }
        return estar;
    }

    public static int controlFilas(Scanner sc, int fila) {
        do {
            boolean salir;
            do {
                try {
                    salir = false;
                    fila = sc.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Solo se permiten numeros");
                    salir = true;
                } finally {
                    sc.nextLine();
                }
            } while (salir);

            if (fila <= 0 || fila > 5) {
                System.out.println("Se a equivocado en la fila");
            }
        } while (fila <= 0 || fila > 5);
        fila--;
        return fila;
    }

    public static int controlColumnas(Scanner sc, int columna) {
        do {
            boolean salir;
            do {
                try {
                    salir = false;
                    columna = sc.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Solo se permiten numeros");
                    salir = true;
                } finally {
                    sc.nextLine();
                }
            } while (salir);

            if (columna <= 0 || columna > 10) {
                System.out.println("Se a equivocado en la columna");
            }
        } while (columna <= 0 || columna > 10);
        columna--;
        return columna;
    }

    public static void generadorSonar(char[][] t, int fila, int columna) {
        char[][] matriz = new char[5][10];
        matriz[fila][columna] = t[fila][columna];
        inicioFlota(matriz);
        
        if ((fila + 1 < 5)) {
            matriz[fila+1][columna] = t[fila+1][columna];
        }
        if (columna+1 < 10) {
            matriz[fila][columna+1] = t[fila][columna+1];
        }
        if ((columna+1 < 10 && columna+1 >= 0) && (fila-1 <= 5 && fila-1 >= 0)) {
            matriz[fila-1][columna+1] = t[fila-1][columna+1];
        }
        if (columna-1 >= 0) {
            matriz[fila][columna-1] = t[fila][columna-1];
        }
        if ((columna-1 >= 0 && columna-1 < 10) && (fila-1 < 5 && fila-1 >= 0)) {
            matriz[fila-1][columna-1] = t[fila-1][columna-1];
        }
        if (fila -1 >= 0) {
            matriz[fila-1][columna] = t[fila-1][columna];
        }
        if (columna-1 >= 0 && fila + 1 < 5 ) {
            matriz[fila+1][columna-1] = t[fila+1][columna-1];
        }
        if ((columna+1 < 10 && columna+1 >= 0)&&(fila+1 < 5 && fila+1 >= 0)) {
            matriz[fila+1][columna+1] = t[fila+1][columna+1];
        }

        System.out.println();
        mostrarFlota(matriz);
    }

    public static int elegirSonar(Scanner sc, int numSonars, int opcionSonar) {
        boolean error;
        do {
            try {
                error = false;
                System.out.println("Indique si quiere utilizar un sonar " + numSonars + "/2 1/si, Resto/no");
                opcionSonar = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Solo se permiten numeros");
                error = true;
            }finally{
                sc.nextLine();
            }    
        } while (error);
        return opcionSonar;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[][] tablero = new char[5][10];
        char[][] tabUsuaio = new char[5][10];
        int fila = 0, columna = 0, numSonars = 0, opcionSonar = 0, filaS = 0, columnaS = 0;
        int cantBarcos = 10;
        int vidas = 3;
        inicioFlota(tabUsuaio);
        mostrarFlota(tabUsuaio);
        inicioFlota(tablero);
        rellenarFlota(tablero);

        do {
            System.out.println();

            if (numSonars < 2) {
                opcionSonar = elegirSonar(sc, numSonars, opcionSonar);

                switch (opcionSonar) {
                    case 1 -> {
                        System.out.println("Indique la fila donde usará el sonar");
                        filaS = controlFilas(sc, filaS);
                        System.out.println("Indique la columna donde usará el sonar");
                        columnaS = controlColumnas(sc, columnaS);
                        generadorSonar(tablero, filaS, columnaS);
                        numSonars++;
                    }
                }
            }

            System.out.println("Introduce la fila del barco a derribar");
            fila = controlFilas(sc, fila);

            System.out.println("Introduce la columna del barco a derribar");
            columna = controlColumnas(sc, columna);

            if (ocupadoBarco(tablero, fila, columna)) {
                System.out.println("A derribado un barco");
                tabUsuaio[fila][columna] = 'x';
                cantBarcos--;
            }
            if (ocupadoBarco(tabUsuaio, fila, columna)) {
                System.out.println("Ha marcado un barco derrivado por lo tanto ha gastado municion innecesariamente");
                vidas--;
            } else if (ocupadoMina(tabUsuaio, fila, columna)) {
                System.out.println("Ha navegado por donde habia una mina anteriormente tenga cuidado podria haber mas");
            }

            if (ocupadoMina(tablero, fila, columna)) {
                if (tabUsuaio[fila][columna] == '_') {
                    vidas--;
                    if (vidas > 0) {
                        System.out.println("Has pisado una mina submarina\nrecuerda te quedan " + vidas + " vidas");
                    }
                    tabUsuaio[fila][columna] = '.';
                }
            }

            mostrarFlota(tabUsuaio);

        } while (vidas != 0 && cantBarcos != 0);
        if (vidas == 0) {
            System.out.println("Has pisado demasiadas minas, te has quedado sin vidas");
        }
        if (cantBarcos == 0) {
            System.out.println("Enhorabuena has destruido todos los barcoos");
        }
        mostrarFlota(tablero);
    }
}
