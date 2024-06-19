package src.TESTS;

import src.Jugador;
import src.Naves.Submarino;
import src.PowerUps.RadarPU;
import src.Tableros.Coordenada;
import src.PowerUps.RevivirHundidoPU;
import src.PowerUps.PowerUpFactory;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayInputStream;
import java.util.Scanner;

public class RadarPUTest {
    @Test
    public void activarRadarTest(){
        //Mockeo del jugador y su oponente con sus tablero y submarinos
        Jugador jugadorMock = new Jugador("mock", 5, 5);
        Jugador oponenteMock = new Jugador("mock", 5, 5);
        jugadorMock.setOponente(oponenteMock);
        //crea un naves y lo coloca en una posicion especifica en el tablero del oponente
        Coordenada coordMock1 = new Coordenada(0, 0);
        Coordenada coordMock2 = new Coordenada(0, 1);
        Coordenada coordMock3 = new Coordenada(0, 2);
        oponenteMock.getTablero().rellenar(new Submarino(false), coordMock1); //Coloca el submarino en la coordenada (0,0
        oponenteMock.getTablero().rellenar(new Submarino(false), coordMock2); //Coloca el submarino en la coordenada (0,1)
        oponenteMock.getTablero().rellenar(new Submarino(true), coordMock3); //Coloca el submarino en la coordenada (0,2)
        
        //---------------------------------------------
        // Crear un objeto de la clase RadarPU y agregarlo al jugador
        PowerUpFactory factoryMock = new PowerUpFactory();
        RadarPU RadarMock = (RadarPU) factoryMock.crearPowerUp("Radar");
        jugadorMock.addPowerUp(RadarMock);

        // Activar el powerup radar
        jugadorMock.activarPowerUp("Radar");

        // Verificar si el powerup radar se ha activado correctamente
        assertTrue(jugadorMock.getTablero().getRadarActivo());
    }
}
