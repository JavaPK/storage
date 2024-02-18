package pl.office.storage.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;
import pl.office.storage.api.InstatnceType;

@Getter
@Configuration
@ConfigurationProperties(prefix = "storage")
@Setter
public class StorageProperties {
	private String directory;
	private boolean encrypted = false;
	private int id;
	private InstatnceType instanceType;
	private String serverHost;
	private int serverPort;
}
