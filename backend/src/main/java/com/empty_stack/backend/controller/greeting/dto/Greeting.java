package com.empty_stack.backend.controller.greeting.dto;

import java.util.Date;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)
public class Greeting
{
	private String message;
	
	private Date timeOnServer;
	
	private int totalAmountOfMessages;
	
	private int messageNumber;
}
