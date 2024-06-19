package src.TESTS;

import org.junit.jupiter.api.*;
import org.mockito.Mock;
import src.Naves.*;
import src.Tableros.Coordenada;
import src.Tableros.Tablero;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

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

    @Test
    public void recibirDisparo(){ //disparo en lugar donde hay nave
        //mandamos coordenada 3,3
        String simulatedInput = "3\n3\n";
        ByteArrayInputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(in);

        //colocamos nave en 3,3
        tablero.colocarNave(buque);
        int vidaAntes= buque.getVida();
        //disparamos alli
        Coordenada coordenada = new Coordenada(3,3);
        boolean result = tablero.recibirDisparo(coordenada);

        //true porque le dimos
        Assertions.assertTrue(result);
        //revisamos que haya impacto luego del disparo
        Assertions.assertInstanceOf(Impacto.class, tablero.getMatriz()[coordenada.getFila()][coordenada.getColumna()]);
        //revisamos que la vida del barco haya disminuido
        Assertions.assertEquals(buque.getVida(), vidaAntes - 1);

    }

    @Test
    public void recibirDisparo2(){ //disparo en lugar donde no hay nave

        //disparamos alli
        Coordenada coordenada = new Coordenada(3,3);
        boolean result = tablero.recibirDisparo(coordenada);

        //true porque le dimos
        Assertions.assertTrue(result);
        //revisamos que haya agua luego del disparo
        Assertions.assertInstanceOf(Agua.class, tablero.getMatriz()[coordenada.getFila()][coordenada.getColumna()]);


    }

    @Test
    public void recibirDisparo3(){ //disparo en lugar donde ya se disparo

        Coordenada coordenada = new Coordenada(3,3);
        tablero.recibirDisparo(coordenada);

        //disparamos alli de nuevo
        boolean result = tablero.recibirDisparo(coordenada);

        //false porque ya se disparo
        Assertions.assertFalse(result);

    }

    @Test
    public void getNaveEnCoordenada(){ //obtener nave en coordenada que si esta
        String simulatedInput = "1\n2\n";
        ByteArrayInputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(in);
        //colocamos nave en 3,3
        tablero.colocarNave(buque);

        //obtenemos nave en 3,3
        Coordenada coordenada = new Coordenada(1,2);
        Nave nave = tablero.getNaveEnCoordenada(coordenada);
        //deberia ser un buque
        Assertions.assertEquals(buque, nave);

    }

    @Test
    public void getNaveEnCoordenada2(){ //obtener nave en coordenada que no esta
        //obtenemos nave en 3,3
        Coordenada coordenada = new Coordenada(1,2);
        Nave nave = tablero.getNaveEnCoordenada(coordenada);
        //deberia ser un buque
        Assertions.assertNull(nave);

    }


}
