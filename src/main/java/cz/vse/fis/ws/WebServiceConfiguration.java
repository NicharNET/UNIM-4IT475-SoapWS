package cz.vse.fis.ws;

import cz.vse.fis.Application;
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
import org.springframework.xml.validation.XmlValidator;
import org.springframework.xml.validation.XmlValidatorFactory;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;
import org.springframework.xml.xsd.XsdSchemaCollection;
import org.xml.sax.SAXException;

import javax.servlet.Servlet;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@EnableWs
@Configuration
public class WebServiceConfiguration extends WsConfigurerAdapter {

    @Override
    public void addInterceptors(List<EndpointInterceptor> interceptors) {
        PayloadValidatingInterceptor validatingInterceptor = new PayloadValidatingInterceptor();
        validatingInterceptor.setValidateRequest(true);
        validatingInterceptor.setValidateResponse(true);
        validatingInterceptor.setXsdSchemaCollection(this.schemaCollection());
        interceptors.add(validatingInterceptor);
        super.addInterceptors(interceptors);
    }

    @Bean
    public ServletRegistrationBean<Servlet> messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean<Servlet>(servlet, "/ws/*");
    }

    @Bean(name = "cipher") // http://localhost:8080/soap/ws/cipher.wsdl
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchemaCollection schemaCollection) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("CipherPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace(Application.NAMESPACE_URI);
        wsdl11Definition.setSchemaCollection(schemaCollection);
        return wsdl11Definition;
    }

    @Bean(name = "schemaCollection")
    public XsdSchemaCollection schemaCollection() {
        return new XsdSchemaCollection() {

            @Override
            public XsdSchema[] getXsdSchemas() {

                return Arrays.stream(resourceCollection())
                        .map(SimpleXsdSchema::new)
                        .peek(xsd -> {
                            try {
                                xsd.afterPropertiesSet();
                            } catch (ParserConfigurationException | IOException | SAXException e1) {
                                // TODO: Log error
                            }
                        })
                        .toArray(SimpleXsdSchema[]::new);
            }

            @Override
            public XmlValidator createValidator() {
                try {
                    return XmlValidatorFactory.createValidator(resourceCollection(), "http://www.w3.org/2001/XMLSchema");
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new UnsupportedOperationException();
                }

            }
        };
    }

    public Resource[] resourceCollection() {
        return new Resource[]{
                new ClassPathResource("xsd/caesar.xsd"),
                new ClassPathResource("xsd/morse.xsd"),
                new ClassPathResource("xsd/polybiusSquare.xsd")
        };
    }
}