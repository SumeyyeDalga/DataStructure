package HWSystem.Protocols;

/**
 * Represents the SPI (Serial Peripheral Interface) protocol.
 * This class implements methods for reading and writing data using the SPI communication protocol.
 * It also inherits logging functionality to log each read and write operation.
 */
public class SPI extends LogProtocol {

    /**
     * Constructor for the SPI class.
     * @param portID Represents the port number.
     * @param logFilePath Specifies the path to the log file.
     */
    public SPI(int portID, String logFilePath) {
        super("SPI", portID, logFilePath);
    }

    /**
     * Performs a read operation using the SPI protocol.
     * This method simulates reading and logs the operation.
     * @return A string representing the data read from the SPI protocol.
     */
    @Override
    public String read() {
        logReading();
        return "<SPI> Reading";
    }

    /**
     * Performs a write operation using the SPI protocol.
     * This method simulates writing data and logs the written data.
     * @param data The data to be written, represented as a string.
     */
    @Override
    public void write(String data) { 
        logWriting(data);
        //System.out.println("SPI: Writing \"" + data + "\"."); 
    }

    /**
     * Returns the name of this protocol.
     * @return A string representing the name of the protocol, "SPI".
     */
    @Override
    public String getProtocolName() {
        return "SPI";
    }
}
