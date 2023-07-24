package com.auroraguatemala.antiphishacademy.menu.notification;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.auroraguatemala.antiphishacademy.R;

public class NotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_fragment);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_container, new NotificationFragment())
                    .commit();
        }
    }
}

