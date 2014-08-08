
package com.room.desire.iclock;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.room.desire.iclock.Utils.ICSH;
import com.room.desire.iclock.Utils.Trace;
import com.room.desire.iclock.fragment.AlarmFragment;
import com.room.desire.iclock.fragment.StopWatchFragment;
import com.room.desire.iclock.fragment.TimerFragment;
import com.room.desire.iclock.fragment.WorldClockFragment;

public class MainActivity extends Activity implements OnClickListener {

    private static final int TAB_WORLD_CLOCK = 0;

    private static final int TAB_ALARM = 1;

    private static final int TAB_STOP_WATCH = 2;

    private static final int TAB_TIMER = 3;

    private static final String TAG_WORLD_CLOCK = "tag_world_clock";

    private static final String TAG_ALARM = "tag_alarm";

    private static final String TAG_STOP_WATCH = "tag_stop_watch";

    private static final String TAG_TIMER = "tag_timer";

    private static final String[] sTags = {
        TAG_WORLD_CLOCK, TAG_ALARM, TAG_STOP_WATCH, TAG_TIMER
    };

    private FragmentManager mFM;

    private int mShowTab;

    private View mWorldClockTab, mAlarmTab, mStopWatchTab, mTimerTab;

    private FrameLayout mMainContainer, mSubContainer;

    private TextView mTitleEditBtn;

    private TextView mTitleNameTv;

    private ImageView mTitleAddBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFM = getFragmentManager();
        mTitleEditBtn = (TextView) findViewById(R.id.main_title_edit_btn);
        mTitleEditBtn.setOnClickListener(this);
        mTitleNameTv = (TextView) findViewById(R.id.main_title_name_tv);
        mTitleAddBtn = (ImageView) findViewById(R.id.main_title_add_btn);
        mTitleAddBtn.setOnClickListener(this);

        mWorldClockTab = findViewById(R.id.main_tab_world_clock);
        initTab(mWorldClockTab, R.drawable.ic_launcher, R.string.world_clock);

        mAlarmTab = findViewById(R.id.main_tab_alarm);
        initTab(mAlarmTab, R.drawable.ic_launcher, R.string.alarm);

        mStopWatchTab = findViewById(R.id.main_tab_stop_watch);
        initTab(mStopWatchTab, R.drawable.ic_launcher, R.string.stop_watch);

        mTimerTab = findViewById(R.id.main_tab_timer);
        initTab(mTimerTab, R.drawable.ic_launcher, R.string.timer);

        mMainContainer = (FrameLayout) findViewById(R.id.main_container);
        int tabIndex = ICSH.getMainTabIndex();
        updateSelectedFragment(tabIndex);

        mSubContainer = (FrameLayout) findViewById(R.id.main_sub_container);

    }

    @Override
    protected void onResume() {
        super.onResume();

        int count = mFM.getBackStackEntryCount();
        Trace.d("@@@@", "---------  count = " + count);
        for (int i = 0; i < count; i++) {
            mFM.popBackStack();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        ICSH.setMainTabIndex(mShowTab);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.main_tab_world_clock:
                updateSelectedFragment(TAB_WORLD_CLOCK);
                break;
            case R.id.main_tab_alarm:
                updateSelectedFragment(TAB_ALARM);
                break;
            case R.id.main_tab_stop_watch:
                updateSelectedFragment(TAB_STOP_WATCH);
                break;
            case R.id.main_tab_timer:
                updateSelectedFragment(TAB_TIMER);
                break;
            case R.id.main_title_edit_btn:
                // TODO
                String tag = sTags[3];
                Fragment f = getFragment(3, tag);
                FragmentTransaction trans = mFM.beginTransaction();
                trans.replace(mSubContainer.getId(), f, tag);
                trans.addToBackStack(tag);
                trans.commit();
                break;
            case R.id.main_title_add_btn:
                // TODO
                break;
        }
    }

    private void initTab(View tab, int iconId, int tabelId) {
        ImageView icon = (ImageView) tab.findViewById(R.id.main_tab_icon);
        icon.setImageResource(iconId);
        TextView tv = (TextView) tab.findViewById(R.id.main_tab_label);
        tv.setText(tabelId);
        tab.setOnClickListener(this);
    }

    private void updateSelectedFragment(int i) {
        if (i < 0 || i > 3) {
            i = 0;
        }
        mShowTab = i;

        updateTitle(i);

        String tag = sTags[i];
        Fragment f = getFragment(i, tag);
        FragmentTransaction trans = mFM.beginTransaction();
        trans.replace(mMainContainer.getId(), f, tag);
        trans.commit();
    }

    private void updateTitle(int i) {
        switch (i) {
            case TAB_WORLD_CLOCK:
                mTitleNameTv.setText(R.string.world_clock);
                mTitleEditBtn.setVisibility(View.VISIBLE);
                mTitleAddBtn.setVisibility(View.VISIBLE);
                break;
            case TAB_ALARM:
                mTitleNameTv.setText(R.string.alarm);
                mTitleEditBtn.setVisibility(View.VISIBLE);
                mTitleAddBtn.setVisibility(View.VISIBLE);
                break;
            case TAB_STOP_WATCH:
                mTitleNameTv.setText(R.string.stop_watch);
                mTitleEditBtn.setVisibility(View.GONE);
                mTitleAddBtn.setVisibility(View.GONE);
                break;
            case TAB_TIMER:
                mTitleNameTv.setText(R.string.timer);
                mTitleEditBtn.setVisibility(View.GONE);
                mTitleAddBtn.setVisibility(View.GONE);
                break;

            default:
                break;
        }
    }

    private Fragment getFragment(int i, String tag) {
        Fragment f = mFM.findFragmentByTag(tag);
        if (f == null) {
            switch (i) {
                case TAB_WORLD_CLOCK:
                    f = new WorldClockFragment();
                    break;
                case TAB_ALARM:
                    f = new AlarmFragment();
                    break;
                case TAB_STOP_WATCH:
                    f = new StopWatchFragment();
                    break;
                case TAB_TIMER:
                    f = new TimerFragment();
                    break;

                default:
                    f = new WorldClockFragment();
                    break;
            }
        }
        return f;
    }
}
