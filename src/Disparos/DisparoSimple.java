package src.Disparos;

import src.Tableros.Coordenada;
import src.Tableros.Tablero;

public class DisparoSimple implements Disparo {

    @Override
    public void disparar(Tablero tablero, Coordenada coord){
        tablero.recibirDisparo(coord);
    }


}
