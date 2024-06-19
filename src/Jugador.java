package src;

import java.util.ArrayList;
import java.util.Scanner;

import src.Disparos.Disparo;
import src.Disparos.DisparoDoble;
import src.Disparos.DisparoSimple;
import src.Naves.*;
import src.PowerUps.PowerUp;
import src.PowerUps.PowerUpFactory;
import src.Tableros.Coordenada;
import src.Tableros.Tablero;

public class Jugador {
    private String nombre;
    private Jugador oponente;
    private Tablero tablero;
    private Disparo disparo;
    private ArrayList<PowerUp> powerUps;
    //private int contAguas;
    private int contAguasRacha;
    //private int contAcierto;
    private int contAciertoRacha;
    private int contDerribo; 



    public Jugador(String nombre, int filas, int columnas) {
        this.nombre = nombre;
        tablero = new Tablero(filas, columnas);
        this.disparo = new DisparoSimple();
        powerUps = new ArrayList<>();
        //contAguas = 0;
        contAguasRacha = 0;
        //contAcierto = 0;
        contAciertoRacha = 0;
        contDerribo = 0;
        this.oponente = oponente;
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
        if(oponente.getTablero().getMatriz()[coord.getFila()][coord.getColumna()] instanceof Agua) {
            //contAguas++;
            contAguasRacha++;
            //contAcierto = 0;
            contAciertoRacha = 0;
        }if(oponente.getTablero().getMatriz()[coord.getFila()][coord.getColumna()] instanceof Impacto){
            //contAcierto++;
            contAciertoRacha++;
            //contAguas = 0;
            contAguasRacha = 0;
        }
        if(disparo instanceof DisparoDoble){
            setDisparo(new DisparoSimple());
        }

        if(oponente.getTablero().getNavesConVida() == 0){
            return false;
        }
        merecePU();
        return !(oponente.getTablero().getMatriz()[coord.getFila()][coord.getColumna()] instanceof Agua);
    }

    private void merecePU(){
        PowerUpFactory fabricaPU = new PowerUpFactory();

        if(contAciertoRacha == 5){
            Scanear scanear = Scanear.getInstance();
            Scanner scanner = scanear.getScanner();
            System.out.println("¡Felicidades 5 aciertos seguidos!, te has ganado un power up, elige uno de los siguientes: ");
            System.out.println("1. Shield   2. DisparoDoble");
            int opcion = 0;
            do {
                try {
                    opcion = scanner.nextInt();
                } catch (Exception e) {
                    System.out.println("Opcion no valida, intente de nuevo");
                    scanner.next(); // consume the invalid input
                }
            } while (opcion != 1 && opcion != 2);

            switch (opcion){
                case 1:
                    addPowerUp(fabricaPU.crearPowerUp("Shield"));
                    break;
                case 2:
                    addPowerUp(fabricaPU.crearPowerUp("DoubleShot"));
                    break;
            }
        }

        if(contAciertoRacha == 7){
            System.out.println("¡No paras! ¡7 aciertos seguidos!, te has ganado un power up, podras revivir un barco hundido");
            addPowerUp(fabricaPU.crearPowerUp("Revivir"));

            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            contAciertoRacha = 0;
        }

        if(contAguasRacha == 5){
            System.out.println("No te precupes, te daremos una ayuda, ahora tienes un radar! revelera una posicion aleatoria de un barco enemigo");
            addPowerUp(fabricaPU.crearPowerUp("Radar"));

            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            contAguasRacha = 0;
        }
    }


    /**
     * Crea y coloca las naves en el tablero
     */
    public void crearYColocarNaves() {

        System.out.println("Colocando submarino - 2 casillas");
        boolean v = askVerticalidad();
        while(!tablero.colocarNave(new Submarino(v))){
            System.out.println("Reingrese coordenadas");
        }
        tablero.mostrarTablero();

        System.out.println("Colocando buque - 4 casillas");
        v= askVerticalidad();
        while(!tablero.colocarNave(new Buque(v))){
            System.out.println("Reingrese coordenadas");
        }
        tablero.mostrarTablero();

        System.out.println("Colocando portaaviones - 5 casillas");
        v= askVerticalidad();
        while(!tablero.colocarNave(new Portaaviones(v))){
            System.out.println("Reingrese coordenadas");
        }
        tablero.mostrarTablero();

        System.out.println("Colocando acorazado - 6 casillas");
        v= askVerticalidad();
        while(!tablero.colocarNave(new Acorazado(v))){
            System.out.println("Reingrese coordenadas");
        }
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

    public void activarPowerUp(String nombre){
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

    public String powerUpsToString(){
        String s ="";
        for(PowerUp powerUp : powerUps){
            s+=("[ "+powerUp.getNombre()+ "] ");
        }
        return s;
    }

    /**
     * Pregunta al usuario si desea que la nave sea vertical
     * @return true si la nave es vertical, false si no
     */
    public boolean askVerticalidad(){
        Scanear scanear = Scanear.getInstance();
        Scanner scanner = scanear.getScanner();
        String input;
        do {
            System.out.println("Desea que la nave sea vertical? (Y: si, N: no)");
            input = scanner.next();
        } while (!input.equalsIgnoreCase("Y") && !input.equalsIgnoreCase("N"));
        return input.equalsIgnoreCase("Y");
    }

    public Jugador getOponente() {
        return oponente;
    }

    public void setOponente(Jugador oponente) {
        this.oponente = oponente;
    }
}
