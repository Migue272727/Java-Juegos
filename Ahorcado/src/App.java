import java.util.Arrays;
import java.util.Scanner;

public class App {

    public static char[] generarPalabra() {
        int opcion;
        String[] palabras = {"magdalena","azucar","taladro", "raton", "caja","loco","auriculares","calcetines","moneda","billete","banco","monitor", "ventana", "pato", "negro", "gato", "marca", "oreo", "alfombrilla", "dedo", "polo"};
        opcion = (int) (Math.random() * palabras.length);
        char[] palabra = palabras[opcion].toCharArray();
        return palabra;
    }

    public static boolean terminado(char[] c, char[] a) {
        boolean terminado = false;
        int tamano = 0;

        for (int i = 0; i < c.length; i++) {
            if (c[i] == a[i]) {
                tamano++;
            }
        }

        if (tamano == c.length) {
            terminado = true;
        }

        return terminado;
    }

    public static String figura(int linea){
        String figura = "";
        if (linea == 1) {
            figura += "*\n*\n*\n*\n*\n*\n*\n*\n*";
        }else if (linea == 2) {
            figura += "*\n*\n*\n*\n*\n*\n*\n*\n* * * * * * * * *";
        }else if (linea == 3) {
            figura += "* * * * * * * * *\n*\n*\n*\n*\n*\n*\n*\n* * * * * * * * *";
        }else{
            figura += "* * * * * * * * *\n*\n*\n*\n*\n*\n*\n*\n* * * * * * * * *";
        }
        return figura;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[] palabra = generarPalabra();
        char adivinar[] = new char[palabra.length];
        char letra;
        int oportunidades = 0;
        
        System.out.println(Arrays.toString(adivinar));
        do {
            boolean estar = false;
            boolean noOpor = false;

            do {
                estar = false;

                System.out.println("Indique una letra");
                letra = (char) (scanner.nextLine().charAt(0));

                for (int i = 0; i < adivinar.length; i++) {
                    if (adivinar[i] == letra) {
                        estar = true;
                    }
                }

                if (estar) {
                    System.out.println("Esa letra ya se encuentra colocada");
                }

                for (int i = 0; i < adivinar.length && !estar; i++) {
                    if (palabra[i] == letra) {
                        adivinar[i] = letra;
                        noOpor = true;
                    }
                }
            } while (estar);

            System.out.println(Arrays.toString(adivinar));

            if (!noOpor) {
                oportunidades++;
                System.out.println("\n"+figura(oportunidades));
            }

        } while (!terminado(palabra, adivinar) && oportunidades != 4);
        System.out.println();
        if (terminado(palabra, adivinar)) {
            System.out.println("\nLa ha acertadoo");
        }
        if (!terminado(palabra, adivinar)) {
            System.out.println(palabra);
        }
    }
}
