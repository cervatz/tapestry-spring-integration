package eu.comparegroup.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class HomeController {
	/**
	 * Redirects to the the login page. The {@link eu.comparegroup.services.common.domain.logging.TraceLog}
	 * is handle in the {@link eu.comparegroup.security.CustomAuthenticationProvider#authenticate(org.springframework.security.core.Authentication)}
	 * method.
	 */
	@RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
	public String login(Model model) {
		return "pages/login";
	}

	/**
	 * Logout the user and redirects to the login page.
	 * Als write a {@link eu.comparegroup.services.common.domain.logging.TraceLog} to log the action
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(Model model) {
		return "pages/login";
	}
}
