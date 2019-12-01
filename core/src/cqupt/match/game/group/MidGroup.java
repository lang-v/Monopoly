package cqupt.match.game.group;

import com.badlogic.gdx.scenes.scene2d.ui.Image;

import java.util.ArrayList;
import java.util.List;

import cqupt.match.game.Monopoly;
import cqupt.match.game.group.base.BaseGroup;
import cqupt.match.game.resource.Res;

public class MidGroup extends BaseGroup {

    //20个方格
    CardGroup cardGroups[][];
    //玩家
    PlayerGroup playerGroup;

    public MidGroup(Monopoly mainGame,float width,float height) {
        super(mainGame);
        init(width,height);
    }

    private void init(float width,float height) {
        cardGroups = new CardGroup[4][5];
        for (int i = 0; i < 4; i++) {
            for (int j =0;j<5;j++){
                if (i==0){
                    cardGroups[i][j] = new CardGroup(getMainGame(),0,j*width,width,height ,Res.Proper.START);
                    if (j==0)
                        cardGroups[i][j].setProper(Res.Proper.START);
                    addActor(cardGroups[i][j]);
                }
                if (i==1){
                    cardGroups[i][j] = new CardGroup(getMainGame(),(j+1)*width,4*width,width,height,Res.Proper.START);
                    addActor(cardGroups[i][j]);
                }
                if (i==2){
                    cardGroups[i][j] = new CardGroup(getMainGame(),6*width,(4-j)*width,width,height,Res.Proper.START);
                    addActor(cardGroups[i][j]);
                }
                if (i==3){
                    cardGroups[i][j] = new CardGroup(getMainGame(),(5-j)*width,0,width,height,Res.Proper.START);
                    addActor(cardGroups[i][j]);
                }
            }
        }



        List<Player> players = new ArrayList<>();
        for (int i=0;i<getMainGame().getNumber();i++){
            players.add(new Player(getMainGame(),"",i));
        }
        playerGroup = new PlayerGroup(getMainGame(),players);
        addActor(playerGroup);
    }

    public void setCardGroups(int proper[]){
        for (int i =0;i<proper.length;i++){
            cardGroups[i/5][i%5].setProper(proper[i]);
        }
    }

    public PlayerGroup getPlayerGroup() {
        return playerGroup;
    }

    public CardGroup getCardGroups(int index) {
        while (index>20)
            index/=20;
        CardGroup card = cardGroups[index/5][index%5];
        card.setShow();
        return card;
    }
}
