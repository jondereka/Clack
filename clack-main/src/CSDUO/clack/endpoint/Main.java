package CSDUO.clack.endpoint;

public class Main
{
    public static void main(String[] args) {
        Client client = new Client("username", "localhost", 12);
        client.start();
    }
}
