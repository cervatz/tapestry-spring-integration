package eu.comparegroup.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import eu.comparegroup.services.common.security.AuthorizeRequest;

/**
 * Abstract class that can be used by ServiceImpl classes for reusing
 * <p/>
 * This class has properties and method that can be shared by those classes
 * like {@link eu.comparegroup.services.tracelogging.TraceLogServiceImpl}
 * <p/>
 * The {@link ObjectMapper} can be static because is thread safe.
 */
public abstract class AbstractServiceImpl {

	protected static final ObjectMapper OM = new ObjectMapper();

	/**
	 * Get an assessToken for making connection with the API.
	 *
	 * @param serviceName the name of the service
	 * @return an accessToken for connecting to API {@link String}
	 */
	public String getAccessToken(String serviceName) {
		return AuthorizeRequest.generateTokenOnly(serviceName, System.currentTimeMillis()).toString();
	}
}
