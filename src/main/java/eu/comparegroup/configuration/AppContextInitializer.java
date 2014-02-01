package eu.comparegroup.configuration;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

/**
 *  Override the initialization of loading the Application context to set the profile based on property value
 */
public class AppContextInitializer implements ApplicationContextInitializer{

	private static final String PROPERTY_FILE = "WEB-INF/conf/application.conf";
	private static final String PROPERTY_KEY = "spring.profile.active";

	@Override
	public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
		try {
			Resource r = configurableApplicationContext.getResource(PROPERTY_FILE);
			if(!r.exists()){
				r = configurableApplicationContext.getResource("/" + PROPERTY_FILE);
				if(!r.exists()){
					throw new RuntimeException("Can not find property file: "+PROPERTY_FILE);
				}
			}
			URL url = r.getURL();
			InputStream in = url.openStream();
			Properties properties = new Properties();
			properties.load(in);

			String profile = properties.getProperty(PROPERTY_KEY);
			if(StringUtils.isBlank(profile)){
				throw new RuntimeException("Can not find key '"+PROPERTY_KEY+"'. Please add in file '"+PROPERTY_FILE+"' a key/value: spring.profile.active=dev (test, acc, prod)");
			}

			configurableApplicationContext.getEnvironment().setActiveProfiles(profile);

		} catch (IOException e) {
			throw new RuntimeException("Error in loading properties for file: "+PROPERTY_FILE, e);
		}
	}
}
