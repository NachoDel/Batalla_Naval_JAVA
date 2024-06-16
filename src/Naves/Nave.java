package src.Naves;

import java.util.Scanner;

public class Nave {
    private String tipo;
    private boolean vertical;
    private int vida;// tamanio
    private boolean estaViva;
    private boolean shield;

    public Nave(String tipo, boolean vertical, int vida) {
        this.tipo = tipo;
        this.vertical = vertical;
        this.vida = vida;
        estaViva = true;
        shield = false;

    }

    public Nave(String tipo, int vida) {
        this.tipo = tipo;
        this.vida = vida;
        vertical = askVerticalidad();
        estaViva = true;
        shield = false;
    }

    /**
     * Pregunta al usuario si desea que la nave sea vertical
     * @return true si la nave es vertical, false si no
     */
    private boolean askVerticalidad(){
        Scanner scanner = new Scanner(System.in);
        String input;
        do {
            System.out.println("Desea que la nave sea vertical? (Y: si, N: no)");
            input = scanner.next();
        } while (!input.equalsIgnoreCase("Y") && !input.equalsIgnoreCase("N"));
        return input.equalsIgnoreCase("Y");
    }

    public void Revive(){
        estaViva = true;
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

    public boolean getEstaViva() {
        return estaViva;
    }

    // quita tanta vida como se le indique
    public void quitarVida() {
        if(shield){
            System.out.println("Nave protegida con escudo, no se le quita vida");
            shield = false;
            return;
        }
        vida--;
        if (vida == 0) {
            estaViva = false;
        }
    }

    public String toString(){
        return tipo + " " + vida + " de vida";
    }

    public boolean getShield(){
        return shield;
    }

    public void setShield(boolean shield){
        this.shield = shield;
    }

}
