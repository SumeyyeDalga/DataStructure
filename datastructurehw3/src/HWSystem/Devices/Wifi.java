package HWSystem.Devices;
import HWSystem.Protocols.Protocol;

/**
 * The {@code Wifi} class represents a Wifi communication device.
 * It extends the {@code WirelessIO} class and provides functionality specific to Wifi devices.
 * The Wifi device supports both UART and SPI protocols.
 */
public class Wifi extends WirelessIO{

    /**
     * Constructs a new {@code Wifi} device with the specified protocol.
     * If the protocol is not UART or SPI, an error message is printed.
     *
     * @param protocol the protocol to be used by the Wifi device (must be UART or SPI)
     */
    public Wifi(Protocol protocol) { 
        super(protocol);
        String proto=protocol.getProtocolName();
        if (!(proto.equals("UART") || proto.equals("SPI"))) {
            System.err.println("Wifi only supports UART or SPI protocol.");
            System.err.println("Command failed.");
        } 
    }
    
    /**
     * Turns the Wifi device ON.
     * If the device is already ON, an error message is printed.
     */
    @Override
    public void turnON() {  
        if (state == State.OFF) {  
            state = State.ON;
            protocol.write("turnON");
            System.out.println("Wifi: Turning ON.");
        } else {
            System.err.println("Wifi is already ON.");
        }
    }

    /**
     * Turns the Wifi device OFF.
     * If the device is already OFF, an error message is printed.
     */
    @Override
    public void turnOFF() {  
        if (state == State.ON) {  
            state = State.OFF;
            protocol.write("turnOFF");
            System.out.println("Wifi: Turning OFF.");
        } else {
            System.err.println("Wifi is already OFF.");
        }
    }

    /**
     * Returns the name of the Wifi device.
     *
     * @return the name of the device, which is "Wifi"
     */
    @Override
    public String getName() {
        return "Wifi";
    }

    /**
     * Returns the type of the Wifi device.
     *
     * @return the type of the device, which is "WirelessIO"
     */
    @Override
    public String getDevType() {
        return "WirelessIO";
    } 
    
}
