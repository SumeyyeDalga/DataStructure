package HWSystem.Protocols;

public interface Protocol {
    public String read();
    public void write(String message);
    public String getProtocolName();
}
