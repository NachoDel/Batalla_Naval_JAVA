package src;

import src.Naves.Buque;
import src.Naves.Nave;
import src.Naves.Submarino;
import src.Tableros.Coordenada;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        int m = Menu.menu();
        switch (m) {
            case 1:
                Scanner scanner = new Scanner(System.in);
                int n;
                do {
                    System.out.println("Ingrese tamanio del tablero: ");
                    n = scanner.nextInt();
                    if (n < 10)
                        System.out.println("El tamanio del tablero debe ser mayor o igual a 10");
                } while (n < 10);

                Jugador j1 = new Jugador("Jugador 1", n, n);
                Jugador j2 = new Jugador("Jugador 2", n, n);

                j1.getTablero().mostrarTablero();
                System.out.println("Colocando naves de " + j1.getNombre());
                j1.crearYColocarNaves();
                System.out.println("\n" + "\n" + "\n" + "\n");

                j2.getTablero().mostrarTablero();
                System.out.println("Colocando naves de " + j2.getNombre());
                j2.crearYColocarNaves();

                System.out.println("Comienza disparando " + j1.getNombre());

                while (j1.getTablero().getNavesConVida() != 0 || j2.getTablero().getNavesConVida() != 0) {
                    do {
                        System.out.println("Turno de disparar de " + j1.getNombre());
                        j2.getTablero().mostrarOculto();
                    } while (j1.disparar(j2));

                    do {
                        System.out.println("Turno de disparar de " + j2.getNombre());
                        j1.getTablero().mostrarOculto();
                    } while (j2.disparar(j1));

                }
                if (j2.getTablero().getNavesConVida() == 0) {
                    System.out.println("Gano " + j1.getNombre());
                } else {
                    System.out.println("Gano " + j2.getNombre());
                }
                break;

            case 2:
                //para simular y debuguear
                Jugador j3 = new Jugador("Jugador 1", 10, 10);
                Jugador j4 = new Jugador("Jugador 2", 10, 10);

                Nave subma = new Submarino(true);
                Coordenada c = new Coordenada(0,0);
                j3.getTablero().rellenar(subma, c);

                Nave buque = new Buque(true);
                c = new Coordenada(c.getFila(), c.getColumna()+1);
                j3.getTablero().rellenar(buque, c);

                Nave subma2 = new Submarino(false);
                Coordenada c2 = new Coordenada(0,0);
                j4.getTablero().rellenar(subma2, c2);

                Nave buque2 = new Buque(false);
                c2 = new Coordenada(c2.getFila()+1, c2.getColumna());
                j4.getTablero().rellenar(buque2, c2);


                j3.getTablero().mostrarTablero();
                System.out.println(" ");
                j4.getTablero().mostrarTablero();

                j3.disparar(j4);
                j4.getTablero().mostrarOculto();

                j3.disparar(j4);
                j4.getTablero().mostrarOculto();

                j3.disparar(j4);
                j4.getTablero().mostrarOculto();

                j3.disparar(j4);
                j4.getTablero().mostrarOculto();









            default:
                break;
        }
    }
}
