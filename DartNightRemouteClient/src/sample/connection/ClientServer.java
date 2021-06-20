package sample.connection;

import javax.imageio.ImageIO;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Base64;
import java.util.Scanner;

public class ClientServer {
    private ServerSocket serverSocket;
    private Socket client;
    private InputStream inStream;           // Читаем данные
    private OutputStream outStream;         // Пишем данные
    private OnReceive onReceive;
    private PrintWriter pw;
    public static String mouseType = "m";
    public static String keyType = "k";

    public ClientServer(int port) throws Exception {
        serverSocket = new ServerSocket(port);
    }

    public void acceptClient() throws Exception {
        client = serverSocket.accept();
        client.setKeepAlive(true);

        outStream = client.getOutputStream();       // Исходящий канал
        inStream = client.getInputStream();         // Входящий канал
                                                    // Их нужно зациклить при нажатии на кнопку

        pw = new PrintWriter(outStream, true);
    }

    public void sendKey(Object obj, String type) throws Exception {
        Object objects[] = new Object[]{obj, type};
        send(objects);
    }

    public void sendMouse(Object obj, double sceneX, double sceneY, String type) throws Exception {
        Object objects[] = new Object[]{obj, type, sceneX, sceneY};
        send(objects);
    }

    private void send(Object obj) throws Exception {
        ByteArrayOutputStream byteArr = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(byteArr);
        oos.writeObject(obj);
        oos.flush();
        byte buff[] = byteArr.toByteArray();
        pw.println(Base64.getEncoder().encodeToString(buff));
    }

    public void receive() throws Exception {
        Scanner scn = new Scanner(inStream);
        int i = 0;
        while (scn.hasNextLine()) {
            System.out.println(i++);
            String line = scn.nextLine();
            byte buff[] = Base64.getDecoder().decode(line);
            onReceive.onReceive(ImageIO.read(
                    new ByteArrayInputStream(buff)));
        }
    }

    public void setOnReceive(OnReceive onReceive) {
        this.onReceive = onReceive;
    }
}
