package Naves;

public class Submarino extends Nave {
    // Definicion de parametros particulares de la subclase
    private static int vida = 2;

    // Definicion del constructor
    public Submarino(boolean vertical) {
        super("Submarino", vertical, vida);
    }

    // Constructor sin parametros
    public Submarino() {
        super("Submarino", vida);
    }
}