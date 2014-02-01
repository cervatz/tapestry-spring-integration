package eu.comparegroup.beans.json;

import org.springframework.validation.ObjectError;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.ArrayList;
import java.util.List;

/**
 * Helper class to put a response in an object send as JSON to the client
 * after an AJAX call is made
 */
public class JsonResponse {

	public enum Status {
		SUCCESS,
		FAIL
	}

	@Enumerated(EnumType.STRING)
	private Status status;

	private String message;
	private List<ObjectError> errors = new ArrayList<>();

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<ObjectError> getErrors() {
		return errors;
	}

	public void setErrors(List<ObjectError> errors) {
		this.errors = errors;
	}
}
