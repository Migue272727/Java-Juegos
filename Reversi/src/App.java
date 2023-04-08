import java.util.*;
import java.util.Scanner;

public class App {

    public static void inciarReversi(char[][] t) {
        for (int i = 0; i < t.length; i++) {
            for (int j = 0; j < t[i].length; j++) {
                t[i][j] = 'X';
            }
        }
    }

    public static void mostrarReversi(char[][] t) {
        int cantRojas = 0;
        int cantAzules = 0;

        for (int i = 0; i < t.length; i++) {
            for (int j = 0; j < t[i].length; j++) {
                if (t[i][j] == 'R') {
                    cantRojas++;
                } else if (t[i][j] == 'A') {
                    cantAzules++;
                }
                System.out.print(t[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("Azules " + cantAzules);
        System.out.println("Rojas " + cantRojas);
    }

    public static boolean insertarFichaEnColumna(char[][] t, int columnaR, char r, Scanner sc) {
        int filaF = t.length - 1;
        boolean insertado = false;
        while (!insertado && filaF >= 0) {
            if (t[filaF][columnaR] == 'X') {
                t[filaF][columnaR] = r;
                insertado = true;
            }
            filaF--;
        }
        return insertado;
    }

    public static int devolverFila(char[][] t, int columnaR, char r, Scanner sc) {
        int filaF = 0;
        boolean insertado = false;
        while (!insertado) {
            filaF++;
            if (t[filaF][columnaR] == r) {
                insertado = true;
            }
        }
        return filaF;
    }

    private static void rellenar(char[][] t, int fila, int columna, char c) {

        if (columna + 1 < t[0].length) {
            t[fila][columna + 1] = c;
        }
        if (fila + 1 < t.length) {
            t[fila + 1][columna] = c;
        }
        if (columna - 1 >= 0) {
            t[fila][columna - 1] = c;
        }
        if (fila - 1 >= 0) {
            t[fila - 1][columna] = c;
        }
        if (fila - 1 >= 0 && columna - 1 >= 0) {
            t[fila - 1][columna - 1] = c;
        }
        if (fila + 1 < t.length && columna + 1 < t[0].length) {
            t[fila + 1][columna + 1] = c;
        }
        if (fila - 1 >= 0 && columna + 1 < t[0].length) {
            t[fila - 1][columna + 1] = c;
        }

    }

    private static boolean finJuego(char[][] tablero) {
        int blancos = 0;
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                if (tablero[i][j] == 'X') {
                    blancos++;
                }
            }
        }
        if (blancos == 0) {
            return true;
        } else {
            return false;
        }
    }

    private static int columnaSinExcepciones(Scanner sc, int columnaR) {
        boolean salir;

        do {
            try {
                salir = false;
                columnaR = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Solo se permiten numeros");
                salir = true;
            } finally {
                sc.nextLine();
            }
        } while (salir);

        return columnaR;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int fila, columna, columnaR = 0, columnaA = 0, filaA, filaR;
        boolean insertado;

        do {
            System.out.println("Indique la cantidad de filas entre 4 y 8");
            fila = sc.nextInt();
        } while (fila < 4 || fila > 8);

        do {
            System.out.println("Indique la cantidad de columnas entre 4 y 8");
            columna = sc.nextInt();
        } while (columna < 4 || columna > 8);

        char[][] tablero = new char[fila][columna];

        inciarReversi(tablero);
        mostrarReversi(tablero);

        while (!finJuego(tablero)) {

            System.out.println("TURNO ROJO");
            System.out.println("Indique la columna para su ficha");
            columnaR = columnaSinExcepciones(sc, columnaR);
            columnaR--;
            if (columnaR > columna - 1 || columnaR < 0) {
                System.out.println("Error");
            } else {
                insertado = insertarFichaEnColumna(tablero, columnaR, 'R', sc);
                if (insertado) {
                    filaR = devolverFila(tablero, columnaR, 'R', sc);
                    rellenar(tablero, filaR, columnaR, 'R');
                }
                mostrarReversi(tablero);
            }

            System.out.println("Turno AZUL");

            System.out.println("Indique la columna para su ficha");
            columnaA = columnaSinExcepciones(sc, columnaA);
            columnaA--;
            if (columnaA > columna - 1 || columnaA < 0) {
                System.out.println("Error");

            } else {
                insertado = insertarFichaEnColumna(tablero, columnaA, 'A', sc);
                if (insertado) {
                    filaA = devolverFila(tablero, columnaA, 'A', sc);
                    rellenar(tablero, filaA, columnaA, 'A');
                }
                mostrarReversi(tablero);
            }
        }
    }
}
