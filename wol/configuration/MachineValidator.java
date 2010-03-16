/*
 * $Id: MachineValidator.java,v 1.2 2004/05/18 13:55:53 gon23 Exp $
 */
package wol.configuration;

/**
 * @author <a href="&#109;&#97;&#105;&#108;&#116;&#111;&#58;&#115;&#46;&#109;&#111;&#108;&#100;&#97;&#110;&#101;&#114;&#64;&#103;&#109;&#120;&#46;&#110;&#101;&#116;">Steffen Moldaner</a>
 */
public interface MachineValidator {
	public boolean nameIsValid(String name);
	public boolean portIsValid(String port);
	public boolean hostIsValid(String host);
	public boolean ethernetAddressIsValid(String ethernetAddress);
	public boolean commentIsValid(String comment);
}

/*
 * $Log: MachineValidator.java,v $
 * Revision 1.2  2004/05/18 13:55:53  gon23
 * *** empty log message ***
 *
 * Revision 1.1  2004/05/17 21:58:59  gon23
 * javadoc
 *
 */