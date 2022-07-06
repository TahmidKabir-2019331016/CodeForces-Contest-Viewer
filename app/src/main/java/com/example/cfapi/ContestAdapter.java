package com.example.cfapi;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cfapi.Model.Contest;

import java.util.List;

public class ContestAdapter extends RecyclerView.Adapter<ContestAdapter.ContestViewHolder> {

    private List<Contest> contestList;

    public void setContestList(List<Contest> contestList) {
        this.contestList = contestList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ContestViewHolder onCreateViewHolder(@androidx.annotation.NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.contest_item, viewGroup, false);
        return new ContestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContestViewHolder contestViewHolder, int i) {
        contestViewHolder.bind(i);
    }

    @Override
    public int getItemCount() {
        if (contestList != null) return contestList.size();
        return 0;
    }

    class ContestViewHolder extends RecyclerView.ViewHolder {
        public final TextView name, phase, duration, startTime;

        public ContestViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_name);
            phase = itemView.findViewById(R.id.tv_phase);
            duration = itemView.findViewById(R.id.tv_duration);
            startTime = itemView.findViewById(R.id.tv_start_time);
        }

        public void bind(int i) {
            Log.v("binding", "here");
            Contest contest = contestList.get(i);
            name.setText(contest.getName());
            if (!contest.isUpcoming())
                phase.setText("Finished");
            else {
                phase.setText("Upcoming");
                startTime.setVisibility(View.VISIBLE);
                startTime.setText("Start Time: " + contest.getStartTime());
            }
            duration.setText("Duration: " + contest.getDuration());
        }
    }

}
