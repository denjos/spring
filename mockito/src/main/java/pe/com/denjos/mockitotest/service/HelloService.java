package pe.com.denjos.mockitotest.service;

import org.springframework.stereotype.Component;

@Component
public class HelloService {
	
	public String hello()
	{
		return "hello world";
	}

}
