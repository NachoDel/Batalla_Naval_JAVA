package src.Disparos;

import src.Tableros.Tablero;

public class DisparoDoble implements Disparo{
    public void disparar(Tablero tablero, int x, int y){
        tablero.recibirDisparo(x, y);
        tablero.recibirDisparo(x+1, y);
    }
}
