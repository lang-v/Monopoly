package cqupt.match.game.gameclient;

import java.io.IOException;
import java.net.Socket;

import cqupt.match.game.resource.Res;

public class GameClient {
    //基础信息
    private String name;
    private int index;
    private String ip;
    private ClientThread clientThread;

    private Socket socket;
    public GameClient(String name,String ip){
        this.name = name;
        this.ip = ip;
        init();
    }

    private void init(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    socket = new Socket(ip, Res.PORT);
                    clientThread = new ClientThread(name, socket, new CommandCallBack() {
                        @Override
                        public void getCommand(String command) {

                        }
                    });
                    index = clientThread.getIndex();
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
}
