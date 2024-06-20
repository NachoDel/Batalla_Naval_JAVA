package src.Tableros;

import java.util.*;

import src.Naves.Agua;
import src.Naves.Impacto;
import src.Naves.Nave;
import src.Scanear;

public class Tablero {
    private int filas;
    private int columnas;
    private Nave[][] matriz; 
    private int navesConVida;
    private HashMap<Nave,ArrayList<Coordenada>> mapaDeNaves;
    private Coordenada coordenadaAyudaRadar;
    private boolean radarActivo = false;

    // constructor
    public Tablero(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        this.matriz = new Nave[filas][columnas];
        this.navesConVida = 0;
        this.mapaDeNaves = new HashMap<>();
    }

    /**
     * me dice si una casilla esta ocupada o esta vacia (null)
     *
     * @param f fila inicial
     * @param c columna inicial
     * @return true si esta ocupada, false si esta vacia
     */
    private boolean celdaOcupada(int f, int c) {
        return matriz[f][c] != null;
    }

    // devuelve true si las coordenadas que se le pase como argumento estan dentro
    // del rango de la matriz
    private boolean validarCoordenadas(Coordenada c) {
        return ((c.getFila() >= 0 && c.getFila() < filas) && (c.getColumna() >= 0 && c.getColumna() < columnas));
    }

    /**
     * pide coordenada al usuario, valida que este dentro del rango de la matriz
       hasta que no sea valida no deja de pedirlas
     * @return objeto Coordenada con las coordenadas validas
     */
    public Coordenada pedirCoordenadas() {
        Coordenada coordenada = new Coordenada();
        Scanear scanear = Scanear.getInstance();
        Scanner scanner = scanear.getScanner();

        try{
            do {
                System.out.print("Ingrese fila: ");
                coordenada.setFila(scanner.nextInt());
                System.out.print("Ingrese columna: ");
                coordenada.setColumna(scanner.nextInt());
                if (!validarCoordenadas(coordenada)) {
                    System.out.println("Error: Coordenadas fuera de rango. Por favor, reingrese.");
                }
            } while (!validarCoordenadas(coordenada));
        }catch (InputMismatchException e){
            System.out.println("Error, debe ingresar un numero entero");
            scanner.nextLine();
            return pedirCoordenadas();
        }

        return coordenada;
    }


    /**
     * Este método valida si un barco puede encajar dentro de la matriz basándose en su posición inicial.
     *
     * @param nave El barco que se necesita colocar en la matriz.
     * @param coord La coordenada inicial para el barco.
     * @return true si el barco cabe dentro de la matriz, false en caso contrario.
     */
    private boolean entraElBarco(Nave nave, Coordenada coord) {

        if (nave.esVertical()) {// chequeo para barco vertical
            if ((coord.getFila() + nave.getVida()) > filas) {// si no entra verticalmente
                System.out.println("Error al colocar " + nave.getTipo() + "  en esa posicion no entra en la matriz de "
                        + filas + " filas y " + columnas + " columnas");
                return false;
            } else
                return true;// si entra verticalmente retorna true
        } else {// chequeo para barco horizontal
            if ((coord.getColumna() + nave.getVida()) > columnas) {// si no entra horizontalmente
                System.out.println("Error al colocar " + nave.getTipo() + "  en esa posicion no entra en la matriz de "
                        + filas + " filas y " + columnas + " columnas");
                return false;

            } else
                return true; // si entra horizontalmente
        }
    }

    /**
     * Este método verifica si alguna posición del barco que se desea colocar ya está ocupada por otro barco.
     *
     * @param nave El barco que se necesita colocar en la matriz.
     * @param coord La coordenada inicial para el barco.
     * @return true si alguna posición del barco está ocupada, false si todas las posiciones están libres.
     */
    private boolean estaOcupado(Nave nave, Coordenada coord) {
        if (nave.esVertical()) { // si es vertical chequeo verticalmente
            for (int i = coord.getFila(); i < coord.getFila() + nave.getVida(); i++) {// voy iterando filas segun vida del barco
                                                                            // partiendo en coord inicial
                if (celdaOcupada(i, coord.getColumna())) {// si alguna esta ocupada retorno true
                    if(matriz[coord.getFila()][coord.getColumna()] instanceof Impacto || matriz[coord.getFila()][coord.getColumna()] instanceof Agua){
                        System.out.println("Error al colocar  " + nave.getTipo() + " el lugar " + coord.getFila() + ","
                                + coord.getColumna() + " ya fue disparado");
                    }else{
                        System.out.println("Error al colocar  " + nave.getTipo() + " el lugar " + coord.getFila() + ","
                                + coord.getColumna() + " esta ocupado por otra nave");
                    }
                    return true;
                }
            }
            return false; // si al terminar de iterar no hay ninguna ocupada retorno false
        } else { // si el barco es horizontal chequeo horizonalmente
            for (int i = coord.getColumna(); i < coord.getColumna() + nave.getVida(); i++) {// voy iterando columnas segun vida del
                                                                            // barco partiendo en coord inicial
                if (celdaOcupada(coord.getFila(), i)) {// si alguna esta ocupada retorno true
                    System.out.println("Error al colocar  " + nave.getTipo() + " el lugar " + coord.getFila() + ","
                            + coord.getColumna() + " esta ocupado por otra nave");
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * Recibe una nave y la coloca pidiendo coordenadas al usuario, valida que
     * estas coordenas sean validas, que el barco entre en la matriz y que no este
     * ocupado el lugar donde se quiere poner (valida todo)
     * @param nave nave a colocar
     */
    public boolean colocarNave(Nave nave) {
        // solo rellena si el barco entra y no esta ocupado ese lugar
        Coordenada coord = pedirCoordenadas();

        if(!entraElBarco(nave, coord) || estaOcupado(nave, coord)) {
            return false;
        }

        rellenar(nave, coord);
        return true;
    }

    /**
     * Este método recibe un barco y una coordenada inicial (la punta del barco), y rellena la matriz con el barco.
     * tanto si es vertical como horizontal. Agrega las coordenadas de la nave a un hashmap con la nave como key.
     * @param nave El barco que se necesita colocar en la matriz.
     * @param coord La coordenada inicial para el barco.
     */
    public void rellenar(Nave nave, Coordenada coord) {
        // si es vertical relleno verticalmente (de arriba hacia abajo)
        if (nave.esVertical()) {
            for (int i = coord.getFila(); i < coord.getFila() + nave.getVida(); i++) {// voy iterando filas

                matriz[i][coord.getColumna()] = nave;
            }
        }

        // si es horizontal relleno horizontalmente (izquierda a derecha)
        else {
            for (int i = coord.getColumna(); i < coord.getColumna() + nave.getVida(); i++) {// voy iterando columnas
                matriz[coord.getFila()][i] = nave;
            }
        }
        navesConVida++;
        //Hasta aca logica para pedir coordenadas y colocar la nave

        //Ahora logica para guardar las coordenadas de esa nave en un hashmap con la nave como key
        ArrayList<Coordenada> listaCoordenadas = new ArrayList<>();
        //guardo la  coordenada inicial primero
        listaCoordenadas.add(coord);
        //guardo el resto de coordenadas

        for (int i = 0; i < nave.getVida()-1; i++){ // el bucle termina en vida -1 porque ya guarde la coordenada inicial antes
            if(nave.esVertical()){
                coord = new Coordenada(coord.getFila()+1, coord.getColumna());
            }else{
                coord = new Coordenada(coord.getFila() , coord.getColumna()+1 );
            }
            listaCoordenadas.add(coord);
        }
        mapaDeNaves.put(nave, listaCoordenadas); //queda guardada la nave con sus coordenadas
    }




    /**
     * recibe un disparo en las coordenadas f,c si hay un barco en esa posicion
     * quita vida al barco y marca con "X" si no hay barco marca con "0"
     * @param coord coordenadas del disparo
     * @return true si se pudo hacer el disparo, false si era una zona ya disparada
     */
    public boolean recibirDisparo(Coordenada coord) {
        // primero veo si esta ocupada prosigo sino,ica agua.
        int f = coord.getFila();
        int c = coord.getColumna();
        if (celdaOcupada(f, c)) {
            // si esta ocupada pero con impacto o con agua repite tiro
            if (matriz[f][c] instanceof Impacto || matriz[f][c] instanceof Agua) {
                System.out.println("repite tiro, zona ya disparada");
                return false;
            } else {// si esta ocupado pero no es impacto ni agua significa que hay un barco
                    if(!matriz[f][c].getShield()){
                        if (matriz[f][c].getVida() > 1) {
                            // si el barco aguanta el tiro se le quita la vida y
                            // se marca con impacto "X" el lugar
                            matriz[f][c].quitarVida();
                            matriz[f][c] = new Impacto();
                            System.out.println("Disparo efectivo");
                            //si la nave se destruyo, reduzco naves con vida

                        }else{
                            matriz[f][c].quitarVida();
                            matriz[f][c] = new Impacto();
                            System.out.println("BARCO HUNDIDO");
                            navesConVida--;
                        }
                    }else{
                        matriz[f][c].quitarVida();
                    }
                return true;
            }

        } else {// si no estaba ocupada pongo agua
            matriz[f][c] = new Agua();
            System.out.println("Disparo al agua");

            return true;
        }

    }


    /**
     * Devuelve la nave que se encuentra en la coordenada pasada como argumento
     * @param coordenada coordenada a buscar
     * @return Nave que se encuentra en la coordenada, null si no hay ninguna nave en esa coordenada
     */
    public Nave getNaveEnCoordenada(Coordenada coordenada){
        for (Nave nave : mapaDeNaves.keySet()){
            for (Coordenada cord : mapaDeNaves.get(nave)){
                if(cord.getFila() == coordenada.getFila() && cord.getColumna() == coordenada.getColumna()){
                    return nave;
                }
            }
        }
        return null;
    }



    /**
     * Devuelve las coordenadas de la nave que se encuentra en la coordenada pasada como argumento
     * @param coordenada coordenada a buscar
     * @return ArrayList de Coordenadas de la nave que se encuentra en la coordenada, null si no hay ninguna nave en esa coordenada
     */
    public ArrayList<Coordenada> getCoordenadasDeNave(Coordenada coordenada){
        try{
            return mapaDeNaves.get(getNaveEnCoordenada(coordenada));
        }catch (NullPointerException e){
            System.out.println("No hay ninguna nave en esa coordenada");
        }
        return null;
    }

   /**
     * Devuelve una coordenada de un barco vivo aleatorio
     * @return Coordenada de un barco vivo aleatorio, null si no hay barcos vivos
     */

    public Coordenada obtenerCoordenadaBarcoRandom() {
        ArrayList<Coordenada> coordenadasBarcosVivos = new ArrayList<>();
        for (Nave nave : mapaDeNaves.keySet()){
            if(nave.getEstaViva()){
                coordenadasBarcosVivos.addAll(mapaDeNaves.get(nave));
            }
        }
        if (coordenadasBarcosVivos.isEmpty()) {
            return null; // No hay barcos vivos
        }
        Random rand = new Random();
        int indiceAleatorio = rand.nextInt(coordenadasBarcosVivos.size());
        return coordenadasBarcosVivos.get(indiceAleatorio);
    }
    public void setRadarActivo(boolean activo){
        this.radarActivo = activo;
    }
    public void setCoordenadaAyudaRadar(Coordenada coordenada) {
        this.coordenadaAyudaRadar = coordenada;
    }
    public Coordenada getCoordenadaAyudaRadar() {
        return coordenadaAyudaRadar;
    }

    public boolean estaLaNave(String nombreNave){
        for (Nave nave : mapaDeNaves.keySet()){
            if(nave.getTipo().equalsIgnoreCase(nombreNave)){
                return true;
            }
        }
        return false;
    }

    public Nave getNave(String nombreNave){
        for (Nave nave : mapaDeNaves.keySet()){
            if(nave.getTipo().equalsIgnoreCase(nombreNave)){
                return nave;
            }
        }
        return null;
    }

    public Nave[][] getMatriz() {
        return matriz;
    }

    public int getNavesConVida() {
        return navesConVida;
    }

    public HashMap<Nave,ArrayList<Coordenada>> getMapaDeNaves(){
        return mapaDeNaves;
    }




    /**
     * muestra el tablero, si no hay nada es ".", si hay un barco muestra el
     * caracter correspondiente a su tipo, se usa cuando se colocan las naves unicamente
     */
    public void mostrarTablero() {
        for (int i = -1; i < filas; i++) {
            for (int j = -1; j < columnas; j++) {
                if (i == -1) {
                    if (j < 9 && j != -1) {
                        System.out.print(j + "  ");
                    } else {
                        System.out.print(j + " ");
                    }
                } else {
                    if (j == -1)
                        if (i > 9) {
                            System.out.print(i + " ");
                        } else {
                            System.out.print(i + "  ");
                        }
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


    /**
     * muestra el tablero con "." si fue impactado muestra "X" y si fue
     * agua muestra "0" se usa cuando se esta disparando (vista del otro jugador)
     */
    public void mostrarOculto() {
        for (int i = -1; i < filas; i++) {
            for (int j = -1; j < columnas; j++) {
                if (i == -1) {
                    if (j < 9 && j != -1) {
                        System.out.print(j + "  ");
                    } else {
                        System.out.print(j + " ");
                    }
                } else {
                    if (j == -1)
                        if (i > 9) {
                            System.out.print(i + " ");
                        } else {
                            System.out.print(i + "  ");
                        }
                    else{
                        Coordenada coord = new Coordenada(i,j);
                        if (coord.equals(getCoordenadaAyudaRadar()) && radarActivo) {

                            System.out.print("\033[32m" + "A" + "\033[0m" + "  "); // Imprime 'A' en color verde

                            setRadarActivo(false);
                    }
                        else if (celdaOcupada(i, j)){
                            if(matriz[i][j] instanceof Agua){

                                System.out.print("\u001B[34m"+ "0" +"\u001B[0m"+ "  ");

                            }
                            else if (matriz[i][j] instanceof Impacto) {
                                if (getNaveEnCoordenada(new Coordenada(i, j)).getEstaViva()) {

                                    System.out.print(matriz[i][j].getTipo() + "  ");


                                } else {

                                    System.out.print("\u001B[31m" + getNaveEnCoordenada(new Coordenada(i, j)).getTipo().toUpperCase().charAt(0) + "\u001B[0m" + "  ");


                                }
                            }else{

                                System.out.print("." + "  ");


                            }
                        }else{

                            System.out.print("." + "  ");

                        }
                    }
                }

            }
            System.out.println();
        }
    }

}


