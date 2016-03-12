package pozzo.apps.cheesecake.util;

import com.splunk.mint.Mint;

import pozzo.apps.cheesecake.BuildConfig;

/**
 * Encapsulate to create our own behaviour.
 *
 * @author Luiz Gustavo Pozzo
 * @since 07/03/16
 */
public class Log {

	public static void e(Exception e) {
		if(BuildConfig.DEBUG)
			e.printStackTrace();
		Mint.logException(e);
	}
}
