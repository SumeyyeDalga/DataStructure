package HWSystem.Protocols;

public class I2C implements Protocol {
    @Override
    public String read() {
        return "<I2C> Reading";
    }
    @Override
    public void write(String data) { 
        System.out.println("I2C: Writing \"" + data + "\"."); 
    }
    @Override
    public String getProtocolName() {
        return "I2C";
    }
}

