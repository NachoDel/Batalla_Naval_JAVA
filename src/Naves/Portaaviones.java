package src.Naves;

public class Portaaviones extends Nave {
    // Definicion de parametros particulares de la subclase
    private static int vida = 5;

    // Definicion del constructor
    public Portaaviones(boolean vertical) {
        super("Portaaviones", vertical, vida);
    }

    // Constructor sin parametros
    public Portaaviones() {
        super("Portaaviones", vida);
    }

    public void Revivir(){
        this.Revive();
        this.setVida(5);
    }
}