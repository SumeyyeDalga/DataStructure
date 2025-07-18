package HWSystem.Devices;
import HWSystem.Protocols.Protocol;

/**
 * The {@code Bluetooth} class represents a Bluetooth device that extends the {@code WirelessIO} class.
 * This class is designed to work only with the UART protocol.
 * It provides methods to turn the Bluetooth device ON or OFF and retrieve its name and type.
 */

public class Bluetooth extends WirelessIO {
    /**
     * Constructs a Bluetooth device with the specified protocol.
     * 
     * @param protocol the protocol to be used by the Bluetooth device
     * @throws IllegalArgumentException if the protocol is not UART
     */

    public Bluetooth(Protocol protocol) { 
        super(protocol); 
        if(!(protocol.getProtocolName().equals("UART"))){
            System.err.println("Bluetooth only supports UART protocol.");
            return;
        }
    }
    
    /**
     * Turns the Bluetooth device ON.
     * If the device is already ON, an error message is printed.
     */
    @Override
    public void turnON() {  
        if (state == State.OFF) {  
            state = State.ON;
            protocol.write("turnON");
            System.out.println("Bluteooth: Turning ON.");
        } else {
            System.err.println("Bluteooth is already ON.");
        }
    }

    /**
     * Turns the Bluetooth device OFF.
     * If the device is already OFF, an error message is printed.
     */
    @Override
    public void turnOFF() {  
        if (state == State.ON) {  
            state = State.OFF;
            protocol.write("turnOFF");
            System.out.println("Bluteooth: Turning OFF");
        } else {
            System.err.println("Bluteooth is already OFF.");
        }
    }

    /**
     * Returns the name of the Bluetooth device.
     * 
     * @return the name of the Bluetooth device
     */
    @Override
    public String getName() {
        return "Bluteooth";
    }

    /**
     * Returns the type of the Bluetooth device.
     * 
     * @return the type of the Bluetooth device
     */
    @Override
    public String getDevType() {
        return "WirelessIO";
    }
    
}
