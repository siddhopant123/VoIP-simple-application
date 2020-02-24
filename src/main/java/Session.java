import java.net.DatagramPacket;
import java.util.ArrayList;
import java.util.List;

/**
 * Session Class
 *
 * The Session class stores the logs (information) about the VoIP conversation
 *  between the UserAgent (SIP Client) and mjUA_1.8 (SIP Server).
 *  It stores the requests sent by the client, the responses sent and received from
 *  the server and the datagram Packets sent through the socket connection.
 *  It has a method to record the conversation in a WireShark capture.
 *
 * @author Daniele Pellegrini <daniele.pellegrini@studenti.unipr.it> - 285240
 * @author Guido Soncini <guido.soncini1@studenti.unipr.it> - 285140
 * @author Mattia Ricci <mattia.ricci1@studenti.unipr.it> - 285237
 */
public class Session {
    private static List<byte[]> requests = new ArrayList<>();
    private static List<byte[]> responses = new ArrayList<>();
    private static List<DatagramPacket> packets = new ArrayList<>();

    public Session(){}

    public static List<byte[]> getRequests() {
        return requests;
    }

    public static List<byte[]> getResponses() {
        return responses;
    }

    public static List<DatagramPacket> getPackets() {
        return packets;
    }

    /**
     * Add a Request to the requests List
     *
     * @param newRequest the request to add
     */
    public static void addRequest(byte[] newRequest){
        requests.add(newRequest);
    }

    /**
     * Add a Response to the responses List
     *
     * @param newResponse the response to add
     */
    public static void addResponse(byte[] newResponse){
        responses.add(newResponse);
    }

    /**
     * Add a Packet to the pakcets List
     *
     * @param newPacket the packet to add
     */
    public static void addPacket(DatagramPacket newPacket){
        packets.add(newPacket);
    }

    /**
     * Record the VoIP conversation's flow in a WireShark capture.
     */
    // TODO Ask for advice for a sniffer on the loopback interface
    public void save(){
        // code here

        // -- libcap not working --
        // -- DataLink link = new DataLink(); --

        // LoopbackInterface loopback = new LoopbackInterface(new SocketAddress(new Ip4Address(address), port1));
        // new LibpcapSniffer(loopback, LibpcapHeader.LINKTYPE_IPV4,"Johhny.pcap");
    }
}
