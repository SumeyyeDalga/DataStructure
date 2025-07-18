package HWSystem.Devices;

import HWSystem.Protocols.Protocol;

/**
 * The {@code MotorDriver} class is an abstract base class that represents a motor driver device.
 * It extends the {@code Device} class and provides functionality specific to motor drivers,
 * such as setting the motor speed.
 * Subclasses of {@code MotorDriver} can implement additional functionality specific to different motor drivers.
 */
public abstract class MotorDriver extends Device {

    /**
     * Constructs a new {@code MotorDriver} device with the specified protocol.
     *
     * @param protocol the protocol to be used by the motor driver
     */
    public MotorDriver(Protocol protocol) {
        super(protocol);
    }
    
    /**
     * Sets the speed of the motor.
     * If the device is OFF, an error message is printed.
     *
     * @param speed the speed to set for the motor
     */
    public void setMotorSpeed(int speed) {
        if (state == Device.State.ON) {
            //protocol.write("Setting motor speed to " + speed);
            protocol.write("Writing:"+ speed);
            System.out.println(getName()+": Setting speed to "+ speed + ".");
        } else {
            System.err.println("Device is OFF. Cannot set motor speed.");
        }
    }

    /**
     * Returns the type of the device.
     *
     * @return the type of the device, which is "MotorDriver"
     */
    @Override
    public String getDevType() {
        return "MotorDriver";
    }
}
