package HWSystem.Devices;

import HWSystem.Protocols.Protocol;

/**
 * The {@code DHT11} class represents a temperature sensor device.
 * It extends the {@code TempSensor} class and provides functionality specific to the DHT11 sensor.
 * The DHT11 sensor works exclusively with the OneWire protocol.
 */
public class DHT11 extends TempSensor {

    /**
     * Constructs a new {@code DHT11} device with the specified protocol.
     * If the protocol is not OneWire, an error message is printed.
     *
     * @param protocol the protocol to be used by the DHT11 device (OneWire)
     */
    public DHT11(Protocol protocol) {
        super(protocol);
        if (!protocol.getProtocolName().equals("OneWire")) {
            System.err.println("DHT11 can only work with OneWire protocol.");
            System.err.println("Command failed.");
        }
    }

    /**
     * Turns the DHT11 device ON.
     * If the device is already ON, an error message is printed.
     */
    @Override
    public void turnON() {  
        if (state == State.OFF) {  
            state = State.ON;
            protocol.write("turnON");
            System.out.println("DHT11: Turning ON.");
        } else {
            System.err.println("DHT11 is already ON.");
        }
    }

    /**
     * Turns the DHT11 device OFF.
     * If the device is already OFF, an error message is printed.
     */
    @Override
    public void turnOFF() {  
        if (state == State.ON) {  
            state = State.OFF;
            protocol.write("turnOFF");
            System.out.println("DHT11: Turning OFF.");
        } else {
            System.err.println("DHT11 is already OFF.");
        }
    }

    /**
     * Returns the name of the DHT11 device.
     *
     * @return the name of the device, which is "DHT11"
     */
    @Override
    public String getName() {
        return "DHT11";
    }
}
