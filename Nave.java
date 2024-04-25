import java.util.Scanner;

public class Nave {
    private String tipo;

    /*
     * Submarino = 2
     * Buque = 4 lugares
     * Portaaviones= 5
     * Acorzado=6
     */
    private boolean vertical;
    private int vida;// tamanio

    public Nave(String tipo, boolean vertical, int vida) {
        this.tipo = tipo;
        this.vertical = vertical;
        this.vida = vida;

    }

    public Nave(String tipo, int vida) {
        this.tipo = tipo;
        this.vida = vida;
        vertical = inputVertical();

    }

    private boolean inputVertical() {
        System.out.print("Desea que " + tipo + " sea vertical?: ");
        Scanner scanner = new Scanner(System.in);
        int s = scanner.nextInt();

        return s == 1;

    }

    public boolean esVertical() {
        return vertical;
    }

    public int getVida() {
        return vida;
    }

    public String getTipo() {
        return tipo;
    }

    public void setVertical(boolean vertical) {
        this.vertical = vertical;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    // quita tanta vida como se le indique
    public void quitarVida(int danio) {
        vida = vida - danio;
    }

}
