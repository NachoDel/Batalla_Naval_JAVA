package src.TESTS;

import src.Jugador;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ObtenerCoordenadaBarcoRandomTest {

    @Test
    public void testObtenerCoordenadaBarcoRandom() {
        // Crea mock de  ObtenerCoordenadaBarcoRandom
        ObtenerCoordenadaBarcoRandom obtenerCoordenadaBarcoRandomMock = Mockito.mock(ObtenerCoordenadaBarcoRandom.class);

        // Define esperado
        int expectedX = 5;
        int expectedY = 3;

        // sEteo de comportamiento
        when(obtenerCoordenadaBarcoRandomMock.obtenerCoordenadaX()).thenReturn(expectedX);
        when(obtenerCoordenadaBarcoRandomMock.obtenerCoordenadaY()).thenReturn(expectedY);

        // Llama prueba
        int actualX = obtenerCoordenadaBarcoRandomMock.obtenerCoordenadaX();
        int actualY = obtenerCoordenadaBarcoRandomMock.obtenerCoordenadaY();

        // Prueba
        assertEquals(expectedX, actualX);
        assertEquals(expectedY, actualY);
    }
}
