package src;

import src.Naves.Buque;
import src.Naves.Nave;
import src.Naves.Submarino;
import src.PowerUps.PowerUpFactory;
import src.Tableros.Coordenada;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        int m = Menu.menu();
        switch (m) {
            case 1:
                Scanear scanear = Scanear.getInstance();
                Scanner scanner = scanear.getScanner();

                //pedimos tamaño del tablero al usuario validando que sea mayor o igual a 10 y sea un numero
                int n=0;
                do {
                    System.out.print("Ingrese tamanio del tablero: ");
                    try {
                        n = scanner.nextInt();
                        if (n < 10)
                            System.out.println("El tamanio del tablero debe ser mayor o igual a 10");
                    }catch (Exception e) {
                        System.out.println("Ingrese un numero mayor a 10");
                        scanner.next();
                    }
                } while (n < 10);


                Jugador j1 = new Jugador("Jugador 1", n, n);
                Jugador j2 = new Jugador("Jugador 2", n, n);

                j1.setOponente(j2);
                j2.setOponente(j1);

                j1.getTablero().mostrarTablero();
                System.out.println("Colocando naves de " + j1.getNombre());
                j1.crearYColocarNaves();
                System.out.println("\n" + "\n" + "\n" + "\n"+"\n" + "\n" + "\n" + "\n"+"\n" + "\n" + "\n" + "\n"+"\n" + "\n" + "\n" + "\n");

                j2.getTablero().mostrarTablero();
                System.out.println("Colocando naves de " + j2.getNombre());
                j2.crearYColocarNaves();

                System.out.println("\n" + "\n" + "\n" + "\n"+"\n" + "\n" + "\n" + "\n"+"\n" + "\n" + "\n" + "\n"+"\n" + "\n" + "\n" + "\n");

                System.out.println("Comienza disparando " + j1.getNombre());

                while (j1.getTablero().getNavesConVida() != 0 || j2.getTablero().getNavesConVida() != 0) {
                    do {
                        System.out.println("\u001B[33m"+ "Turno de disparar de " + j1.getNombre()+"\u001B[0m");
                        j2.getTablero().mostrarOculto();
                        String input = "N";
                        while(!j1.getPowerUps().isEmpty()) {
                            System.out.println("Tienes los siguientes powerUps disponibles, deseas usar alguno? Y: si, N: no");
                            System.out.println(j1.powerUpsToString());
                            input = scanner.next();
                            if (input.equalsIgnoreCase("Y")) {
                                System.out.println("Cual desea usar?");
                                input = scanner.next();
                                j1.activarPowerUp(input);
                            } else {
                                break;
                            }
                        }

                    } while (j1.disparar(j2));

                    String enter;
                    System.out.println("Presiona Enter para continuar...");
                    enter = scanner.nextLine();
                    System.out.println("\n" + "\n" + "\n" + "\n"+"\n" + "\n" + "\n" + "\n"+"\n" + "\n" + "\n" + "\n"+"\n" + "\n" + "\n" + "\n");

                    do {
                        System.out.println("\u001B[36m"+ "Turno de disparar de " + j2.getNombre()+ "\u001B[0m");
                        j1.getTablero().mostrarOculto();
                        String input = "N";
                        while(!j2.getPowerUps().isEmpty()) {
                            System.out.println("Tienes los siguientes powerUps disponibles, deseas usar alguno? Y: si, N: no");
                            System.out.println(j2.powerUpsToString());
                            input = scanner.next();
                            if (input.equalsIgnoreCase("Y")) {
                                System.out.println("Cual desea usar?");
                                input = scanner.next();
                                j2.activarPowerUp(input);
                            } else {
                                break;
                            }
                        }
                    } while (j2.disparar(j1));

                    System.out.println("Presiona Enter para continuar...");
                    enter = scanner.nextLine();
                    System.out.println("\n" + "\n" + "\n" + "\n"+"\n" + "\n" + "\n" + "\n"+"\n" + "\n" + "\n" + "\n"+"\n" + "\n" + "\n" + "\n");

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

                j3.setOponente(j4);
                j4.setOponente(j3);

                Nave subma = new Submarino(true);
                Coordenada c = new Coordenada(0, 0);
                j3.getTablero().rellenar(subma, c);

                Nave buque = new Buque(true);
                c = new Coordenada(c.getFila(), c.getColumna() + 1);
                j3.getTablero().rellenar(buque, c);

                Nave subma2 = new Submarino(false);
                Coordenada c2 = new Coordenada(0, 0);
                j4.getTablero().rellenar(subma2, c2);

                Nave buque2 = new Buque(false);
                c2 = new Coordenada(c2.getFila() + 1, c2.getColumna());
                j4.getTablero().rellenar(buque2, c2);


                PowerUpFactory fabricaPowerUp = new PowerUpFactory();

                j3.getTablero().mostrarTablero();
                System.out.println(" ");
                j4.getTablero().mostrarTablero();

                j3.disparar(j4);
                j4.getTablero().mostrarOculto();

                j3.disparar(j4);
                j4.getTablero().mostrarOculto();

                j3.addPowerUp(fabricaPowerUp.crearPowerUp("Radar"));
                j3.activarPowerUp("Radar");
                
                j3.disparar(j4);
                j4.getTablero().mostrarOculto();

                j3.disparar(j4);
                j4.getTablero().mostrarOculto();




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
