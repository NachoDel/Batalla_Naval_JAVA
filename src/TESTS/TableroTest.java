package src.TESTS;

import src.Naves.Buque;
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

    @Test
    public void testEntraElBarco() {
        Tablero tablero = new Tablero(10, 10);

        //testeo para nave vertical
        Buque buqueV = new Buque(true);
        assertTrue(tablero.entraElBarco(buqueV, 0,0 )); //posicion cero
        assertTrue(tablero.entraElBarco(buqueV, 3,3));  //posicion media
        assertTrue(tablero.entraElBarco(buqueV, 6, 0)); //posicion borde


        assertFalse(tablero.entraElBarco(buqueV, 9, 9)); //posicion fuera de rango maxima
        assertFalse(tablero.entraElBarco(buqueV, 7, 0)); //posicion fuera de rango borde
        assertFalse(tablero.entraElBarco(buqueV, 8, 5));  //posicion fuera de rango media



        //testeo para nave horizontal
        Buque buqueH = new Buque(false);
        assertTrue(tablero.entraElBarco(buqueH, 0,0 ));
        assertTrue(tablero.entraElBarco(buqueH, 3,3));
        assertTrue(tablero.entraElBarco(buqueH, 0, 6));


        assertFalse(tablero.entraElBarco(buqueH, 9, 9));
        assertFalse(tablero.entraElBarco(buqueH, 0, 7));
        assertFalse(tablero.entraElBarco(buqueH, 5, 8));

    }
    @Test
    public void testEstaOcupado(){
        Tablero tablero = new Tablero(10, 10);

        //testeo para nave vertical
        Buque buqueV = new Buque(true);
        tablero.rellenar(buqueV, 0, 0);

        // coordenadas ocupadas
        assertTrue(tablero.estaOcupado(buqueV, 0,0 ));
        assertTrue(tablero.estaOcupado(buqueV, 1,0));
        assertTrue(tablero.estaOcupado(buqueV, 2, 0));
        // coordenadas vacias
        assertFalse(tablero.estaOcupado(buqueV, 0, 3));
        assertFalse(tablero.estaOcupado(buqueV, 1, 3));
        assertFalse(tablero.estaOcupado(buqueV, 2, 3));

        //testeo para nave horizontal
        Buque buqueH = new Buque(false);
        tablero.rellenar(buqueH, 0, 1);
        //coordenadas ocupadas
        assertTrue(tablero.estaOcupado(buqueH, 0,1 ));
        assertTrue(tablero.estaOcupado(buqueH, 0,2));
        assertTrue(tablero.estaOcupado(buqueH, 0, 3));
        //coordenadas vacias
        assertFalse(tablero.estaOcupado(buqueH, 3, 3));
        assertFalse(tablero.estaOcupado(buqueH, 1, 3));
        assertFalse(tablero.estaOcupado(buqueH, 2, 3));

    }

}
