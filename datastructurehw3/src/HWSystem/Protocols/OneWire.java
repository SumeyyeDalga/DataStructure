package HWSystem.Protocols;

/**
 * Represents the OneWire protocol.
 * This class implements methods for reading and writing data using the OneWire communication protocol.
 * It also inherits logging functionality to log each read and write operation.
 */
public class OneWire extends LogProtocol {

    /**
     * Constructor for the OneWire class.
     * @param portID Represents the port number.
     * @param logFilePath Specifies the path to the log file.
     */
    public OneWire(int portID, String logFilePath) {
        super("OneWire", portID, logFilePath);
    }

    /**
     * Performs a read operation using the OneWire protocol.
     * This method simulates reading and logs the operation.
     * @return A string representing the data read from the OneWire protocol.
     */
    @Override
    public String read() {
        logReading();
        return "<OneWire> Reading";
    }

    /**
     * Performs a write operation using the OneWire protocol.
     * This method simulates writing data and logs the written data.
     * @param data The data to be written, represented as a string.
     */
    @Override
    public void write(String data) { 
        logWriting(data);
        //System.out.println("OneWire: Writing \"" + data + "\"."); 
    }

    /**
     * Returns the name of this protocol.
     * @return A string representing the name of the protocol, "OneWire".
     */
    @Override
    public String getProtocolName() {
        return "OneWire";
    }
}