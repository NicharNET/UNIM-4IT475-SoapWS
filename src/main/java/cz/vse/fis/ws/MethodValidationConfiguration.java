package cz.vse.fis.ws;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

/**
 * Configuration class enabling the method parameters validation
 * 
 * @author Nikolas Charalambidis
 */
@Configuration
public class MethodValidationConfiguration {

	/**
	 * The bean registering the method validation post processor
	 * @return The method validation post processor instance
	 */
	@Bean
	public MethodValidationPostProcessor methodValidationPostProcessor() {
		return new MethodValidationPostProcessor();
	}
}