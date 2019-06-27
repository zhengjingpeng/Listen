package com.example.listen.fragment;


import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.listen.MediaService;
import com.example.listen.R;
import com.example.listen.adapter.MyAdapter;
import com.example.listen.bean.MusicBean;
import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class f1 extends Fragment {
private RecyclerView recyclerView;
private List<MusicBean.DataBean.ListBean> listBeans;
private OkHttpClient okHttpClient;
private Gson gson=new Gson();
private MediaService mediaService;
private static MediaPlayer mMediaPlayer;


    public f1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_f1, container, false);
        okHttpClient=new OkHttpClient();
        mMediaPlayer = new MediaPlayer();
        recyclerView=view.findViewById(R.id.home_rv);
        getdata();
        return view;
    }

    private void getdata() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Request request=new Request.Builder().url("http://mobile.ximalaya.com/mobile/v1/album/track?albumId=203355&device=android&isAsc=true&pageId=4&pageSize=20&statEvent=pageview%2Falbum%40203355&statModule=最多收藏榜&statPage=ranklist%40最多收藏榜&statPosition=8").build();
                try {
                    Response response=okHttpClient.newCall(request).execute();
                    String string = response.body().string();
                    Log.i("sssssssss",string);
                    MusicBean musicBean = gson.fromJson(string, MusicBean.class);
                    listBeans=musicBean.getData().getList();
                    getActivity().runOnUiThread(new Runnable() {

                        @Override
                        public void run() {

                            LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
                            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                            recyclerView.setLayoutManager(linearLayoutManager);
                            recyclerView.setAdapter(new Adapter());
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }


    class Adapter extends RecyclerView.Adapter<Adapter.viwerholder>{

    @NonNull
    @Override
    public Adapter.viwerholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(getContext()).inflate(R.layout.music_item,null);
        return new viwerholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.viwerholder viwerholder, final int i) {
        viwerholder.title.setText(listBeans.get(i).getTitle());
        Glide
                .with(getContext())
                .load(listBeans.get(i).getCoverLarge())
                .error(R.mipmap.ic_launcher)
                .into(viwerholder.iv_pic);
        viwerholder.radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaService=new MediaService();
                if(mediaService!=null&&!mMediaPlayer.isPlaying()){
                  mediaService.playMusic(listBeans.get(i).getPlayUrl32());
                }
                if(mMediaPlayer.isPlaying()){
                    mediaService.stopMusic();
                }



            }
        });


    }

    @Override
    public int getItemCount() {
        return listBeans.size();
    }
    class viwerholder extends RecyclerView.ViewHolder{
        private ImageView iv_pic,iv_music;
        private TextView title;
        private RadioButton radioButton;
        public viwerholder(@NonNull View itemView) {
            super(itemView);
            radioButton=itemView.findViewById(R.id.rb);
            iv_pic=itemView.findViewById(R.id.item_iv);
            title=itemView.findViewById(R.id.item_tv);
        }
    }
}
}
