package eu.comparegroup.util;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

/**
 * Validate IP address
 */
public class ValidateIPAddress {

	private static final String PATTERN =
			"^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
					"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
					"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
					"([01]?\\d\\d?|2[0-4]\\d|25[0-5])(/([0-9]|1[0-9]|2[0-9]|3[0-2]))?$";
	private static final Pattern COMPILED_PATTERN = Pattern.compile(PATTERN);

	/**
	 * Check if IP address is valid
	 * @param address   name of ip address to check
	 * @return true in case address is valid and false otherwise
	 */
	public static boolean isValidInetAddress(final String address) {
		if (StringUtils.isBlank(address)) {
			return false;
		}
		return COMPILED_PATTERN.matcher(address).matches();
		//return InetAddresses.isInetAddress(address);
	}
}
