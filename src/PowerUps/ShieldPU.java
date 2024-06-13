package src.PowerUps;

import src.Naves.Nave;

import java.util.Scanner;

public class ShieldPU extends PowerUp {


    public ShieldPU(){
    }

    @Override
    public void activar(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("A que barco desea ponerle un escudo?");
        System.out.println("Barcos Disponibles: ");
        for (Nave nave : jugador.getTablero().getMapaDeNaves().keySet()){
            System.out.println("[ " + nave.toString() + " ]");
        }
        String input;
        System.out.print("Ingrese el nombre del barco: ");
        input = scanner.next();
        while (!jugador.getTablero().estaLaNave(input)){
            System.out.print("Barco no valido, intente de nuevo:");
            input = scanner.next();
        }
        System.out.println("Shield activado para " + input);
        for (Nave nave : jugador.getTablero().getMapaDeNaves().keySet()){
            if (nave.toString().equals(input)){
                nave.setShield(true);
            }
        }
    }

}
