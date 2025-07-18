package HWSystem.Devices;
import HWSystem.Protocols.Protocol;

public abstract class Display extends Device {
    public Display(Protocol protocol) {
        super(protocol);
    }
    @Override
    public String getDevType() {
        return "Display";
    }
    public void printData(String data) {
        if (state == Device.State.ON) {
            protocol.write("Displaying: " + data);
        } else {
            System.out.println("Device is OFF. Cannot print.");
        }
    }
    
}
