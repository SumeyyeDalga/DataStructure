package HWSystem;

import HWSystem.Devices.*;
import HWSystem.Protocols.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The HWSystem class manages various hardware devices including sensors, displays, wireless adapters, and motor drivers.
 * It supports device management, configuration loading, communication over different protocols, and device control operations.
 */
public class HWSystem {
    private ArrayList<Device> devices; 
    private ArrayList<Sensor> sensors;
    private ArrayList<Display> displays;
    private ArrayList<WirelessIO> wirelessIOs;
    private ArrayList<MotorDriver> motorDrivers;
    private ArrayList<Protocol> ports;
    private String logFilePath;
    
    
    private int maxSensors;
    private int maxDisplays;
    private int maxWirelessAdapters;
    private int maxMotorDrivers;
    
    /**
     * Constructor for the HWSystem class.
     * Initializes the hardware system with the given configuration file and log file path.
     * The configuration file specifies the number of each device type and the protocol for each port.
     * @param configFile Path to the configuration file.
     * @param logFilePath Path to the log file.
     */
    public HWSystem(String configFile,String logFilePath) {
        this.logFilePath = logFilePath;
        devices = new ArrayList<>();
        sensors = new ArrayList<>();
        displays = new ArrayList<>();
        wirelessIOs = new ArrayList<>();
        motorDrivers = new ArrayList<>();
        ports = new ArrayList<>();
        
        loadConfiguration(configFile);

        //After ports are initialized, devices are filled with `null` of appropriate length.
        for (int i = 0; i < ports.size(); i++) {
            devices.add(null);
        }

        for (int i = 0; i < maxWirelessAdapters; i++) {
            wirelessIOs.add(null);
        }
        for (int i = 0; i < maxMotorDrivers; i++) {
            motorDrivers.add(null);
        }
        for (int i = 0; i < maxDisplays; i++) {
            displays.add(null);
        }
        for (int i = 0; i < maxSensors; i++) {
            sensors.add(null);
        }

        // Start logging for each port (to add "Port Opened" log at startup)
        for (int i = 0; i < ports.size(); i++) {
            Protocol port = ports.get(i);
        }
    }
    
    /**
     * Loads configuration data from the specified file.
     * The configuration file specifies the number of devices for each type and the protocol configuration for each port.
     * @param filePath Path to the configuration file.
     */
    public void loadConfiguration(String filePath) {
        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {

                String line = scanner.nextLine();
                String[] parts = line.split(":");
                if (parts[0].equals("# of sensors")) {
                    maxSensors = Integer.parseInt(parts[1]);
                } else if (parts[0].equals("# of displays")) {
                    maxDisplays = Integer.parseInt(parts[1]);
                } else if (parts[0].equals("# of wireless adapters")) {
                    maxWirelessAdapters = Integer.parseInt(parts[1]);
                } else if (parts[0].equals("# of motor drivers")) {
                    maxMotorDrivers = Integer.parseInt(parts[1]);
                } else if (parts[0].equals("Port Configuration")) {
                    String[] portList = parts[1].split(",");
                    for (int i = 0; i < portList.length; i++) {
                        ports.add(toProtocol(portList[i].trim(), i));
                    }
                }
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("Configuration file not found: " + filePath);
        }
    }

    /**
     * Converts a string representing a protocol to an actual Protocol object.
     * @param protocolName The name of the protocol (e.g., "I2C", "SPI", "UART", etc.).
     * @param portID The port ID.
     * @return The Protocol object corresponding to the given protocol name.
     */
    private Protocol toProtocol(String protocolName, int portID) {
        if (protocolName.equals("I2C")) {
            return new I2C(portID, logFilePath);
        } else if (protocolName.equals("SPI")) {
            return new SPI(portID, logFilePath);
        } else if (protocolName.equals("UART")) {
            return new UART(portID, logFilePath);
        } else if (protocolName.equals("OneWire")) {
            return new OneWire(portID, logFilePath);
        } else {
            throw new IllegalArgumentException("Unknown protocol: " + protocolName);
        }
    }

    /**
     * Adds a device to the system.
     * The device is added to the appropriate protocol port and its list based on its type.
     * @param devName The name of the device (e.g., "DHT11", "BME280", "LCD", etc.).
     * @param portID The port ID where the device should be connected.
     * @param devID The device ID.
     */
    //While writing this function, I got some errors and got help from artificial intelligence.
    public void addDevice(String devName, int portID, int devID) {
        // Check if port exists
        if (portID < 0 || portID >= ports.size()) {
            System.err.println("Invalid port ID.");
            return;
        }
        
        // Check if port is already occupied
        if (devices.get(portID) != null) {
            System.err.println("Port " + portID + " is already occupied.");
            return;
        }
        
        Protocol portProtocol = ports.get(portID);
        String protocolName = portProtocol.getProtocolName();
        try {
            // Create device based on name
            switch (devName) {
                case "DHT11":
                    if (!protocolName.equals("OneWire")) {
                        System.err.println("DHT11 is incompatible with " + protocolName + " protocol.");
                        return;
                    }
                    
                    // Check if invalid devID
                    if (devID < 0 || devID >= maxSensors) {
                        System.err.println("Invalid device ID for sensor.");
                        return;
                    }
                    // Check if devID is already used
                    if(sensors.get(devID) != null && sensors.get(devID).getDevType().contains("Sensor")){
                        System.err.println("Device ID " + devID + " is already used.");
                        return;
                    }

                    Sensor dht11Sensor = new DHT11(portProtocol);
                    sensors.set(devID,dht11Sensor);
                    devices.set(portID, dht11Sensor);

                    break;
                    
                case "BME280":
                    if (!protocolName.equals("I2C") && !protocolName.equals("SPI")) {
                        System.err.println("BME280 is incompatible with " + protocolName + " protocol.");
                        return;
                    }
                   
                    // Check if invalid devID
                    if (devID < 0 || devID >= maxSensors) {
                        System.err.println("Invalid device ID for sensor.");
                        return;
                    }
                    // Check if devID is already used
                    if(sensors.get(devID) != null && sensors.get(devID).getDevType().contains("Sensor")){
                        System.err.println("Device ID " + devID + " is already used.");
                        return;
                    }

                    Sensor bme280Sensor = new BME280(portProtocol);
                    sensors.set(devID,bme280Sensor);
                    devices.set(portID, bme280Sensor);
                    break;
                    
                case "MPU6050":
                    if (!protocolName.equals("I2C")) {
                        System.err.println("MPU6050 is incompatible with " + protocolName + " protocol.");
                        return;
                    }
                    
                    // Check if invalid devID
                    if (devID < 0 || devID >= maxSensors) {
                        System.err.println("Invalid device ID for sensor.");
                        return;
                    }
                    // Check if devID is already used
                    if(sensors.get(devID) != null && sensors.get(devID).getDevType().contains("Sensor")){
                        System.err.println("Device ID " + devID + " is already used.");
                        return;
                    }
                    Sensor mpu6050Sensor = new MPU6050(portProtocol);
                    sensors.set(devID,mpu6050Sensor);
                    devices.set(portID, mpu6050Sensor);
                    break;
                    
                case "GY951":
                    if (!protocolName.equals("SPI") && !protocolName.equals("UART")) {
                        System.err.println("GY951 is incompatible with " + protocolName + " protocol.");
                        return;
                    }
                    
                    // Check if invalid devID
                    if (devID < 0 || devID >= maxSensors) {
                        System.err.println("Invalid device ID for sensor.");
                        return;
                    }
                    // Check if devID is already used
                    if(sensors.get(devID) != null && sensors.get(devID).getDevType().contains("Sensor")){
                        System.err.println("Device ID " + devID + " is already used.");
                        return;
                    }
                    Sensor gy951Sensor = new GY951(portProtocol);
                    sensors.set(devID,gy951Sensor);
                    devices.set(portID, gy951Sensor);
                    break;
                    
                case "LCD":
                    if (!protocolName.equals("I2C")) {
                        System.err.println("LCD is incompatible with " + protocolName + " protocol.");
                        return;
                    }
                    
                    // Check if invalid devID
                    if (devID < 0 || devID >= maxDisplays) {
                        System.err.println("Invalid device ID for display.");
                        return;
                    }
                    // Check if devID is already used
                    if(displays.get(devID) != null && displays.get(devID).getDevType().contains("Display")){
                        System.err.println("Device ID " + devID + " is already used.");
                        return;
                    }
                    Display lcdDisplay = new LCD(portProtocol);
                    displays.set(devID,lcdDisplay);
                    devices.set(portID, lcdDisplay);
                    break;
                    
                case "OLED":
                    if (!protocolName.equals("SPI")) {
                        System.err.println("OLED is incompatible with " + protocolName + " protocol.");
                        return;
                    }
                    
                    // Check if invalid devID
                    if (devID < 0 || devID >= maxDisplays) {
                        System.err.println("Invalid device ID for display.");
                        return;
                    }
                    // Check if devID is already used
                    if(displays.get(devID) != null && displays.get(devID).getDevType().contains("Display")){
                        System.err.println("Device ID " + devID + " is already used.");
                        return;
                    }
                    Display oledDisplay = new OLED(portProtocol);
                    displays.set(devID,oledDisplay);
                    devices.set(portID, oledDisplay);
                    break;
                    
                case "Bluetooth":
                    if (!protocolName.equals("UART")) {
                        System.err.println("Bluetooth is incompatible with " + protocolName + " protocol.");
                        return;
                    }
                    // Check if invalid devID
                    if (devID < 0 || devID >= maxWirelessAdapters) {
                        System.err.println("Invalid device ID for wireless adapter.");
                        return;
                    }
                    // Check if devID is already used
                    if(wirelessIOs.get(devID) != null && wirelessIOs.get(devID).getDevType().contains("WirelessIO")){
                        System.err.println("Device ID " + devID + " is already used.");
                        return;
                    }
                    WirelessIO bluetooth = new Bluetooth(portProtocol);
                    wirelessIOs.set(devID,bluetooth);
                    devices.set(portID, bluetooth);
                    break;
                    
                case "Wifi":
                    if (!protocolName.equals("SPI") && !protocolName.equals("UART")) {
                        System.err.println("Wifi is incompatible with " + protocolName + " protocol.");
                        return;
                    }
                    
                    // Check if invalid devID
                    if (devID < 0 || devID >= maxWirelessAdapters) {
                        System.err.println("Invalid device ID for wireless adapter.");
                        return;
                    }
                    // Check if devID is already used
                    if(wirelessIOs.get(devID) != null && wirelessIOs.get(devID).getDevType().contains("WirelessIO")){
                        System.err.println("Device ID " + devID + " is already used.");
                        return;
                    }
                    WirelessIO wifi = new Wifi(portProtocol);
                    wirelessIOs.set(devID,wifi);
                    devices.set(portID, wifi);
                    break;
                    
                case "PCA9685":
                    if (!protocolName.equals("I2C")) {
                        System.err.println("PCA9685 is incompatible with " + protocolName + " protocol.");
                        return;
                    }
                    
                    // Check if invalid devID
                    if (devID < 0 || devID >= maxMotorDrivers) {
                        System.err.println("Invalid device ID for motor driver.");
                        return;
                    }
                    // Check if devID is already used
                    if(motorDrivers.get(devID) != null && motorDrivers.get(devID).getDevType().contains("MotorDriver")){
                        System.err.println("Device ID " + devID + " is already used.");
                        return;
                    }
                    MotorDriver pca9685 = new PCA9685(portProtocol);
                    motorDrivers.set(devID,pca9685);
                    devices.set(portID, pca9685);
                    break;
                    
                case "SparkFunMD":
                    if (!protocolName.equals("SPI")) {
                        System.err.println("SparkFunMD is incompatible with " + protocolName + " protocol.");
                        return;
                    }
                    
                    // Check if invalid devID
                    if (devID < 0 || devID >= maxMotorDrivers) {
                        System.err.println("Invalid device ID for motor driver.");
                        return;
                    }
                    // Check if devID is already used
                    if(motorDrivers.get(devID) != null && motorDrivers.get(devID).getDevType().contains("MotorDriver")){
                        System.err.println("Device ID " + devID + " is already used.");
                        return;
                    }
                    MotorDriver sparkFunMD = new SparkFunMD(portProtocol);
                    motorDrivers.set(devID,sparkFunMD);
                    devices.set(portID, sparkFunMD);
                    break;
                    
                default:
                    System.err.println("Unknown device: " + devName);
                    return;
            }
            
            System.out.println("Device added.");
        } catch (Exception e) {
            System.err.println("Error adding device: " + e.getMessage());
        }
    }
    
    /**
     * Removes a device from the system.
     * The device is deactivated and removed from the appropriate lists.
     * @param portID The port ID where the device is located.
     */
    public void removeDevice(int portID) {
        // Check if port exists
        if (portID < 0 || portID >= ports.size()) {
            System.err.println("Invalid port ID.");
            return;
        }
        
        // Check if there's a device on this port
        Device device = devices.get(portID);
        if (device == null) {
            System.err.println("No device on port " + portID + ".");
            return;
        }
        
        // Check if the device is turned on
        if (device.getState() == Device.State.ON) {
            System.err.println("Device is active.");
            System.err.println("Command failed.");
            return;
        }
        
        // Remove the device from appropriate lists
        if (device.getDevType().contains("Sensor")) {
            sensors.set(sensors.indexOf(device), null);
        } else if (device.getDevType().contains("Display")) {
            displays.set(displays.indexOf(device), null);
        } else if (device.getDevType().contains("WirelessIO")) {
            wirelessIOs.set(wirelessIOs.indexOf(device), null);
        } else if (device.getDevType().contains("MotorDriver")) {
            motorDrivers.set(motorDrivers.indexOf(device), null);
        }
        
        devices.set(portID, null);
        System.out.println("Device removed.");
    }
    
    /**
     * Turns a device on at the given port.
     * @param portID The port ID where the device is connected.
     */
    public void turnDeviceOn(int portID) {
        // Check if port exists
        if (portID < 0 || portID >= ports.size()) {
            System.err.println("Invalid port ID.");
            return;
        }
        
        // Check if there's a device on this port
        Device device = devices.get(portID);
        if (device == null) {
            System.err.println("No device on port " + portID + ".");
            return;
        }
        
        device.turnON();
        
    }
    
    /**
     * Turns a device off at the given port.
     * @param portID The port ID where the device is connected.
     */
    public void turnDeviceOff(int portID) {
        // Check if port exists
        if (portID < 0 || portID >= ports.size()) {
            System.err.println("Invalid port ID.");
            return;
        }
        
        // Check if there's a device on this port
        Device device = devices.get(portID);
        if (device == null) {
            System.err.println("No device on port " + portID + ".");
            return;
        }
        
        device.turnOFF();
        
    }
    
    /**
    * Lists all the ports, displaying their status (occupied or empty) along with the protocol used.
    */
    public void listPorts() {
        System.out.println("list of ports:");
        for (int i = 0; i < ports.size(); i++) {
            Protocol protocol = ports.get(i);
            Device device = devices.get(i);
            
            String status;
            if (device == null) {
                status = "empty";
            } else {
                status = "occupied";
            }

            if (device == null) {
                System.out.println(i + " " + protocol.getProtocolName() + " " + status);
            } else {
                System.out.println(i + " " + protocol.getProtocolName() + " " + status + " " + device.getName() + " " + device.getDevType() + " " + 
                                  getDeviceID(device) + " " + device.getState());
            }
        }
    }
    
    /**
     * Lists devices of the specified type (Sensor, Display, WirelessIO, MotorDriver) along with their details.
     * @param devType The device type to be listed (e.g., "Sensor", "Display", "WirelessIO", "MotorDriver").
     */
    public void listDeviceType(String devType) {
        System.out.println("list of " + devType + "s:");
        
        ArrayList<Device> deviceList = new ArrayList<>();
        
        switch (devType) {
            case "Sensor":
                for (int i = 0; i < sensors.size(); i++) {
                    Sensor s = sensors.get(i);
                    deviceList.add(s);
                }
                break;
                
            case "Display":
                for (int i = 0; i < displays.size(); i++) {
                    Display d = displays.get(i);
                    deviceList.add(d);
                }
                break;
                
            case "WirelessIO":
                for (int i = 0; i < wirelessIOs.size(); i++) {
                    WirelessIO w = wirelessIOs.get(i);
                    deviceList.add(w);
                }
                break;
                
            case "MotorDriver":
                for (int i = 0; i < motorDrivers.size(); i++) {
                    MotorDriver m = motorDrivers.get(i);
                    deviceList.add(m);
                }
                break;
                
            default:
                System.err.println("Unknown device type: " + devType);
                return;
        }
        
        // Print device information
        for (int i = 0; i < deviceList.size(); i++) {
            Device device = deviceList.get(i);
            int portID = getPortID(device);
            String protocolName;
            if (portID != -1) {
                protocolName = ports.get(portID).getProtocolName();
            } else {
                protocolName = "Unknown";
            }
            System.out.println(device.getName() + " " + getDeviceID(device) + " " + portID + " " + protocolName);
        }
    }
    
    /**
     * Reads data from a sensor identified by its device ID. Ensures the sensor is turned on before reading.
     * @param devID The device ID of the sensor to read.
     */
    public void readSensor(int devID) {
        // Check if devID is valid
        if (devID < 0 || devID >= sensors.size()) {
            System.err.println("Invalid sensor ID.");
            System.err.println("Command failed.");
            return;
        }
        
        // Get the sensor and check if it's turned on
        Sensor sensor = sensors.get(devID);
        if (sensor.getState() == Device.State.OFF) {
            System.err.println("Device is not active.");
            System.err.println("Command failed.");
            return;
        }
        
        System.out.println(sensor.getName() + " " + sensor.getDevType() + ": " + sensor.data2String());

    }
    
    /**
     * Prints the data to the specified display device.
     * Ensures that the display is turned on before printing.
     * @param devID The device ID of the display.
     * @param data The data to print to the display.
     */
    public void printDisplay(int devID, String data) {
        // Check if devID is valid
        if (devID < 0 || devID >= displays.size()) {
            System.err.println("Invalid display ID.");
            System.err.println("Command failed.");
            return;
        }
        
        // Get the display and check if it's turned on
        Display display = displays.get(devID);
        if (display.getState() == Device.State.OFF) {
            System.err.println("Device is not active.");
            System.err.println("Command failed.");
            return;
        }
        
        display.printData(data);
    }
    
    /**
     * Receives data from the specified wireless adapter.
     * Ensures the wireless adapter is turned on before receiving data.
     * @param devID The device ID of the wireless adapter.
     */
    public void readWireless(int devID) {
        // Check if devID is valid
        if (devID < 0 || devID >= wirelessIOs.size()) {
            System.err.println("Invalid wireless adapter ID.");
            System.err.println("Command failed.");
            return;
        }
        
        // Get the wireless adapter and check if it's turned on
        WirelessIO wireless = wirelessIOs.get(devID);
        if (wireless.getState() == Device.State.OFF) {
            System.err.println("Device is not active.");
            System.err.println("Command failed.");
            return;
        }
        
        wireless.recvData();
    }
    
    /**
     * Sends data to the specified wireless adapter.
     * Ensures the wireless adapter is turned on before sending data.
     * @param devID The device ID of the wireless adapter.
     * @param data The data to send through the wireless adapter.
     */
    public void writeWireless(int devID, String data) {
        // Check if devID is valid
        if (devID < 0 || devID >= wirelessIOs.size()) {
            System.err.println("Invalid wireless adapter ID.");
            System.err.println("Command failed.");
            return;
        }
        
        // Get the wireless adapter and check if it's turned on
        WirelessIO wireless = wirelessIOs.get(devID);
        if (wireless.getState() == Device.State.OFF) {
            System.err.println("Device is not active.");
            System.err.println("Command failed.");
            return;
        }
        
        wireless.sendData(data);
    }
    
    /**
     * Sets the speed for a motor driver device.
     * Ensures the motor driver is turned on before setting the speed.
     * @param devID The device ID of the motor driver.
     * @param speed The speed value to set for the motor driver.
     */
    public void setMotorSpeed(int devID, int speed) {
        // Check if devID is valid
        if (devID < 0 || devID >= motorDrivers.size()) {
            System.err.println("Invalid motor driver ID.");
            System.err.println("Command failed.");
            return;
        }
        
        // Get the motor driver and check if it's turned on
        MotorDriver motor = motorDrivers.get(devID);
        if (motor.getState() == Device.State.OFF) {
            System.err.println("Device is not active.");
            System.err.println("Command failed.");
            return;
        }
        
        motor.setMotorSpeed(speed);
    }
    
    /**
     * Gets the port ID for a given device.
     * @param device The device whose port ID is to be fetched.
     * @return The port ID associated with the device, or -1 if not found.
     */
    private int getPortID(Device device) {
        for (int i = 0; i < devices.size(); i++) {
            if (device == devices.get(i)) {
                return i;
            }
        }
        return -1;
    }
    
    /**
     * Gets the device ID for a given device.
     * @param device The device whose ID is to be fetched.
     * @return The device ID associated with the device.
     */
    private int getDeviceID(Device device) {
        if(device.getDevType().contains("Sensor")){
            return sensors.indexOf(device);
        } else if(device.getDevType().contains("Display")){
            return displays.indexOf(device);
        } else if(device.getDevType().contains("WirelessIO")){
            return wirelessIOs.indexOf(device);
        } else if(device.getDevType().contains("MotorDriver")){
            return motorDrivers.indexOf(device);
        }
        return -1;
    }

    /**
     * Writes all logs for all connected protocols to their respective log files.
     */
    public void writeAllLogs() {
        for (int i = 0; i < ports.size(); i++) {
            Protocol port = ports.get(i);
            port.writeLogsToFile();
        }
    }
    
}