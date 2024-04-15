public class Tablero {
    private int filas;
    private int columnas;
    private Nave[][] matriz;

    //constructor
    public Tablero(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        this.matriz = new Nave[filas][columnas];
    }


    //devuelve true si las coordenadas que se le pase como argumento estan dentro del rango de la matriz
    private boolean validarCoordenadas(int f, int c){
        if(f<0 || f > filas || c<0 || c>columnas){
            return false;
        }else{
            return true;
        }
    }

    //me dice si una casilla esta ocupada o esta vacia (null)
    private boolean celdaOcupada(int f, int c){
        return matriz[f][c] != null;
    }

    //valida que el barco entre en la matriz retorna true si entra false si no entra
    private boolean entraElBarco(Nave nave, int filInicial, int colInicial){
        if(!validarCoordenadas(filInicial, colInicial)){ //chequeo las coord iniciales esten dentro del rango de la matriz
            System.out.println("Error al colocar "+nave.getTipo()+" coordenadas iniciales fuera de rango de matriz");
            return false;
        }
        if(nave.esVertical()){//chequeo para barco vertical
            if((filInicial+nave.getVida())>filas){//si no entra verticalmente
                System.out.println("Error al colocar  "+nave.getTipo()+"  en esa posicion no entra en la matriz de "+filas+" filas y "+columnas+" columnas");
                return false;

            }else return true;//si entra verticalmente retorna true    
        }else{//chequeo para barco horizontal
            if((colInicial+nave.getVida())>columnas){//si no entra horizontalmente
                System.out.println("Error al colocar  "+nave.getTipo()+"  en esa posicion no entra en la matriz de "+filas+" filas y "+columnas+" columnas");
                return false;

            }else return true; //si entra horizontalmente
        }
    }

    //valida si alguna posicion del barco que quiero poner ya esta ocupada por otro barco
    private boolean estaOcupado(Nave nave, int filInicial, int colInicial){
        if(nave.esVertical()){ //si es vertical chequeo verticalmente
            for(int i=filInicial; i<filInicial+nave.getVida();i++){//voy iterando filas segun vida del barco partiendo en coord inicial
                if(celdaOcupada(i, colInicial)){//si alguna esta ocupada retorno true
                    System.out.println("Error al colocar  "+nave.getTipo()+" el lugar "+filInicial+","+colInicial+" esta ocupado por otra nave");
                    return true;
                }
            }
            return false; //si al terminar de iterar no hay ninguna ocupada retorno false
        }else{ // si el barco es horizontal chequeo horizonalmente
            for(int i=colInicial; i<colInicial+nave.getVida();i++){//voy iterando columnas segun vida del barco partiendo en coord inicial
                if(celdaOcupada(filInicial, i)){//si alguna esta ocupada retorno true
                    System.out.println("Error al colocar  "+nave.getTipo()+" el lugar "+filInicial+","+colInicial+" esta ocupado por otra nave");
                    return true;
                }
            }
            return false;
        }
    }


    

    //recibe el barco y la coordenada inicial (la punta del barco), rellena con el barco 
    private void rellenar(Nave nave, int filInicial, int colInicial){
        //si es vertical relleno verticalmente (de arriba hacia abajo)
        if(nave.esVertical()){
            for(int i=filInicial; i<filInicial+nave.getVida();i++){//voy iterando filas
                
                matriz[i][colInicial] = nave;
            }
        }

        //si es horizontal relleno horizontalmente (izquierda a derecha)
        else{
            for(int i=colInicial; i<colInicial+nave.getVida();i++){//voy iterando columnas
                matriz[filInicial][i] = nave;
            }
        }
    }



    public void colocarNave(Nave nave, int filInicial, int colInicial) {
        //solo rellena si el barco entra y no esta ocupado ese lugar
        if(entraElBarco(nave, filInicial, colInicial) && !estaOcupado(nave, filInicial, colInicial))
            rellenar(nave, filInicial, colInicial);

    }


    //muestra el tablero, si no hay nada es ".", si hay un barco muestra el caracter correspondiente a su tipo
    //si hubo disparo efectivo muestra "X", si hubo disparo errado muestra "0"
    //tambien imprime numero de filas y columnas
    public void mostrarTablero(){
        for(int i=-1; i<filas;i++){
            for(int j=-1; j<columnas; j++){
                if(i == -1 ){
                    System.out.print(j+"  ");
                }else{
                    if(j==-1) System.out.print(i+"   ");
                    else{
                    if(celdaOcupada(i, j)){
                        System.out.print(matriz[i][j].getTipo().toUpperCase().charAt(0)+ "  ");
                    }else{  
                        System.out.print("."+"  ");
                    }}
                }
                
            }
            System.out.println();
        }
    }


    //dispara a una coordenada de la matriz, con un cierto danio (por si se agrega en un futuro algun powerup que quite mas de uno de vida)
    public boolean disparar(int f, int c, int danio){
        //valido que las coordenadas esten dentro de la matriz
        if(!validarCoordenadas(f, c)){
            System.out.println("Error coord fuera de rango de matriz");
            return false;
        }
        //primero veo si esta ocupada prosigo sino, pongo "-" significa agua.
        if(celdaOcupada(f, c)){
            //si esta ocupada pero con impacto o con agua repite tiro
            if(matriz[f][c].getTipo().equals("X") || matriz[f][c].getTipo().equals("0" )){
                System.out.println("repite tiro, zona ya disparada");
                return false;
            }else{//si esta ocupado pero no es impacto ni agua significa que hay un barco
                if( matriz[f][c].getVida()>1){//si el barco tiene mas de 1 de vida se le quita la vida y se marca con impacto "X" el lugar
                    matriz[f][c].quitarVida(danio);
                    matriz[f][c] = new Impacto();
                    System.out.println("Disparo efectivo");
                    return true;
                }else{ //si el barco tiene 1 de vida se hace lo mismo pero se informa que el barco fue destruido
                    matriz[f][c].quitarVida(danio);
                    matriz[f][c] = new Impacto();
                    System.out.println("Disparo efectivo, Barco hundido");
                    return true;
                }
            }
            
        }else{//si no estaba ocupada pongo agua

            matriz[f][c] = new Agua();
            System.out.println("Disparo al agua");
            return false;
        }

    }

    
}
