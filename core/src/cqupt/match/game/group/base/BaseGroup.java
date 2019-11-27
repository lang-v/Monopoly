package cqupt.match.game.group.base;

import com.badlogic.gdx.scenes.scene2d.Group;

import cqupt.match.game.Monopoly;

public class BaseGroup extends Group {
    Monopoly mainGame;

    public BaseGroup(Monopoly mainGame){
        this.mainGame = mainGame;
    }

    public Monopoly getMainGame() {
        return mainGame;
    }
}
