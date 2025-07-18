package HWSystem.Protocols;

/**
 * Represents the I2C protocol.
 * This class implements methods for reading and writing data using the I2C communication protocol.
 * It also inherits logging functionality to log each read and write operation.
 */
public class I2C extends LogProtocol{

    /**
     * Constructor for the I2C class.
     * @param portID Represents the port number.
     * @param logFilePath Specifies the path to the log file.
     */
    public I2C(int portID, String logFilePath) {
        super("I2C", portID, logFilePath);
    }

    /**
     * Performs a read operation using the I2C protocol.
     * This method simulates reading and logs the operation.
     * @return A string representing the data read from the I2C protocol.
     */
    @Override
    public String read() {
        logReading();
        return "<I2C> Reading";
    }

    /**
     * Performs a write operation using the I2C protocol.
     * This method simulates writing data and logs the written data.
     * @param data The data to be written, represented as a string.
     */
    @Override
    public void write(String data) { 
        logWriting(data);
        //System.out.println("I2C: Writing \"" + data + "\"."); 
    }

    /**
     * Returns the name of this protocol.
     * @return A string representing the name of the protocol, "I2C".
     */
    @Override
    public String getProtocolName() {
        return "I2C";
    }
}

