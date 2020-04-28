package com.jmtp.jabakardex;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public WebServerFactoryCustomizer<TomcatServletWebServerFactory> servletContainer() {
		return server -> {
			if (server instanceof TomcatServletWebServerFactory) {
				((TomcatServletWebServerFactory) server).addAdditionalTomcatConnectors(redirectConnector());
			}
		};
	}

	private Connector redirectConnector() {
		Connector connector = new Connector("AJP/1.3");
		connector.setScheme("http");
		connector.setPort(9090);
		connector.setSecure(false);
		connector.setAllowTrace(false);
		return connector;
	}

	

	
}
