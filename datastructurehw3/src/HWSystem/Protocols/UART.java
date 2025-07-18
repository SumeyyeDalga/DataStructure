package HWSystem.Protocols;

/**
 * Represents the UART (Universal Asynchronous Receiver-Transmitter) protocol.
 * This class implements methods for reading and writing data using the UART communication protocol.
 * It also inherits logging functionality to log each read and write operation.
 */
public class UART extends LogProtocol {

    /**
     * Constructor for the UART class.
     * @param portID Represents the port number.
     * @param logFilePath Specifies the path to the log file.
     */
    public UART(int portID, String logFilePath) {
        super("UART", portID, logFilePath);
    }

    /**
     * Performs a read operation using the UART protocol.
     * This method simulates reading and logs the operation.
     * @return A string representing the data read from the UART protocol.
     */
    @Override
    public String read() {
        logReading();
        return "<UART> Reading";
    }

    /**
     * Performs a write operation using the UART protocol.
     * This method simulates writing data and logs the written data.
     * @param data The data to be written, represented as a string.
     */
    @Override
    public void write(String data) { 
        logWriting(data);
        //System.out.println("UART: Writing \"" + data + "\"."); 
    }

    /**
     * Returns the name of this protocol.
     * @return A string representing the name of the protocol, "UART".
     */
    @Override
    public String getProtocolName() {
        return "UART";
    }
}
