package eu.comparegroup.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Throwables;
import eu.comparegroup.services.common.service.exception.ServiceExceptionObject;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.MessageFormat;

@Controller
@RequestMapping("/errors")
public class ExceptionController {
	private static ObjectMapper objectMapper = new ObjectMapper();

	/**
	 * Composes the message to be shown in error page.
	 */
	@RequestMapping(value = "/errors.html", method = RequestMethod.GET)
	public String handleStatusCodeErrors(HttpServletRequest request, HttpServletResponse response, Model model) {
		int type;
		String errorMessage = "An error occured";
		if (NumberUtils.isNumber(request.getParameter("type"))) {
			type = Integer.parseInt(request.getParameter("type"));
			switch (type) {
				case 401:
					errorMessage = type + " ERROR: unauthorized to view page " + getRequestURI(request);
					break;
				case 403:
					errorMessage = type + " ERROR: access denied for page " + getRequestURI(request);
					break;
				case 404:
					errorMessage = type + " ERROR: page " + getRequestURI(request) + " could not be found";
					break;
				case 500:
					errorMessage = type + " ERROR: page " + getRequestURI(request) + " gives an internal service error";
					break;
				default:
					errorMessage = "ERROR: page " + getRequestURI(request) + " produced an error";
					break;
			}
		}
		model.addAttribute("errorMessage", errorMessage);
		return "pages/exceptions/error";
	}

	/**
	 * Composes the content to be shown in exception page.
	 */
	@RequestMapping(value = "/exception.html")
	public String handleException(HttpServletRequest request, HttpServletResponse response, Model model) {
		Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
		Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
		String exceptionMessage = getExceptionMessage(throwable, statusCode);

		String message = MessageFormat.format("ERROR CODE {0} returned for {1} with message: {2}",
				statusCode, getRequestURI(request), exceptionMessage);

		ServiceExceptionObject seo;
		try {
			seo = objectMapper.readValue(exceptionMessage, ServiceExceptionObject.class);
		} catch (Exception e) {
			seo = new ServiceExceptionObject();
			seo.setMessage(message);
			if (throwable.getCause() != null) {
				seo.setCause(throwable.getCause().toString());
			}
			seo.setStatus(statusCode);
		}
		model.addAttribute("serviceExceptionObject", seo);
		return "pages/exceptions/exception";
	}

	private String getExceptionMessage(Throwable throwable, Integer statusCode) {
		if (throwable != null) {
			return Throwables.getRootCause(throwable).getMessage();
		}
		HttpStatus httpStatus = HttpStatus.valueOf(statusCode);
		return httpStatus.getReasonPhrase();
	}

	private String getRequestURI(HttpServletRequest request) {
		return request.getAttribute("javax.servlet.error.request_uri") != null
				? (String) request.getAttribute("javax.servlet.error.request_uri") : "Unknown";
	}
}
