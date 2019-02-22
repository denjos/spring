package pe.com.denjos.mockitotest.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.com.denjos.mockitotest.service.HelloService;



@RestController
@RequestMapping("/hello")
public class HelloController {
	
	
	HelloService helloService;
	
	public HelloController(HelloService helloService) {
		this.helloService=helloService;
	}

	@GetMapping
	public String helloWorld()
	{
		return helloService.hello();
	}
	
	@GetMapping(value="/json",produces=MediaType.APPLICATION_JSON_VALUE)
	public Parameter json()
	{
		return new Parameter("name","oscar");
	}

	@PostMapping(value="/post",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public Parameter post(@RequestBody Parameter parameter)
	{
		return parameter;
	}
	
	private static class Parameter{
		private String key;
		private String value;
		
		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		public Parameter(String key, String value) {
			super();
			this.key = key;
			this.value = value;
		}
		
		public Parameter() {
		}
		
	}

}
