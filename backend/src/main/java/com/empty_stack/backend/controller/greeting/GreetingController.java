package com.empty_stack.backend.controller.greeting;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.empty_stack.backend.controller.greeting.dto.Greeting;

@Controller
public class GreetingController
{
	private static List<String> GREETINGS = Collections.unmodifiableList(Arrays.asList(
		"Hello World", "Hello User", "I hope you like this prototype",
		"Have you tried this prototype with and without javascript?",
		"You could reach this \"valuable\" comments by leveraging the modal and by using the section"));
	
	private static final Random RAND = new Random();
	
	@RequestMapping(method=RequestMethod.GET, path = "/greeting")
	public Object randomGreeting()
	{
		int greetingNumber = RAND.nextInt(GREETINGS.size());
		
		return new Greeting()
				.setMessage(GREETINGS.get(greetingNumber))
				.setMessageNumber(greetingNumber+1)
				.setTimeOnServer(new Date())
				.setTotalAmountOfMessages(GREETINGS.size());
	}
}
