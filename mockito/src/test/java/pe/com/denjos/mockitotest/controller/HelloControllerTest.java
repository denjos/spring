package pe.com.denjos.mockitotest.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import pe.com.denjos.mockitotest.service.HelloService;


@RunWith(SpringJUnit4ClassRunner.class)
public class HelloControllerTest {
	
	private MockMvc mockMVC;

	@Mock
	private HelloService helloService;
	
	@InjectMocks
	private HelloController helloController;
	
	
	@Before
	public void setUp() throws Exception{
		mockMVC=MockMvcBuilders.standaloneSetup(helloController).build();
	}
	@Test
	public void testHelloWorld() throws Exception
	{
		Mockito.when(helloService.hello()).thenReturn("hello");
		
		mockMVC.perform(MockMvcRequestBuilders.get("/hello"))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.content().string("hello"));
		
		Mockito.verify(helloService).hello();
	}
	
	@Test
	public void testHelloWorld2() throws Exception
	{
		Mockito.when(helloService.hello()).thenReturn("hello");
		mockMVC.perform(get("/hello"))
		.andExpect(status().isOk())
		.andExpect(content().string("hello"));
	}

	@Test
	public void testHelloJson() throws Exception
	{
		mockMVC.perform(get("/hello/json").accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.key", Matchers.is("name")))
		.andExpect(MockMvcResultMatchers.jsonPath("$.value",Matchers.is("oscar")))
		.andExpect(MockMvcResultMatchers.jsonPath("$.*",Matchers.hasSize(2)));
	}
	
	@Test
	public void testPost() throws Exception
	{
		String json="{\r\n" + 
				"    \"key\":\"alumno\",\r\n" + 
				"    \"value\":\"oscar\"\r\n" + 
				"}";
		mockMVC.perform(MockMvcRequestBuilders.post("/hello/post").contentType(MediaType.APPLICATION_JSON).content(json))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.key", Matchers.is("alumno")))
		.andExpect(MockMvcResultMatchers.jsonPath("$.value",Matchers.is("oscar")))
		.andExpect(MockMvcResultMatchers.jsonPath("$.*", Matchers.hasSize(2)));
		
		
	}
}
