package src.TESTS;

import src.Jugador;
import src.Naves.Submarino;
import src.Tableros.Coordenada;
import src.PowerUps.RevivirHundidoPU;
import src.PowerUps.PowerUpFactory;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayInputStream;
import java.util.Scanner;

public class RevivirHundidoPUTest{

    //Caso nave hundida
    @Test
    public void activarTest1(){
        //Mockeo del jugador con su tablero y submarino
        Jugador jugadorMock = new Jugador("mock", 3, 3);
        Coordenada coordMock1 = new Coordenada(0, 0);
        Coordenada coordMock2 = new Coordenada(0, 1);
        jugadorMock.getTablero().rellenar(new Submarino(false), coordMock1); //Coloca el submarino en la coordenada (0,0)
        //---------------------------------------------
        //Mockeo del powerUp
        PowerUpFactory factoryMock = new PowerUpFactory();
        RevivirHundidoPU revivirMock = (RevivirHundidoPU) factoryMock.crearPowerUp("Revivir");
        jugadorMock.addPowerUp(revivirMock);
        //---------------------------------------------
        //Mockeo de la entrada del usuario
        //String simulatedInput = "n\n1\n0\n"; NO LO PUEDO USAR, YA QUE ASKVERTICALIDAD ME RESETEA
                                               //LA SIMULACION DE LOS IMPUTS, CAMBIE TMB LA APARICION DE SUBMARINO EN POWERUP
                                               //LO INSTA SETIE EN VERTICAL:FALSE
        String simulatedInput = "1\n0\n";
        ByteArrayInputStream input = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(input);
        //---------------------------------------------

        //Guardado de estados para despues comprar
        int navesVivas = jugadorMock.getTablero().getNavesConVida();
        assertEquals(1, navesVivas);
        //---------------------------------------------

        //Destruccion del submarino
        jugadorMock.getTablero().recibirDisparo(coordMock1);
        jugadorMock.getTablero().recibirDisparo(coordMock2);
        assertEquals(0, jugadorMock.getTablero().getNavesConVida());
        //---------------------------------------------

        //Activo el powerUp
        jugadorMock.activarPowerUp("Revivir");
        //---------------------------------------------

        //Comprobacion de que se ha revivido la nave
        assertEquals(1, navesVivas);
        //---------------------------------------------
    }

    //Caso en el que todas las naves estan vivas
    @Test
    public void activarTest2(){
        //Mockeo del jugador con su tablero y submarino
        Jugador jugadorMock = new Jugador("mock", 10, 10);
        Coordenada coordMock = new Coordenada(0, 0);
        jugadorMock.getTablero().rellenar(new Submarino(false), coordMock); //Coloca el submarino en la coordenada (0,0)
        Coordenada coordMock2 = new Coordenada(1, 0);
        jugadorMock.getTablero().rellenar(new Submarino(false), coordMock2); //Coloca el submarino en la coordenada (0,0)
        Coordenada coordMock3 = new Coordenada(2, 0);
        jugadorMock.getTablero().rellenar(new Submarino(false), coordMock3); //Coloca el submarino en la coordenada (0,0)
        Coordenada coordMock4 = new Coordenada(3, 0);
        jugadorMock.getTablero().rellenar(new Submarino(false), coordMock4); //Coloca el submarino en la coordenada (0,0)
        //---------------------------------------------
        //Mockeo del powerUp
        PowerUpFactory factoryMock = new PowerUpFactory();
        RevivirHundidoPU revivirMock = (RevivirHundidoPU) factoryMock.crearPowerUp("Revivir");
        jugadorMock.addPowerUp(revivirMock);
        //---------------------------------------------
        //Mockeo de la entrada del usuario
        String simulatedInput = "4\n0\n"; //LO MISMO DEL TEST 1
        ByteArrayInputStream input = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(input);
        //---------------------------------------------

        //Activo el powerUp
        jugadorMock.activarPowerUp("Revivir");
        //---------------------------------------------

        //Comprovacion que se ha añadido una nave
        assertEquals(5, jugadorMock.getTablero().getNavesConVida());
        //---------------------------------------------


    }



}