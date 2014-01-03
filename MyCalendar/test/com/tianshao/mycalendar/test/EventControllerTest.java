package com.tianshao.mycalendar.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.tianshao.mycalendar.model.Event;
import com.tianshao.mycalendar.services.EventService;
import com.tianshao.mycalendar.test.config.WebAppContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;  
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;  
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;  
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*; 
import static org.springframework.test.web.servlet.result.ModelResultMatchers.*;


@RunWith(SpringJUnit4ClassRunner.class)

@WebAppConfiguration
@ContextConfiguration(classes=WebAppContext.class)
public class EventControllerTest {
	
	@Autowired
	private WebApplicationContext wac;
	
	MockMvc mockMvc;
	@Before
	public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build(); 

	}
	
	@Test
	public void testCRUD() throws Exception{
		addTest();
		getTest();
		putTest();
		delTest();
	}
	
	public void addTest() throws Exception {
	   ResultActions a=mockMvc.perform(post("/event").contentType(MediaType.APPLICATION_JSON)
												   .accept(MediaType.APPLICATION_JSON)
												   .content("{\"id\":\"0\",\"title\":\"newbe\",\"loc\":\"newbe\",\"detail\":\"newbe\"}"));;
	   a=mockMvc.perform(get("/event"));
	   //TODO At this point, no get(id) api is defined yet, so 
/*	    try {
	    
	    	a.andExpect(jsonPath("$[0].title").value("asd"))
	    	 .andExpect(jsonPath("$[0].loc").value("asd"))
	    	 .andExpect(jsonPath("$[0].detail").value("asd"));
		} catch (Exception e) {
			
			e.printStackTrace();
		}*/
	}

	public void getTest() throws Exception {
        
        ResultActions a=mockMvc.perform(get("/event"));
        try {
			a.andExpect(jsonPath("$[0].id").value(0))
			 .andExpect(jsonPath("$[0].detail").value("detail1"))
			 .andExpect(jsonPath("$[0].loc").value("loc1"))
			 .andExpect(jsonPath("$[0].title").value("title1"))
			 .andExpect(jsonPath("$[1].id").value(1))
			 .andExpect(jsonPath("$[1].detail").value("detail2"))
			 .andExpect(jsonPath("$[1].loc").value("loc2"))
			 .andExpect(jsonPath("$[1].title").value("title2"));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	


	
	public void putTest() throws Exception {
        
        ResultActions a=mockMvc.perform(put("/event/0").contentType(MediaType.APPLICATION_JSON)
				 									   .accept(MediaType.APPLICATION_JSON)
				 									   .content("{\"id\":\"0\",\"title\":\"asd\",\"loc\":\"asd\",\"detail\":\"asd\"}"));
        a=mockMvc.perform(get("/event"));
        try {
        	a.andExpect(jsonPath("$[0].title").value("asd"))
        	 .andExpect(jsonPath("$[0].loc").value("asd"))
        	 .andExpect(jsonPath("$[0].detail").value("asd"));
		} catch (Exception e) {
			
			e.printStackTrace();
		}

	}
	
	public void delTest() throws Exception {
        
        ResultActions a=mockMvc.perform(delete("/event/0"));
        a=mockMvc.perform(get("/event"));
        try {
        	a.andExpect(jsonPath("$[0].id").value(1));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
