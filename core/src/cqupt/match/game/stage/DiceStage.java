package cqupt.match.game.stage;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.Random;

import cqupt.match.game.Monopoly;
import cqupt.match.game.resource.Res;
import cqupt.match.game.stage.base.BaseStage;

public class DiceStage extends BaseStage {

    Label tip;
    int point;
    public DiceStage(Monopoly mainGame, Viewport viewport) {
        super(mainGame, viewport);
        init();
    }

    private void init() {
        Image bg = new Image(getMainGame().getTextureAtlas().findRegion(Res.Atlas.BG_BLACK));
        bg.setColor(0, 0, 0, 0.6f);
        bg.setScale(getWidth(), getHeight());
        bg.setPosition(0, 0);
        addActor(bg);

        addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                getMainGame().getGameScreen().setShowDiceStage(false);
                if (tip!=null)
                    tip.remove();
            }
        });

        addListener(new InputListener() {
            @Override
            public boolean keyUp(InputEvent event, int keycode) {
                if (keycode == Input.Keys.BACK) {
                    //捕获系统返回键
                    //关闭游戏帮助显示游戏舞台
                    getMainGame().getGameScreen().setShowDiceStage(false);
                    if (tip!=null)
                        tip.remove();
                }
                return super.keyUp(event, keycode);
            }
        });
    }

    @Override
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (visible)
                    DiceStage.this.getPoint();
                else
                    getMainGame().getGameScreen().getGameStage().move(getMainGame().getIndex(), point);
            }
        }).start();

    }

    public void getPoint() {
        Random random = new Random(System.currentTimeMillis());
        final int point = random.nextInt(6) + 1;
        Label.LabelStyle style = new Label.LabelStyle();
        style.font = getMainGame().getBitmapFont();
        style.fontColor = Color.BLACK;
        tip = new Label(point + "", style);
        tip.setPosition(getWidth() / 2 - tip.getWidth() / 2, getHeight() / 2 - tip.getHeight() / 2);
        addActor(tip);
        this.point = point;
    }
}
