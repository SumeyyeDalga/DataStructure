package HWSystem.Devices;
import HWSystem.Protocols.Protocol;

public abstract class WirelessIO extends Device {
    public WirelessIO(Protocol protocol) { 
        super(protocol); 
    }
    
    public void sendData(String data) {
        if (state == Device.State.ON) {
            protocol.write("Sending: " + data);
        } else {
            System.out.println(getName() + " is OFF. Cannot send data.");
        }
    }

    public String recvData() {
        if (state == Device.State.ON) {
            String receivedData = protocol.read();
            System.out.println(getName() + " received: " + receivedData);
            return receivedData;
        } else {
            System.out.println(getName() + " is OFF. Cannot receive data.");
            return null;
        }
    }
}

