/**
 * Represents sensor data associated with a node, such as a planet or satellite.
 * The sensor data includes temperature, pressure, humidity, and radiation levels.
 */
public class SensorData {
    double temperature;
    double pressure;
    double humidity;
    double radiation;

    /**
     * Constructs a new SensorData object with the specified values.
     * 
     * @param temperature The temperature value.
     * @param pressure    The pressure value.
     * @param humidity    The humidity percentage (must be between 0 and 100).
     * @param radiation   The radiation level.
     * 
     * @throws IllegalArgumentException if the humidity is not between 0 and 100.
     */
    public SensorData(double temperature, double pressure, double humidity, double radiation) {
        if(temperature<0){
            throw new IllegalArgumentException("Temperature must be >= 0 (Kelvin).");
        }
        if(pressure<0){
            throw new IllegalArgumentException("Pressure must be >= 0 (Pascal).");
        }
        if(humidity<0 || humidity>100){
            throw new IllegalArgumentException("Humidity must be between 0 and 100.");
        }
        if(radiation<0){
            throw new IllegalArgumentException("Radiation must be >= 0 (milliSievert).");
        }
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        this.radiation = radiation;
    }

}
