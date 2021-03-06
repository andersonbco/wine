package com.algaworks.wine.config;

import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.support.DomainClassConverter;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer(){
		return (container ->    //Lambda que está sendo utilizado para a implementação de uma interface
			container.addErrorPages( new ErrorPage(HttpStatus.NOT_FOUND, "/404"), //O direcionamento da página "/404" foi definido no ErrorController
									 new ErrorPage(HttpStatus.FORBIDDEN, "/403")) 
		);
	}
	
	public DomainClassConverter<FormattingConversionService> domainClassConverter( FormattingConversionService conversionService ){
		return new DomainClassConverter<FormattingConversionService>(conversionService);
	}
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		
		registry.addRedirectViewController("/", "/vinhos/novo");
	}
}
