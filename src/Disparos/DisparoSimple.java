package src.Disparos;

import src.Tableros.Tablero;

public class DisparoSimple implements Disparo {

    @Override
    public void disparar(Tablero tablero, int x, int y){
        tablero.recibirDisparo(x, y);
    }
}
