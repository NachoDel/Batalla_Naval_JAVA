package src.TESTS;

import src.Jugador;
import src.Naves.Submarino;

import org.junit.jupiter.api.Test;
import src.Tableros.Coordenada;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.*;

public class JugadorTest {


    @Test
    public void testDispararExitoso() {
        Jugador jugadorMock = new Jugador("JugadorMock", 10, 10);
        Jugador oponenteMock = new Jugador("OponenteMock", 10, 10);

        // Colocar una nave en el tablero del oponente
        Coordenada Coord1   = new Coordenada(0, 0);
        oponenteMock.getTablero().rellenar(new Submarino(false), Coord1);

        //Mockeo de la entrada del usuario
        String simulatedInput = "0\n0\n";
        ByteArrayInputStream input = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(input);

        // Disparar a la coordenada (0,0)
        boolean resultado = jugadorMock.disparar(oponenteMock);

        assertTrue(resultado);
    }

    @Test
    public void testDispararFallido() {
        Jugador jugador1 = new Jugador("Jugador1", 10, 10);
        Jugador jugador2 = new Jugador("Jugador2", 10, 10);

        //Mockeo de la entrada del usuario
        String simulatedInput = "0\n0\n";
        ByteArrayInputStream input = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(input);
        
        // Disparar a la coordenada (0,0)
        boolean resultado = jugador1.disparar(jugador2);

        assertFalse(resultado);
    }
}
