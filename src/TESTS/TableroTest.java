package src.TESTS;

import src.Tablero;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TableroTest {

    @Test
    public void testValidarCoordenadas() {
        Tablero tablero = new Tablero(10, 10);

        // Test case 1: Coordenadas dentro del rango
        assertTrue(tablero.validarCoordenadas(5, 5));

        // Test case 2: Coordenadas fuera del rango
        assertFalse(tablero.validarCoordenadas(15, 5));
        assertFalse(tablero.validarCoordenadas(5, 15));

        // Test case 3: Coordenadas en el límite inferior del rango
        assertTrue(tablero.validarCoordenadas(0, 0));

        // Test case 4: Coordenadas en el límite superior del rango
        assertTrue(tablero.validarCoordenadas(9,9));
    }



}
