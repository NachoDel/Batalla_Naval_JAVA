import java.util.Scanner;

public abstract class Menu {
    
    public static int menu(){
       System.out.println("       BATALLA NAVAL    ");
       System.out.println("--------------------------");
       System.out.println("1. Iniciar Juego");
       System.out.println("2. Salir");
       
       Scanner scanner = new Scanner(System.in);
       System.out.print("Elija una opcion: ");
       int n = scanner.nextInt();
       return n;
    }
}
