import java.io.IOException;

import javax.swing.JOptionPane;

import wol.WakeUpUtil;
import wol.configuration.EthernetAddress;
import wol.configuration.IllegalEthernetAddressException;

/**
 * Small program where you can enter an MAC address and send a wake on lan package to it.
 * 
 * @see wol.WakeUpUtil
 * @author Hallvard Nygard <hn@jaermuseet.no>
 */
public class wolTester {
	public static void main(String[] args) {
		String mac = (String)JOptionPane.showInputDialog(
					null,
					"Enter MAC address to send a magic package:",
					"Wake on lan Tester",
					JOptionPane.PLAIN_MESSAGE
				);
		mac = mac.replace('.', ':').replace('-', ':');
		try {
			WakeUpUtil.wakeup(new EthernetAddress(mac));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "IOException " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} catch (IllegalEthernetAddressException e) {
			JOptionPane.showMessageDialog(null, "Invalid MAC address", "Error", JOptionPane.ERROR_MESSAGE);
			main(args);
		}
	}
}
