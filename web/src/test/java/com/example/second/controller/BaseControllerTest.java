package com.example.second.controller;

import org.junit.runner.RunWith;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.example.config.SecondConfig;
import com.example.test.config.ControllerConfig;

/**
 * @author Bilal El Uneis (bilaleluneis@gmail.com)
 * @since April 2019
 */

@Configuration
@ImportResource("classpath:rootContext-test.xml")
@Import(SecondConfig.class)
class TestConfig{}

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {TestConfig.class})
public abstract class BaseControllerTest extends ControllerConfig{
	
	public BaseControllerTest(){
		super();
	}

}
