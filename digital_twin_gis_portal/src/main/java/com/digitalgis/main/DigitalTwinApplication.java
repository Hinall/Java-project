package com.digitalgis.main;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.digitalgis.utils.LoggerUtil;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.digitalgis")
@PropertySource("classpath:application.properties")
public class DigitalTwinApplication extends SpringBootServletInitializer {

	@Autowired
	private Environment env;

	public static void main(String[] args) {
		SpringApplication.run(DigitalTwinApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(DigitalTwinApplication.class);
	}

	@Bean(name = "postgresDB")
	@Primary
	@ConfigurationProperties(prefix = "spring.postgres")
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		try {
			dataSource.setDriverClassName(env.getProperty("spring.postgres.datasource.driver-class-name"));
			dataSource.setUrl(env.getProperty("spring.postgres.datasource.url"));
			dataSource.setUsername(env.getProperty("spring.postgres.datasource.username"));
			dataSource.setPassword(env.getProperty("spring.postgres.datasource.password"));
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtil.setError(this.getClass(), "Error :::==>" + e.getMessage());
		}

		return dataSource;
	}

}
