import java.io.IOException;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        try {
            //构建 datagramSocket 对象实例
            DatagramSocket socket = new DatagramSocket();
            //传递的消息
            String message = "Hello Server";

            //构建 DatagramPacket 对象实例，并包含接收者主机地址、接收端口号等信息
            InetAddress receiverAddress = InetAddress.getByName("127.0.0.1");
            int receiverPort = 8080;
            byte[] data = message.getBytes();
            DatagramPacket packet = new DatagramPacket(data, data.length, receiverAddress, receiverPort);

            //调用datagramSocket对象实例的send方法，将DatagramPacket对象实例作为参数发送。
            //创建并启动发送数据的线程
            Thread sentThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        socket.send(packet);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            sentThread.start();
        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }
}
