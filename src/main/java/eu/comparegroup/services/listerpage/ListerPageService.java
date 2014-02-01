package eu.comparegroup.services.listerpage;

import eu.comparegroup.services.common.client.WebResponseException;
import eu.comparegroup.services.frontend.poc.dto.listerpage.SearchResultsContainer;
import java.io.IOException;

public interface ListerPageService {
	SearchResultsContainer getSearchResults(int amount) throws IOException, WebResponseException;
}
