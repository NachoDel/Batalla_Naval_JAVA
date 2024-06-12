package src;

import src.Disparos.Disparo;
import src.Disparos.DisparoSimple;
import src.Naves.*;
import src.Tableros.Tablero;

public class Jugador {
    private String nombre;
    private Tablero tablero;
    private Disparo disparo;


    public Jugador(String nombre, int filas, int columnas) {
        this.nombre = nombre;
        tablero = new Tablero(filas, columnas);
        this.disparo = new DisparoSimple();
    }

    public void setDisparo(Disparo disparo) {
        this.disparo = disparo;
    }

    /** @returns true si el disparo fue exitoso, false si no */

    public boolean disparar(Jugador oponente) {
        int[] coord = tablero.pedirCoordenadas();
        disparo.disparar(oponente.getTablero(), coord[0], coord[1]);
        return !(oponente.getTablero().getMatriz()[coord[0]][coord[1]] instanceof Agua);
    }


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
}
