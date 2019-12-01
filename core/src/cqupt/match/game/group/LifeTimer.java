package cqupt.match.game.group;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import cqupt.match.game.Monopoly;
import cqupt.match.game.group.base.BaseGroup;
import cqupt.match.game.resource.Res;

public class LifeTimer extends BaseGroup {

    Label time;
    int m = 0;

    public LifeTimer(Monopoly mainGame) {
        super(mainGame);
        init();
    }

    private void init() {
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.fontColor = Color.RED;
        labelStyle.font = getMainGame().getBitmapFont();
        Label life = new Label("Life: 10",labelStyle);
        life.setFontScale(0.3f);
        life.setSize(life.getPrefWidth(), life.getPrefHeight());
        life.setPosition(5,25);
        addActor(life);

        Label.LabelStyle style = new Label.LabelStyle();
        style.font = getMainGame().getBitmapFont();
        style.fontColor = Color.WHITE;
        time = new Label("  Time:00:00", style);
        time.setFontScale(0.2f);
        time.setSize(time.getPrefWidth(), time.getPrefHeight());
        time.setPosition(5, getHeight()-time.getHeight()-2);
        addActor(time);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    setTime(format(++m));
                }
            }
        }).start();
    }

    private void setTime(String t) {
        time.setText(t);
        time.setSize(time.getPrefWidth(), time.getPrefHeight());
    }

    private String format(int t) {
        String time = "Time:" + (t/60>10?t/60:("0"+t/60)) + ":" + (t > 10 ? t : "0" + t);
        return time;
    }
}
