package src.TESTS;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import src.Jugador;
import src.Naves.Acorazado;
import src.Naves.Buque;
import src.Naves.Submarino;
import src.Tableros.Coordenada;
import src.PowerUps.RevivirHundidoPU;
import src.PowerUps.PowerUpFactory;

import org.junit.jupiter.api.Test;
import src.Tableros.Tablero;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

public class RevivirHundidoPUTest{

    private Jugador jugador;
    private PowerUpFactory factoryPU;
    @Mock
    private Acorazado acorazado;


    @BeforeEach
    void setUp() {
        factoryPU = new PowerUpFactory();
        jugador = new Jugador("jugadorTest", 10, 10);
        acorazado = mock(Acorazado.class);
    }

    //Caso nave hundida
    @Test
    public void activarTest1(){ //Caso en el que no hay barcos hundidos
        String simulatedInput = "0\n0\n0\n1\n0\n2\n0\n3\nY\n0\n9\n" ;//coord para 4 barcos verticalidad y colocar nuevo barco
        ByteArrayInputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(in);

        //colocamos mocks de barcos en tablero
        jugador.getTablero().colocarNave(acorazado);
        jugador.getTablero().colocarNave(acorazado);
        jugador.getTablero().colocarNave(acorazado);
        jugador.getTablero().colocarNave(acorazado);

        int cantBarcosAntes = jugador.getTablero().getNavesConVida();

        //usamos PU
        jugador.addPowerUp(factoryPU.crearPowerUp("Revivir"));
        jugador.activarPowerUp("Revivir");

        //naves con vida debera ser mayor a la cant de barcos antes de usar el PU
        Assertions.assertEquals(jugador.getTablero().getNavesConVida(), cantBarcosAntes+1);
        Assertions.assertNotNull(jugador.getTablero().getNave("Submarino"));


    }

    //Caso en el que todas las naves estan vivas
    @Test
    public void activarTest2(){ //Caso en el hay barcos hundidos

        String simulatedInput = "\n0\n1\nY\n0\n9\n" ;//coord para barco verticalidad y colocar nuevo barco
        ByteArrayInputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(in);

        //colocamos mocks de barcos en tablero
        jugador.getTablero().colocarNave(acorazado);
        int cantBarcosAntes = jugador.getTablero().getNavesConVida();

        when(acorazado.getEstaViva()).thenReturn(false);
        when(acorazado.getTipo()).thenReturn("Acorazado");
        //usamos PU
        jugador.addPowerUp(factoryPU.crearPowerUp("Revivir"));
        jugador.activarPowerUp("Revivir");

        //naves con vida debera ser mayor a la cant de barcos antes de usar el PU
        Assertions.assertEquals(jugador.getTablero().getNavesConVida(), cantBarcosAntes+1);


    }


}