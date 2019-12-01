package cqupt.match.game.stage;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.List;

import cqupt.match.game.Monopoly;
import cqupt.match.game.group.LifeTimer;
import cqupt.match.game.group.MidGroup;
import cqupt.match.game.group.Player;
import cqupt.match.game.group.RightGroup;
import cqupt.match.game.resource.Res;
import cqupt.match.game.stage.base.BaseStage;

public class GameStage extends BaseStage {

    private static MidGroup midGroup;
    private RightGroup rightGroup;

    public GameStage(Monopoly mainGame, Viewport viewport) {
        super(mainGame, viewport);
        init();
    }

    private void init(){
        Image bg = new Image(getMainGame().getTextureAtlas().findRegion(Res.Atlas.BG_LOGO));
        bg.setScale(getMainGame().getWorldWidth() / bg.getWidth(),
                getMainGame().getWorldHeight() / bg.getHeight());
        bg.setPosition(0,0);
        addActor(bg);

        Image help = new Image(getMainGame().getTextureAtlas().findRegion(Res.Atlas.BG_BLACK));
        help.setColor(0,0,0,0.6f);
        Label.LabelStyle style = new Label.LabelStyle();
        style.font = getMainGame().getBitmapFont();
        style.fontColor = Color.WHITE;
        Label label = new Label("帮助",style);
        label.setFontScale(0.3f);
        label.setSize(label.getPrefWidth(),label.getPrefHeight());
        label.setPosition(0,110);
        help.setSize(28,25);
        help.setPosition(0,100);
        help.addListener(new ClickListener(){
            @Override
            public void clicked (InputEvent event, float x, float y) {
                getMainGame().getGameScreen().setShowHelpStage(true);
            }
        });
        addActor(label);
        addActor(help);

        LifeTimer lifeTimer = new LifeTimer(getMainGame());
        lifeTimer.setSize(100,60);
        lifeTimer.setPosition(0,getHeight()-50);
        addActor(lifeTimer);

        float height = getMainGame().getWorldHeight();
        midGroup = new MidGroup(getMainGame(),height/5,height/5);
        midGroup.setSize(getMainGame().getWorldHeight()/5*7,getMainGame().getWorldHeight());
        midGroup.setPosition(getMainGame().getWorldWidth()/2 -midGroup.getWidth()/2, 0);
        midGroup.setCardGroups(new int[]{2,4,4,8,64,4,32,4,8,64,32,16,8,4,64,4,8,4,8,16});
        addActor(midGroup);

        rightGroup = new RightGroup(getMainGame());
        addActor(rightGroup);
    }

    public void move(int index,int target){
        List<Player> players = midGroup.getPlayerGroup().getPlayers();
        Player player = players.get(index);
        if (player.isFirst()){
            //第一次先跳到第一格
            player.move(midGroup.getCardGroups(0));
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        player.setCurrentPosition(target);
        player.move(midGroup.getCardGroups(player.getCurrentPosition()));
    }

    public RightGroup getRightGroup() {
        return rightGroup;
    }
}
