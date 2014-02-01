package eu.comparegroup.configuration;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Objects;
import org.apache.http.client.utils.URIBuilder;

import java.net.URI;
import java.net.URISyntaxException;

public class MicroService {
	public static enum Type {
		NONE, NORMAL, ADMIN
	};
	
	private String name;

	private String protocol = "http";

	private String server;

	private int port;

	private int adminPort;

	private String endPoint;

	private boolean required = true;

	@JsonIgnore
	private boolean isRunning;

	/**
	 * Gets the URI for the microservice.
	 */
	public URI getServiceURI() throws URISyntaxException {
		URIBuilder uriBuilder = new URIBuilder();
		uriBuilder.setScheme(protocol);
		uriBuilder.setHost(server);
		uriBuilder.setPort(port);
		uriBuilder.setPath("/".concat(endPoint));
		return uriBuilder.build();
	}

	/**
	 * Builds the path to the microservice, depending on the type.
	 */
	public String getPath(Type type) {
		if (type == null) {
			type = Type.NONE;
		}
		StringBuilder sb = new StringBuilder();
		sb.append(protocol);
		sb.append("://").append(server);
		switch (type) {
			case NORMAL:
				sb.append(":").append(port).append("/");
				break;
			case ADMIN:
				sb.append(":").append(adminPort).append("/");
				break;
			case NONE:
				sb.append("/");
				break;
			default:
				throw new RuntimeException("Unknown enum Type in MicroService");
		}
		return sb.toString();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getAdminPort() {
		return adminPort;
	}

	public void setAdminPort(int adminPort) {
		this.adminPort = adminPort;
	}

	public String getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(String endPoint) {
		this.endPoint = endPoint;
	}

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public boolean isRunning() {
		return isRunning;
	}

	public void setRunning(boolean running) {
		isRunning = running;
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this)
				.add("name", name)
				.add("protocol", protocol)
				.add("server", server)
				.add("port", port)
				.add("adminPort", adminPort)
				.add("endPoint", endPoint)
				.add("required", required)
				.toString();
	}
}
