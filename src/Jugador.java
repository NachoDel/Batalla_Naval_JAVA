package src;

import src.Naves.*;
import src.Tableros.Tablero;

public class Jugador {
    private String nombre;
    private Tablero tablero;

    public Jugador(String nombre, int filas, int columnas) {
        this.nombre = nombre;
        tablero = new Tablero(filas, columnas);
    }

    /** @returns true si el disparo fue exitoso, false si no */
    public boolean disparar(Jugador oponente, int danio) {
        int[] coord;
        do {
            coord = tablero.pedirCoordenadas();

        } while (!oponente.getTablero().recibirDisparo(coord[0], coord[1], danio));
        return !(oponente.getTablero().getMatriz()[coord[0]][coord[1]] instanceof Agua);
    }

    //Solo permite disparar a la coordenada (0,0)
    public boolean dispararParaTest(Jugador oponente, int danio) {
        int[] coord = new int[2];
        coord[0] = 0;
        coord[1] = 0;
        oponente.getTablero().recibirDisparo(coord[0], coord[1], danio);
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
