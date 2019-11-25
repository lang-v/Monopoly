package cqupt.match.game.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cqupt.match.game.R;
import cqupt.match.game.adapter.PlayerNameAdapter;
import cqupt.match.game.getlocalhost.NetWorkUtil;
import cqupt.match.game.playeritem.PlayerItem;
import cqupt.match.monopoly.gameclient.GameClient;
import cqupt.match.game.gameserver.GameServer;
import cqupt.match.game.gameserver.OnAddPlayer;

public class RoomActivity extends AppCompatActivity {

    //服务器实例
    private GameServer gameServer = null;

    //客户端实例
    private static GameClient gameClient = null;

    //姓名和房间地址
    private static String uName ="";
    private static String uIp="";

    //玩家集合
    private static List<PlayerItem> players = null;
    //ListView对象
    private ListView listView = null;
    //list适配器
    private static PlayerNameAdapter adapter = null;
    //玩家人数
    private static int number= 0;
    //更新UI的标识符
    private static final int FRESH = 101;
    //是否开启服务端
    private static boolean isServer = false;

    //更新UI
    private static Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case FRESH:
                    adapter.notifyDataSetChanged();
                    default:
                        break;
            }
        }
    };

    private Thread listenTask = null;

    private static OnAddPlayer addPlayerCallBack = new OnAddPlayer() {
        @Override
        public void addPlayer(String name) {
            PlayerItem item = new PlayerItem(name,++number);
            players.add(item);
            Message msg = Message.obtain();
            msg.what = FRESH;
            handler.sendMessage(msg);
        }

        @Override
        public void delPlayer(int index) {
            GameServer.closeClient(index);
            players.remove(index);
            Message msg = Message.obtain();
            msg.what = FRESH;
            handler.sendMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);
        init();
    }

    private void init() {
        //绑定控件
        players = new ArrayList<>();
        listView = findViewById(R.id.player_list);
        adapter = new PlayerNameAdapter(this, R.layout.palyer_item, players,addPlayerCallBack);
        players.add(new PlayerItem("s", 0));
        players.add(new PlayerItem("s", 0));
        players.add(new PlayerItem("s", 0));
        players.add(new PlayerItem("s", 0));
        players.add(new PlayerItem("s", 0));
        listView.setAdapter(adapter);
        if ("".equals(uIp)) {
            if (gameServer == null && gameClient == null) {
               gameClient = new GameClient();
            }
            new Thread(new Runnable() {
                @Override
                public void run() {
                    //获取本机的内网地址
                    uIp = NetWorkUtil.getHostIp();
                    Log.e("SL", uIp);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            TextView textView = findViewById(R.id.room_number);
                            textView.setText(uIp);
                        }
                    });
                }
            }).start();
            //开始监听
            listenTask = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        gameServer = new GameServer(addPlayerCallBack);
                        gameClient = new GameClient();
                    } catch (IOException e) {
                        Log.e("SL","未能成功开启服务");
                    }
                    gameServer.listen();
                }
            });
            listenTask.start();
        } else {
            if (gameClient == null)
                gameClient = new GameClient();
            TextView text_ip = findViewById(R.id.room_number);
            text_ip.setText(uIp);
        }
        TextView text_name = findViewById(R.id.player_name);
        text_name.setText(uName);

        Button begin = findViewById(R.id.begin_game);
        begin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //gameClient.sendMsg("start");
                Log.e("SL","开始游戏");
            }
        });

        if (!isServer){
            begin.setVisibility(View.GONE);
        }
        Button quit = findViewById(R.id.quit_game);
        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //退出房间
                Log.e("SL","退出游戏");
                finish();
            }
        });

    }

    public static String getuName() {
        return uName;
    }

    public static void createRoom(Context context, String name) {
        Intent intent = new Intent(context, RoomActivity.class);
        uName = name;
        uIp = "";
        isServer = true;
        context.startActivity(intent);
    }

    public static void joinRoom(Context context, String name, String ip) {
        Intent intent = new Intent(context, RoomActivity.class);
        uName = name;
        uIp = ip;
        isServer = false;
        context.startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("SL","销毁房间");
        gameServer.end();
        gameServer = null;
        gameClient = null;
        uName = "";
        uIp = "";
        players = null;
        adapter = null;
        listView = null;
        if (listenTask != null) {
            listenTask.interrupt();
            listenTask = null;
        }
    }
}
