package com.example.listen.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.listen.R;
import com.example.listen.bean.MusicBean;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.viwerholder>{
    private Context context;
    private List<MusicBean.DataBean.ListBean> listBeans;

    public MyAdapter(Context context, List<MusicBean.DataBean.ListBean> listBeans) {
        this.context = context;
        this.listBeans = listBeans;
    }

    @NonNull
    @Override
    public MyAdapter.viwerholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.music_item,null);
        return new viwerholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.viwerholder viwerholder, int i) {
            viwerholder.title.setText(listBeans.get(i).getTitle());
        Glide
                .with(context)
                .load(listBeans.get(i).getCoverLarge())
                .error(R.mipmap.ic_launcher)
                .into(viwerholder.iv_pic);

    }

    @Override
    public int getItemCount() {
        return listBeans.size();
    }
    class viwerholder extends RecyclerView.ViewHolder{
        private ImageView iv_pic,iv_music;
        private TextView title;
        public viwerholder(@NonNull View itemView) {
            super(itemView);
            iv_pic=itemView.findViewById(R.id.item_iv);
            title=itemView.findViewById(R.id.item_tv);
        }
    }
}
