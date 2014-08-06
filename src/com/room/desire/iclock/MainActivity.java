
package com.room.desire.iclock;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {

    private View mWorldClockTab, mAlarmTab, mStopWatchTab, mTimerTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWorldClockTab = findViewById(R.id.main_tab_world_clock);
        initTab(mWorldClockTab, R.drawable.ic_launcher, R.string.world_clock);
        
        mAlarmTab = findViewById(R.id.main_tab_alarm);
        initTab(mAlarmTab, R.drawable.ic_launcher, R.string.alarm);
        
        mStopWatchTab = findViewById(R.id.main_tab_stop_watch);
        initTab(mStopWatchTab, R.drawable.ic_launcher, R.string.stop_watch);
        
        mTimerTab = findViewById(R.id.main_tab_timer);
        initTab(mTimerTab, R.drawable.ic_launcher, R.string.timer);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    private void initTab(View tab, int iconId, int tabelId) {
        ImageView icon = (ImageView) tab.findViewById(R.id.main_tab_icon);
        icon.setImageResource(iconId);
        TextView tv = (TextView) tab.findViewById(R.id.main_tab_label);
        tv.setText(tabelId);
    }

}
