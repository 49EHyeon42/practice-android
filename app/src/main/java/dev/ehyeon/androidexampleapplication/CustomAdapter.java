package dev.ehyeon.androidexampleapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter extends BaseAdapter {

    private final LayoutInflater inflater;
    private List<User> list;

    public CustomAdapter(Context context, List<User> list) {
        inflater = LayoutInflater.from(context);
        this.list = list;
    }

    public void updateList(List<User> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.activity_listview, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.tvEmail = convertView.findViewById(R.id.email);
            viewHolder.tvName = convertView.findViewById(R.id.name);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        User user = list.get(position);

        viewHolder.tvEmail.setText(user.email);
        viewHolder.tvName.setText(user.name);

        return convertView;
    }

    private static class ViewHolder {
        TextView tvEmail;
        TextView tvName;
    }
}
