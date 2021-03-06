package eu.comparegroup.tapestrytest.pages;

import eu.comparegroup.services.common.client.WebResponseException;
import eu.comparegroup.services.frontend.poc.dto.listerpage.SearchResultsContainer;
import eu.comparegroup.services.listerpage.ListerPageServiceImpl;
import java.io.IOException;
import java.util.Date;
import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.ioc.annotations.*;
import org.apache.tapestry5.corelib.components.*;
import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.alerts.AlertManager;

/**
 * Start page of application tapestry1.
 */
public class Index
{
    @Property
    @Inject
    @Symbol(SymbolConstants.TAPESTRY_VERSION)
    private String tapestryVersion;

    @InjectComponent
    private Zone zone;

    @Persist
    @Property
    private int clickCount;

    @Inject
    private AlertManager alertManager;
	
	@Inject
	private ListerPageServiceImpl listerPageService;

    public Date getCurrentTime() throws IOException, WebResponseException
    {
		SearchResultsContainer searchResults = listerPageService.getSearchResults(10);
        return new Date();
    }

    void onActionFromIncrement()
    {
        alertManager.info("Increment clicked");

        clickCount++;
    }

    Object onActionFromIncrementAjax()
    {
        clickCount++;

        alertManager.info("Increment (via Ajax) clicked");

        return zone;
    }
}
