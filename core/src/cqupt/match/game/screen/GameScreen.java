package cqupt.match.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;

import cqupt.match.game.Monopoly;

public class GameScreen extends ScreenAdapter {

    private Monopoly mainGame;

    public GameScreen(Monopoly mainGame) {
        this.mainGame = mainGame;
        init();
    }

    public void init() {

    }

    //绘制游戏
    @Override
    public void render(float delta) {
        Color color = new Color(Color.WHITE);
        Gdx.gl.glClearColor(color.r, color.g, color.b, color.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    }
}
