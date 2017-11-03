package com.empty_stack.backend.controller.chart.dto;

import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)
public class ChartData
{
	private List<String> labels;
	
	private List<ChartDataset> datasets;
}
