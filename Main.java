import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        /*
         * Tablero tablero = new Tablero(10 ,10);
         * 
         * Nave submarino = new Nave("submarino", true,2);
         * Nave buque = new Nave("Buque", true,4);
         * Nave portaaviones = new Nave("Portaaviones", true,5);
         * Nave acorazado = new Nave("Acorazado", true,6);
         * 
         * tablero.colocarNave(submarino, 0, 0);
         * tablero.colocarNave(buque, 0, 4);
         * tablero.colocarNave(portaaviones, 1, 0);
         * tablero.colocarNave(acorazado, 1, 9);
         * 
         * 
         * tablero.disparar(2, 2,1);
         * tablero.disparar(8, 9,1);
         * tablero.disparar(2, 2,1);
         * tablero.disparar(2, 4,1);
         * 
         * tablero.mostrarTablero();
         */
        int n = Menu.menu();
        switch (n) {
            case 1:
                // Creacion del tablero
                int f1, c1;
                Scanner scanner = new Scanner(System.in);
                System.out.print("Ingrese tamanio de tablero: ");
                int x = scanner.nextInt();
                Tablero tableroJugador1 = new Tablero(x, x);
                tableroJugador1.mostrarTablero();

                // Creacion de las Naves
                // Submarino
                System.out.println("Submarino - 2 casillas");

                Nave submarinoJ1 = new Submarino(); // Polimorfismo (Creo un objeto de tipo submarino, como si fuese
                                                    // tipo NAVE)
                System.out.print("Ingrese coord iniciales para submarino: ");
                System.out.print("Fila: ");
                f1 = scanner.nextInt();
                System.out.println();
                System.out.print("Columna");
                c1 = scanner.nextInt();
                tableroJugador1.colocarNave(submarinoJ1, f1, c1);
                tableroJugador1.mostrarTablero();

                // Buque
                System.out.println("Buque -> 4 casillas");
                Nave buqueJ1 = new Buque();
                System.out.print("Ingrese coord iniciales para buque: ");
                System.out.print("Fila: ");
                f1 = scanner.nextInt();
                System.out.println();
                System.out.print("Columna");
                c1 = scanner.nextInt();
                tableroJugador1.colocarNave(buqueJ1, f1, c1);
                tableroJugador1.mostrarTablero();

                // Portaaviones
                System.out.println("Portaaviones -> 5 casillas");
                Nave portaavionesJ1 = new Portaaviones();
                System.out.print("Ingrese coord iniciales para Portaaviones: ");
                System.out.print("Fila: ");
                f1 = scanner.nextInt();
                System.out.println();
                System.out.print("Columna: ");
                c1 = scanner.nextInt();
                tableroJugador1.colocarNave(portaavionesJ1, f1, c1);
                tableroJugador1.mostrarTablero();

                // Acorazado
                System.out.println("Acorazado -> 6 casillas");
                Nave AcorazadoJ1 = new Acorazado();
                System.out.print("Ingrese coord iniciales para Acorazado: ");
                System.out.print("Fila: ");
                f1 = scanner.nextInt();
                System.out.println();
                System.out.print("Columna");
                c1 = scanner.nextInt();
                tableroJugador1.colocarNave(AcorazadoJ1, f1, c1);
                tableroJugador1.mostrarTablero();

                // Logica encargada del juego
                int d;
                do {
                    System.out.print("Ingrese Fila de disparo: ");
                    f1 = scanner.nextInt();
                    System.out.println();
                    System.out.print("Ingrese Columna de disparo: ");
                    c1 = scanner.nextInt();
                    System.out.println();
                    tableroJugador1.disparar(f1, c1, 1);
                    tableroJugador1.mostrarTablero();

                    System.out.print("Quiere seguir disparando?: ");
                    d = scanner.nextInt();
                } while (d != 0);

                break;

            default:
                break;
        }
    }
}
