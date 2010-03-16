/*
 * $Id: IllegalEthernetAddressException.java,v 1.2 2004/05/18 13:55:53 gon23 Exp $
 */
package wol.configuration;

/**
 * Thrown to indicate that a ethernet address could not be created.
 * 
 * @author <a href="&#109;&#97;&#105;&#108;&#116;&#111;&#58;&#115;&#46;&#109;&#111;&#108;&#100;&#97;&#110;&#101;&#114;&#64;&#103;&#109;&#120;&#46;&#110;&#101;&#116;">Steffen Moldaner</a>
 * @see wol.configuration.EthernetAddress
 */
public class IllegalEthernetAddressException extends Exception {

	public IllegalEthernetAddressException() {
		super();
	}

	public IllegalEthernetAddressException(String message) {
		super(message);
	}
}

/*
 * $Log: IllegalEthernetAddressException.java,v $
 * Revision 1.2  2004/05/18 13:55:53  gon23
 * *** empty log message ***
 *
 * Revision 1.1  2004/04/27 19:08:15  gon23
 * moved to wol.configuration
 *
 * Revision 1.1  2004/04/15 22:57:57  gon23
 * *** empty log message ***
 *
 */