import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    public static void main(String[] args) {
        try {
            //构建 datagramSocket 对象实例，指定接收的端口号。
            int receiverPort = 8080;
            DatagramSocket socket = new DatagramSocket(receiverPort);

            //通过线程池来管理client线程
            ExecutorService executorService = Executors.newFixedThreadPool(10);
            while (true) {
                //构建 DatagramPacket 对象实例，用于重组接收到的消息。
                byte[] receiverData = new byte[1024];
                DatagramPacket packet = new DatagramPacket(receiverData, receiverData.length);

                //调用datagramSocket对象实例的receive方法，进行消息接收，并将DatagramPacket对象实例作为参数。
                socket.receive(packet);

                //处理消息
                String receiverMessage = new String(packet.getData(), 0, packet.getLength());
                System.out.println("message from client: " + receiverMessage);

                //使用线程池处理消息
                executorService.execute(() -> System.out.println(Thread.currentThread().getName()));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }





}
