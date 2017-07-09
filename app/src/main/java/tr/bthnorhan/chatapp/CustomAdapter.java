package tr.bthnorhan.chatapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

/**
 * Created by bthnorhan on 9.07.2017.
 */

public class CustomAdapter extends BaseAdapter {
    ArrayList<Message> list = new ArrayList<>();
    LayoutInflater layoutInflater;
    FirebaseUser fUser;

    public CustomAdapter(Activity activity, ArrayList<Message> list, FirebaseUser fUser) {
        this.list = list;
        layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.fUser = fUser;
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
        View view;
        if (fUser.getEmail().equals(list.get(position).getEmail()))
        {
            view = layoutInflater.inflate(R.layout.lv_row_right,null);
            TextView email = (TextView) view.findViewById(R.id.tv_row_right_email);
            TextView message = (TextView) view.findViewById(R.id.tv_row_right_message);
            TextView time = (TextView) view.findViewById(R.id.tv_row_right_time);

            email.setText(list.get(position).getEmail());
            message.setText(list.get(position).getMessage());
            time.setText(list.get(position).getTime());

        }
        else
        {
            view = layoutInflater.inflate(R.layout.lv_row_left,null);
            TextView email = (TextView) view.findViewById(R.id.tv_row_left_email);
            TextView message = (TextView) view.findViewById(R.id.tv_row_left_message);
            TextView time = (TextView) view.findViewById(R.id.tv_row_left_time);

            email.setText(list.get(position).getEmail());
            message.setText(list.get(position).getMessage());
            time.setText(list.get(position).getTime());
        }
        return view;
    }
}
