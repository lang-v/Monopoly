package cqupt.match.game.stage;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.List;

import cqupt.match.game.Monopoly;
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

        float height = getMainGame().getWorldHeight();
        midGroup = new MidGroup(getMainGame(),height/5,height/5);
        midGroup.setSize(getMainGame().getWorldHeight()/5*7,getMainGame().getWorldHeight());
        midGroup.setPosition(getMainGame().getWorldWidth()/2 -midGroup.getWidth()/2, 0);
        addActor(midGroup);

        rightGroup = new RightGroup(getMainGame());
        addActor(rightGroup);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                move(0,14);
                move(1,5);
            }
        }).start();
    }

    public void move(int index,int target){
        List<Player> players = midGroup.getPlayerGroup().getPlayers();
        Player player = players.get(index);
        if (player.isFirst()){
            //第一次先跳到第一格
            player.move(midGroup.getCardGroups(0));
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        player.move(midGroup.getCardGroups(target));
    }

    public RightGroup getRightGroup() {
        return rightGroup;
    }
}
