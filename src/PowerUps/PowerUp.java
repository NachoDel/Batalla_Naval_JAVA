package src.PowerUps;

import src.Jugador;

public abstract class PowerUp {
     String nombre;
     Jugador jugador;

     public abstract void activar();

     public void setJugador(Jugador jugador){
         this.jugador = jugador;
     }

     public String getNombre(){
         return nombre;
     }



}
