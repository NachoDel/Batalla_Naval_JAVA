package src.PowerUps;

import src.Tableros.Tablero;
import src.Tableros.Coordenada;
import src.Naves.Nave;
import src.Naves.Acorazado;
import src.Naves.Buque;
import src.Naves.Submarino;
import src.Naves.Portaaviones;

import java.sql.SQLOutput;

public class RevivirHundidoPU extends PowerUp {

    private final int cantidadBarcos = 4; //Se debe actualizar en caso de cambiar la cantidad
    //de barcos en el juego
    private boolean flag;

    public RevivirHundidoPU(){
        nombre = "Revivir";
    }

    @Override
    public void activar() {
        if (jugador.getTablero().getNavesConVida() == cantidadBarcos) {
            System.out.println("No hay barcos hundidos para revivir");
            return;
        }
        System.out.println("Reviviendo a un barco hundido al azar");

        for (Nave barco : jugador.getTablero().getMapaDeNaves().keySet()) {
            if (!barco.getEstaViva()) {
                switch (barco.getTipo()) {
                    case "Submarino" -> {
                        System.out.println("Reviviendo submarino");
                        Nave submarino = new Submarino();
                        jugador.getTablero().colocarNave(submarino);
                        System.out.println("Submarino revivido");
                        return;
                    }
                    case "Acorazado" -> {
                        System.out.println("Reviviendo Acorazado");
                        Nave acorazado = new Acorazado();
                        jugador.getTablero().colocarNave(acorazado);
                        System.out.println("Acorazado revivido");
                        return;
                    }
                    case "Buque" -> {
                        System.out.println("Reviviendo Buque");
                        Nave buque = new Buque();
                        jugador.getTablero().colocarNave(buque);
                        System.out.println("Buque revivido");
                        return;
                    }
                    default -> {
                        System.out.println("Reviviendo Porta aviones");
                        Nave portaAviones = new Portaaviones();
                        jugador.getTablero().colocarNave(portaAviones);
                        System.out.println("Porta aviones revivido");
                    }
                }
                return;
            }
        }
    }
}
