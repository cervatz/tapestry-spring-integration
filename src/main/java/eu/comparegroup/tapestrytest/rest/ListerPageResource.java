package eu.comparegroup.tapestrytest.rest;

import java.util.ArrayList;
import javax.ws.rs.*;
import java.util.List;

@Path("/listerpage")
public class ListerPageResource {
	@GET
	@Produces({"application/json"})
	public List<String> getProducts() {
	
		ArrayList<String> products = new ArrayList<>();
		products.add("one");
		products.add("two");
		products.add("three");
		return products;
	}
}