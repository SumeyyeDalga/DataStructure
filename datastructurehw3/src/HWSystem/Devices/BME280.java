package HWSystem.Devices;

import HWSystem.Protocols.Protocol;

/**
 * BME280 class represents a temperature sensor device.
 * It extends the TempSensor class and implements the required methods.
 * The BME280 sensor supports I2C and SPI protocols.
 */
public class BME280 extends TempSensor {

   /**
     * Constructs a new {@code BME280} device with the specified protocol.
     * If the protocol is not I2C or SPI, an error message is printed.
     *
     * @param protocol the protocol to be used by the BME280 device (I2C or SPI)
     */
   public BME280(Protocol protocol) {
        super(protocol);
        
        String proto = protocol.getProtocolName();
        if (!proto.equals("I2C") && !proto.equals("SPI")) {
            System.err.println("BME280 only supports I2C or SPI protocol.");
            System.err.println("Command failed.");
        }
    }

    /**
     * Returns the name of the BME280 device.
     *
     * @return the name of the device, which is "BME280"
     */
    @Override
    public String getName() {
        return "BME280";
    }

    /**
     * Turns the BME280 device ON.
     * If the device is already ON, an error message is printed.
     */
    @Override
    public void turnON() {
        if (state == State.OFF) {
            state=State.ON;
            protocol.write("turnON");
            System.out.println("BME280: Turning ON.");
        } else {
            System.err.println("BME280 is already ON.");
        }
    }

    /**
     * Turns the BME280 device OFF.
     * If the device is already OFF, an error message is printed.
     */
    @Override
    public void turnOFF() {
        if (state == State.ON) {
            state = State.OFF;
            protocol.write("turnOFF");
            System.out.println("BME280: Turning OFF.");
        } else {
            System.err.println("BME280 is already OFF.");
        }
    }
    
}
