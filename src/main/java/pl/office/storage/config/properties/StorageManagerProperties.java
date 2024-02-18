package pl.office.storage.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Getter
@Configuration
@ConfigurationProperties(prefix = "storage-manager")
@Setter
public class StorageManagerProperties {
	private String url;
	private String register;
}
