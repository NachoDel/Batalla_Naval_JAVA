package src.TESTS;

import src.Jugador;
import src.PowerUps.DoubleShotPU;
import src.Tableros.Coordenada;
import src.Naves.Submarino;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

public class DoubleShotPUTest {

    @Test
    public void activarTest() {
        // Mockeo el jugador y el submarino
        Jugador jugadorMock = mock(Jugador.class);
        Submarino submarinoMock = mock(Submarino.class);
        Coordenada coordMock = mock(Coordenada.class);

        // Creato la instancia de DoubleShotPU
        DoubleShotPU doubleShotPU = new DoubleShotPU();

        // Seteando el comportamiento del mock
        when(jugadorMock.getTablero().getNavesConVida()).thenReturn(1);
        when(jugadorMock.getTablero().getNave(coordMock)).thenReturn(submarinoMock);

        // Activacion del power up con mock
        doubleShotPU.activar(jugadorMock, coordMock);

        // Verifico disparo
        verify(jugadorMock.getTablero(), times(2)).recibirDisparo(coordMock);
    }
}