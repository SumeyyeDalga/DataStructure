package HWSystem.Devices;
import HWSystem.Protocols.Protocol;

/**
 * The {@code SparkFunMD} class represents a motor driver device.
 * It extends the {@code MotorDriver} class and provides functionality specific to the SparkFun motor driver.
 * The SparkFun motor driver works exclusively with the SPI protocol.
 */
public class SparkFunMD extends MotorDriver{

    /**
     * Constructs a new {@code SparkFunMD} device with the specified protocol.
     * If the protocol is not SPI, an error message is printed.
     *
     * @param protocol the protocol to be used by the SparkFun motor driver (must be SPI)
     */
    public SparkFunMD(Protocol protocol) {
        super(protocol);
        if(!(protocol.getProtocolName().equals("SPI"))){
            System.err.println("SparkFun Motor Driver only supports SPI protocol.");
            return;
        }
    }

    /**
     * Turns the SparkFun motor driver ON.
     * If the device is already ON, an error message is printed.
     */
    @Override
    public void turnON() {  
        if (state == State.OFF) {  
            state = State.ON;
            protocol.write("turnON");
            System.out.println("SparkFun Motor Driver: Turning ON.");
        } else {
            System.err.println("SparkFun Motor Driver is already ON.");
        }
    }

    /**
     * Turns the SparkFun motor driver OFF.
     * If the device is already OFF, an error message is printed.
     */
    @Override
    public void turnOFF() {  
        if (state == State.ON) {  
            state = State.OFF;
            protocol.write("turnOFF");
            System.out.println("SparkFun Motor Driver: Turning OFF.");
        } else {
            System.err.println("SparkFun Motor Driver is already OFF.");
        }
    }

    /**
     * Returns the name of the SparkFun motor driver.
     *
     * @return the name of the device, which is "SparkFun"
     */
    @Override
    public String getName() {
        return "SparkFun";
    }
    
}
