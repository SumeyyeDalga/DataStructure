package HWSystem.Devices;

import HWSystem.Protocols.Protocol;

public abstract class MotorDriver extends Device {
    public MotorDriver(Protocol protocol) {
        super(protocol);
    }
    
    public void setMotorSpeed(int speed) {
        if (state == Device.State.ON) {
            protocol.write("Setting motor speed to " + speed);
        } else {
            System.out.println("Device is OFF. Cannot set motor speed.");
        }
    }
    @Override
    public String getDevType() {
        return "Motor Driver";
    }
}
