package com.davsan.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.davsan.controller.MainController;
import com.davsan.dto.Kullanici;

@Configuration
public class DavsanConfig 
{
	@Bean(name = "davsanUser")
	public Kullanici create()
	{
		return new Kullanici("davsan", "123", "dapdavsan", "özdavşan", "1235", "davsan adresi", "12345678912");
	}
	
	@Bean(name = "davsanUser2")
	public Kullanici create1()
	{
		return new Kullanici("davsan2", "123", "dapdavsan", "özdavşan", "1235", "davsan adresi", "12345678912");
	}
	
	@Bean(name = "mainContorller")
	public MainController controller()
	{
		return MainController.getInstance();
	}
}
