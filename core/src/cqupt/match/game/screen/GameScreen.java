package cqupt.match.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import cqupt.match.game.Monopoly;
import cqupt.match.game.stage.DiceStage;
import cqupt.match.game.stage.GameStage;
import cqupt.match.game.stage.HelpStage;

public class GameScreen extends ScreenAdapter {

    private Monopoly mainGame;
    private GameStage gameStage;
    private DiceStage diceStage;
    private HelpStage helpStage;

    public GameScreen(Monopoly mainGame) {
        this.mainGame = mainGame;
        init();
    }

    public void init() {
        gameStage = new GameStage(mainGame,new StretchViewport(
                mainGame.getWorldWidth(),
                mainGame.getWorldHeight()
        ));
        diceStage = new DiceStage(mainGame,new StretchViewport(
                mainGame.getWorldWidth(),
                mainGame.getWorldHeight()
        ));

        helpStage = new HelpStage(mainGame,new StretchViewport(
                mainGame.getWorldWidth(),
                mainGame.getWorldHeight()
        ));

        gameStage.setVisible(true);
        Gdx.input.setInputProcessor(gameStage);
    }

    public void setShowDiceStage(boolean visible){
        diceStage.setVisible(visible);
        if (visible){
            Gdx.input.setInputProcessor(diceStage);
        }else {
            Gdx.input.setInputProcessor(gameStage);
        }
    }

    public void setShowHelpStage(boolean visible){
        helpStage.setVisible(visible);
        if (visible){
            Gdx.input.setInputProcessor(helpStage);
        }else {
            Gdx.input.setInputProcessor(gameStage);
        }
    }

    public DiceStage getDiceStage() {
        return diceStage;
    }

    public GameStage getGameStage() {
        return gameStage;
    }

    //绘制游戏
    @Override
    public void render(float delta) {
        Color color = new Color(Color.WHITE);
        Gdx.gl.glClearColor(color.r, color.g, color.b, color.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gameStage.act();
        gameStage.draw();
        if (diceStage.isVisible()){
            diceStage.act();
            diceStage.draw();
        }
        if(helpStage.isVisible()){
            helpStage.act();
            helpStage.draw();
        }
    }
}
