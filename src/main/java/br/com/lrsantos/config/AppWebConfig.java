package br.com.lrsantos.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages={"br.com.lrsantos.model","br.com.lrsantos.config","br.com.lrsantos.controller"})
public class AppWebConfig extends WebMvcConfigurerAdapter {

	private HandlerInterceptor interceptor;

	@Autowired
	public AppWebConfig(HandlerInterceptor interceptor) {
		this.interceptor = interceptor;
	}

	public AppWebConfig() {
		super();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new ChamadoLogInterceptor());
		super.addInterceptors(registry);
	}
	
}
