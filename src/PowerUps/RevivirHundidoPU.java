package src.PowerUps;

import src.Tableros.Tablero;
import src.Tableros.Coordenada;
import src.Naves.Nave;
import src.Naves.Acorazado;
import src.Naves.Buque;
import src.Naves.Submarino;
import src.Naves.Portaaviones;

public class RevivirHundidoPU extends PowerUp {

    private final int cantidadBarcos = 4; //Se debe actualizar en caso de cambiar la cantidad
                                          //de barcos en el juego
    private boolean flag;
    
    public RevivirHundidoPU() {
        nombre = "Revivir";
        flag = false;
    }
    
    @Override
    public void activar() {
        if(jugador.getTablero().getNavesConVida() == cantidadBarcos){
            System.out.println("No hay barcos hundidos para revivir");
            return;
        }
        System.out.println("Reviviendo a un barco hundido al azar");
        Tablero tableroJug = jugador.getTablero();
        do{
            Coordenada coordenada = tableroJug.obtenerCoordenadaBarcoRandom();
            Nave barco = tableroJug.getNaveEnCoordenada(coordenada);
            if(!barco.getEstaViva()){
                flag = true;
                if(barco.getTipo().equals("Submarino")){
                    System.out.println("Reviviendo submarino");
                    Nave submarino = new Submarino();
                    tableroJug.colocarNave(submarino);
                }
                if(barco.getTipo().equals("Acorazado")){
                    System.out.println("Reviviendo Acorazado");
                    Nave acorazado = new Acorazado();
                    tableroJug.colocarNave(acorazado);
                }
                if(barco.getTipo().equals("Buque")){
                    System.out.println("Reviviendo Buque");
                    Nave buque = new Buque();
                    tableroJug.colocarNave(buque);
                }
                else{
                    System.out.println("Reviviendo Porta aviones");
                    Nave portaAviones = new Portaaviones();
                    tableroJug.colocarNave(portaAviones);
                }
                break;
            }
        }while(flag == false);
    }
}