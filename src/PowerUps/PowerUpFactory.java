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
            return new ShieldPU();
        }
        else if(powerUpType.equalsIgnoreCase("Radar")){
            return new RadarPU();
        }
        else if(powerUpType.equalsIgnoreCase("Revivir")){
            return new RevivirHundidoPU();
        }
        return null;

    }
}
