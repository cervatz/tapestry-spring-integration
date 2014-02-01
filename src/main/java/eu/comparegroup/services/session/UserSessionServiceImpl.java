package eu.comparegroup.services.session;

import com.fasterxml.jackson.core.type.TypeReference;
import eu.comparegroup.services.frontend.poc.dto.session.UserSession;
import eu.comparegroup.configuration.MicroService;
import eu.comparegroup.services.AbstractServiceImpl;
import eu.comparegroup.services.common.client.JSONClient;
import eu.comparegroup.services.common.client.WebResponseException;
import java.io.IOException;
import org.springframework.stereotype.Service;

@Service
public class UserSessionServiceImpl extends AbstractServiceImpl implements UserSessionService {

	private static MicroService microService;
	private static String path;

	/**
	 * Setter IoC
	 *
	 * @param microService the {@link MicroService} to use
	 */
	public void setMicroService(MicroService microService) {
		this.microService = microService;
		this.path = microService.getPath(MicroService.Type.NORMAL)  + microService.getEndPoint();
	}

	@Override
	public UserSession getUserSession() throws IOException, WebResponseException {
		return (UserSession) JSONClient.execute(JSONClient.Type.GET,
			path + "user-session/",
			null,
			"",
			new TypeReference<UserSession>() {
			}
		);
	}}
