var packageJSON = require("./package.json");
var path = require("path");
var webpack = require("webpack");
const BUILD_PATH = path.join(__dirname, 'target', 'classes', 'META-INF', 'resources', 'webjars', packageJSON.name, packageJSON.version);

module.exports = {
	entry: "./app/index.js",
	output: {
		path: BUILD_PATH,
		libraryTarget: "var",
		library: "Application",
		filename: "app-bundle.js"
	},
	module: {
		rules: [ {
			test: /\.js$/,
			include: [
				path.resolve(__dirname, "app"),
				path.resolve(__dirname, "node_modules/ajax-promise-es6")],

			use: {
				loader: "babel-loader",
				options: {
					plugins: ["transform-runtime"],
					presets: ["env"]
				}
			}
		}, {
			test: /\.css$/,
			use: [{
					loader: "style-loader"
				}, {
					loader: "css-loader"
					}]
		}, {
			test: require.resolve("jquery"),
			use: [{
					loader: "expose-loader",
					options: "jQuery"}, {
					loader: "expose-loader",
					options: "$"
				}]
		}, {
			test: /\.eot(\?v=\d+\.\d+\.\d+)?$/,
			use: [{loader: "file-loader"}]
		}, {
			test: /\.(woff|woff2)$/,
			use: [{loader: "url-loader?prefix=font/&limit=5000"}]
		}, {
			test: /\.ttf(\?v=\d+\.\d+\.\d+)?$/,
			use: [{loader: "url-loader?limit=10000&mimetype=application/octet-stream" }]
		}, {
			test: /\.svg(\?v=\d+\.\d+\.\d+)?$/,
			use: [{loader: "url-loader?limit=10000&mimetype=image/svg+xml"}]
		}]
	}
}
