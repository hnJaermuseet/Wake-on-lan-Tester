/*
 * $Id: Machine.java,v 1.1 2004/04/21 20:40:57 gon23 Exp $
 */
package wol.configuration;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * This class represents a WakeOnLan configuration for a single computer.
 * 
 * @author <a href="&#109;&#97;&#105;&#108;&#116;&#111;&#58;&#115;&#46;&#109;&#111;&#108;&#100;&#97;&#110;&#101;&#114;&#64;&#103;&#109;&#120;&#46;&#110;&#101;&#116;">Steffen Moldaner</a>
 */
public class Machine {
	private String comment = "";
	private String name = "";
	private String host = "255.255.255.255";
	private String ethernetAddress ="";
	private int port = 9;
	private PropertyChangeSupport pcs;
	
	/**
	 * Creates a new machine configuration
	 */
	public Machine() {
		super();
		pcs = new PropertyChangeSupport(this);
	}
	
	/**
	 * Creates a new machine configuration with the given name.
	 * 
	 * @param name the name of this configuration
	 */
	public Machine(String name) {
		this();
		setName(name);
	}
	
	/**
	 * Returns the host the magic packet will be sent to.
	 * 
	 * @return the host
	 */
	public String getHost() {
		return host;
	}
	
	/**
	 * Returns the machines ethernet address
	 *  
	 * @return the ethernet address
	 */
	public String getEthernetAddress() {
		return ethernetAddress;
	}
	
	/**
	 * Returns this configurations name.
	 *  
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * returns the port the magic packet will be send to.
	 * 
	 * @return the port
	 */
	public int getPort() {
		return port;
	}
	
	/**
	 * Sets the host the magic packet will be sent to.
	 * 
	 * @param host the host. Tis can be an IP addres or the name of the machine
	 */
	public void setHost(String host) {
		String oldValue = this.host;
		
		this.host = host;
		pcs.firePropertyChange("host", oldValue, host);
	}
	
	/**
	 * Sets the ethernet address.
	 * 
	 * @param ethernetAddress the ethernet address
	 */
	public void setEthernetAddress(String ethernetAddress) {
		String oldValue = this.ethernetAddress;
		
		this.ethernetAddress = ethernetAddress;
		pcs.firePropertyChange("ethernetAddress", oldValue, ethernetAddress);
	}
	
	/**
	 * Sets the name.
	 * @param name the name
	 */
	public void setName(String name) {
		String oldValue = this.name;
		
		this.name = name;
		pcs.firePropertyChange("name", oldValue, name);
	}
	
	/**
	 * Sets the port.
	 * 
	 * @param port the port. The port must be between 0 and 0xFFFF
	 * @throws IllegalArgumentException if the port is not between the valid range
	 */
	public void setPort(int port) {
		if (port < 0 || port > 0xFFFF) {
			throw new IllegalArgumentException("Port value out of range: " + port);
		}
		
		int oldValue = this.port;
		
		this.port = port;
		pcs.firePropertyChange("port", oldValue, port);
	}
	
	public String toString() {
		return getName();
	}
	
	/**
	 * Returns the comment
	 * 
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}
	
	/**
	 * Sets the comment.
	 * 
	 * @param comment the comment
	 */
	public void setComment(String comment) {
		String oldValue = this.comment;
		
		this.comment = comment;
		pcs.firePropertyChange("comment", oldValue, comment);
	}
	
	/**
	 * @see PropertyChangeSupport#addPropertyChangeListener(java.beans.PropertyChangeListener)  
	 */
	public synchronized void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}
	
	/**
	 * @see PropertyChangeSupport#addPropertyChangeListener(java.lang.String, java.beans.PropertyChangeListener) 
	 */
	public synchronized void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(propertyName, listener);
	}
	
	/**
	 * @see PropertyChangeSupport#removePropertyChangeListener(java.beans.PropertyChangeListener)
	 */
	public synchronized void removePropertyChangeListener(PropertyChangeListener listener) {
		pcs.removePropertyChangeListener(listener);
	}
	
	/**
	 * @see PropertyChangeSupport#removePropertyChangeListener(java.lang.String, java.beans.PropertyChangeListener)
	 */
	public synchronized void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
		pcs.removePropertyChangeListener(propertyName, listener);
	}

	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		
		if (null == obj) {
			return false;
		}
		
		
		if (obj.getClass() != this.getClass()) {
			return false;
		}
		
		Machine other = (Machine) obj;
		
		if (null != this.name) {
			if (!this.name.equals(other.name)) {
				return false;
			}
		} else {
			if (null != other.name) {
				return false;
			}
		}
		
		if (null != this.host) {
			if (!this.host.equals(other.host)) {
				return false;
			}
		} else {
			if (null != other.host) {
				return false;
			}
		}
		
		if (null != this.ethernetAddress) {
			if (!this.ethernetAddress.equals(other.ethernetAddress)) {
				return false;
			}
		} else {
			if (null != other.ethernetAddress) {
				return false;
			}
		}
		
		if (null != this.comment) {
			if (!this.comment.equals(other.comment)) {
				return false;
			}
		} else {
			if (null != other.comment) {
				return false;
			}
		}
		
		if (this.port != other.port) {
			return false;
		}
		
		return true;
	}
}

/*
 * $Log: Machine.java,v $
 * Revision 1.1  2004/04/21 20:40:57  gon23
 * - renamed Host -> Machine
 * - javadoc
 *
 * Revision 1.5  2004/04/16 12:27:11  gon23
 * *** empty log message ***
 *
 * Revision 1.4  2004/04/15 22:57:44  gon23
 * *** empty log message ***
 *
 * Revision 1.3  2004/04/14 22:14:49  gon23
 * *** empty log message ***
 *
 * Revision 1.2  2004/04/14 18:21:39  gon23
 * *** empty log message ***
 *
 * Revision 1.1  2004/04/14 11:13:08  gon23
 * *** empty log message ***
 *
 * Revision 1.1  2004/04/08 22:10:06  gon23
 * Initial
 *
 */