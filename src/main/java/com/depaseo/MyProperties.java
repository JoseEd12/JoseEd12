package com.depaseo;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@ConfigurationProperties("environments")
@Data
public  class MyProperties {
	public  String url;
	public String api;
	public String scriptmail;

	
	
}
