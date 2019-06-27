package com.example.listen.fragment;


import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.listen.R;
import com.example.listen.bean.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
private TabLayout tabLayout;
private List<Fragment> fragments=new ArrayList<>();
private ViewPager viewPager;
private ImageView iv_jia;
private View view1;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);
        tabLayout=view.findViewById(R.id.tabs);
        viewPager=view.findViewById(R.id.home_vp);
        iv_jia=view.findViewById(R.id.jia);
        iv_jia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  view1 = LayoutInflater.from(getContext()).inflate(R.layout.layout_popwin, null);
                initData();
                 PopupWindow popupWindow = new PopupWindow(view1, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
                view1.setBackgroundColor(Color.rgb(255,255,255));
                popupWindow.showAsDropDown(v);
            }
        });
        tabLayout.addTab(tabLayout.newTab().setText("推荐"));
        tabLayout.addTab(tabLayout.newTab().setText("段子"));
        tabLayout.addTab(tabLayout.newTab().setText("直播"));
        tabLayout.addTab(tabLayout.newTab().setText("音乐"));
        tabLayout.addTab(tabLayout.newTab().setText("粤语"));
        tabLayout.addTab(tabLayout.newTab().setText("广播"));
        tabLayout.addTab(tabLayout.newTab().setText("精品"));
        tabLayout.addTab(tabLayout.newTab().setText("相声"));
        tabLayout.addTab(tabLayout.newTab().setText("人文"));
        tabLayout.addTab(tabLayout.newTab().setText("历史"));
        tabLayout.addTab(tabLayout.newTab().setText("小说"));
        tabLayout.addTab(tabLayout.newTab().setText("儿童"));
        fragments.add(new f1());
        fragments.add(new f2());
        fragments.add(new f3());
        fragments.add(new f4());
        fragments.add(new f5());
        viewPager.setAdapter(new FragmentPagerAdapter(getActivity().getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return fragments.get(i);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
               /* for (int i = 0; i <tabLayout.getTabCount() ; i++) {
                    if(tab==tabLayout.getTabAt(i)){
                        viewPager.setCurrentItem(i);
                    }

                }*/
                viewPager.setCurrentItem(tab.getPosition());


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {


            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
               tabLayout.getTabAt(i).select();
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        return view;
    }

    private void initData() {
        ListView listView = view1.findViewById(R.id.lv2);
        final List<Item>  list= new ArrayList<Item>();
        list.add(new Item(R.drawable.xiazai, "下载"));
        list.add(new Item(R.drawable.shengyin, "声音"));
        list.add(new Item(R.drawable.saoyisao, "扫一扫"));
        listView.setAdapter(new BaseAdapter() {
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
                convertView= LayoutInflater.from(getContext()).inflate(R.layout.popwin_item,null);
                ImageView imageView=convertView.findViewById(R.id.img_logo);
                imageView.setImageResource(list.get(position).logo);
                TextView textView=convertView.findViewById(R.id.tv_title);
                textView.setText(list.get(position).title);
                return convertView;
            }
        });
    }

}
