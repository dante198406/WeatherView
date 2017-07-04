package com.yaomei.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.terry.weather.R;
import com.yaomei.model.WeatherMdoel;

public class strHelpeUtil {

	Context context;

	public strHelpeUtil(Context context) {
		this.context = context;
	}

	/**
	 * 加载天气图片
	 * 
	 * @param url
	 * @return
	 */
	public static Drawable loadImage(String url) {

		try {

			return Drawable.createFromStream((InputStream) new URL(
					"http://www.google.com/" + url).getContent(), "drawable");

		} catch (MalformedURLException e) {

			Log.e("exception", e.getMessage());
		} catch (IOException e) {

			Log.e("exception", e.getMessage());
		}

		return null;
	}

	/**
	 * 查询天气
	 * 
	 * @param city
	 * @return
	 */
	public static List<WeatherMdoel> searchWeather(String google, String city) {
		SAXParserFactory sapFactory = SAXParserFactory.newInstance();
		List<WeatherMdoel> weatherList = null;
		try {
			SAXParser sp = sapFactory.newSAXParser();
			XMLReader reader = sp.getXMLReader();
			GoogleWeatherHandler handler = new GoogleWeatherHandler();
			reader.setContentHandler(handler);
			URL url = new URL(google + URLEncoder.encode(city));
			InputStream is = url.openStream();
			InputStreamReader isr = new InputStreamReader(is, "GBK");
			InputSource source = new InputSource(isr);
			reader.parse(source);
			weatherList = handler.getWeatherList();

			is.close();
			isr.close();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return weatherList;
	}

	// 获得当前星期
	public static String getWeekOfDate(Date dt) {
		String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 0;
		return weekDays[w];
	}

	@Override
	public String toString() {
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DAY_OF_MONTH);

		return context.getResources().getString(R.string.date_str, year,
				month + 1, day);
	}
}
