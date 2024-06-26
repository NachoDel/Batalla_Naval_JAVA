package src.PowerUps;
import  src.Tableros.Coordenada;


public class RadarPU extends PowerUp{
    public RadarPU(){
        this.nombre = "Radar";
    }

    @Override
    public void activar(){
        jugador.getOponente().getTablero().setRadarActivo(true);
        System.out.println("Radar activado");
        Coordenada coordenadaBarco = jugador.getOponente().getTablero().obtenerCoordenadaBarcoRandom();
        jugador.getOponente().getTablero().setCoordenadaAyudaRadar(coordenadaBarco);
        System.out.println("Radar: Hay un barco en la posición " + coordenadaBarco.getFila() + ", " + coordenadaBarco.getColumna());
        jugador.getOponente().getTablero().mostrarOculto();
    }
}
