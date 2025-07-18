package HWSystem.Devices;

import HWSystem.Protocols.OneWire;

public class DHT11 extends TempSensor {
    public DHT11(OneWire protocol) { 
        super(protocol);
    }

    @Override
    public void turnON() {  
        if (state == State.OFF) {  
            state = State.ON;
            protocol.write("turnON");
            System.out.println("DHT11 is ON");
        } else {
            System.out.println("DHT11 is already ON.");
        }
    }

    @Override
    public void turnOFF() {  
        if (state == State.ON) {  
            state = State.OFF;
            protocol.write("turnOFF");
            System.out.println("DHT11 is OFF");
        } else {
            System.out.println("DHT11 is already OFF.");
        }
    }

    @Override
    public String getName() {
        return "DHT11";
    }
}
