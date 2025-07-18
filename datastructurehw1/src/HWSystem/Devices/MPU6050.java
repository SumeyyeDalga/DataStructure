package HWSystem.Devices;

import HWSystem.Protocols.I2C;

public class MPU6050 extends IMUSensor{
    
    public MPU6050(I2C protocol) {
        super(protocol);
    }
    @Override
    public String getName() {
        return "MPU6050";
    }

    @Override
    public void turnON() {
        if (state == State.OFF) {
            state = State.ON;
            protocol.write("turnON");
            System.out.println("MPU6050 is ON");
        } else {
            System.out.println("MPU6050 is already ON.");
        }
    }

    @Override
    public void turnOFF() {
        if (state == State.ON) {
            state = State.OFF;
            protocol.write("turnOFF");
            System.out.println("MPU6050 is OFF");
        } else {
            System.out.println("MPU6050 is already OFF.");
        }
    }
    
}
