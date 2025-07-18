package HWSystem.Protocols;

import java.io.File;
import java.io.FileWriter;
import java.util.Stack;

/**
 * Abstract class representing a protocol that logs its operations.
 * This class implements basic logging functionality for read and write operations.
 * It is designed to be extended by specific protocols like I2C, SPI, etc.
 */
//I got some help from ChatGPT to write this class
public abstract class LogProtocol implements Protocol {
    private String protocolName;
    private int portID;
    private String logFilePath;
    private Stack<String> logStack;

    /**
     * Returns the name of the protocol.
     * @return A string representing the protocol's name.
     */
    @Override
    public String getProtocolName() {
        return protocolName;
    }

    /**
     * Constructor for the LogProtocol class.
     * Initializes the protocol with its name, port ID, and log file path.
     * It also sets up the log stack and logs the initial "Port Opened" message.
     * @param protocolName Name of the protocol.
     * @param portID The port ID associated with the protocol.
     * @param logFilePath The file path where logs will be saved.
     */
    public LogProtocol(String protocolName, int portID, String logFilePath) {
        this.protocolName = protocolName;
        this.portID = portID;
        this.logFilePath = logFilePath;
        this.logStack = new Stack<>();

        logStack.push("Port Opened.");
    }
    
    /**
     * Logs a "Reading" message to the log stack.
     * This method should be called whenever a read operation is performed.
     */
    public void logReading(){
        logStack.push("Reading.");
    }

    /**
     * Logs a "Writing" message with the given data to the log stack.
     * This method should be called whenever a write operation is performed.
     * @param data The data that is being written, represented as a string.
     */
    public void logWriting(String data) {
        logStack.push("Writing " + "\"" + data + "\".");
    }

    /**
     * Writes the logs to a file. The log file is named based on the protocol name and port ID.
     * Each log entry is written on a new line in the log file.
     * If there is an error during writing, an error message is printed to the standard error stream.
     */
    public void writeLogsToFile(){
        String logFileName = logFilePath + "/" + protocolName + "_" + portID + ".log";
        try{
            File logFile = new File(logFileName);
            FileWriter writer = new FileWriter(logFile);
            while (!logStack.isEmpty()) {
                writer.write(logStack.pop() + "\n");
            }
            writer.close();
        }
        catch (Exception e) {
            System.err.println("Error writing to log file: " + e.getMessage());
        }
    }
}
