package src;

import src.Disparos.Disparo;
import src.Disparos.DisparoSimple;
import src.Naves.*;
import src.PowerUps.PowerUp;
import src.PowerUps.PowerUpFactory;
import src.Tableros.Coordenada;
import src.Tableros.Tablero;

import java.util.ArrayList;
import java.util.Scanner;

public class Jugador {
    private String nombre;
    private Tablero tablero;
    private Disparo disparo;
    private ArrayList<PowerUp> powerUps;


    public Jugador(String nombre, int filas, int columnas) {
        this.nombre = nombre;
        tablero = new Tablero(filas, columnas);
        this.disparo = new DisparoSimple();
        powerUps = new ArrayList<>();
    }

    public void setDisparo(Disparo disparo) {
        this.disparo = disparo;
    }

    /**
     * Realiza un disparo al tablero del oponente
     * @param oponente Jugador al que se le dispara
     * @return true si se acertó un disparo, false si no
     */
    public boolean disparar(Jugador oponente) {
        Coordenada coord = tablero.pedirCoordenadas();
        disparo.disparar(oponente.getTablero(), coord);
        return !(oponente.getTablero().getMatriz()[coord.getFila()][coord.getColumna()] instanceof Agua);
    }


    /**
     * Crea y coloca las naves en el tablero
     */
    public void crearYColocarNaves() {
        System.out.println("Colocando submarino - 2 casillas");
        tablero.colocarNave(new Submarino());
        tablero.mostrarTablero();

        System.out.println("Colocando buque - 4 casillas");
        tablero.colocarNave(new Buque());
        tablero.mostrarTablero();

        System.out.println("Colocando portaaviones - 5 casillas");
        tablero.colocarNave(new Portaaviones());
        tablero.mostrarTablero();

        System.out.println("Colocando acorazado - 6 casillas");
        tablero.colocarNave(new Acorazado());
        tablero.mostrarTablero();

    }



    public String getNombre() {
        return nombre;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }

    public ArrayList<PowerUp> getPowerUps() {
        return powerUps;
    }

    public void addPowerUp(PowerUp powerUp){
        powerUp.setJugador(this);
        powerUps.add(powerUp);
    }

    public void usarPowerUp(String nombre){
        for(PowerUp powerUp : powerUps){
            if(powerUp.getNombre().equalsIgnoreCase(nombre)){
                powerUp.activar();
                powerUps.remove(powerUp);
                return;
            }else{
                System.out.println("No tienes ese power up");
            }
        }
    }



}
