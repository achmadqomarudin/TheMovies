package com.project.themovies.activities;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.project.themovies.R;
import com.project.themovies.notification.NotificationDailyReceiver;
import com.project.themovies.notification.NotificationReleaseReceiver;
import com.project.themovies.preference.SettingPreference;

public class SettingActivity extends AppCompatActivity {

    private Switch switchReminder;
    private Switch switchRelease;
    private NotificationDailyReceiver notificationDailyReceiver;
    private NotificationReleaseReceiver notificationReleaseReceiver;
    private SettingPreference settingPreference;
    private Button button;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        switchReminder = findViewById(R.id.swDailyReminder);
        switchRelease = findViewById(R.id.swRealeseToday);
        button = findViewById(R.id.btnChangeLanguage);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        notificationDailyReceiver = new NotificationDailyReceiver();
        notificationReleaseReceiver = new NotificationReleaseReceiver();

        settingPreference = new SettingPreference(this);

        setSwitchRelease();
        setSwitchReminder();

        // Switch Reminder OnClick
        switchReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (switchReminder.isChecked()) {
                    notificationDailyReceiver.setDailyAlarm(getApplicationContext());
                    settingPreference.setDailyReminder(true);
                    Toast.makeText(getApplicationContext(), "Daily reminders activated", Toast.LENGTH_SHORT).show();
                } else {
                    notificationDailyReceiver.cancelAlarm(getApplicationContext());
                    settingPreference.setDailyReminder(false);
                    Toast.makeText(getApplicationContext(), "Daily reminders not activated", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Switch Release OnClick
        switchRelease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (switchRelease.isChecked()) {
                    notificationReleaseReceiver.setReleaseAlarm(getApplicationContext());
                    settingPreference.setReleaseReminder(true);
                    Toast.makeText(getApplicationContext(), "Release reminder activated", Toast.LENGTH_SHORT).show();
                } else {
                    notificationReleaseReceiver.cancelAlarm(getApplicationContext());
                    settingPreference.setReleaseReminder(false);
                    Toast.makeText(getApplicationContext(), "Release reminder not activated", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Button Change Language OnClick
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
                startActivity(mIntent);
            }
        });
    }

    private void setSwitchReminder() {
        if (settingPreference.getDailyReminder()) switchReminder.setChecked(true);
        else switchReminder.setChecked(false);
    }

    private void setSwitchRelease() {
        if (settingPreference.getReleaseReminder()) switchRelease.setChecked(true);
        else switchRelease.setChecked(false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
