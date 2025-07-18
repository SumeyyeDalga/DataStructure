package HWSystem;

import HWSystem.Devices.*;
import HWSystem.Protocols.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class HWSystem {
    private ArrayList<Device> devices; 
    private ArrayList<Sensor> sensors;
    private ArrayList<Display> displays;
    private ArrayList<WirelessIO> wirelessIOs;
    private ArrayList<MotorDriver> motorDrivers;
    private ArrayList<Protocol> ports;
    
    
    private int maxSensors;
    private int maxDisplays;
    private int maxWirelessAdapters;
    private int maxMotorDrivers;
    
    public HWSystem(String configFile) {
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
    }
    
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
                    for (String port : portList) {
                        ports.add(toProtocol(port));
                    }
                }
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Configuration file not found: " + filePath);
        }
    }

    
    private Protocol toProtocol(String protocolName) {
        if (protocolName.equals("SPI")) {
            return new SPI();
        } else if (protocolName.equals("I2C")) {
            return new I2C();
        } else if (protocolName.equals("UART")) {
            return new UART();
        } else if (protocolName.equals("OneWire")) {
            return new OneWire();
        } else {
            return null;
        }
    }

    public void addDevice(String devName, int portID, int devID) {
        // Check if port exists
        if (portID < 0 || portID >= ports.size()) {
            System.out.println("Invalid port ID.");
            return;
        }
        
        // Check if port is already occupied
        if (devices.get(portID) != null) {
            System.out.println("Port " + portID + " is already occupied.");
            return;
        }
        
        Protocol portProtocol = ports.get(portID);
        String protocolName = portProtocol.getProtocolName();
        Device device = null;
        try {
            // Create device based on name
            switch (devName) {
                case "DHT11":
                    if (!protocolName.equals("OneWire")) {
                        System.out.println("DHT11 is incompatible with " + protocolName + " protocol.");
                        return;
                    }
                    // Check if we have reached the limit for sensors
                    if (sensors.size() >= maxSensors) {
                        System.out.println("Maximum number of sensors reached.");
                        return;
                    }
                    // Check if devID is already used
                    if (devID < 0 || devID >= maxSensors) {
                        System.out.println("Invalid device ID for sensor.");
                        return;
                    }
                    for (Sensor s : sensors) {
                        if (s.getDevType().contains("Sensor") && s.getName().equals("DHT11")) {
                            System.out.println("Device ID " + devID + " is already used.");
                            return;
                        }
                        if(s.getDevType().contains("Sensor") && getDeviceID(s) == devID){ 
                            System.out.println("Device ID " + devID + " is already used.");
                            return;
                        }
                    }
                    device = new DHT11((OneWire) portProtocol);
                    sensors.add((Sensor) device);
                    break;
                    
                case "BME280":
                    if (!protocolName.equals("I2C") && !protocolName.equals("SPI")) {
                        System.out.println("BME280 is incompatible with " + protocolName + " protocol.");
                        return;
                    }
                    // Check if we have reached the limit for sensors
                    if (sensors.size() >= maxSensors) {
                        System.out.println("Maximum number of sensors reached.");
                        return;
                    }
                    // Check if devID is already used
                    if (devID < 0 || devID >= maxSensors) {
                        System.out.println("Invalid device ID for sensor.");
                        return;
                    }
                    for (Sensor s : sensors) {
                        if (s.getDevType().contains("Sensor") && s.getName().equals("BME280")) {
                            System.out.println("Device ID " + devID + " is already used.");
                            return;
                        }
                        if(s.getDevType().contains("Sensor") && getDeviceID(s) == devID){ 
                            System.out.println("Device ID " + devID + " is already used.");
                            return;
                        }
                    }
                    if (protocolName.equals("I2C")) {
                        device = new BME280((I2C) portProtocol);
                    } else if (protocolName.equals("SPI")) {
                        device = new BME280((SPI) portProtocol);
                    }
                    sensors.add((Sensor) device);
                    break;
                    
                case "MPU6050":
                    if (!protocolName.equals("I2C")) {
                        System.out.println("MPU6050 is incompatible with " + protocolName + " protocol.");
                        return;
                    }
                    // Check if we have reached the limit for sensors
                    if (sensors.size() >= maxSensors) {
                        System.out.println("Maximum number of sensors reached.");
                        return;
                    }
                    // Check if devID is already used
                    if (devID < 0 || devID >= maxSensors) {
                        System.out.println("Invalid device ID for sensor.");
                        return;
                    }
                    for (Sensor s : sensors) {
                        if (s.getDevType().contains("Sensor") && s.getName().equals("MPU6050")) {
                            System.out.println("Device ID " + devID + " is already used.");
                            return;
                        }
                        if(s.getDevType().contains("Sensor") && getDeviceID(s) == devID){ 
                            System.out.println("Device ID " + devID + " is already used.");
                            return;
                        }
                    }
                    device = new MPU6050((I2C) portProtocol);
                    sensors.add((Sensor) device);
                    break;
                    
                case "GY951":
                    if (!protocolName.equals("SPI") && !protocolName.equals("UART")) {
                        System.out.println("GY951 is incompatible with " + protocolName + " protocol.");
                        return;
                    }
                    // Check if we have reached the limit for sensors
                    if (sensors.size() >= maxSensors) {
                        System.out.println("Maximum number of sensors reached.");
                        return;
                    }
                    // Check if devID is already used
                    if (devID < 0 || devID >= maxSensors) {
                        System.out.println("Invalid device ID for sensor.");
                        return;
                    }
                    for (Sensor s : sensors) {
                        if (s.getDevType().contains("Sensor") && s.getName().equals("GY951")) {
                            System.out.println("Device ID " + devID + " is already used.");
                            return;
                        }
                        if(s.getDevType().contains("Sensor") && getDeviceID(s) == devID){ 
                            System.out.println("Device ID " + devID + " is already used.");
                            return;
                        }
                    }
                    if (protocolName.equals("SPI")) {
                        device = new GY951((SPI) portProtocol);
                    } else if (protocolName.equals("UART")) {
                        device = new GY951((UART) portProtocol);
                    }
                    sensors.add((Sensor) device);
                    break;
                    
                case "LCD":
                    if (!protocolName.equals("I2C")) {
                        System.out.println("LCD is incompatible with " + protocolName + " protocol.");
                        return;
                    }
                    // Check if we have reached the limit for displays
                    if (displays.size() >= maxDisplays) {
                        System.out.println("Maximum number of displays reached.");
                        return;
                    }
                    // Check if devID is already used
                    if (devID < 0 || devID >= maxDisplays) {
                        System.out.println("Invalid device ID for display.");
                        return;
                    }
                    for (Display d : displays) {
                        if (d.getName().equals("LCD")) {
                            System.out.println("Device ID " + devID + " is already used.");
                            return;
                        }
                        if(d.getDevType().contains("Display") && getDeviceID(d) == devID){ 
                            System.out.println("Device ID " + devID + " is already used.");
                            return;
                        }
                    }
                    device = new LCD((I2C) portProtocol);
                    displays.add((Display) device);
                    break;
                    
                case "OLED":
                    if (!protocolName.equals("SPI")) {
                        System.out.println("OLED is incompatible with " + protocolName + " protocol.");
                        return;
                    }
                    // Check if we have reached the limit for displays
                    if (displays.size() >= maxDisplays) {
                        System.out.println("Maximum number of displays reached.");
                        return;
                    }
                    // Check if devID is already used
                    if (devID < 0 || devID >= maxDisplays) {
                        System.out.println("Invalid device ID for display.");
                        return;
                    }
                    for (Display d : displays) {
                        if (d.getName().equals("OLED")) {
                            System.out.println("Device ID " + devID + " is already used.");
                            return;
                        }
                        if(d.getDevType().contains("Display") && getDeviceID(d) == devID){ 
                            System.out.println("Device ID " + devID + " is already used.");
                            return;
                        }
                    }
                    device = new OLED((SPI) portProtocol);
                    displays.add((Display) device);
                    break;
                    
                case "Bluetooth":
                    if (!protocolName.equals("UART")) {
                        System.out.println("Bluetooth is incompatible with " + protocolName + " protocol.");
                        return;
                    }
                    // Check if we have reached the limit for wireless IOs
                    if (wirelessIOs.size() >= maxWirelessAdapters) {
                        System.out.println("Maximum number of wireless adapters reached.");
                        return;
                    }
                    // Check if devID is already used
                    if (devID < 0 || devID >= maxWirelessAdapters) {
                        System.out.println("Invalid device ID for wireless adapter.");
                        return;
                    }
                    for (WirelessIO w : wirelessIOs) {
                        if (w.getName().equals("Bluetooth")) {
                            System.out.println("Device ID " + devID + " is already used.");
                            return;
                        }
                        if(w.getDevType().contains("WirelessIO") && getDeviceID(w) == devID){ 
                            System.out.println("Device ID " + devID + " is already used.");
                            return;
                        }
                    }
                    device = new Bluetooth((UART) portProtocol);
                    wirelessIOs.add((WirelessIO) device);
                    break;
                    
                case "Wifi":
                    if (!protocolName.equals("SPI") && !protocolName.equals("UART")) {
                        System.out.println("Wifi is incompatible with " + protocolName + " protocol.");
                        return;
                    }
                    // Check if we have reached the limit for wireless IOs
                    if (wirelessIOs.size() >= maxWirelessAdapters) {
                        System.out.println("Maximum number of wireless adapters reached.");
                        return;
                    }
                    // Check if devID is already used
                    if (devID < 0 || devID >= maxWirelessAdapters) {
                        System.out.println("Invalid device ID for wireless adapter.");
                        return;
                    }
                    for (WirelessIO w : wirelessIOs) {
                        if (w.getName().equals("Wifi")) {
                            System.out.println("Device ID " + devID + " is already used.");
                            return;
                        }
                        if(w.getDevType().contains("WirelessIO") && getDeviceID(w) == devID){ 
                            System.out.println("Device ID " + devID + " is already used.");
                            return;
                        }
                    }
                    if (protocolName.equals("SPI")) {
                        device = new Wifi((SPI) portProtocol);
                    } else if (protocolName.equals("UART")) {
                        device = new Wifi((UART) portProtocol);
                    }
                    wirelessIOs.add((WirelessIO) device);
                    break;
                    
                case "PCA9685":
                    if (!protocolName.equals("I2C")) {
                        System.out.println("PCA9685 is incompatible with " + protocolName + " protocol.");
                        return;
                    }
                    // Check if we have reached the limit for motor drivers
                    if (motorDrivers.size() >= maxMotorDrivers) {
                        System.out.println("Maximum number of motor drivers reached.");
                        return;
                    }
                    // Check if devID is already used
                    if (devID < 0 || devID >= maxMotorDrivers) {
                        System.out.println("Invalid device ID for motor driver.");
                        return;
                    }
                    for (MotorDriver m : motorDrivers) {
                        if (m.getName().equals("PCA9685")) {
                            System.out.println("Device ID " + devID + " is already used.");
                            return;
                        }
                        if(m.getDevType().contains("MotorDriver") && getDeviceID(m) == devID){ 
                            System.out.println("Device ID " + devID + " is already used.");
                            return;
                        }
                    }
                    device = new PCA9685((I2C) portProtocol);
                    motorDrivers.add((MotorDriver) device);
                    break;
                    
                case "SparkFunMD":
                    if (!protocolName.equals("SPI")) {
                        System.out.println("SparkFunMD is incompatible with " + protocolName + " protocol.");
                        return;
                    }
                    // Check if we have reached the limit for motor drivers
                    if (motorDrivers.size() >= maxMotorDrivers) {
                        System.out.println("Maximum number of motor drivers reached.");
                        return;
                    }
                    // Check if devID is already used
                    if (devID < 0 || devID >= maxMotorDrivers) {
                        System.out.println("Invalid device ID for motor driver.");
                        return;
                    }
                    for (MotorDriver m : motorDrivers) {
                        if (m.getName().equals("SparkFunMD")) {
                            System.out.println("Device ID " + devID + " is already used.");
                            return;
                        }
                        if(m.getDevType().contains("MotorDriver") && getDeviceID(m) == devID){ 
                            System.out.println("Device ID " + devID + " is already used.");
                            return;
                        }
                    }
                    device = new SparkFunMD((SPI) portProtocol);
                    motorDrivers.add((MotorDriver) device);
                    break;
                    
                default:
                    System.out.println("Unknown device: " + devName);
                    return;
            }
            
            devices.set(portID, device);
            
        } catch (Exception e) {
            System.out.println("Error adding device: " + e.getMessage());
        }
    }
    
    public void removeDevice(int portID) {
        // Check if port exists
        if (portID < 0 || portID >= ports.size()) {
            System.out.println("Invalid port ID.");
            return;
        }
        
        // Check if there's a device on this port
        Device device = devices.get(portID);
        if (device == null) {
            System.out.println("No device on port " + portID + ".");
            return;
        }
        
        // Check if the device is turned on
        if (device.getState() == Device.State.ON) {
            System.out.println("Device is active.");
            System.out.println("Command failed.");
            return;
        }
        
        // Remove the device from appropriate lists
        if(device.getDevType().contains("Sensor")){
            sensors.remove((Sensor) device);
        } else if(device.getDevType().contains("Display")){
            displays.remove((Display) device);
        } else if(device.getDevType().contains("WirelessIO")){
            wirelessIOs.remove((WirelessIO) device);
        } else if(device.getDevType().contains("MotorDriver")){
            motorDrivers.remove((MotorDriver) device);
        }
        
        devices.set(portID, null);
    }
    
    public void turnDeviceOn(int portID) {
        // Check if port exists
        if (portID < 0 || portID >= ports.size()) {
            System.out.println("Invalid port ID.");
            return;
        }
        
        // Check if there's a device on this port
        Device device = devices.get(portID);
        if (device == null) {
            System.out.println("No device on port " + portID + ".");
            return;
        }
        
        device.turnON();
    }
    

    public void turnDeviceOff(int portID) {
        // Check if port exists
        if (portID < 0 || portID >= ports.size()) {
            System.out.println("Invalid port ID.");
            return;
        }
        
        // Check if there's a device on this port
        Device device = devices.get(portID);
        if (device == null) {
            System.out.println("No device on port " + portID + ".");
            return;
        }
        
        device.turnOFF();
    }
    

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
    

    public void listDeviceType(String devType) {
        System.out.println("list of " + devType + "s:");
        
        ArrayList<Device> deviceList = new ArrayList<>();
        
        switch (devType) {
            case "Sensor":
                for (Sensor s : sensors) {
                    deviceList.add(s);
                }
                break;
                
            case "Display":
                for (Display d : displays) {
                    deviceList.add(d);
                }
                break;
                
            case "WirelessIO":
                for (WirelessIO w : wirelessIOs) {
                    deviceList.add(w);
                }
                break;
                
            case "MotorDriver":
                for (MotorDriver m : motorDrivers) {
                    deviceList.add(m);
                }
                break;
                
            default:
                System.out.println("Unknown device type: " + devType);
                return;
        }
        
        // Print device information
        for (Device device : deviceList) {
            int portID = getPortID(device);
            String protocolName;
            if(portID != -1){
                protocolName = ports.get(portID).getProtocolName();
            } else {
                protocolName = "Unknown";
            }
            System.out.println(device.getName() + " " + getDeviceID(device) + " " + portID + " " + protocolName);
        }
    }
    

    public void readSensor(int devID) {
        // Check if devID is valid
        if (devID < 0 || devID >= sensors.size()) {
            System.out.println("Invalid sensor ID.");
            System.out.println("Command failed.");
            return;
        }
        
        // Get the sensor and check if it's turned on
        Sensor sensor = sensors.get(devID);
        if (sensor.getState() == Device.State.OFF) {
            System.out.println("Device is not active.");
            System.out.println("Command failed.");
            return;
        }
        
        System.out.println(sensor.getName() + " " + sensor.getDevType() + ": " + sensor.data2String());
    }
    
    public void printDisplay(int devID, String data) {
        // Check if devID is valid
        if (devID < 0 || devID >= displays.size()) {
            System.out.println("Invalid display ID.");
            System.out.println("Command failed.");
            return;
        }
        
        // Get the display and check if it's turned on
        Display display = displays.get(devID);
        if (display.getState() == Device.State.OFF) {
            System.out.println("Device is not active.");
            System.out.println("Command failed.");
            return;
        }
        
        display.printData(data);
    }
    

    public void readWireless(int devID) {
        // Check if devID is valid
        if (devID < 0 || devID >= wirelessIOs.size()) {
            System.out.println("Invalid wireless adapter ID.");
            System.out.println("Command failed.");
            return;
        }
        
        // Get the wireless adapter and check if it's turned on
        WirelessIO wireless = wirelessIOs.get(devID);
        if (wireless.getState() == Device.State.OFF) {
            System.out.println("Device is not active.");
            System.out.println("Command failed.");
            return;
        }
        
        System.out.println(wireless.recvData());
    }
    
 
    public void writeWireless(int devID, String data) {
        // Check if devID is valid
        if (devID < 0 || devID >= wirelessIOs.size()) {
            System.out.println("Invalid wireless adapter ID.");
            System.out.println("Command failed.");
            return;
        }
        
        // Get the wireless adapter and check if it's turned on
        WirelessIO wireless = wirelessIOs.get(devID);
        if (wireless.getState() == Device.State.OFF) {
            System.out.println("Device is not active.");
            System.out.println("Command failed.");
            return;
        }
        
        wireless.sendData(data);
    }
    

    public void setMotorSpeed(int devID, int speed) {
        // Check if devID is valid
        if (devID < 0 || devID >= motorDrivers.size()) {
            System.out.println("Invalid motor driver ID.");
            System.out.println("Command failed.");
            return;
        }
        
        // Get the motor driver and check if it's turned on
        MotorDriver motor = motorDrivers.get(devID);
        if (motor.getState() == Device.State.OFF) {
            System.out.println("Device is not active.");
            System.out.println("Command failed.");
            return;
        }
        
        motor.setMotorSpeed(speed);
    }
    
 
    private int getPortID(Device device) {
        for (int i = 0; i < devices.size(); i++) {
            if (device == devices.get(i)) {
                return i;
            }
        }
        return -1;
    }
    
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
}