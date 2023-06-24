import java.io.*;
import java.net.Socket;

//构建客户端程序和服务器端程序都需要的MyStreamSocket类，定义继承自java Socket的sendMessage和receiveMessage方法
public class MyStreamSocket extends Socket {
    private PrintWriter output;
    private BufferedReader input;

    public MyStreamSocket(String serverAddress, int serverPort) throws IOException {
        super(serverAddress, serverPort);
        init();
    }

    public void sendMessage(String message) {
        output.println(message);
    }

    public String receiveMessage() throws IOException {
        return input.readLine();
    }

    public void init() throws IOException {
        output = new PrintWriter(super.getOutputStream(), true);
        input = new BufferedReader(new InputStreamReader(super.getInputStream()));
    }

}
