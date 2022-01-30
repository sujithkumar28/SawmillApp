package com.sawmill.test.controller;

import static org.mockito.Mockito.when;
import static org.junit.Assert.*;


import java.net.URI;

import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import com.sawmill.controller.SawmillController;

import com.sawmill.model.Sawmill;
import com.sawmill.service.SawmillService;


@RunWith(SpringRunner.class)
@WebMvcTest(value=SawmillController.class)
public class SawmillControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private SawmillService sawmillService;
	
	Sawmill mockSawmill = new Sawmill("testname",null,null,null,null);
	
	
	
	@Test
	public void listSawmillTest() throws Exception{
		
		mockSawmill.setId(1);
		when(sawmillService.findAll(Sort.by(Sort.Direction.ASC, "name"))).thenReturn(Stream.
				of(mockSawmill).collect(Collectors.toList()));
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/sawmill/listAllSawmill")
											.accept(MediaType.APPLICATION_JSON);
		
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		String expectedSawmill = "[{\"id\":1,\"name\": \"testname\"}]";
		JSONAssert.assertEquals(expectedSawmill, mvcResult.getResponse().getContentAsString(), false);
		
		
	}
	
	@Test
	public void createSawmillTest() throws Exception{
		
		String newSawMill = "{\"name\": \"testname\"}";
		
		when(sawmillService.save(Mockito.any())).thenReturn(mockSawmill);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/sawmill/createSawmill")
				.accept(MediaType.APPLICATION_JSON).content(newSawMill)
				.contentType(MediaType.APPLICATION_JSON);
		

		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		
		MockHttpServletResponse response = mvcResult.getResponse();
		
		
		assertEquals(HttpStatus.CREATED.value(),response.getStatus());
	}
	
	@Test
	public void updateSawmillTest() throws Exception{
		
		String newSawMill = "{\"name\": \"testname1\"}";
		mockSawmill.setName("testname1");
		
		when(sawmillService.findById(Mockito.any())).thenReturn(Optional.of(mockSawmill));
		
		when(sawmillService.save(Mockito.any())).thenReturn(mockSawmill);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/sawmill/updateSawmill/1")
				.accept(MediaType.APPLICATION_JSON).content(newSawMill)
				.contentType(MediaType.APPLICATION_JSON);
		

		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		
		MockHttpServletResponse response = mvcResult.getResponse();
		
		
		JSONAssert.assertEquals(newSawMill, mvcResult.getResponse().getContentAsString(), false);
		
	}
	
	@Test
	public void deleteSawmillTest() throws Exception{
		
		
		when(sawmillService.findById(0)).thenReturn(Optional.empty());
		
		sawmillService.delete(mockSawmill); 
		
		Mockito.verify(sawmillService).delete(mockSawmill);
		
	}

}
