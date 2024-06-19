package src.TESTS;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import src.Naves.Acorazado;
import src.Naves.Buque;
import src.Naves.Nave;
import src.Tableros.Coordenada;
import src.Tableros.Tablero;

import java.io.ByteArrayInputStream;

import static org.mockito.Mockito.*;

public class TableroTest {
    private Buque buque;

    private Tablero tablero;

    @Mock
    private Acorazado acorazado;

    @BeforeEach
    void setUp() {
        tablero = new Tablero(10,10);
        buque = new Buque();
        acorazado = mock(Acorazado.class);
    }

    @Test
    @DisplayName("GIVEN a ship WHEN colocarNave() THEN return true")
    public void colocarNave () {//barco en lugar adecuado

        //coloco buque en 0,0
        String simulatedInput = "0\n0\n";
        ByteArrayInputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(in);

        boolean result = tablero.colocarNave(buque);

        for(int i=0; i<buque.getVida(); i++){
            Assertions.assertNotNull(tablero.getMatriz()[i][0]);
        }
        Assertions.assertTrue(result);

    }

    @Test
    @DisplayName("GIVEN a ship WHEN colocarNave() THEN return false")
    public void colocarNave2 () { // para barco que no entra en el tablero

        //coloco barco vertical en fila 9 (no podra colocarlo)
        String simulatedInput = "9\n0\n";
        ByteArrayInputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(in);
        boolean result = tablero.colocarNave(buque);

        Assertions.assertFalse(result);
        

    }

    @Test
    @DisplayName("GIVEN a ship WHEN colocarNave() THEN return false")
    public void colocarNave3() { //para lugar ya ocupado

        //coloco barco vertical en fila 9 (no podra colocarlo)
        String simulatedInput = "0\n0\n0\n0\n";
        ByteArrayInputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(in);
        tablero.colocarNave(buque);

        when(acorazado.getVida()).thenReturn(5);
        when(acorazado.esVertical()).thenReturn(true);

        boolean result = tablero.colocarNave(acorazado);

        Assertions.assertFalse(result);

    }


}
