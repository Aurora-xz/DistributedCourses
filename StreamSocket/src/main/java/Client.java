import java.io.IOException;

public class Client {

    public static void main(String[] args) {
        try {
            //创建一个 MyStreamSocket 的实例对象，并将其指定接收服务器和端口号
            String serverAddress = "127.0.0.1";
            int serverPort = 12345;
            MyStreamSocket socket = new MyStreamSocket(serverAddress, serverPort);

            //调用该 socket 的 receiveMessage 方法读取从服务器端获得的消息
            String receiveMessage = socket.receiveMessage();
            System.out.println("message from server: " + receiveMessage);

            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
