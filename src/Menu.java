package src;

import java.util.Scanner;

public abstract class Menu {

    public static int menu() {
        System.out.println("\n\n\n");
        System.out.println("--------------------------");
        System.out.println("\u001B[31m       BATALLA NAVAL    \u001B[0m");
        System.out.println("--------------------------");
        System.out.println("\u001B[31m 1.\u001B[0m Iniciar Juego");
        System.out.println("\u001B[31m 2.\u001B[0m Salir");

        Scanear scanear = Scanear.getInstance();
        Scanner scanner = scanear.getScanner();
        System.out.print("Elija una opcion: ");
        int opcion = 0;
        do {
            try {
                opcion = scanner.nextInt();
                if(opcion != 1 && opcion != 2)
                    System.out.print("Opcion no valida, intente de nuevo: ");
            } catch (Exception e) {
                System.out.print("Opcion no valida, intente de nuevo: ");
                scanner.next(); // consume the invalid input
            }
        } while (opcion != 1 && opcion != 2);
        return opcion;
    }
}
