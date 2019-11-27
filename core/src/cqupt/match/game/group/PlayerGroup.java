package cqupt.match.game.group;

import com.badlogic.gdx.scenes.scene2d.ui.Image;

import java.util.List;

import cqupt.match.game.Monopoly;
import cqupt.match.game.group.base.BaseGroup;
import cqupt.match.game.resource.Res;

public class PlayerGroup extends BaseGroup {

    //玩家集合
    List<Player> players;
    public PlayerGroup(Monopoly mainGame,List<Player> list) {
        super(mainGame);
        players = list;
        init();
    }

    private void init(){
        for (int i = 0;i<players.size();i++){
            addPlayer(i);
        }
    }

    private void addPlayer(int index){
        switch (index){
            case 0:
                players.get(index).setImage(new Image(getMainGame().getTextureAtlas().findRegion(Res.Atlas.PLAYER_ONE)),index* Res.PLAYER_WIDTH);
                break;
            case 1:
                players.get(index).setImage(new Image(getMainGame().getTextureAtlas().findRegion(Res.Atlas.PLAYER_TWO)),index* Res.PLAYER_WIDTH);
                break;
            case 2:
                players.get(index).setImage(new Image(getMainGame().getTextureAtlas().findRegion(Res.Atlas.PLAYER_THREE)),index* Res.PLAYER_WIDTH);
                break;
            case 3:
                players.get(index).setImage(new Image(getMainGame().getTextureAtlas().findRegion(Res.Atlas.PLAYER_FOUR)),index* Res.PLAYER_WIDTH);
                break;
            case 4:
                players.get(index).setImage(new Image(getMainGame().getTextureAtlas().findRegion(Res.Atlas.PLAYER_FIVE)),index* Res.PLAYER_WIDTH);
                break;
            case 5:
                players.get(index).setImage(new Image(getMainGame().getTextureAtlas().findRegion(Res.Atlas.PLAYER_SIX)),index* Res.PLAYER_WIDTH);
                break;
        }
        addActor(players.get(index));
    }

    public List<Player> getPlayers() {
        return players;
    }
}
