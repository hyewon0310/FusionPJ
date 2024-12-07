package Project;

public class Main {
    public static void main(String[] args) throws Exception {
        Server server = new Server();
        server.run();
        Client.test();
    }
}
