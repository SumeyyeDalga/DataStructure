package HWSystem.Devices;
import HWSystem.Protocols.Protocol;

/**
 * The {@code GY951} class represents an IMU (Inertial Measurement Unit) sensor device.
 * It extends the {@code IMUSensor} class and provides functionality specific to the GY951 sensor.
 * The GY951 sensor supports both UART and SPI protocols.
 */
public class GY951 extends IMUSensor{
    
    /**
     * Constructs a new {@code GY951} device with the specified protocol.
     * If the protocol is not UART or SPI, an error message is printed.
     *
     * @param protocol the protocol to be used by the GY951 device (UART or SPI)
     */
    public GY951(Protocol protocol) {
        super(protocol);
        String proto=protocol.getProtocolName();
        if (!proto.equals("UART") && !proto.equals("SPI")) {
            System.err.println("GY951 only supports UART or SPI protocol.");
            System.err.println("Command failed.");
        }
    }
    
    /**
     * Returns the name of the GY951 device.
     *
     * @return the name of the device, which is "GY-951"
     */
    @Override
    public String getName() {
        return "GY-951";
    }

    /**
     * Turns the GY951 device ON.
     * If the device is already ON, an error message is printed.
     */
    @Override
    public void turnON() {
        if (state == State.OFF) {
            state = State.ON;
            protocol.write("turnON");
            System.out.println("GY951: Turning ON.");
        } else {
            System.err.println("GY951 is already ON.");
        }
    }

    /**
     * Turns the GY951 device OFF.
     * If the device is already OFF, an error message is printed.
     */
    @Override
    public void turnOFF() {
        if (state == State.ON) {
            state = State.OFF;
            protocol.write("turnOFF");
            System.out.println("GY951: Turning OFF.");
        } else {
            System.err.println("GY951 is already OFF.");
        }
    }

    
}
