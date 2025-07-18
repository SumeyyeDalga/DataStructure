package HWSystem.Devices;

import HWSystem.Protocols.Protocol;

public abstract class Sensor extends Device {
    public Sensor(Protocol protocol) {
        super(protocol); 
    }
    @Override
    public String getDevType() {
        return "Sensor";
    }
    public abstract String getSensType();

    public abstract String data2String();

}

