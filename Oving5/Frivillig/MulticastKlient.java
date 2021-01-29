import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 * Created by Kasper on 18.03.20.
 */
public class MulticastKlient {
    public static void main(final String[] args) throws IOException {

        final MulticastSocket socket = new MulticastSocket(4446);
        final InetAddress address = InetAddress.getByName("230.0.0.1");
        socket.joinGroup(address);

        DatagramPacket packet;
        boolean mer = true;
        while (mer) {
            final byte[] buffer = new byte[256];
            packet = new DatagramPacket(buffer, buffer.length);
            System.out.println("venter...");
            socket.receive(packet);

            final String mottatt = new String(packet.getData(), 0, packet.getLength());

            if (mottatt.equals("")) {
                mer = false;
            }
            System.out.println("Mottatt fra server: " + mottatt);
        }

        socket.leaveGroup(address);
        socket.close();
    }
}