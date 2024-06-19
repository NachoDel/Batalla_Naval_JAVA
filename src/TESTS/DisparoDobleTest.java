package src.TESTS;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.Disparos.DisparoDoble;
import src.Tableros.Coordenada;
import src.Tableros.Tablero;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

public class DisparoDobleTest {

    private DisparoDoble disparoDoble;
    private Tablero tablero;
    private Coordenada coordenada;
    private ArrayList<Coordenada> listaCoordenadas;

    @BeforeEach
    void setUp() {
        disparoDoble = new DisparoDoble();
        tablero = mock(Tablero.class);
        coordenada = mock(Coordenada.class);
        listaCoordenadas = new ArrayList<>();
        listaCoordenadas.add(coordenada);
        listaCoordenadas.add(mock(Coordenada.class));
    }

    @Test
    void shouldFireAtTwoCoordinatesWhenShipIsPresent() {
        when(tablero.getCoordenadasDeNave(coordenada)).thenReturn(listaCoordenadas);
        when(tablero.recibirDisparo(any(Coordenada.class))).thenReturn(true);
        disparoDoble.disparar(tablero, coordenada);
        verify(tablero, times(2)).recibirDisparo(any(Coordenada.class));
    }

    @Test
    void shouldFireAtOneCoordinateWhenNoShipIsPresent() {
        when(tablero.getCoordenadasDeNave(coordenada)).thenReturn(null);
        disparoDoble.disparar(tablero, coordenada);
        verify(tablero, times(1)).recibirDisparo(any(Coordenada.class));
    }
}