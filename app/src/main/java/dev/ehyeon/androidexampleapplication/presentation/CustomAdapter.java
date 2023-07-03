package dev.ehyeon.androidexampleapplication.presentation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import dev.ehyeon.androidexampleapplication.R;
import dev.ehyeon.androidexampleapplication.data.user.User;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private List<User> list;

    public CustomAdapter(List<User> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomAdapter.ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_recyclerview, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void updateList(List<User> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvEmail;
        TextView tvName;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvEmail = itemView.findViewById(R.id.email);
            tvName = itemView.findViewById(R.id.name);
        }

        void onBind(User user) {
            tvEmail.setText(user.email);
            tvName.setText(user.name);
        }
    }
}
