package eu.comparegroup.configuration;

import com.google.common.base.Objects;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class MicroServicesConfiguration {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private List<MicroService> microservices = new LinkedList<MicroService>();
	private String pingCmd = "ping";
	private String metricsCmd = "metrics";
	private String healthCmd = "healthcheck";

	public List<MicroService> getMicroservices() {
		return microservices;
	}

	public void setMicroservices(List<MicroService> microservices) {
		this.microservices = microservices;
	}

	public String getPingCmd() {
		return pingCmd;
	}

	public void setPingCmd(String pingCmd) {
		this.pingCmd = pingCmd;
	}

	public String getMetricsCmd() {
		return metricsCmd;
	}

	public void setMetricsCmd(String metricsCmd) {
		this.metricsCmd = metricsCmd;
	}

	public String getHealthCmd() {
		return healthCmd;
	}

	public void setHealthCmd(String healthCmd) {
		this.healthCmd = healthCmd;
	}

	/**
	 * Gets the microservice by name.
	 */
	public MicroService getMicroService(String name) {
		for (MicroService ms : getMicroservices()) {
			if (StringUtils.equalsIgnoreCase(ms.getName(), name)) {
				return ms;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this)
				.add("microservices", microservices)
				.add("pingCmd", pingCmd)
				.add("metricsCmd", metricsCmd)
				.add("healthCmd", healthCmd)
				.toString();
	}
}
