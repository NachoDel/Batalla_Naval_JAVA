package src.Naves;

public class Acorazado extends Nave {
    // Definicion de parametros particulares de la subclase
    private static int vida = 6;

    // Definicion del constructor
    public Acorazado(boolean vertical) {
        super("Acorazado", vertical, vida);
    }

    // Constructor sin parametros
    public Acorazado() {
        super("Acorazado", true,  vida);
    }
}