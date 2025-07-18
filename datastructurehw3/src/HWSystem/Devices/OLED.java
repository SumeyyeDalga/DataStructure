package HWSystem.Devices;
import HWSystem.Protocols.Protocol;

/**
 * The {@code OLED} class represents an OLED display device.
 * It extends the {@code Display} class and provides functionality specific to OLED displays.
 * The OLED device works exclusively with the SPI protocol.
 */
public class OLED extends Display {

    /**
     * Constructs a new {@code OLED} device with the specified protocol.
     * If the protocol is not SPI, an error message is printed.
     *
     * @param protocol the protocol to be used by the OLED device (must be SPI)
     */
    public OLED(Protocol protocol) {
        super(protocol);
        if (!(protocol.getProtocolName().equals("SPI"))) {
            System.err.println("OLED only supports SPI protocol.");
            return;
        }
    }

    /**
     * Turns the OLED device ON.
     * If the device is already ON, an error message is printed.
     */
    @Override
    public void turnON() {
        if (state == State.OFF) {
            state = State.ON;
            protocol.write("turnON");
            System.out.println("OLED: Turning ON.");
        } else {
            System.err.println("OLED is already ON.");
        }
    }

    /**
     * Turns the OLED device OFF.
     * If the device is already OFF, an error message is printed.
     */
    @Override
    public void turnOFF() {
        if (state == State.ON) {
            state = State.OFF;
            protocol.write("turnOFF");
            System.out.println("OLED: Turning OFF.");
        } else {
            System.err.println("OLED is already OFF.");
        }
    }

    /**
     * Returns the name of the OLED device.
     *
     * @return the name of the device, which is "OLED"
     */
    @Override
    public String getName() {
        return "OLED";
    }
    
}
