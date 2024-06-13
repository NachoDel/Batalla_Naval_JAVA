package src.PowerUps;

import src.Jugador;

public abstract class PowerUp {
     Jugador jugador;

     abstract void activar();

     public void setJugador(Jugador jugador){
         this.jugador = jugador;
     }

}
