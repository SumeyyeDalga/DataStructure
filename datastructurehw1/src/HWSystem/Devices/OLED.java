package HWSystem.Devices;
import HWSystem.Protocols.SPI;

public class OLED extends Display {
    public OLED(SPI protocol) {
        super(protocol);
    }

    @Override
    public void turnON() {
        if (state == State.OFF) {
            state = State.ON;
            protocol.write("turnON");
            System.out.println("OLED is ON");
        } else {
            System.out.println("OLED is already ON.");
        }
    }

    @Override
    public void turnOFF() {
        if (state == State.ON) {
            state = State.OFF;
            protocol.write("turnOFF");
            System.out.println("OLED is OFF");
        } else {
            System.out.println("OLED is already OFF.");
        }
    }

    @Override
    public String getName() {
        return "OLED";
    }
    
}
