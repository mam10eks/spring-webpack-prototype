package com.empty_stack.backend.aspect;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.empty_stack.backend.controller.overview.OverviewController;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Aspect
@Component
public class RenderContentInMainWindowAspect
{
	private static final List<String> JSON_TYPES = Collections.unmodifiableList(jsonContentTypeNames());
	
	private static final MappingJackson2JsonView MAPPING_TO_JACKSON_VIEW = new MappingJackson2JsonView();
	
	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
	
	private static Map<String, Object> proceedJointPointAndParseReturnValueAsModel(ProceedingJoinPoint proceedingJointPoint) throws Throwable
	{
		return OBJECT_MAPPER.convertValue(proceedingJointPoint.proceed(), new TypeReference<Map<String, Object>>() {});
	}
	
	private static List<String> jsonContentTypeNames()
	{
		return Arrays.asList(MediaType.APPLICATION_JSON_UTF8, MediaType.APPLICATION_JSON).stream()
			.map(Object::toString)
			.collect(Collectors.toList());
	}
	
	@Around("@annotation(org.springframework.web.bind.annotation.RequestMapping) && execution(* *(..))")
	public Object renderModelIntoMainWindow(ProceedingJoinPoint thisJointPoint) throws Throwable
	{
		Map<String, Object> returnModel = proceedJointPointAndParseReturnValueAsModel(thisJointPoint);
		
		String acceptHeader = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest()
				.getHeader(HttpHeaders.ACCEPT);
		
		if(returnModel == null)
		{
			returnModel = new HashMap<>();
		}
		
		if(acceptHeader != null && JSON_TYPES.contains(acceptHeader))
		{
			return new ModelAndView(MAPPING_TO_JACKSON_VIEW, returnModel);
		}

		returnModel.put("menuLinks", Arrays.asList(OverviewController.linkToHomepage(), OverviewController.linkToRandomChartService(),
				OverviewController.linkToRandomGreetingService()));
		returnModel.put("view", thisJointPoint.getSignature().getName());
		returnModel.put("baseLocation", OverviewController.linkToHomepage());
		
		return new ModelAndView("menu", returnModel);
	}
}
