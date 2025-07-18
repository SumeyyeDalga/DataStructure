package HWSystem.Devices;
import HWSystem.Protocols.I2C;

public class PCA9685 extends MotorDriver{
    public PCA9685(I2C protocol) {
        super(protocol);
    }
    @Override
    public void turnON() {  
        if (state == State.OFF) {  
            state = State.ON;
            protocol.write("turnON");
            System.out.println("PCA9685 is ON");
        } else {
            System.out.println("PCA9685 is already ON.");
        }
    }

    @Override
    public void turnOFF() {  
        if (state == State.ON) {  
            state = State.OFF;
            protocol.write("turnOFF");
            System.out.println("PCA9685 is OFF");
        } else {
            System.out.println("PCA9685 is already OFF.");
        }
    }

    @Override
    public String getName() {
        return "PCA9685";
    }
    
}
