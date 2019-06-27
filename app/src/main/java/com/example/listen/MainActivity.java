package com.example.listen;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;

import com.example.listen.fragment.FindFragment;
import com.example.listen.fragment.HomeFragment;
import com.example.listen.fragment.ListenFragment;
import com.example.listen.fragment.MeFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MediaService.IMediaStateListener, ServiceConnection{
private List<Fragment> fragments=new ArrayList<>();
private ViewPager viewPager;
private RadioGroup radioGroup;
private MediaService mediaService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        if (mediaService == null) {
            // bind service
            Intent intent = new Intent(MainActivity.this, MediaService.class);
            MainActivity.this.bindService(intent, MainActivity.this, Context.BIND_AUTO_CREATE);
        }
        fragments.add(new HomeFragment());
        fragments.add(new FindFragment());
        fragments.add(new ListenFragment());
        fragments.add(new MeFragment());
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.main_rbtn_home:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.main_rbtn_listen:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.find:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.main_rbtn_me:
                        viewPager.setCurrentItem(3);
                        break;
                }
            }
        });
        viewPager.setAdapter(new FragmentPagerAdapter(this.getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return fragments.get(i);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });
        viewPager.setOffscreenPageLimit(3);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                radioGroup.check(radioGroup.getChildAt(i).getId());
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    private void initView() {
        radioGroup=findViewById(R.id.main_gp);
        viewPager=findViewById(R.id.main_vp);

    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        MediaService.MediaBinder mediaBinder = (MediaService.MediaBinder) service;
        mediaService = mediaBinder.getService();
        mediaService.setMediaStateListener(MainActivity.this);
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }

    @Override
    public void onPrepared(int duration) {

    }

    @Override
    public void onProgressUpdate(int currentPos, int duration) {

    }

    @Override
    public void onSeekComplete() {

    }

    @Override
    public void onCompletion() {

    }

    @Override
    public boolean onInfo(int what, int extra) {
        return false;
    }

    @Override
    public boolean onError(int what, int extra) {
        return false;
    }
}
