import java.io.IOException;
import java.net.*;

/**
 * UserAgent Class
 *
 * The UserAgent is the client of SIP (Session Initiation Protocol). The UA sends
 *  Request objects to the server (mjua_1.8) through the SocketSourcePort on port 5080,
 *  and receives Response objects through socketDestinationPort on port 5070, on the
 *  loopback address.
 *
 * @author Daniele Pellegrini <daniele.pellegrini@studenti.unipr.it> - 285240
 * @author Guido Soncini <guido.soncini1@studenti.unipr.it> - 285140
 * @author Mattia Ricci <mattia.ricci1@studenti.unipr.it> - 285237
 */
public class UserAgent{
    public static InetAddress address = getAddress();
    public static int sourcePort = 5080;
    public static int destinationPort = 5070;
    public static DatagramSocket socketOutgoing = getSocketOutgoing();
    public static DatagramSocket socketIncoming = getSocketIncoming();

    /**
     * Class Constructor
     */
    public UserAgent(){
    }

    /**
     * Set the loopback address for a local VoIP Communication.
     */
    public static InetAddress getAddress(){
        try {
            return InetAddress.getByName("127.0.0.1");
        }catch(UnknownHostException e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Set the Sockets Port for Outgoing stream.
     * Outgoing Socket is used to receive byte Response
     */
    public static DatagramSocket getSocketOutgoing(){
        try {
            return new DatagramSocket();
        }catch(SocketException e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Set the Sockets Port for Incoming stream.
     * Incoming Socket is used to send byte Request
     */
    public static DatagramSocket getSocketIncoming(){
        try {
            return new DatagramSocket(destinationPort, getAddress());
        }catch(SocketException e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Send a request in byte to the Server mjua_1.8
     *
     * @param request the request to send (in byte)
     */
    public static void send(byte[] request){
        try {
            socketOutgoing.send(new DatagramPacket(request, request.length, getAddress(), sourcePort));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Receive a response in byte from the Server and print
     * the related message
     */
    public static void receive(){
        try {
            byte[] response = new byte[1024];
            DatagramPacket received = new DatagramPacket(response, response.length, address, destinationPort);
            socketIncoming.receive(received);
            new Response(received).showMessage();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Listen for a new DatagramPacket on the Incoming DatagramSocket mjua_1.8
     */
    public static DatagramPacket listen(){
        try {
            byte[] response = new byte[1024];
            DatagramPacket received = new DatagramPacket(response, response.length, address, destinationPort);
            socketIncoming.receive(received);
            return received;
        }catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }

    public static void run() {
        send(Request.INVITE);
        receive();
        send(Request.BYE);
        System.out.println("BYE sent");
        receive();
    }
}