package com.empty_stack.backend.chart;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.empty_stack.backend.chart.dto.ChartData;
import com.empty_stack.backend.chart.dto.ChartDataset;

import lombok.Data;
import lombok.SneakyThrows;
import lombok.experimental.Accessors;

@Controller
public class ChartController
{
	private static final List<MeasuringPoint> MEASURING_POINTS = Collections.unmodifiableList(Arrays.asList(
		new MeasuringPoint().setBackgroundColor("rgba(255, 99, 132, 0.2)").setBorderColor("rgba(255,99,132,1)").setDisplayName("Red"),
		new MeasuringPoint().setBackgroundColor("rgba(54, 162, 235, 0.2)").setBorderColor("rgba(54, 162, 235, 1)").setDisplayName("Blue"),
		new MeasuringPoint().setBackgroundColor("rgba(255, 206, 86, 0.2)").setBorderColor("rgba(255, 206, 86, 1)").setDisplayName("Yellow"),
		new MeasuringPoint().setBackgroundColor("rgba(75, 192, 192, 0.2)").setBorderColor("rgba(75, 192, 192, 1)").setDisplayName("Green"),
		new MeasuringPoint().setBackgroundColor("rgba(153, 102, 255, 0.2)").setBorderColor("rgba(153, 102, 255, 1)").setDisplayName("Purple"),
		new MeasuringPoint().setBackgroundColor("rgba(255, 159, 64, 0.2)").setBorderColor("rgba(255, 159, 64, 1)").setDisplayName("Orange")));
	
	
	private static final int BORDER_WIDTH = 1;
	
	private static final String LABEL = "# of Votes";
	
	@RequestMapping(method=RequestMethod.GET, path="/chart")
	public @ResponseBody ChartData randomChart()
	{
		List<MeasuringPoint> measuringPointsToReturn = measuringPointsInRandomOrder();
		
		List<Integer> data = IntStream.range(0, measuringPointsToReturn.size())
			.mapToObj(i -> (int) (Math.random() *25))
			.collect(Collectors.toList());
		
		List<String> labels = measuringPointsToReturn.stream()
			.map(MeasuringPoint::getDisplayName)
			.collect(Collectors.toList());
		
		List<String> backgroundColors = measuringPointsToReturn.stream()
			.map(MeasuringPoint::getBackgroundColor)
			.collect(Collectors.toList());
		
		List<String> borderColors = measuringPointsToReturn.stream()
				.map(MeasuringPoint::getBorderColor)
				.collect(Collectors.toList());
		
		ChartDataset dataSet = new ChartDataset()
			.setLabel(LABEL)
			.setData(data)
			.setBackgroundColor(backgroundColors)
			.setBorderColor(borderColors)
			.setBorderWidth(BORDER_WIDTH);
		
		return new ChartData()
			.setLabels(labels)
			.setDatasets(Arrays.asList(dataSet));
	}
	
	private static List<MeasuringPoint> measuringPointsInRandomOrder()
	{
		List<MeasuringPoint> ret = new ArrayList<>(MEASURING_POINTS);
		Collections.shuffle(ret);
		
		return ret;
	}

	@Data
	@Accessors(chain=true)
	private static class MeasuringPoint
	{
		private String backgroundColor;
		
		private String borderColor;
		
		private String displayName;
	}
}
