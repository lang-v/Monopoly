package cqupt.match.game.gameclient;

public interface CommandCallBack {
    void move(int index,int target);
    void end();
    void start();
}
