package HWSystem.Devices;

import HWSystem.Protocols.Protocol;

public abstract class Device {
    public enum State {
        ON, OFF
    }
    public State state=State.OFF;

    protected Protocol protocol;
    public Device(Protocol protocol) { // Constructor ekledik
        this.protocol = protocol;
    }

    public abstract void turnON();
    public abstract void turnOFF();
    public abstract String getName();
    public abstract String getDevType();

    public State  getState() {
        return state;
    }
}
