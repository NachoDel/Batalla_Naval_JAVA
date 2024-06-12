package src.Disparos;

import src.Tableros.Coordenada;
import src.Tableros.Tablero;

import java.util.ArrayList;

public class DisparoDoble implements Disparo{

    @Override
    public void disparar(Tablero tablero, Coordenada coordenada){
        ArrayList<Coordenada> listaCoordenadas = new ArrayList<>();
        listaCoordenadas = tablero.getCoordenadasNave(coordenada);
        int index = 0;
        for(Coordenada c : listaCoordenadas){
            if(c.getFila() == coordenada.getFila() && c.getColumna() == coordenada.getColumna()){
                index = listaCoordenadas.indexOf(c);
            }
        }
            // Disparar a la coordenada elegida
            tablero.recibirDisparo(coordenada);
            // Disparar a la siguiente coordenada en la lista, si existe
            if(index + 1 < listaCoordenadas.size()) {
                Coordenada siguienteCoordenada = listaCoordenadas.get(index + 1);
                tablero.recibirDisparo(siguienteCoordenada);
            }
            else{  // Disparar a la coordenada anterior en la lista, si existe
                if(index - 1 >= 0){
                    Coordenada anteriorCoordenada = listaCoordenadas.get(index - 1);
                    tablero.recibirDisparo(anteriorCoordenada);
                }
        }
    }
}

