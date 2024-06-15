package src.PowerUps;

public class PowerUpFactory {

    public PowerUp crearPowerUp(String powerUpType){

        if (powerUpType == null) {
            return null;
        }
        if (powerUpType.equalsIgnoreCase("DoubleShot")) {
            return new DoubleShotPU();
        }
        else if (powerUpType.equalsIgnoreCase("Shield")) {
            return new Shield();
        }
        else if(powerUpType.equalsIgnoreCase("Radar")){
            return new RadarPU();
        }
        return null;

    }
}
