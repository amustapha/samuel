package ng.name.amustapha.samuel.utils;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import ng.name.amustapha.samuel.R;

public class CustomListAdapter extends ArrayAdapter<String> {
 
	private final Activity context;
	private final ArrayList<Contact> dataList;
	public CustomListAdapter(Activity context, ArrayList<Contact> dataList) {
		super(context, R.layout.contact_row);
		// TODO Auto-generated constructor stub

		this.dataList = dataList;
		this.context=context;
	}

	@Override
	public int getCount() {
		return dataList.size();
	}

	public View getView(int position, View view, ViewGroup parent) {
		LayoutInflater inflater=context.getLayoutInflater();
		View rowView=inflater.inflate(R.layout.contact_row, null,true);
		
		TextView txtTitle = (TextView) rowView.findViewById(R.id.username);
		ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);

		txtTitle.setText(dataList.get(position).getTitle());
		imageView.setImageResource(dataList.get(position).getIcon());
		return rowView;
		
	};
}