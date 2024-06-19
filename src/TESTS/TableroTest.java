package src.TESTS;

import src.Jugador;
import src.Tableros.Tablero;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class TableroTest {

    @Test
    public void testObtenerCoordenadaBarcoRandom() {
        // Crea mock de  ObtenerCoordenadaBarcoRandom
        Tablero obtenerCoordenadaBarcoRandomMock = Mockito.mock(Tablero.class);

        // Define esperado
        int esperadaX = 5;
        int esperadaY = 3;

        // sEteo de comportamiento
        when(obtenerCoordenadaBarcoRandomMock.obtenerCoordenadaX()).thenReturn(expectedX);
        when(obtenerCoordenadaBarcoRandomMock.obtenerCoordenadaY()).thenReturn(expectedY);

        // Llama prueba
        int actualX = obtenerCoordenadaBarcoRandomMock.obtenerCoordenadaX();
        int actualY = obtenerCoordenadaBarcoRandomMock.obtenerCoordenadaY();

        // Prueba
        assertEquals(esperadaX, actualX);
        assertEquals(esperadaY, actualY);
    }
}
