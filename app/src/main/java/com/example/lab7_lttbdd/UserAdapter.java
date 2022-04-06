package com.example.lab7_lttbdd;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class UserAdapter extends BaseAdapter {
    private Context context;
    private int idPeopleItem;
    private List<User> list;

    public UserAdapter(Context context, int idPeopleItem, List<User> list) {
        this.context = context;
        this.idPeopleItem = idPeopleItem;
        this.list = list;
    }

    @Override
    public int getCount() {
       if (list.size() != 0 && !list.isEmpty())
           return list.size();
       return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null)
            view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(idPeopleItem, viewGroup, false);

        TextView id = view.findViewById(R.id.peopleID);
        TextView name = view.findViewById(R.id.peopleName);

       User user = list.get(i);

        if (user != null && !list.isEmpty()){
            id.setText(user.getId() + "");
            name.setText(user.getName());
        }

        return view;
    }
}
