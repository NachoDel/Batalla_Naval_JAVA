package src.Naves;

public class Buque extends Nave {
    // Definicion de parametros particulares de la subclase
    private static int vida = 4;

    // Definicion del constructor
    public Buque(boolean vertical) {
        super("Buque", vertical, vida);
    }

    // Constructor sin parametros
    public Buque() {
        super("Buque", vida);
    }
}