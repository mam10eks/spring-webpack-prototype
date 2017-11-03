package com.empty_stack.backend.controller.chart.dto;

import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)
public class ChartDataset
{
	private String label;
	
	private List<Integer> data;
	
	private List<String> backgroundColor;
	
	private List<String> borderColor;
	
	private Integer borderWidth;
}
