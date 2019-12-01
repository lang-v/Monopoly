package cqupt.match.game.gameclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread {

    private String name;
    private Socket socket;
    private int index = -1;
    private PrintWriter out;
    private BufferedReader in;
    private CommandCallBack commandCallBack;
    public ClientThread(String name,Socket socket,CommandCallBack commandCallBack) throws IOException {
        this.name = name;
        this.socket = socket;
        this.commandCallBack = commandCallBack;
        init();
    }

    private void init() throws IOException {
        out = new PrintWriter(socket.getOutputStream(),true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        //第一次连接发送姓名
        out.println(name);
        //接收自己在游戏中的编号
        String index = in.readLine();
        this.index = Integer.valueOf(index);
    }

    public int getIndex() {
        return index;
    }

    @Override
    public void run(){
        while (!isInterrupted()){
            try {
                String command = in.readLine();
                choose(command);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private void choose(String cmd){
        System.out.println("cmd="+cmd);
        switch (cmd){
            case "start":
                commandCallBack.start();
                break;
            case "end":
                commandCallBack.end();
                default:
                    break;
        }
    }
}


//
//        case "1":
//        case "2":
//        case "3":
//        case "4":
//        case "5":
//        case "6":