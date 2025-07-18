package HWSystem.Devices;

import HWSystem.Protocols.Protocol;

/**
 * The {@code LCD} class represents an LCD display device.
 * It extends the {@code Display} class and provides functionality specific to LCD displays.
 * The LCD device works exclusively with the I2C protocol.
 */
public class LCD extends Display{

    /**
     * Constructs a new {@code LCD} device with the specified protocol.
     * If the protocol is not I2C, an error message is printed.
     *
     * @param protocol the protocol to be used by the LCD device (must be I2C)
     */
    public LCD(Protocol protocol) {
        super(protocol);
        if(!(protocol.getProtocolName().equals("I2C"))){
            System.err.println("LCD only supports I2C protocol.");
            return;
        }
    }

    /**
     * Turns the LCD device ON.
     * If the device is already ON, an error message is printed.
     */
    @Override
    public void turnON() {
        if (state == State.OFF) {
            state = State.ON;
            protocol.write("turnON");
            System.out.println("LCD: Turning ON.");
        } else {
            System.err.println("LCD is already ON.");
        }
    }

    /**
     * Turns the LCD device OFF.
     * If the device is already OFF, an error message is printed.
     */
    @Override
    public void turnOFF() {
        if (state == State.ON) {
            state = State.OFF;
            protocol.write("turnOFF");
            System.out.println("LCD: Turning OFF.");
        } else {
            System.err.println("LCD is already OFF.");
        }
    }

    /**
     * Returns the name of the LCD device.
     *
     * @return the name of the device, which is "LCD"
     */
    @Override
    public String getName() {
        return "LCD";
    }
    
}
