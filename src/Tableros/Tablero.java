package src.Tableros;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;
import src.Naves.Agua;
import src.Naves.Impacto;
import src.Naves.Nave;

public class Tablero {
    private int filas;
    private int columnas;
    private Nave[][] matriz;
    private int navesConVida;
    private HashMap<Nave,ArrayList<Coordenada>> coordenadasNave;

    // constructor
    public Tablero(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        this.matriz = new Nave[filas][columnas];
        this.navesConVida = 0;
    }

    // devuelve true si las coordenadas que se le pase como argumento estan dentro
    // del rango de la matriz
    public boolean validarCoordenadas(int f, int c) {
        if (f < 0 || f > filas || c < 0 || c > columnas) {
            return false;
        } else {
            return true;
        }
    }

    public int[] pedirCoordenadas() {
        int[] coordenadas = new int[2];
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.print("Ingrese fila: ");
            coordenadas[0] = scanner.nextInt();
            System.out.print("Ingrese columna: ");
            coordenadas[1] = scanner.nextInt();
            if (!validarCoordenadas(coordenadas[0], coordenadas[1])) {
                System.out.println("Error: Coordenadas fuera de rango. Por favor, reingrese.");
            }
        } while (!validarCoordenadas(coordenadas[0], coordenadas[1]));
        return coordenadas;
    }

    // me dice si una casilla esta ocupada o esta vacia (null)
    private boolean celdaOcupada(int f, int c) {
        return matriz[f][c] != null;
    }

    // valida que el barco entre en la matriz retorna true si entra false si no
    // entra
    public boolean entraElBarco(Nave nave, int filInicial, int colInicial) {

        if (nave.esVertical()) {// chequeo para barco vertical
            if ((filInicial + nave.getVida()) > filas) {// si no entra verticalmente
                System.out.println("Error al colocar " + nave.getTipo() + "  en esa posicion no entra en la matriz de "
                        + filas + " filas y " + columnas + " columnas");
                return false;
            } else
                return true;// si entra verticalmente retorna true
        } else {// chequeo para barco horizontal
            if ((colInicial + nave.getVida()) > columnas) {// si no entra horizontalmente
                System.out.println("Error al colocar " + nave.getTipo() + "  en esa posicion no entra en la matriz de "
                        + filas + " filas y " + columnas + " columnas");
                return false;

            } else
                return true; // si entra horizontalmente
        }
    }

    // valida si alguna posicion del barco que quiero poner ya esta ocupada por otro
    // barco
    public boolean estaOcupado(Nave nave, int filInicial, int colInicial) {
        if (nave.esVertical()) { // si es vertical chequeo verticalmente
            for (int i = filInicial; i < filInicial + nave.getVida(); i++) {// voy iterando filas segun vida del barco
                                                                            // partiendo en coord inicial
                if (celdaOcupada(i, colInicial)) {// si alguna esta ocupada retorno true
                    System.out.println("Error al colocar  " + nave.getTipo() + " el lugar " + filInicial + ","
                            + colInicial + " esta ocupado por otra nave");
                    return true;
                }
            }
            return false; // si al terminar de iterar no hay ninguna ocupada retorno false
        } else { // si el barco es horizontal chequeo horizonalmente
            for (int i = colInicial; i < colInicial + nave.getVida(); i++) {// voy iterando columnas segun vida del
                                                                            // barco partiendo en coord inicial
                if (celdaOcupada(filInicial, i)) {// si alguna esta ocupada retorno true
                    System.out.println("Error al colocar  " + nave.getTipo() + " el lugar " + filInicial + ","
                            + colInicial + " esta ocupado por otra nave");
                    return true;
                }
            }
            return false;
        }
    }

    // recibe el barco y la coordenada inicial (la punta del barco), rellena con el
    // barco
    public void rellenar(Nave nave, int filInicial, int colInicial) {
        // si es vertical relleno verticalmente (de arriba hacia abajo)
        if (nave.esVertical()) {
            for (int i = filInicial; i < filInicial + nave.getVida(); i++) {// voy iterando filas

                matriz[i][colInicial] = nave;
            }
        }

        // si es horizontal relleno horizontalmente (izquierda a derecha)
        else {
            for (int i = colInicial; i < colInicial + nave.getVida(); i++) {// voy iterando columnas
                matriz[filInicial][i] = nave;
            }
        }
        navesConVida++;
    }

    /*
     * public void colocarNave(Nave nave, int filInicial, int colInicial) {
     * // solo rellena si el barco entra y no esta ocupado ese lugar
     * if (entraElBarco(nave, filInicial, colInicial) && !estaOcupado(nave,
     * filInicial, colInicial))
     * rellenar(nave, filInicial, colInicial);
     * else{
     * if()
     * }
     * 
     * }
     */
    public void colocarNave(Nave nave) {
        // solo rellena si el barco entra y no esta ocupado ese lugar
        int[] coord = pedirCoordenadas();
        int filInicial = coord[0];
        int colInicial = coord[1];

        while (!entraElBarco(nave, filInicial, colInicial) || estaOcupado(nave, filInicial, colInicial)) {
            System.out.println("Reingrese coordenadas");
            coord = pedirCoordenadas();
            filInicial = coord[0];
            colInicial = coord[1];
        }

        rellenar(nave, filInicial, colInicial);
        //Hasta aca logica para pedir coordenadas y colocar la nave

        //Ahora logica para guardar las coordenadas de esa nave
        ArrayList<Coordenada> listaCoordenadas = new ArrayList<Coordenada>();
        //guardo la  coordenada inicial primero
        Coordenada cord = new Coordenada(filInicial, colInicial);
        listaCoordenadas.add(cord);
        //guardo el resto de coordenadas
        for (int i = nave.getVida(); i > 0; i--){
            if(nave.esVertical()){
                cord = new Coordenada(filInicial + i, colInicial);
            }else{
                cord = new Coordenada(filInicial, colInicial + i);
            }
            listaCoordenadas.add(cord);
        }
        coordenadasNave.put(nave, listaCoordenadas);
    }
    public ArrayList<Coordenada> getCoordenadasNave(Coordenada coordenada){
        for (Nave nave : coordenadasNave.keySet()){
            for (Coordenada cord : coordenadasNave.get(nave)){
                if(cord.equals(coordenada)){
                    return coordenadasNave.get(nave);
                }
            }
        }
        return null;
    }

    //Tener en cuenta que no se puede llenar el tablero desde aca NOTHANDLED
    public void colocarNaveParaTest(Nave nave) {
        // Inserto como coordenadas inciales las (0,0)
        int[] coord = new int[2];
        coord[0] = 0;
        coord[1] = 0;
        // Si llega a estar ocupado, sumo en 1 la fila, para ir recorriendo hacia abajo
        while (estaOcupado(nave, coord[0], coord[1]) && coord[0] < this.filas) {
            coord[0]++;
        }
        if(coord[0] == this.filas){
            coord[0] = 0;
        }
        // En caso de que toda la fila este ocupada, Recorro por columnas
        while (estaOcupado(nave, coord[0], coord[1]) && coord[1] < this.columnas) {
            coord[1]++;
        }
        rellenar(nave, coord[0], coord[1]);
    }

    // muestra el tablero, si no hay nada es ".", si hay un barco muestra el
    // caracter correspondiente a su tipo
    // si hubo disparo efectivo muestra "X", si hubo disparo errado muestra "0"
    // tambien imprime numero de filas y columnas
    public void mostrarTablero() {
        for (int i = -1; i < filas; i++) {
            for (int j = -1; j < columnas; j++) {
                if (i == -1) {
                    System.out.print(j + "  ");
                } else {
                    if (j == -1)
                        System.out.print(i + "   ");
                    else {
                        if (celdaOcupada(i, j)) {
                            System.out.print(matriz[i][j].getTipo().toUpperCase().charAt(0) + "  ");
                        } else {
                            System.out.print("." + "  ");
                        }
                    }
                }

            }
            System.out.println();
        }
    }

    public void mostrarOculto() {
        for (int i = -1; i < filas; i++) {
            for (int j = -1; j < columnas; j++) {
                if (i == -1) {
                    System.out.print(j + "  ");
                } else {
                    if (j == -1)
                        System.out.print(i + "   ");
                    else {
                        if (matriz[i][j] instanceof Impacto) {
                            System.out.print("X" + "  ");
                        } else if (matriz[i][j] instanceof Agua) {
                            System.out.print("0" + "  ");
                        } else {
                            System.out.print("." + "  ");
                        }

                    }

                }
            }
            System.out.println();
        }
    }

    // dispara a una coordenada de la matriz, con un cierto danio (por si se agrega
    // en un futuro algun powerup que quite mas de uno de vida)
    // retorna true si el disparo pudo hacerse, false si esa zona ya fue disparada
    // previamente
    public boolean recibirDisparo(int f, int c) {
        // primero veo si esta ocupada prosigo sino, pongo "-" significa agua.
        if (celdaOcupada(f, c)) {
            // si esta ocupada pero con impacto o con agua repite tiro
            if (matriz[f][c] instanceof Impacto || matriz[f][c] instanceof Agua) {
                System.out.println("repite tiro, zona ya disparada");
                return false;
            } else {// si esta ocupado pero no es impacto ni agua significa que hay un barco
                if (matriz[f][c].getVida() > 0) {
                    // si el barco aguanta el tiro se le quita la vida y
                    // se marca con impacto "X" el lugar
                    matriz[f][c].quitarVida();
                    matriz[f][c] = new Impacto();
                    System.out.println("Disparo efectivo");
                    //si la nave se destruyo, reduzco naves con vida
                    if(!matriz[f][c].getEstaViva()) {
                        System.out.println("Barco hundido");
                        navesConVida--;
                    }
                }
                return true;
            }

        } else {// si no estaba ocupada pongo agua

            matriz[f][c] = new Agua();
            System.out.println("Disparo al agua");
            return true;
        }

    }

    public Nave[][] getMatriz() {
        return matriz;
    }

    public int getNavesConVida() {
        return navesConVida;
    }

}
