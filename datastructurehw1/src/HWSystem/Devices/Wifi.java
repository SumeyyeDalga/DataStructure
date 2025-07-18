package HWSystem.Devices;
import HWSystem.Protocols.SPI;
import HWSystem.Protocols.UART;

public class Wifi extends WirelessIO{
    public Wifi(SPI protocol) { 
        super(protocol); 
    }
    public Wifi(UART protocol) { 
        super(protocol); 
    }
    
    @Override
    public void turnON() {  
        if (state == State.OFF) {  
            state = State.ON;
            protocol.write("turnON");
            System.out.println("Wifi is ON");
        } else {
            System.out.println("Wifi is already ON.");
        }
    }

    @Override
    public void turnOFF() {  
        if (state == State.ON) {  
            state = State.OFF;
            protocol.write("turnOFF");
            System.out.println("Wifi is OFF");
        } else {
            System.out.println("Wifi is already OFF.");
        }
    }

    @Override
    public String getName() {
        return "Wifi";
    }
    @Override
    public String getDevType() {
        return "WirelessIO";
    } 
    
}
