package com.yaomei.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.terry.weather.R;
import com.yaomei.model.WeatherMdoel;
import com.yaomei.util.strHelpeUtil;

public class weatherAdapter extends BaseAdapter {

	private Context context;
	private LayoutInflater inflater;
	private List<WeatherMdoel> weatherModel;

	public weatherAdapter(Context weatherContext, List<WeatherMdoel> model) {

		this.context = weatherContext;
		this.weatherModel = model;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 3;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return weatherModel.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		inflater = LayoutInflater.from(context);
		viewHolder holder;
		if (convertView == null) {
			holder = new viewHolder();
			convertView = inflater.inflate(R.layout.wether_row_layout, null);
			holder.iv_other_weather = (ImageView) convertView
					.findViewById(R.id.iv_other_weather);
			holder.tv_ohter_temperature = (TextView) convertView
					.findViewById(R.id.tv_ohter_temperature);
			holder.tv_ohter_week = (TextView) convertView
					.findViewById(R.id.tv_ohter_week);
			convertView.setTag(holder);
		} else {
			holder = (viewHolder) convertView.getTag();
		}
		WeatherMdoel model = (WeatherMdoel) getItem(position + 1);
		holder.iv_other_weather
				.setImageDrawable(model.getImageUrl() == "" ? model
						.getImageDrawable() : strHelpeUtil.loadImage(model
						.getImageUrl()));
		holder.tv_ohter_temperature.setText(context.getResources().getString(
				R.string.temp_format, model.getLowTemp(), model.getHighTemp()));
		holder.tv_ohter_week.setText(model.getWeek());

		return convertView;
	}

	public class viewHolder {
		private TextView tv_ohter_week, tv_ohter_temperature;
		private ImageView iv_other_weather;
	}

}
