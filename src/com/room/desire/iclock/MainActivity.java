
package com.room.desire.iclock;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {

    public static final String TAG_WORLD_CLOCK = "tag_world_clock";

    public static final String TAG_ALARM = "tag_alarm";

    public static final String TAG_STOP_WATCH = "tag_stop_watch";

    public static final String TAG_TIMER = "tag_timer";

    private View mWorldClockTab, mAlarmTab, mStopWatchTab, mTimerTab;
    
    private ViewGroup mContainer;

    private TextView mTitleEditBtn;

    private TextView mTitleNameTv;

    private ImageView mTitleAddBtn;

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
        
        mContainer = (ViewGroup) findViewById(R.id.main_container);

    }
    
    

    private void initTab(View tab, int iconId, int tabelId) {
        ImageView icon = (ImageView) tab.findViewById(R.id.main_tab_icon);
        icon.setImageResource(iconId);
        TextView tv = (TextView) tab.findViewById(R.id.main_tab_label);
        tv.setText(tabelId);
    }

    private void updateSelectedFragment(int i) {
    }
}
