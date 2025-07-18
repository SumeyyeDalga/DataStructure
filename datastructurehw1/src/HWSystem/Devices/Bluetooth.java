package HWSystem.Devices;
import HWSystem.Protocols.UART;

public class Bluetooth extends WirelessIO {
    public Bluetooth(UART protocol) { 
        super(protocol); 
    }
    
    @Override
    public void turnON() {  
        if (state == State.OFF) {  
            state = State.ON;
            protocol.write("turnON");
            System.out.println("Bluteooth is ON");
        } else {
            System.out.println("Bluteooth is already ON.");
        }
    }

    @Override
    public void turnOFF() {  
        if (state == State.ON) {  
            state = State.OFF;
            protocol.write("turnOFF");
            System.out.println("Bluteooth is OFF");
        } else {
            System.out.println("Bluteooth is already OFF.");
        }
    }

    @Override
    public String getName() {
        return "Bluteooth";
    }

    @Override
    public String getDevType() {
        return "WirelessIO";
    }
    
}
