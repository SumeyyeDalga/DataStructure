package HWSystem.Protocols;

public class UART implements Protocol {
    @Override
    public String read() {
        return "<UART> Reading";
    }
    @Override
    public void write(String data) { 
        System.out.println("UART: Writing \"" + data + "\"."); 
    }
    @Override
    public String getProtocolName() {
        return "UART";
    }
}
