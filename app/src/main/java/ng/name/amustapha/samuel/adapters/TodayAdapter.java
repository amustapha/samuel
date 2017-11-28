package ng.name.amustapha.samuel.adapters;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ng.name.amustapha.samuel.R;
import ng.name.amustapha.samuel.databases.Schedule;
import ng.name.amustapha.samuel.fragments.DetailsFragment;

/**
 * Created by amustapha on 9/22/17.
 */

public class TodayAdapter extends RecyclerView.Adapter<TodayAdapter.ViewHolder>{
    List<Schedule> dataList;
    FragmentManager fm;
    public TodayAdapter(FragmentManager fm, List<Schedule> dataList){
        this.fm = fm;
        this.dataList = dataList;
//        dataList = Schedule.find(Schedule.class, null, null);
//        dataList = Lists.newArrayList(Schedule.find(Schedule.class, String.format(" DAY_OF_WEEK = ? AND TIME_OF_DAY %s ? ",  check), dow, hod));
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.schedule_row, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Schedule object = dataList.get(position);
        holder.inflate(object);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView schedule_time;
        TextView schedule_title;

        public ViewHolder(View view) {
            super(view);
            schedule_time = itemView.findViewById(R.id.schedule_time);
            schedule_title = itemView.findViewById(R.id.schedule_title);
        }

        public void inflate(final Schedule sch){
            schedule_time.setText(sch.startHour + "");
            schedule_title.setText(sch.title);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DetailsFragment frag = new DetailsFragment();
                    Bundle arg = new Bundle();
                    arg.putLong("id", sch.getId());
                    frag.setArguments(arg);
                    frag.show(fm, "aa");

                }
            });
        }
    }
}
