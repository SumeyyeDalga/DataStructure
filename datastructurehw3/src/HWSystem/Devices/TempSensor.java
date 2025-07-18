package HWSystem.Devices;

import HWSystem.Protocols.Protocol;

/**
 * The {@code TempSensor} class is an abstract base class that represents a temperature sensor device.
 * It extends the {@code Sensor} class and provides functionality specific to temperature sensors.
 * Subclasses of {@code TempSensor} can override methods to provide specific behavior for different temperature sensors.
 */
public abstract class TempSensor extends Sensor {

    /**
     * Constructs a new {@code TempSensor} device with the specified protocol.
     *
     * @param protocol the protocol to be used by the temperature sensor
     */
    public TempSensor(Protocol protocol) {
        super(protocol); 
    }

    /**
     * Retrieves the temperature data from the sensor.
     * Subclasses can override this method to provide specific temperature values.
     *
     * @return the temperature value in degrees Celsius
     */
    public float getTemp(){
        return 24.00f;
    }

    /**
     * Converts the temperature data into a formatted string.
     * The data is read from the protocol and formatted as:
     * "Temperature: [value]C".
     *
     * @return a formatted string representing the temperature data
     */
    @Override
    public String data2String() {
        float temp=getTemp();
        protocol.read();
        return String.format("Temperature: %.2fC.", temp);

    }

    /**
     * Returns the specific type of the sensor.
     *
     * @return the type of the sensor, which is "TempSensor"
     */
    @Override
    public String getSensType() {
        return "TempSensor";
    }

    /**
     * Returns the type of the device.
     *
     * @return the type of the device, which is "TempSensor Sensor"
     */
    @Override
    public String getDevType() {
        return "TempSensor Sensor";
    }

    
}
