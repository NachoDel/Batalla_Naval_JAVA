package src.TESTS;

import org.junit.Test;
import src.Jugador;
import org.mockito.Mockito;
import src.Naves.Portaaviones;
import src.Naves.Submarino;
import src.PowerUps.RadarPU;
import src.Tableros.Coordenada;

import src.Tableros.Tablero;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class RadarPUTest {
    @Test
    public void activarRadarTest(){
        //Mockeo del jugador y su oponente con sus tablero y submarinos
        Jugador jugadorMock = Mockito.mock(Jugador.class);
        Jugador oponenteMock = Mockito.mock(Jugador.class);

        // Creo un mock de la clase Tablero
        Tablero tableroMock = Mockito.mock(Tablero.class);
        Tablero tableroMockOponente = Mockito.mock(Tablero.class);

        // Configuro el comportamiento del mock del oponente
        when(jugadorMock.getTablero()).thenReturn(tableroMock);
        when(oponenteMock.getTablero()).thenReturn(tableroMockOponente);
        when(jugadorMock.getOponente()).thenReturn(oponenteMock);

        // Creao un mock de la clase Coordenada
        Coordenada coordMock1 = Mockito.mock(Coordenada.class);
        Coordenada coordMock2 = Mockito.mock(Coordenada.class);

        // Configuro el comportamiento del mock de Coordenada
        when(coordMock1.getFila()).thenReturn(0);
        when(coordMock1.getColumna()).thenReturn(0);
        when(coordMock2.getFila()).thenReturn(0);
        when(coordMock2.getColumna()).thenReturn(1);

        when(tableroMockOponente.obtenerCoordenadaBarcoRandom()).thenReturn(coordMock1);

        // Creo un mock de la clase Submarino
        Submarino submarinoMock = Mockito.mock(Submarino.class);
        Portaaviones portaavionesMock = Mockito.mock(Portaaviones.class);

        // lleno el tablero con las naves
        tableroMock.rellenar(submarinoMock, coordMock1);
        tableroMock.rellenar(portaavionesMock, coordMock2);

        // Creo un objeto de la clase RadarPU
        RadarPU radarMock = new RadarPU();
        radarMock.setJugador(jugadorMock);
        radarMock.activar();


        // Llamo al método que estoy probando
        jugadorMock.activarPowerUp("Radar");

        // Verifico que se llamó al método setRadarActivo(true)
        Mockito.verify(tableroMock).setRadarActivo(true);

    }
}

