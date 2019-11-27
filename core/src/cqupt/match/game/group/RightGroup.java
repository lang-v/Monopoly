package cqupt.match.game.group;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import cqupt.match.game.Monopoly;
import cqupt.match.game.group.base.BaseGroup;

public class RightGroup extends BaseGroup {

    private Label tips;
    private boolean isShowTip = false;
    public RightGroup(Monopoly mainGame) {
        super(mainGame);
        init();
    }

    private void init(){
        Label.LabelStyle style = new Label.LabelStyle();
        style.font = getMainGame().getBitmapFont();
        style.fontColor = Color.BLACK;
        Label button = new Label("Throw dice",style);
        button.setFontScale(0.2f);
        button.setSize(button.getPrefWidth(),button.getPrefHeight());
        button.setColor(Color.GRAY);
        button.setPosition(getMainGame().getWorldWidth()-button.getWidth(),50);
        addActor(button);

        tips = new Label("轮到你了",style);
        tips.setPosition(getMainGame().getWorldWidth()/2,getMainGame().getWorldHeight()/2);
        tips.setSize(tips.getPrefWidth(),tips.getPrefHeight());
        tips.setColor(Color.GRAY);

        button.addListener(new ClickListener(){
            @Override
            public void clicked (InputEvent event, float x, float y) {
                getMainGame().getGameScreen().setShowDiceStage(true);
            }
        });
    }

    public void turn(){
        if (isShowTip){
            isShowTip = false;
            removeActor(tips);
        }else {
            addActor(tips);
        }
    }
}
