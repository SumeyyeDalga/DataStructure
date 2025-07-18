package HWSystem.Devices;

import HWSystem.Protocols.Protocol;

/**
 * The {@code MPU6050} class represents an IMU (Inertial Measurement Unit) sensor device.
 * It extends the {@code IMUSensor} class and provides functionality specific to the MPU6050 sensor.
 * The MPU6050 sensor works exclusively with the I2C protocol.
 */
public class MPU6050 extends IMUSensor{
    
    /**
     * Constructs a new {@code MPU6050} device with the specified protocol.
     * If the protocol is not I2C, an error message is printed.
     *
     * @param protocol the protocol to be used by the MPU6050 device (must be I2C)
     */
    public MPU6050(Protocol protocol) {
        super(protocol);
        if(!(protocol.getProtocolName().equals("I2C"))){
            System.err.println("MPU6050 only supports I2C protocol.");
            return;
        }
    }

    /**
     * Returns the name of the MPU6050 device.
     *
     * @return the name of the device, which is "MPU6050"
     */
    @Override
    public String getName() {
        return "MPU6050";
    }

    /**
     * Turns the MPU6050 device ON.
     * If the device is already ON, an error message is printed.
     */
    @Override
    public void turnON() {
        if (state == State.OFF) {
            state = State.ON;
            protocol.write("turnON");
            System.out.println("MPU6050: Turning ON.");
        } else {
            System.err.println("MPU6050 is already ON.");
        }
    }

    /**
     * Turns the MPU6050 device OFF.
     * If the device is already OFF, an error message is printed.
     */
    @Override
    public void turnOFF() {
        if (state == State.ON) {
            state = State.OFF;
            protocol.write("turnOFF");
            System.out.println("MPU6050: Turning OFF.");
        } else {
            System.err.println("MPU6050 is already OFF.");
        }
    }
    
}
