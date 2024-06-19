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


import static org.junit.jupiter.api.Assertions.*;
        import static org.mockito.Mockito.when;

public class JugadorTest {


    @Test
    public void testDispararExitoso() {
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

        //Mockeo de la entrada del usuario
        when(jugadorMock.getTablero().pedirCoordenadas()).thenReturn(CoordMock);

        when(jugadorMock.disparar(oponenteMock)).thenReturn(true);
        boolean resultado = jugadorMock.disparar(oponenteMock);

        assertTrue(resultado);
    }


    @Test
    public void testDispararFallido() {
        Jugador jugadorMock = Mockito.mock(Jugador.class);
        Jugador oponenteMock = Mockito.mock(Jugador.class);

        // Crea un mock de la clase Tablero y configura su comportamiento
        Tablero tableroMock = Mockito.mock(Tablero.class);
        when(jugadorMock.getTablero()).thenReturn(tableroMock);
        Coordenada CoordMock = Mockito.mock(Coordenada.class);

        //Mockeo de la entrada del usuario
        when(jugadorMock.getTablero().pedirCoordenadas()).thenReturn(CoordMock);

        when(jugadorMock.disparar(oponenteMock)).thenReturn(false);
        boolean resultado = jugadorMock.disparar(oponenteMock);

        assertFalse(resultado);
    }

    @Test
    public void activarPowerUpTest() {
        // Crea un objeto de la clase Jugador
        Jugador jugadorMock = Mockito.mock(Jugador.class);
        Tablero tableroMock = Mockito.mock(Tablero.class);
        when(jugadorMock.getTablero()).thenReturn(tableroMock);
        Submarino submarino = Mockito.mock(Submarino.class);
        Portaaviones portaaviones = Mockito.mock(Portaaviones.class);
        Coordenada coordMock = Mockito.mock(Coordenada.class);
        jugadorMock.getTablero().rellenar(submarino, coordMock);
        jugadorMock.getTablero().rellenar(portaaviones, coordMock);


        // Crea un mock de la clase PowerUp y configura su comportamiento
        PowerUp powerUpMock = Mockito.mock(PowerUp.class);
        when(powerUpMock.getNombre()).thenReturn("Shield");

        // Añade el PowerUp mock al Jugador mock
        jugadorMock.addPowerUp(powerUpMock);
        powerUpMock.activar();

        // Llama al método activarPowerUp del Jugador mock
        jugadorMock.activarPowerUp("Shield");

        // Verifica que se llamó al método activar del PowerUp mock
        Mockito.verify(powerUpMock).activar();
    }
}