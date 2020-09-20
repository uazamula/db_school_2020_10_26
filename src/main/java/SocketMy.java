import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketMy {
    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(8080);
             System.out.println(socket.getLocalPort());
            System.out.println(socket.isClosed());
            System.out.println(socket.isBound());
        System.out.println(socket.getInetAddress());
        System.out.println(socket.getLocalSocketAddress());
        Socket socket1 = new Socket();
        System.out.println(socket1.getLocalPort());
        System.out.println(socket1.getInetAddress());
            socket.close();
            socket1.close();
    }
}

