// bootstrap
import "jquery";
import "bootstrap/dist/js/bootstrap.min.js";
import "bootstrap/dist/css/bootstrap.min.css";

// charts
import Chart from "chart.js";

import Bootbox from "bootbox";

// nice ajax api with promises
import Ajax from "ajax-promise-es6";

//nprogress like youtube/github
import NProgress from "nprogress";
import "nprogress/nprogress.css";

//custom stylesheet
import "./style.css";


const BAR_CHART_OPTIONS = {
	scales: {
		yAxes: [{
			ticks: {
				beginAtZero:true
			}
		}]
	}
}

function showChart(data) {
	Bootbox.dialog({
		title: "Random Bar Chart",
		message: "<div style='width: auto;'><canvas id='chartCanvas'></canvas></div>",
		buttons: {
			cancel: {
				label: "Close",
				className: "btn-info"
			}
		}});

	renderChartIntoCanvas("chartCanvas", data);
}

function renderChartIntoCanvas(canvasId, data) {
	new Chart(document.getElementById(canvasId).getContext('2d'), {
		type: "bar",
		data: data,
		options: BAR_CHART_OPTIONS
	});
}

function loadChart(url) {
	NProgress.start();

	Ajax.get(url, {}, {Accept: "application/json"})
		.then(JSON.parse)
		.then(r => {NProgress.done(); return r;})
		.then(showChart)
		.catch(e => console.log(e));
}

module.exports = {
	loadChart: loadChart,
	renderChartIntoCanvas : renderChartIntoCanvas};
