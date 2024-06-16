package src.PowerUps;
import  src.Tableros.Coordenada;


public class RadarPU extends PowerUp{
    public RadarPU(){
        this.nombre = "Radar";
    }

    @Override
    public void activar(){
        jugador.getTablero().setRadarActivo(true);
        System.out.println("Radar activado");
        Coordenada coordenadaBarco = jugador.getTablero().obtenerCoordenadaBarcoRandom();
        jugador.getTablero().setCoordenadaAyudaRadar(coordenadaBarco);
        System.out.println("Ayuda: Hay un barco en la posición " + coordenadaBarco.getFila() + ", " + coordenadaBarco.getColumna());
    }
}