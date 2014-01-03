package com.tianshao.mycalendar.ws;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.inject.Qualifier;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.tianshao.mycalendar.model.Event;
import com.tianshao.mycalendar.services.EventService;

@Controller
@RequestMapping("/event")
public class EventController {
	@Autowired
	private EventService eventService;
	
	public EventController(){
		
	}
	public EventService getEventService() {
		return eventService;
	}

	public void setEventService(EventService eventService) {
		this.eventService = eventService;
	}

	@RequestMapping( method = RequestMethod.GET, headers={ "Accept=application/json" },produces="application/json")
	public @ResponseBody List<Event> getevents() {

        return  eventService.getEvents();
    }
	
	@RequestMapping(value="{id}",method = RequestMethod.PUT,headers="Accept=*/*",produces="application/json")
	public @ResponseBody Event putevents(@RequestBody Event e,@PathVariable long id, HttpServletResponse resp) throws IOException {
		boolean fail=false;
		
		try{
			this.eventService.update(id, e);
		}catch(Exception ex)
		{
			fail=true;
		}
		if(fail)
			resp.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE, "error occurred, please contact your system administrator");
		return e;
	}
	
	
	@RequestMapping(value="{id}",method = RequestMethod.DELETE,headers="Accept=*/*")
	public @ResponseBody Event delevents(@PathVariable long id, HttpServletResponse resp) throws IOException {
		boolean fail=false;
		Event ret=null;
		try{
			ret=this.eventService.removeEvent(id);
		}catch(Exception ex)
		{
			fail=true;
		}
		if(fail)
			resp.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE, "error occurred, please contact your system administrator");
		return ret;
	}
	
	@RequestMapping(method = RequestMethod.POST,headers="Accept=*/*")
	public @ResponseBody Event createEvent(@RequestBody Event e,HttpServletResponse resp) throws IOException {
		boolean fail=false;
		Event ret=null;
		try{
			long id= this.eventService.addEvent(e);
			e.id=id;
			return e;
		}catch(Exception ex)
		{
			fail=true;
		}
		if(fail)
			resp.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE, "error occurred, please contact your system administrator");
		return ret;
	}
	
	@ExceptionHandler
	@ResponseBody
	void handleException(MethodArgumentNotValidException ex) {
	   System.out.print(ex.getMessage());
	}
}
