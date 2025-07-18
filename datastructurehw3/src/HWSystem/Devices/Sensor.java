package HWSystem.Devices;

import HWSystem.Protocols.Protocol;

/**
     * Returns the name of the PCA9685 device.
     *
     * @return the name of the device, which is "PCA9685"
     */
public abstract class Sensor extends Device {

    /**
     * Constructs a new {@code Sensor} device with the specified protocol.
     *
     * @param protocol the protocol to be used by the sensor device
     */
    public Sensor(Protocol protocol) {
        super(protocol); 
    }

    /**
     * Returns the type of the device.
     *
     * @return the type of the device, which is "Sensor"
     */
    @Override
    public String getDevType() {
        return "Sensor";
    }

    /**
     * Returns the specific type of the sensor.
     * Subclasses must implement this method to provide the sensor type.
     *
     * @return the specific type of the sensor
     */
    public abstract String getSensType();

    /**
     * Converts the sensor data into a formatted string.
     * Subclasses must implement this method to provide the sensor data representation.
     *
     * @return a formatted string representing the sensor data
     */
    public abstract String data2String();

}

