package HWSystem.Devices;

import HWSystem.Protocols.Protocol;

public abstract class TempSensor extends Sensor {
    public TempSensor(Protocol protocol) {
        super(protocol); 
    }
    public float getTemp(){
        return 25.0f;
    }
    @Override
    public String data2String() {
        float temp=getTemp();
        return String.format("Temperature: %.2fC", temp);

    }
    @Override
    public String getSensType() {
        return "TempSensor";
    }

    
}
