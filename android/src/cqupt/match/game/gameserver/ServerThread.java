package cqupt.match.game.gameserver;

import java.io.*;
import java.net.Socket;

import cqupt.match.game.gameserver.OnAddPlayer;

public class ServerThread extends Thread {
    /**客户端*/
    private Socket client;
    /**输出到客户端*/
    private PrintWriter out;
    /**接收客户端的输入*/
    private BufferedReader in;
    /**玩家的标识符*/
    private String index = "0";
    /**游戏开始和结束的控制器*/
    private cqupt.match.game.gameserver.GameControl control;

    /**
    * 客户端状态码
    * 0 游戏准备阶段
    * 1 游戏中
    * 2 游戏结束，即玩家死亡
    * */
    private int status = 0;

    //增加删除玩家
    private OnAddPlayer onAddPlayer;


    /**
     * 客户端线程对象
     * @param socket 客户端的套接字对象
     * @param index 玩家的标识符
     * @param control 游戏开始和结束的控制器
     * @throws IOException
     */
    public ServerThread(Socket socket,int index,GameControl control,OnAddPlayer onAddPlayer) throws IOException {
        this.index = Integer.toString(index);
        this.control = control;
        this.onAddPlayer = onAddPlayer;
        client = socket;
        out = new PrintWriter(client.getOutputStream(),true);
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
    }

    /**
     * 分析客户端发送的消息,单次消息格式为一行
     */
    @Override
    public void run() {
        super.run();
        try {
            String str = in.readLine();
            while (true) {
                switch (str) {
                    case "start":
                        //游戏开始,通知其他客户端
                        pushOther(index + ":" + "start");
                        //游戏开始，开始准备游戏数据
                        control.beginGame();
                        //发送地图
                        String temp = GameServer.getMap();
                        //System.out.println("map = "+temp);
                        pushMsg(temp);
                        pushOther(temp);
                        //标记状态码 进入游戏
                        status = 1;
                        break;
                    //骰子转到1-6
                    case "1":
                    case "2":
                    case "3":
                    case "4":
                    case "5":
                    case "6":
                        // 返回信息的格式为  标识符：骰子点数
                        pushOther(index + ":" + str);
                        break;
                    case "judge":
                        //进入判定模式
                        pushOther(str);
                        break;
                    case "quit":
                        onAddPlayer.delPlayer(Integer.valueOf(index));

                        return;

                    case "end":
                        //游戏结束，此客户端的线程终止
                        status = 2;
                        control.endGame();
                        //interrupt();
                        return;

                    default:
                        //此时为接收答题情况,客户端接收到信息就开始计时，
                        // 客户端发送的信息包括:,回答时间
                        // 时间00表示，超时
                        // 返回的信息格式为  标识符：时间
                        pushOther(index + ":" + str);
                        break;
                }
                str = in.readLine();
            }
        }catch (IOException e){
        } finally {
            try {
                in.close();
            } catch (IOException e) {
            }
        }
    }

    /**
     * 向客户端推送消息
     * @param str 要推送的消息，单行字符串
     */
    public void pushMsg(String str){
        if (status == 2)
            return;
        out.println(str);
    }

    /**
     * 向其他客户端推送消息
     * @param str  要推送的消息，单行字符串
     */
    public void pushOther(String str){
        ServerThread[] client = GameServer.getClient();
        for (ServerThread thread : client){
            if (thread!=null && thread!=this)
                thread.pushMsg(str);
        }
    }
    public void close(){
        this.close();
    }
}
