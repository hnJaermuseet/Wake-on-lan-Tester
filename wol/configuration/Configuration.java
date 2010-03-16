/*
 * $Id: Configuration.java,v 1.7 2004/05/18 13:55:53 gon23 Exp $
 */
package wol.configuration;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * This class represents a WakeOnLan configuration.
 * 
 * @author <a href="&#109;&#97;&#105;&#108;&#116;&#111;&#58;&#115;&#46;&#109;&#111;&#108;&#100;&#97;&#110;&#101;&#114;&#64;&#103;&#109;&#120;&#46;&#110;&#101;&#116;">Steffen Moldaner</a>
 */
public class Configuration {
	private final static Logger LOG = Logger.getLogger(Configuration.class.getName());
	private Machine[] machines;
	private File file;
	
	public Configuration() {
		this(System.getProperty("user.home") + File.separatorChar + ".wakeonlan.hosts");
	}
	
	/**
	 * Creates a new configuration with the given path.
	 * 
	 * @param path a path that denotes a file the configuration will be saved to
	 */
	public Configuration(String path) {
		this(new File(path));
	}
	
	/**
	 * Creates a new configuration with the given file. If the file exists
	 * the configurations loads immidiatly from this file.
	 * 
	 * @param file a file this configuration will be saved to
	 * @see #loadConfig()
	 */
	public Configuration(File file) {
		super();
		this.file = file;
		
		if (file.exists()) {
			try {
				loadConfig();
			} catch (FileNotFoundException e) {
				String errMsg = "Could not load configuration";
				
				if (LOG.isLoggable(Level.FINE)) {
					LOG.log(Level.WARNING, errMsg, e);
				} else {
					LOG.warning(errMsg);
				}
				
			}
		} else {
			this.file = file;
		}
	}
	
	/**
	 * Returns the machines.
	 * 
	 * @return the machines
	 */
	public Machine[] getMachines() {
		return null == machines ? new Machine[0] : machines;
	}
	
	/**
	 * Sets the machines.
	 * 
	 * @param machines the machines
	 */
	public void setMachines(Machine[] machines) {
		this.machines = machines;
	}
	
	/**
	 * Loads this configuration from the file returned by {@link #getFile()}.
	 * 
	 * @throws FileNotFoundException if the file does not exist.
	 * @see #getFile()
	 */
	public void loadConfig() throws FileNotFoundException {
		XMLDecoder decoder = new XMLDecoder(new FileInputStream(file));
		
		try {
			this.machines = (Machine[]) decoder.readObject();
		} catch (Throwable t) {
			String errMsg = "Could not load configuration";
			
			LOG.log(Level.SEVERE, errMsg, t);
		}
		
		decoder.close();
	}
	
	/**
	 * Saves this configuration to the file returned by {@link #getFile()}.
	 * This is equal to saveConfig(getFile()).
	 * 
	 * @throws FileNotFoundException if the file exists but is a directory
    * 		  rather than a regular file, does not exist but cannot
    *         be created, or cannot be opened for any other reason
	 * @see #getFile()
	 * @see #saveConfigAs(File)
	 */
	public void saveConfig() throws FileNotFoundException {
		saveConfigAs(this.file);
	}
	
	/**
	 * Saves this configuration to the given file. The configuration will then 
	 * use this file for saves.
	 * 
	 * @param file the file
	 * @throws FileNotFoundException if the file exists but is a directory
    * 		  rather than a regular file, does not exist but cannot
    *         be created, or cannot be opened for any other reason
	 * @see #saveConfigAs(File)
	 */
	public void saveConfigAs(File file) throws FileNotFoundException {
		XMLEncoder encoder = new XMLEncoder(new FileOutputStream(file));
		
		encoder.writeObject(this.machines);
		encoder.close();
		this.file = file;
	}
	
	/**
	 * Returns the file for this configuration.
	 * 
	 * @return the file
	 */
	public File getFile() {
		return file;
	}
}

/*
 * $Log: Configuration.java,v $
 * Revision 1.7  2004/05/18 13:55:53  gon23
 * *** empty log message ***
 *
 * Revision 1.6  2004/04/28 05:39:02  gon23
 * Added default constructor
 *
 * Revision 1.5  2004/04/21 20:39:35  gon23
 * javadoc
 *
 * Revision 1.4  2004/04/15 22:50:39  gon23
 * New Constructor
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
 */