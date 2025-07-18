package HWSystem.Devices;
import HWSystem.Protocols.Protocol;

/**
 * The {@code PCA9685} class represents a motor driver device.
 * It extends the {@code MotorDriver} class and provides functionality specific to the PCA9685 motor driver.
 * The PCA9685 device works exclusively with the I2C protocol.
 */
public class PCA9685 extends MotorDriver{

    /**
     * Constructs a new {@code PCA9685} device with the specified protocol.
     * If the protocol is not I2C, an error message is printed.
     *
     * @param protocol the protocol to be used by the PCA9685 device (must be I2C)
     */
    public PCA9685(Protocol protocol) {
        super(protocol);
        if(!(protocol.getProtocolName().equals("I2C"))){
            System.err.println("PCA9685 only supports I2C protocol.");
            return;
        }
    }

    /**
     * Turns the PCA9685 device ON.
     * If the device is already ON, an error message is printed.
     */
    @Override
    public void turnON() {  
        if (state == State.OFF) {  
            state = State.ON;
            protocol.write("turnON");
            System.out.println("PCA9685: Turning ON.");
        } else {
            System.err.println("PCA9685 is already ON.");
        }
    }

    /**
     * Turns the PCA9685 device OFF.
     * If the device is already OFF, an error message is printed.
     */
    @Override
    public void turnOFF() {  
        if (state == State.ON) {  
            state = State.OFF;
            protocol.write("turnOFF");
            System.out.println("PCA9685: Turning OFF.");
        } else {
            System.err.println("PCA9685 is already OFF.");
        }
    }

    /**
     * Returns the name of the PCA9685 device.
     *
     * @return the name of the device, which is "PCA9685"
     */
    @Override
    public String getName() {
        return "PCA9685";
    }
    
}
