package HWSystem.Devices;

import HWSystem.Protocols.Protocol;

public abstract class IMUSensor extends Sensor {
    public IMUSensor(Protocol protocol) {
        super(protocol);
    }
    public float getAccel(){
        return 5.0f;
    }
    public float getRot(){
        return 10.0f;
    }
    @Override
    public String data2String() {
        float accel=getAccel();
        float rot=getRot();
        return String.format("Acceleration: %.2f m/s^2, Rotation: %.2f deg/s", accel, rot);

    }
    @Override
    public String getSensType() {
        return "IMUSensor";
    }
    
}
