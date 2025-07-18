package HWSystem.Devices;
import HWSystem.Protocols.Protocol;

/**
 * The {@code WirelessIO} class is an abstract base class that represents a wireless communication device.
 * It extends the {@code Device} class and provides functionality for sending and receiving data wirelessly.
 * Subclasses of {@code WirelessIO} can implement additional functionality specific to different wireless devices.
 */
public abstract class WirelessIO extends Device {

    /**
     * Constructs a new {@code WirelessIO} device with the specified protocol.
     *
     * @param protocol the protocol to be used by the wireless device
     */
    public WirelessIO(Protocol protocol) { 
        super(protocol); 
    }
    
    /**
     * Sends data wirelessly using the device's protocol.
     * If the device is OFF, an error message is printed.
     *
     * @param data the data to be sent
     */
    public void sendData(String data) {
        if (state == Device.State.ON) {
            protocol.write("Sending: " + data);
            System.out.println(getName()+" Sending " + "\"" + data + "\" ." );
        } else {
            System.err.println(getName() + " is OFF. Cannot send data.");
        }
    }

    /**
     * Receives data wirelessly using the device's protocol.
     * If the device is OFF, an error message is printed, and {@code null} is returned.
     *
     * @return the data received, or {@code null} if the device is OFF
     */
    public String recvData() {
        if (state == Device.State.ON) {
            String receivedData = protocol.read();
            System.out.println(getName() + " Received: " + "\"" + receivedData + "\" .");
            return receivedData;
        } else {
            System.err.println(getName() + " is OFF. Cannot receive data.");
            return null;
        }
    }
}

