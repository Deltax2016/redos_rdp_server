package ir.ashkanabd;

import ir.ashkanabd.Request.GetInput;
import ir.ashkanabd.Request.SendScreen;
import ir.ashkanabd.connection.Server;

public class StarterMain {

    private static StarterMain starterMain;
    private Server server;
    private GetInput getInput;
    private Thread socketThread, sendThread;
    private SendScreen sendScreen;

    public static void main(String[] args) throws Exception {
        StarterMain starterMain = new StarterMain();
        starterMain.start();
    }

    public void start() throws Exception {
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
