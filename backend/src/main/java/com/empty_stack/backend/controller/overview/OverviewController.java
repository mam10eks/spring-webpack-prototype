package com.empty_stack.backend.controller.overview;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.BasicLinkBuilder;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.empty_stack.backend.controller.chart.ChartController;
import com.empty_stack.backend.controller.greeting.GreetingController;

@Controller
public class OverviewController
{
	@RequestMapping(method=RequestMethod.GET, path = "/")
	public Object index()
	{
		Model model = new ExtendedModelMap();
		model.addAttribute("dynamicHeader", "Demo Project");
		model.addAttribute("randomChartService", linkToRandomChartService());
		model.addAttribute("randomGreetingService", linkToRandomGreetingService());
		model.addAttribute("baseLocation", linkToHomepage());
		
		return model;
	}
	
	public static Link linkToRandomChartService()
	{
		return ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(ChartController.class).randomChart()).withRel("Create Random Chart!");
	}
	
	public static Link linkToRandomGreetingService()
	{
		return ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(GreetingController.class).randomGreeting()).withRel("Create Random Greeting!");
	}
	
	public static Link linkToHomepage()
	{
		return BasicLinkBuilder.linkToCurrentMapping().slash("").withRel("Home");
	}
}
