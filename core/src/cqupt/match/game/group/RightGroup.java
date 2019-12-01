package cqupt.match.game.group;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import cqupt.match.game.Monopoly;
import cqupt.match.game.group.base.BaseGroup;
import cqupt.match.game.resource.Res;

public class RightGroup extends BaseGroup {

    private Label tips;
    private boolean isShowTip = false;
    public RightGroup(Monopoly mainGame) {
        super(mainGame);
        init();
    }

    private void init(){
        Image bgImage = new Image(getMainGame().getTextureAtlas().findRegion(Res.Atlas.BG_BLACK));
        bgImage.setColor(0,0,0,0.6f);
        bgImage.setSize(35,25);
        bgImage.setPosition(getMainGame().getWorldWidth()-36,100);
        bgImage.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event,float x,float y){
                getMainGame().getGameScreen().setShowDiceStage(true);
            }
        });
        addActor(bgImage);


        Label.LabelStyle style = new Label.LabelStyle();
        style.font = getMainGame().getBitmapFont();
        style.fontColor = Color.WHITE;
        Label button = new Label("扔骰子",style);
        button.setFontScale(0.3f);
        button.setSize(button.getPrefWidth(),button.getPrefHeight());
        button.setColor(Color.GRAY);
        button.setPosition(getMainGame().getWorldWidth()-button.getWidth(),110);
        addActor(button);

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
