package src.TESTS;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import src.Jugador;
import src.Naves.Nave;
import src.Naves.Portaaviones;
import src.Naves.Submarino;
import src.PowerUps.PowerUp;
import src.PowerUps.PowerUpFactory;
import src.Tableros.Coordenada;
import src.Tableros.Tablero;

import static org.mockito.Mockito.when;


public class DoubleShotTest {
    @Test
    public void activarDoubleShotTest(){
        //crea mocks de los jugadores
        Jugador jugadorMock = Mockito.mock(Jugador.class);
        Jugador oponenteMock = Mockito.mock(Jugador.class);
        // Crea mocks de las clases Submarino y Portaaviones
        Submarino submarinoMock = Mockito.mock(Submarino.class);
        Portaaviones portaavionesMock = Mockito.mock(Portaaviones.class);
        // Colocar una nave en el tablero del oponente
        Coordenada CoordMock = Mockito.mock(Coordenada.class);
        when(CoordMock.getFila()).thenReturn(0);
        when(CoordMock.getColumna()).thenReturn(0);
        // Crea un mock de la clase Tablero y configura su comportamiento
        Tablero tableroMock = Mockito.mock(Tablero.class);
        when(jugadorMock.getTablero()).thenReturn(tableroMock);
        tableroMock.rellenar(submarinoMock, CoordMock);
        tableroMock.rellenar(portaavionesMock, CoordMock);

        // Crea un mock de la clase PowerUpFactory y configura su comportamiento
        PowerUpFactory powerUpFactoryMock = Mockito.mock(PowerUpFactory.class);
        PowerUp doubleShotPUMock = Mockito.mock(PowerUp.class);
        String tipo = "DoubleShotPU";
        when(powerUpFactoryMock.crearPowerUp("DoubleShotPU")).thenReturn(doubleShotPUMock);


        // Activa el PowerUp
        jugadorMock.activarPowerUp(tipo);
        doubleShotPUMock.activar();

        // Verifica que el PowerUp se activó
        Mockito.verify(doubleShotPUMock).activar();
    }

}




