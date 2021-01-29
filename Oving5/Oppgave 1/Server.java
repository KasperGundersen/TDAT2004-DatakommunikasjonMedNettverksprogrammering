import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

class Server {
    public static void main(String[] args) throws IOException {
        System.out.println("Logg for tjenersiden. Skriv et regnestykke fra klientsiden...");

        DatagramSocket socket = new DatagramSocket(4445);
        boolean mer = true;
        while (mer) {
            byte[] buffer = new byte[256];

            // receive request
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);

            String regnestykke = new String(packet.getData(), 0, packet.getLength());
            if (regnestykke.equals("")) {
                mer = false;
            }

            Calculator k = new Calculator(regnestykke);
            String retur = k.toString();
            System.out.println("Setter returverdi: " + retur);

            buffer = retur.getBytes();

            InetAddress address = packet.getAddress();
            int port = packet.getPort();
            packet = new DatagramPacket(buffer, buffer.length, address, port);
            socket.send(packet);
        }
        socket.close();
    }
}