package com.mm.zhice;

import com.mm.zhice.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Sprng Boot项目入口函数
 * 
 * @author zm
 *
 */
@SpringBootApplication
@EnableScheduling
@EnableJpaRepositories(basePackages = "com.mm.zhice.dao", repositoryFactoryBeanClass = BaseDaoFactoryBean.class)
public class App extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(App.class);
	}

	public static void main(String[] args) throws Exception {
		ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
		context.getBean(UserService.class);
	}

}
