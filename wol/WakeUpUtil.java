/*
 * $Id: WakeUpUtil.java,v 1.7 2004/05/17 21:58:59 gon23 Exp $
 */
package wol;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

import wol.configuration.*;

/**
 * A class to wake up wake-on-lan enabled machines.
 * 
 * @author <a href="&#109;&#97;&#105;&#108;&#116;&#111;&#58;&#115;&#46;&#109;&#111;&#108;&#100;&#97;&#110;&#101;&#114;&#64;&#103;&#109;&#120;&#46;&#110;&#101;&#116;">Steffen Moldaner</a>
 */
public class WakeUpUtil {
	/**
	 * The default wakeup host: 255.255.255.255 (limited broadcast address).
	 */
	public final static InetAddress DEFAULT_HOST;
	
	//this is ugly
	static {
		InetAddress tmpInetAddress = null;
		
		try {
			tmpInetAddress = InetAddress.getByName("255.255.255.255");
		} catch (UnknownHostException e) {
			 //should not happen, because this _is_ a valid IP!
		}
		
		DEFAULT_HOST = tmpInetAddress;
	}
	
	/**
	 * The default wakeup port: 9
	 */
	public final static int DEFAULT_PORT = 9;
	
	private WakeUpUtil() {
		super();
	}
	
	/**
	 * Wakes up the machine with the provided ethernet address, using the default port and host.
	 * 
	 * @param ethernetAddress the ethernet address to wake up
	 * @throws IOException if an I/O error occurs
	 * @see #DEFAULT_HOST
	 * @see #DEFAULT_PORT
	 */
	public static void wakeup(EthernetAddress ethernetAddress) throws IOException {
		WakeUpUtil.wakeup(new EthernetAddress[]{ ethernetAddress });
	}
	/**
	 * Wakes up the machines with the provided ethernet addresses, using the default port and host.
	 * 
	 * @param ethernetAddresses the ethernet addresses to wake up
	 * @throws IOException if an I/O error occurs
	 * @see #DEFAULT_HOST
	 * @see #DEFAULT_PORT
	 */
	public static void wakeup(EthernetAddress[] ethernetAddresses) throws IOException {
		WakeUpUtil.wakeup(ethernetAddresses, DEFAULT_HOST);
	}
	
	/**
	 * Wakes up the machine with the provided ethernet addresses, using the default port.
	 * 
	 * @param ethernetAddress the ethernet address to wake up
	 * @param host the host, the magic sequence will be send to
	 * @throws IOException if an I/O error occurs
	 * @see #DEFAULT_PORT
	 */
	public static void wakeup(EthernetAddress ethernetAddress, InetAddress host) throws IOException {
		WakeUpUtil.wakeup(new EthernetAddress[]{ ethernetAddress }, host);
	}
	
	/**
	 * Wakes up the machines with provided ethernet address.
	 * Equal to <code>WakeUpUtil.wakeup(ethernetAddresses, host, DEFAULT_PORT);</code>
	 * 
	 * @param ethernetAddresses the ethernet addresses to wake up
	 * @param host the host, the magic sequence will be send to
	 * @throws IOException if an I/O error occurs
	 * @see #DEFAULT_PORT
	 */
	public static void wakeup(EthernetAddress[] ethernetAddresses, InetAddress host) throws IOException {
			WakeUpUtil.wakeup(ethernetAddresses, host, DEFAULT_PORT);	
	}
	
	/**
	 * Wakes up the machines with provided ethernet address.
	 * Equal to <code>wakeup(new EthernetAddress[]{ethernetAddress}, host, port);</code>
	 * 
	 * @param ethernetAddress the ethernet address to wake up
	 * @param host the host, the magic sequence will be send to
	 * @param port the port number
	 * @throws IOException if an I/O error occurs
	 * @see #wakeup(EthernetAddress[], InetAddress, int)
	 */
	public static void wakeup(EthernetAddress ethernetAddress, InetAddress host, int port) throws IOException {
		WakeUpUtil.wakeup(new EthernetAddress[]{ ethernetAddress }, host, port);		
	}
	
	/**
	 * Wakes up the machines with provided ethernet addresses. 
	 * The magic sequences are sent to the given host and port.
	 * 
	 * @param ethernetAddresses the ethernet addresses to wake up
	 * @param host the host, the magic sequence will be send to
	 * @param port the port number
	 * @throws IOException if an I/O error occurs
	 */
	public static void wakeup(EthernetAddress[] ethernetAddresses, InetAddress host, int port) throws IOException {
		DatagramSocket socket = new DatagramSocket();
		
		for (int i = 0; i < ethernetAddresses.length; i++) {
			byte[] wakeupFrame = createWakeupFrame(ethernetAddresses[i]);
			
			DatagramPacket packet = new DatagramPacket(wakeupFrame, wakeupFrame.length, host, port);
			
			socket.send(packet);
		}
	}
	
	/**
	 * Creates the byte representation of a wakeupframe for the given ethernet address.
	 * 
	 * @param ethernetAddress the ethernet address
	 * 
	 * @return a byte representation of the wakeupframe
	 */
	protected static byte[] createWakeupFrame(EthernetAddress ethernetAddress) {
		byte[] ethernetAddressBytes = ethernetAddress.toBytes();
		byte[] wakeupFrame = new byte[6 + 16 * ethernetAddressBytes.length];
		
		Arrays.fill(wakeupFrame, 0, 6, (byte)0xFF);
        
		for (int j = 6; j < wakeupFrame.length; j += ethernetAddressBytes.length) {
			System.arraycopy(ethernetAddressBytes, 0, wakeupFrame, j, ethernetAddressBytes.length);
		}
		
		return wakeupFrame;
	}
}

/*
 * $Log: WakeUpUtil.java,v $
 * Revision 1.7  2004/05/17 21:58:59  gon23
 * javadoc
 *
 * Revision 1.6  2004/04/27 19:08:16  gon23
 * moved to wol.configuration
 *
 * Revision 1.5  2004/04/16 09:26:16  gon23
 * extracted  createWakeupFrame
 *
 * Revision 1.4  2004/04/15 22:57:57  gon23
 * *** empty log message ***
 *
 */