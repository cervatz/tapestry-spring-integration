package eu.comparegroup.services.session;

import eu.comparegroup.services.frontend.poc.dto.listerpage.SearchResultsContainer;
import eu.comparegroup.services.frontend.poc.dto.session.UserSession;
import eu.comparegroup.services.common.client.WebResponseException;

import java.io.IOException;

public interface UserSessionService {
	UserSession getUserSession() throws IOException, WebResponseException;
}