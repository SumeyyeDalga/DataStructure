package HWSystem.Devices;
import HWSystem.Protocols.Protocol;

/**
 * The {@code Display} class is an abstract base class that represents a display device.
 * It extends the {@code Device} class and provides functionality specific to display devices.
 * Subclasses of {@code Display} must implement the abstract methods from the {@code Device} class.
 */
public abstract class Display extends Device {

    /**
     * Constructs a new {@code Display} device with the specified protocol.
     * 
     * @param protocol the protocol to be used by the display device
     */
    public Display(Protocol protocol) {
        super(protocol);
    }

    /**
     * Returns the type of the device.
     *
     * @return the type of the device, which is "Display"
     */
    @Override
    public String getDevType() {
        return "Display";
    }

    /**
     * Prints the specified data on the display.
     * If the device is OFF, an error message is printed.
     *
     * @param data the data to be printed on the display
     */
    public void printData(String data) {
        if (state == Device.State.ON) {
            protocol.write("Displaying: " + data);
            System.out.println(getName()+": Printing \"" + data + "\".");
        } else {
            System.err.println("Device is OFF. Cannot print.");
        }
    }
    
}
