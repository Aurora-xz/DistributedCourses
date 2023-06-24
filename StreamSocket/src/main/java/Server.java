
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            //构建连接socket实例，并与指定的端口号绑定，该连接socket随时侦听客户端的连接请求
            int serverPort = 12345;
            ServerSocket serverSocket = new ServerSocket(serverPort);
            while (true) {
                Socket clientSocket = serverSocket.accept();

                //创建一个 MyStreamSocket 的实例对象
                MyStreamSocket socket = new MyStreamSocket(clientSocket.getInetAddress().getHostAddress(), serverPort);

                //调用 MyStreamSocket 的实例对象的 sendMessage 方法，进行消息反馈
                String message = "Hello Client";
                socket.sendMessage(message);
                System.out.println("sent message: " + message);

                socket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
