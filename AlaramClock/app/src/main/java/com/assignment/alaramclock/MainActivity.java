package com.assignment.alaramclock;

import android.app.Activity; import
        android.app.AlarmManager; import
        android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import
        android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import
        android.os.Bundle; import
        android.util.Log; import
        android.view.View; import
        android.widget.TextView; import
        android.widget.TimePicker; import
        android.widget.ToggleButton;
import java.util.Calendar; public
class MainActivity extends Activity
{
    AlarmManager alarmManager;
    private
    PendingIntent pendingIntent;private
TimePicker alarmTimePicker;private
static MainActivity inst;
    private TextView
            alarmTextView;
    public static MainActivity instance() {
        return inst;
    }
    @Override
    public void onStart() {
        super.onStart();
        this;
        inst =;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        alarmTimePicker = (TimePicker) findViewById(R.id.alarmTimePicker);
        alarmTextView
                = (TextView) findViewById(R.id.alarmText);
        ToggleButton alarmToggle = (ToggleButton) findViewById(R.id.alarmToggle);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
    }
    public void onToggleClicked(View view) {
        if
        (((ToggleButton) view).isChecked()) {
            Log.d("MyActivity", "Alarm On");
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY,
                    alarmTimePicker.getCurrentHour());
            calendar.set(Calendar.MINUTE, alarmTimePicker.getCurrentMinute());
            Intent myIntent = new Intent(MainActivity.this, MainActivity.class);
            pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, myIntent, 0);
            alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis(), pendingIntent);
        } else {
            alarmManager.cancel(pendingIntent);
            setAlarmText("");
            Log.d("MyActivity", "Alarm Off");
        }
    }
    public void setAlarmText(String alarmText) {
        alarmTextView.setText(alarmText);
    }

    public static class
    MainActivity extends WakefulBroadcastReceiver {
        @Override
        public void onReceive(final Context context, Intent intent) {
    //this will update the UI with message
            AlarmActivity.instance();
            AlarmActivity inst =
                    inst.setAlarmText("Alarm! Wake up!
                            Wake up!");
    //this will sound the alarm tone
    //this will sound the alarm once, if you wish to
    //raise alarm in loop continuously then use MediaPlayer and setLooping(true)
            Uri alarmUri =
                    RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM
                    );
            if (alarmUri == null) {
                alarmUri =
                        RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            }
            Ringtone ringtone = RingtoneManager.getRingtone(context, alarmUri);
            ringtone.play();
    //this will send a notification message
            ComponentName comp = new ComponentName(context.getPackageName(),
                    AlarmService.class.getName());
            startWakefulService(context, (intent.setComponent(comp)));
            setResultCode(RESULT_OK);
        }
    }
}