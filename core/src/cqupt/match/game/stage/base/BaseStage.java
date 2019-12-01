package cqupt.match.game.stage.base;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

import cqupt.match.game.Monopoly;

public class BaseStage extends Stage {

    private boolean isVisible = false;
    private Monopoly mainGame;

    public BaseStage(Monopoly mainGame, Viewport viewport){
        super(viewport);
        this.mainGame = mainGame;
    }

    public BaseStage(Monopoly mainGame) {
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public Monopoly getMainGame() {
        return mainGame;
    }
}
