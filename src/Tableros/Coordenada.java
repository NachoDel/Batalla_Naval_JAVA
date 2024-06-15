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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Coordenada that = (Coordenada) obj;
        return fila == that.fila && columna == that.columna;
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
