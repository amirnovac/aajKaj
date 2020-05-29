package com.amir.serviceman.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amir.serviceman.R;
import com.amir.serviceman.interfaces.OnAdapterItemClick;

public class JobListForContractor extends RecyclerView.Adapter<JobListForContractor.JobListHolder> {


    private Context context;
    private OnAdapterItemClick click;

    public JobListForContractor(Context context, OnAdapterItemClick click) {
        this.context = context;
        this.click = click;
    }

    @NonNull
    @Override
    public JobListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.project_list_adapter,parent,false);
        return new JobListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final JobListHolder holder, final int position) {
         
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.onClick(position,true);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 8;
    }

    class JobListHolder extends RecyclerView.ViewHolder {
        private ImageView imgProject;
        private RelativeLayout layout;
        private TextView tvAddress;
        private Button btnApply;
        private TextView tvProjectName;
        public JobListHolder(@NonNull View itemView) {
            super(itemView);
            imgProject = itemView.findViewById(R.id.projectImg);
            tvProjectName = itemView.findViewById(R.id.tvRvProjectName);
            btnApply = itemView.findViewById(R.id.btnApply);
            tvAddress = itemView.findViewById(R.id.tvRvLocation);
            layout = itemView.findViewById(R.id.layout_rv_list);

        }
    }
}
