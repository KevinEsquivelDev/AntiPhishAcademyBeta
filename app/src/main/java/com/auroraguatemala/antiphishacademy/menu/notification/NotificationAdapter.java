package com.auroraguatemala.antiphishacademy.menu.notification;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.auroraguatemala.antiphishacademy.R;

import java.util.List;

public class NotificationAdapter extends ArrayAdapter<Notification> {
    public NotificationAdapter(@NonNull Context context, int resource, @NonNull List<Notification> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_notification, parent, false);
        }

        Notification notification = getItem(position);
        TextView title = convertView.findViewById(R.id.titulo_notification);
        title.setText(notification.getTitle());

        return convertView;
    }
}

