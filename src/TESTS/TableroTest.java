package src.TESTS;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import src.Naves.Buque;
import src.Tableros.Coordenada;
import src.Tableros.Tablero;

import java.io.ByteArrayInputStream;

import static org.mockito.Mockito.*;

public class TableroTest {
    @Mock
    private Buque buque;

    private Tablero tablero;

    @BeforeEach
    void setUp() {
        tablero = new Tablero(10,10);
        buque = mock(Buque.class);
    }

    @Test
    public void colocarNave () {
        String simulatedInput = "0\n0";
        ByteArrayInputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(in);
        boolean result = tablero.colocarNave(buque);


    }
}
