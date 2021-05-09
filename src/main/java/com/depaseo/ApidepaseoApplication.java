package com.depaseo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableConfigurationProperties(MyProperties.class)
public class ApidepaseoApplication implements CommandLineRunner{

	public static ApplicationContext ctx;
	
	@Autowired BCryptPasswordEncoder passwordEncoder;
	
	public static void main(String[] args) {
		SpringApplication.run(ApidepaseoApplication.class, args);
	}
	 @Autowired
	    private void setApplicationContext(ApplicationContext applicationContext) {
	        ctx = applicationContext;       
	    }
	 
	 @Bean
	 ApplicationRunner applicationRunner(MyProperties myProperties) {
		 return args ->{
			System.out.println(myProperties); 
		 };
	 }
	 
	 
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		String password = "admin1234";
		String bcryotPassword = passwordEncoder.encode(password);
		System.out.println(bcryotPassword);
	}
	 
	 
}
