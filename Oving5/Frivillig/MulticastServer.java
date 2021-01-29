import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

/**
 * Created by Kasper on 18.03.20.
 */
public class MulticastServer extends Thread {
    public static void main(final String[] args) throws IOException {
        final DatagramSocket socket = new DatagramSocket(4445);

        boolean mer = true;
        while (mer) {
            try {
                final Scanner lesFraTerminal = new Scanner(System.in);
                System.out.print("Skriv message til alle klienter: ");
                final String message = lesFraTerminal.nextLine();

                if (message.equals("")) {
                    mer = false;
                    lesFraTerminal.close();
                }
                final byte[] buffer = message.getBytes();

                final InetAddress group = InetAddress.getByName("230.0.0.1");
                final DatagramPacket packet = new DatagramPacket(buffer, buffer.length, group, 4446);
                socket.send(packet);
                System.out.println("    Sender: " + new String(packet.getData(), 0, packet.getLength()));
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }
        socket.close();
    }

}