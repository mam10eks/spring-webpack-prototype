package com.empty_stack.backend.overview;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.empty_stack.backend.chart.ChartController;


@Controller
public class OverviewController
{
	@RequestMapping(method=RequestMethod.GET, path = "/")
	public ModelAndView index()
	{
		Model model = new ExtendedModelMap();
		model.addAttribute("dynamicHeader", "Demo Project");
		model.addAttribute("randomChartService", linkToRandomChartService());
		
		return new ModelAndView("index", model.asMap());
	}
	
	private static Link linkToRandomChartService()
	{
		return ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(ChartController.class).randomChart()).withRel("randomChart");
	}
}
