import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TableroTest {

    @Mock
    private Tablero tableroMock;

    @Test
    public void testGetNaveEncordenada() {
        //Defino filas y columna x
        int fila = 2;
        int columna = 3;
        Nave NaveEsperada = new Nave();

        // Mock the behavior of the Tablero class
        Mockito.when(tableroMock.getNaveEncordenada(fila, columna)).thenReturn(NaveEsperada);

        // Busca y guarda
        Nave actualNave = tableroMock.getNaveEncordenada(fila, columna);

        // Testeo
        assertEquals(expectedNave, actualNave);
    }
}