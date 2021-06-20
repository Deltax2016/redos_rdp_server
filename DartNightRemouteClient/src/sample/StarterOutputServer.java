package sample;

import sample.Request.GetInput;
import sample.Request.SendScreen;
import sample.connection.Server;

public class StarterOutputServer {

    private static StarterOutputServer starterOutputServer;
    private Server server;
    private GetInput getInput;
    private Thread socketThread, sendThread;
    private SendScreen sendScreen;

    public static void main(String[] args) throws Exception {
        StarterOutputServer starterOutputServer = new StarterOutputServer();
        starterOutputServer.start();
    }

    void start() throws Exception {
        server = new Server("192.168.0.16", 52000);
        getInput = new GetInput();
        server.setOnReceive(getInput::onReceive);
        sendScreen = new SendScreen();
        socketThread = new Thread(() -> {
            try {
                server.receive();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "Receive");
        socketThread.start();
        sendThread = new Thread(() -> {
            try {
                while (true) {
//                    Thread.sleep(5);
                    server.sendImage(sendScreen.getScreen());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "Send");
        sendThread.start();
    }
}
