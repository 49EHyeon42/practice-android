package dev.ehyeon.androidexampleapplication;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CustomAdapter extends BaseAdapter {

    private final LayoutInflater inflater;
    private final UserRepository userRepository;

    public CustomAdapter(Context context, UserRepository userRepository) {
        inflater = LayoutInflater.from(context);
        this.userRepository = userRepository;
    }

    public void saveUser(String email, String name) {
        userRepository.save(new UserDto(email, name));
    }

    @Override
    public int getCount() {
        return userRepository.getCount();
    }

    @Override
    public Object getItem(int position) {
        return userRepository.findUserById((long) position);
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

        User user = userRepository.findUserById((long) position);

        // TODO refactor
        if (user != null) {
            if (user.email != null) {
                viewHolder.tvEmail.setText(user.email);
            }

            if (user.name != null) {
                viewHolder.tvName.setText(user.name);
            }

            // TODO clear
            Log.i("DDD", "id = " + user.id + " email = " + user.email + " name = " + user.name);
        }

        return convertView;
    }

    private static class ViewHolder {
        TextView tvEmail;
        TextView tvName;
    }
}
