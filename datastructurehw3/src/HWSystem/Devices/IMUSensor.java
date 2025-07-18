package HWSystem.Devices;

import HWSystem.Protocols.Protocol;

/**
 * The {@code IMUSensor} class is an abstract base class that represents an Inertial Measurement Unit (IMU) sensor.
 * It extends the {@code Sensor} class and provides functionality specific to IMU sensors, such as retrieving
 * acceleration and rotation data.
 * 
 * Subclasses of {@code IMUSensor} can override the methods to provide specific behavior for different IMU sensors.
 */
public abstract class IMUSensor extends Sensor {

    /**
     * Constructs a new {@code IMUSensor} device with the specified protocol.
     *
     * @param protocol the protocol to be used by the IMU sensor
     */
    public IMUSensor(Protocol protocol) {
        super(protocol);
    }

    /**
     * Constructs a new {@code IMUSensor} device with the specified protocol.
     *
     * @param protocol the protocol to be used by the IMU sensor
     */
    public float getAccel(){
        return 1.00f;
    }

    /**
     * Retrieves the rotation data from the IMU sensor.
     * Subclasses can override this method to provide specific rotation values.
     *
     * @return the rotation value in degrees per second (deg/s)
     */
    public float getRot(){
        return 0.50f;
    }

     /**
     * Converts the sensor data (acceleration and rotation) into a formatted string.
     * The data is read from the protocol and formatted as:
     * "Acceleration: [value] m/s^2, Rotation: [value] deg/s".
     *
     * @return a formatted string representing the acceleration and rotation data
     */
    @Override
    public String data2String() {
        float accel=getAccel();
        float rot=getRot();
        protocol.read();
        return String.format("Acceleration: %.2f m/s^2, Rotation: %.2f deg/s.", accel, rot);

    }

    /**
     * Returns the type of the sensor.
     *
     * @return the type of the sensor, which is "IMUSensor"
     */
    @Override
    public String getSensType() {
        return "IMUSensor";
    }
    
}
