package com.parkway.medical.conf;


import java.io.IOException;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;

@Component
public class FreemarkerConfig {

	@Bean
	@Qualifier(value = "freemarker")
	public Configuration getFreeMarkerConfiguration() throws IOException, TemplateException {
		FreeMarkerConfigurationFactoryBean bean = new FreeMarkerConfigurationFactoryBean();
		bean.setTemplateLoaderPath("/templates/");
		return bean.createConfiguration();
	}
}
