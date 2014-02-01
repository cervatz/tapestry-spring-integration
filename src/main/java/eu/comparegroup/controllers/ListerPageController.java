package eu.comparegroup.controllers;

import eu.comparegroup.services.common.client.WebResponseException;
import eu.comparegroup.services.frontend.poc.dto.listerpage.SearchResultsContainer;
import eu.comparegroup.services.frontend.poc.dto.session.UserSession;
import eu.comparegroup.services.listerpage.ListerPageService;
import eu.comparegroup.services.session.UserSessionService;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/listerpage")
public class ListerPageController {
	protected static final String LISTER_PAGE = "pages/listerpage/listerpage";

	@Autowired
	@Qualifier("listerPageServiceImpl")
	private ListerPageService listerPageService;

	@Autowired
	@Qualifier("userSessionServiceImpl")
	private UserSessionService userSessionService;

	// Populating the searchResultContainer and redirecting to the page.
	@RequestMapping(method = RequestMethod.GET)
	public String loadEverythingForListerPage(Model model) throws IOException, WebResponseException {
		//SearchResultsContainer searchResultsContainer = listerPageService.getSearchResults();
		//model.addAttribute("searchResultsContainer", searchResultsContainer);
		return LISTER_PAGE;
	}

	// Returning json for searchResultContainer.
	@RequestMapping(value = "/product-offer-summary-search", produces = "application/json")
	@ResponseBody
	public SearchResultsContainer searchProducts(@RequestParam(value = "amount", required = true) int amount) throws IOException, WebResponseException {
		SearchResultsContainer searchResultsContainer = listerPageService.getSearchResults(amount);
		return searchResultsContainer;
	}

	@RequestMapping(value = "/user-session", produces = "application/json")
	@ResponseBody
	public UserSession retrieveSession() throws IOException, WebResponseException {
		return userSessionService.getUserSession();
	}
}
