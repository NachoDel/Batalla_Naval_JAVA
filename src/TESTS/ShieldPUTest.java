package src.TESTS;
import org.junit.Test;
import src.Jugador;
import org.mockito.Mockito;
import src.Naves.Portaaviones;
import src.Naves.Submarino;
import src.PowerUps.RadarPU;
import src.PowerUps.ShieldPU;
import src.Scanear;
import src.Tableros.Coordenada;
import src.Tableros.Tablero;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.mockito.Mockito.when;

public class ShieldPUTest {
    @Test
    public void activarShieldPUTest(){
        //Mockeo del jugador y su oponente con sus tablero y submarinos
        Jugador jugadorMock = Mockito.mock(Jugador.class);
        Tablero tableroMock = Mockito.mock(Tablero.class);
        when(jugadorMock.getTablero()).thenReturn(tableroMock);
        Coordenada coordMock1 = Mockito.mock(Coordenada.class);
        when(coordMock1.getFila()).thenReturn(0);
        when(coordMock1.getColumna()).thenReturn(0);
        Submarino submarinoMock = Mockito.mock(Submarino.class);
        tableroMock.rellenar(submarinoMock, coordMock1);

        // Configura System.in para que lea de un String en lugar de la consola

        String simulatedInput = "Submarino" ;
        ByteArrayInputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(in);
        when(jugadorMock.getTablero().estaLaNave("Submarino")).thenReturn(true);
        when(jugadorMock.getTablero().getNave("Submarino")).thenReturn(submarinoMock);
        when(submarinoMock.getEstaViva()).thenReturn(true);


        ShieldPU shieldMock = new ShieldPU();
        shieldMock.setJugador(jugadorMock);
        shieldMock.activar();
        submarinoMock.setShield(true);
        jugadorMock.activarPowerUp("Shield");
        Mockito.verify(submarinoMock).setShield(true);

    }
}