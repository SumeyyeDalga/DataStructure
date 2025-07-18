package HWSystem.Devices;

import HWSystem.Protocols.I2C;
import HWSystem.Protocols.SPI;

public class BME280 extends TempSensor {
    public BME280(I2C protocol) {
        super(protocol);
    }
    public BME280(SPI protocol) {
        super(protocol);
    }

    @Override
    public String getName() {
        return "BME280";
    }

    @Override
    public void turnON() {
        if (state == State.OFF) {
            state=State.ON;
            protocol.write("turnON");
            System.out.println("BME280 is ON");
        } else {
            System.out.println("BME280 is already ON.");
        }
    }

    @Override
    public void turnOFF() {
        if (state == State.ON) {
            state = State.OFF;
            protocol.write("turnOFF");
            System.out.println("BME280 is OFF");
        } else {
            System.out.println("BME280 is already OFF.");
        }
    }
    
}
