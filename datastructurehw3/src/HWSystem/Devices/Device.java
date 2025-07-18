package HWSystem.Devices;

import HWSystem.Protocols.Protocol;

/**
 * The {@code Device} class is an abstract base class that represents a generic hardware device.
 * It provides a common interface for all devices, including methods to turn the device ON or OFF,
 * retrieve the device's name and type, and manage its state.
 * 
 * Subclasses must implement the abstract methods to define specific device behavior.
 */
public abstract class Device {

    /**
     * The {@code State} enum represents the possible states of a device: ON or OFF.
     */
    public enum State {
        ON, OFF
    }

    /**
     * The {@code State} enum represents the possible states of a device: ON or OFF.
     */
    public State state=State.OFF;

    /**
     * The protocol used by the device for communication.
     */
    protected Protocol protocol;

    /**
     * Constructs a new {@code Device} with the specified protocol.
     * 
     * @param protocol the protocol to be used by the device
     */
    public Device(Protocol protocol) { // Constructor ekledik
        this.protocol = protocol;
    }

    /**
     * Turns the device ON. Subclasses must provide the implementation.
     */
    public abstract void turnON();

    /**
     * Turns the device OFF. Subclasses must provide the implementation.
     */
    public abstract void turnOFF();

    /**
     * Turns the device ON. Subclasses must provide the implementation.
     */
    public abstract String getName();

    /**
     * Turns the device ON. Subclasses must provide the implementation.
     */
    public abstract String getDevType();

    /**
     * Returns the current state of the device.
     *
     * @return the current state of the device, either {@code ON} or {@code OFF}
     */
    public State  getState() {
        return state;
    }
}
