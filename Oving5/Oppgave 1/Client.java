import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {

        DatagramSocket socket = new DatagramSocket();
        boolean mer = true;
        while (mer) {
            try {
                Scanner lesFraTerminal = new Scanner(System.in);
                System.out.print(
                        "Bruk mellomrom mellom tall og operator.\nGyldige operatorer er + og -\nSkriv et regneregnestykke: \n");
                String regnestykke = lesFraTerminal.nextLine();

                if (regnestykke.equals("")) {
                    mer = false;
                    lesFraTerminal.close();
                }
                byte[] buffer = regnestykke.getBytes();
                InetAddress address = InetAddress.getLocalHost();
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, 4445);
                socket.send(packet);
                System.out.println("Forespørsel sendt:");
                System.out.println(regnestykke + "\n");

                buffer = new byte[256];
                packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);

                String received = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Svar mottatt:");
                System.out.println(received + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        socket.close();
    }
}