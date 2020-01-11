package com.saucedo.courses;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.MyViewHolder> {
    private List<Course> myData;
    private ItemClickListener parentLayout;

    public MyListAdapter(List<Course> myData, ItemClickListener parentLayout) {
        this.myData = myData;
        this.parentLayout = parentLayout;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_recycleview, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Course course = myData.get(position);
        holder.itemTitle.setText(course.getTitle());
        holder.itemCategory.setText(course.getCategoria());
        holder.itemDate.setText(course.getDate());
        holder.itemDuration.setText(course.getDuration());
        holder.alerMessage.setText(course.getAlerMessage());
    }

    @Override
    public int getItemCount() {
        return myData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView itemTitle;
        private TextView itemCategory;
        private TextView itemDate;
        private TextView itemDuration;
        private TextView alerMessage;
        private LinearLayout itemAlertButton;
        private LinearLayout itemResumeButton;

        private LinearLayout headerButton;
        private ConstraintLayout collapsingLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemTitle = itemView.findViewById(R.id.item_titulo);
            this.itemCategory = itemView.findViewById(R.id.item_categoria);
            this.itemDate = itemView.findViewById(R.id.item_fecha);
            this.itemDuration = itemView.findViewById(R.id.item_duracion);
            this.alerMessage = itemView.findViewById(R.id.item_sms_alert);
            this.itemAlertButton = itemView.findViewById(R.id.item_alert_button);
            this.itemResumeButton = itemView.findViewById(R.id.item_resume_button);

            this.collapsingLayout = itemView.findViewById(R.id.item_collapcing);
            this.headerButton = itemView.findViewById(R.id.item_header_button);
            this.initialize();
        }

        private void initialize() {
            this.headerButton.setOnClickListener(this.collapsingListener);
            this.itemAlertButton.setOnClickListener(this.alertButtonListener);
            this.itemResumeButton.setOnClickListener(this.resumeButtonListener);

        }

        private View.OnClickListener collapsingListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (collapsingLayout.getVisibility() == View.VISIBLE) {
                    collapsingLayout.setVisibility(View.GONE);
                } else {
                    collapsingLayout.setVisibility(View.VISIBLE);
                }
            }
        };
        private View.OnClickListener resumeButtonListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentLayout.onItemClickResumeButton(v,getAdapterPosition());

            }
        };
        private View.OnClickListener alertButtonListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentLayout.onItemClickAlertButton(v, getAdapterPosition());
            }
        };

    }

    public interface ItemClickListener {
        // void onItemClickListener(View view, int position);

        void onItemClickResumeButton(View view, int position);

        void onItemClickAlertButton(View view, int position);
    }

}
