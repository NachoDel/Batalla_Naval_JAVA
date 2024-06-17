package src.Disparos;

import src.Tableros.Coordenada;
import src.Tableros.Tablero;

import java.util.ArrayList;

public class DisparoDoble implements Disparo{

    @Override
    public void disparar(Tablero tablero, Coordenada coordenada){
        ArrayList<Coordenada> listaCoordenadas = new ArrayList<>();
        listaCoordenadas = tablero.getCoordenadasDeNave(coordenada);
        // en lista Coordenadas queda guardada la lista de coordenadas de la nave en la que se disparo
        int conta = 0;

        tablero.recibirDisparo(coordenada);
        //recibo el disparo en la coordenada que se disparo
        if(listaCoordenadas!=null) {
            while (conta < 1) {
                for (Coordenada c : listaCoordenadas) {
                    if (tablero.recibirDisparo(c)) {
                        conta++;
                        break;

                    }
                }
            }
        }
        //este while se corta cuando se hayan hecho 2 disparos
    }
}

