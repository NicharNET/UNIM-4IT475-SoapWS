package cz.vse.fis.ws;

import java.util.List;

import javax.servlet.Servlet;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.soap.server.endpoint.interceptor.PayloadValidatingInterceptor;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

import cz.vse.fis.Application;

/**
 * Configuration class registering the SOAP endpoints and constructing WSDL
 * 
 * @author Nikolas Charalambidis
 */
@EnableWs
@Configuration
public class WebServiceConfiguration extends WsConfigurerAdapter {

	/**
	 * The SOAP payloadValidatingInterceptor added among interceptors
	 * @param interceptors The interceptor
	 */
    @Override
    public void addInterceptors(List<EndpointInterceptor> interceptors) {
        PayloadValidatingInterceptor payloadValidatingInterceptor = new PayloadValidatingInterceptor();
        payloadValidatingInterceptor.setValidateRequest(true);
        payloadValidatingInterceptor.setValidateResponse(true);
        payloadValidatingInterceptor.setXsdSchema(this.schema());
        interceptors.add(payloadValidatingInterceptor);
        super.addInterceptors(interceptors);
    }

    /**
     * Registering the WSDL into the application context
     * @param applicationContext The application context
     * @return The registered servlet bean
     */
    @Bean
    public ServletRegistrationBean<Servlet> messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean<Servlet>(servlet, "/ws/*");
    }

    /**
     * The bean registering the cipher WSDL
     * @param schema The injected XSD schema
     * @return The WSDL definition
     */
    @Bean(name = "cipher") // http://localhost:8080/soap/ws/cipher.wsdl
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema schema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("CipherPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace(Application.NAMESPACE_URI);
        wsdl11Definition.setSchema(schema);
        
        return wsdl11Definition;
    }
    
    /**
     * The XSD schema
     * @return The XSD schema
     */
    @Bean(name = "schema")
    public XsdSchema schema() {
    	return new SimpleXsdSchema(this.xsdResource());
    }
    
    /**
     * The XSD resource
     * @return The XSD resource
     */
    public Resource xsdResource() {
    	return new ClassPathResource("xsd/cipher.xsd");
    }
}