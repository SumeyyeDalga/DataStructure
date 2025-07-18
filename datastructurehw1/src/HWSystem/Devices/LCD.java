package HWSystem.Devices;
import HWSystem.Protocols.I2C;

public class LCD extends Display{
    public LCD(I2C protocol) {
        super(protocol);
    }

    @Override
    public void turnON() {
        if (state == State.OFF) {
            state = State.ON;
            protocol.write("turnON");
            System.out.println("LCD is ON");
        } else {
            System.out.println("LCD is already ON.");
        }
    }

    @Override
    public void turnOFF() {
        if (state == State.ON) {
            state = State.OFF;
            protocol.write("turnOFF");
            System.out.println("LCD is OFF");
        } else {
            System.out.println("LCD is already OFF.");
        }
    }

    @Override
    public String getName() {
        return "LCD";
    }
    
}
