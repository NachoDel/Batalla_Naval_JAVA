package src.Disparos;

import src.Tableros.Coordenada;
import src.Tableros.Tablero;

import java.util.ArrayList;

public class DisparoDoble implements Disparo{
    public void disparar(Tablero tablero, int x, int y){
        Coordenada coordenada = new Coordenada(x,y);
        ArrayList<Coordenada> coordenadas = new ArrayList<Coordenada>();
        coordenadas = tablero.getCoordenadasNave(coordenada);
        int index = 0;
        for(Coordenada c : coordenadas){
            if(c.getFila() == x && c.getColumna() == y){
                index = coordenadas.indexOf(c);
            }
        }
            // Disparar a la coordenada elegida
            tablero.recibirDisparo(coordenada.getFila(), coordenada.getColumna());
            // Disparar a la siguiente coordenada en la lista, si existe
            if(index + 1 < coordenadas.size()) {
                Coordenada siguienteCoordenada = coordenadas.get(index + 1);
                tablero.recibirDisparo(siguienteCoordenada.getFila(), siguienteCoordenada.getColumna());
            }
            else{  // Disparar a la coordenada anterior en la lista, si existe
                if(index - 1 >= 0){
                    Coordenada anteriorCoordenada = coordenadas.get(index - 1);
                    tablero.recibirDisparo(anteriorCoordenada.getFila(), anteriorCoordenada.getColumna());
                }
        }
    }
}

