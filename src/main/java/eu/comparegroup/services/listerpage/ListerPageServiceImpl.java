package eu.comparegroup.services.listerpage;

import com.fasterxml.jackson.core.type.TypeReference;
import eu.comparegroup.configuration.MicroService;
import eu.comparegroup.services.AbstractServiceImpl;
import eu.comparegroup.services.common.client.JSONClient;
import eu.comparegroup.services.common.client.WebResponseException;
import eu.comparegroup.services.frontend.poc.dto.listerpage.SearchResultsContainer;
import java.io.IOException;
import org.springframework.stereotype.Service;

@Service
public class ListerPageServiceImpl extends AbstractServiceImpl implements ListerPageService {
	private static MicroService microService;

	private static String path;

	/**
	 * Setter IoC
	 *
	 * @param microService the {@link MicroService} to use
	 */
	public void setMicroService(MicroService microService) {
		this.microService = microService;
		this.path = microService.getPath(MicroService.Type.NORMAL) + microService.getEndPoint();
	}

	@Override
	public SearchResultsContainer getSearchResults(int amount) throws IOException, WebResponseException {
		return (SearchResultsContainer) JSONClient.execute(JSONClient.Type.GET,
				path + "product-offer-summary-search/?amount=" + String.valueOf(amount),
				null,
				"",
				new TypeReference<SearchResultsContainer>() {
				});
	}
}
