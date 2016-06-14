package com.rolandoamarillo.workportalinboxbizagi.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.rolandoamarillo.workportalinboxbizagi.R;
import com.rolandoamarillo.workportalinboxbizagi.models.Task;

import java.text.DateFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TasksRecyclerViewAdapter extends RecyclerView.Adapter<TasksRecyclerViewAdapter.ViewHolder> {

    private final List<Task> mValues;
    private OnListFragmentInteractionListener mListener;

    public TasksRecyclerViewAdapter(List<Task> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_row, parent, false);
        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Task item = mValues.get(position);
        holder.mItem = item;
        holder.textViewName.setText(item.getEmployee());
        final Context context = holder.mView.getContext();
        holder.textViewNumberOfDays.setText(context.getString(R.string.days, String.valueOf(item.getNumberOfDays())));
        holder.textViewBeginDate.setText(DateFormat.getDateInstance().format(item.getBeginDate()));
        holder.textViewEndDate.setText(DateFormat.getDateInstance().format(item.getEndDate()));

        int color = -1;
        if (item.getState() == Task.TaskState.ACCEPTED) {
            color = R.color.card_background_accepted;
        } else if (item.getState() == Task.TaskState.REJECTED) {
            color = R.color.card_background_rejected;
        } else {
            color = R.color.card_background;
        }

        holder.mView.setCardBackgroundColor(ContextCompat.getColor(context, color));

        holder.imageButtonReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] options = {"Aceptar", "Rechazar", "Pendiente"};
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle(context.getString(R.string.review));
                builder.setSingleChoiceItems(options, -1, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        if (whichButton == 0) {
                            holder.mItem.setState(Task.TaskState.ACCEPTED);
                        } else if (whichButton == 1) {
                            holder.mItem.setState(Task.TaskState.REJECTED);
                        } else {
                            holder.mItem.setState(Task.TaskState.NOT_REVIEWED);
                        }
                        mListener.onListFragmentInteraction(holder.mItem);
                        notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });
                builder.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final CardView mView;
        public Task mItem;
        @BindView(R.id.textViewName)
        TextView textViewName;
        @BindView(R.id.textViewNumberOfDays)
        TextView textViewNumberOfDays;
        @BindView(R.id.textViewBeginDate)
        TextView textViewBeginDate;
        @BindView(R.id.textViewEndDate)
        TextView textViewEndDate;
        @BindView(R.id.imageButtonReview)
        ImageButton imageButtonReview;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            mView = (CardView) view;
        }
    }


    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(Task item);
    }

}
