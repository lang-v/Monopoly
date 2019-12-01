package cqupt.match.game.gameclient;

import android.util.Log;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import cqupt.match.game.resource.Res;

public class GameClient {
    //基础信息
    private String name;
    private static int index;
    private String ip;
    private ClientThread clientThread;
    private Socket socket;

    public GameClient(String name,String ip,CommandCallBack commandCallBack){
        this.name = name;
        this.ip = ip;
        init(commandCallBack);
    }

    private void init(final CommandCallBack commandCallBack){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    socket = new Socket(ip, Res.PORT);
                    clientThread = new ClientThread(name, socket, commandCallBack);
                    index = clientThread.getIndex();
                    Log.e("SL","玩家index="+index);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void sendMsg(final String msg){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
                    out.println(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void close(){
        if (clientThread != null) {
            clientThread.interrupt();
        }
    }

    public static int getIndex() {
        return index;
    }
}
