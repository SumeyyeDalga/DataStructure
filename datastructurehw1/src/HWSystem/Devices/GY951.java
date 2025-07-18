package HWSystem.Devices;
import HWSystem.Protocols.SPI;
import HWSystem.Protocols.UART;

public class GY951 extends IMUSensor{
    
    public GY951(SPI protocol) {
        super(protocol);
    }
    public GY951(UART protocol) {
        super(protocol);
    }
    @Override
    public String getName() {
        return "GY-951";
    }

    @Override
    public void turnON() {
        if (state == State.OFF) {
            state = State.ON;
            protocol.write("turnON");
            System.out.println("GY951 is ON");
        } else {
            System.out.println("GY951 is already ON.");
        }
    }

    @Override
    public void turnOFF() {
        if (state == State.ON) {
            state = State.OFF;
            protocol.write("turnOFF");
            System.out.println("GY951 is OFF");
        } else {
            System.out.println("GY951 is already OFF.");
        }
    }

    
}
