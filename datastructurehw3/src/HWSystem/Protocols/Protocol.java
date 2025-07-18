package HWSystem.Protocols;

/**
 * Interface representing a communication protocol.
 * This interface defines the basic operations that any communication protocol class should implement, 
 * including reading and writing data, logging operations, and writing logs to a file.
 */
public interface Protocol {

    /**
     * Reads data using the protocol.
     * @return A string representing the data read from the protocol.
     */
    public String read();

    /**
     * Writes a message using the protocol.
     * @param message The message to be written, represented as a string.
     */
    public void write(String message);

    /**
     * Returns the name of the protocol.
     * @return A string representing the name of the protocol.
     */
    public String getProtocolName();

    /**
     * Logs a "Reading" operation.
     * This method should be implemented to log a message whenever a read operation occurs.
     */
    public void logReading();

    /**
     * Logs a "Writing" operation along with the message.
     * This method should be implemented to log a message whenever a write operation occurs.
     * @param message The message being written, represented as a string.
     */
    public void logWriting(String message);

    /**
     * Writes the logs to a file.
     * This method should be implemented to save the logs to a file at a specified location.
     */
    public void writeLogsToFile();
}
