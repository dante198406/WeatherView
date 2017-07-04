package com.yaomei.util;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.yaomei.model.WeatherMdoel;

public class GoogleWeatherHandler extends DefaultHandler {

	private List<WeatherMdoel> weatherList;
	private boolean inForcast;
	private WeatherMdoel currentWeather;

	public List<WeatherMdoel> getWeatherList() {
		return weatherList;
	}

	public void setWeatherList(List<WeatherMdoel> weathers) {
		this.weatherList = weathers;
	}

	public GoogleWeatherHandler() {
		weatherList = new ArrayList<WeatherMdoel>();
		inForcast = false;
	}

	@Override
	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.startDocument();
	}

	@Override
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.endDocument();
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		String tagName = localName.length() != 0 ? localName : qName;
		tagName = tagName.toLowerCase();
		if (tagName.equals("forecast_conditions")) {
			inForcast = true;
			currentWeather = new WeatherMdoel();
		}
		if (inForcast) {
			if (tagName.equals("day_of_week")) {
				currentWeather.setWeek(attributes.getValue("data"));
			} else if (tagName.equals("low")) {
				currentWeather.setLowTemp(attributes.getValue("data"));
			} else if (tagName.equals("high")) {
				currentWeather.setHighTemp(attributes.getValue("data"));
			} else if (tagName.equals("icon")) {
				currentWeather.setImageUrl(attributes.getValue("data"));
			} else if (tagName.equals("condition")) {
				currentWeather.setConditions(attributes.getValue("data"));
			}
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		String tagName = localName.length() != 0 ? localName : qName;
		tagName = tagName.toLowerCase();
		if (tagName.equals("forecast_conditions")) {
			inForcast = false;
			weatherList.add(currentWeather);
		}

	}
}
