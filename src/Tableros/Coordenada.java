package src.Tableros;

public class Coordenada {
    private int fila;
    private int columna;

    public Coordenada(int fila, int columna){
        this.fila = fila;
        this.columna = columna;
    }

    public Coordenada(){
        this.fila = 0;
        this.columna = 0;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }
    public void setColumna(int columna) {
        this.columna = columna;
    }
    public int getFila() {
        return fila;
    }
    public int getColumna() {
        return columna;
    }
}
