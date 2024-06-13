package src.PowerUps;

import src.Disparos.DisparoDoble;
import src.Jugador;

public class DoubleShotPU extends PowerUp{
    public DoubleShotPU(){
        this.nombre = "DoubleShot";
    }

    @Override
    public void activar(){
        System.out.println("DoubleShot activado");
    }

}
