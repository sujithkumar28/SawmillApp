package com.sawmill.test.controller;



import org.junit.Test;

import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.MediaType;

import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.sawmill.SawmillApplication;



@RunWith(SpringRunner.class)
@SpringBootTest(classes=SawmillApplication.class, webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource(locations= "classpath:application-test.properties")
@ActiveProfiles("test")
public class SawmillControllerIntegrationTest {
	
	
	@Autowired
	private MockMvc mockMvc;
	
	
	@Test
	public void listSawmillTest() throws Exception{
		
		
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/sawmill")
											.accept(MediaType.APPLICATION_JSON);
		
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(mvcResult.getResponse().getContentAsString());
		
	}
	
	@Test   
	public void updateSawmillTest() throws Exception{
		
		String newSawmill = "{\"name\": \"testname\",\"city\":\"berlin\"}";
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/sawmill/1")
				.accept(MediaType.APPLICATION_JSON).content(newSawmill)
				.contentType(MediaType.APPLICATION_JSON);
		

		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		
		MockHttpServletResponse response = mvcResult.getResponse();
		
		System.out.println(response.getContentAsString());
		
	}
	
	@Test   
	public void createSawmillTest() throws Exception{
		
		String newSawmill = "{\"name\": \"testname\"}";
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/sawmill")
				.accept(MediaType.APPLICATION_JSON).content(newSawmill)
				.contentType(MediaType.APPLICATION_JSON);
		

		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		
		MockHttpServletResponse response = mvcResult.getResponse();
		
		System.out.println(response.getContentAsString());
		
	}
	
	@Test   
	public void deleteSawmillTest() throws Exception{
		
		
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/sawmill/1")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON);
		

		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		
		MockHttpServletResponse response = mvcResult.getResponse();
		
		System.out.println(response.getContentAsString());
		
	}

}
