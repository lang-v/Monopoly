package cqupt.match.game.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.daimajia.swipe.SwipeLayout;

import java.util.ArrayList;
import java.util.List;

import cqupt.match.game.R;
import cqupt.match.game.gameserver.OnAddPlayer;
import cqupt.match.game.playeritem.PlayerItem;

public class PlayerNameAdapter extends ArrayAdapter<PlayerItem>{

    //上下文对象
    private Context context;
    //用户列表
    List<PlayerItem> players = new ArrayList<>();
    //item布局文件
    private int resourceId;

    private OnAddPlayer callBack;

    public PlayerNameAdapter(@NonNull Context context, int resource, List<PlayerItem> players, OnAddPlayer callBack) {
        super(context, resource);
        this.players = players;
        resourceId = resource;
        this.callBack = callBack;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent){
        PlayerItem playerItem = players.get(position);
        View view  = LayoutInflater.from(getContext()).inflate(resourceId,null);
        TextView textView = view.findViewById(R.id.text_box);
        textView.setText(playerItem.getName());
        Button delete = view.findViewById(R.id.delete_item);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBack.delPlayer(position);
            }
        });
        return view;
    }
    @Override
    public int getCount() {
        return players.size();
    }

}
