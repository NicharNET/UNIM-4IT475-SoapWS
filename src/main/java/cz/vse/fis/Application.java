package cz.vse.fis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * The main application class
 * 
 * @author Nikolas Charalambidis
 */
@SpringBootApplication
public class Application extends SpringBootServletInitializer {
	
	/**
	 * The namespace used for the cipher SOAP WS
	 */
	public static final String NAMESPACE_URI = "https://fis.vse.cz/";
	
	/**
	 * The Sprint-Boot application configurer
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

	/**
	 * The Sprint-Boot application launcher
	 * @param args The arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}