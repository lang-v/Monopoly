package cqupt.match.game.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;

import cqupt.match.game.Monopoly;
import cqupt.match.game.resource.Res;
import cqupt.match.game.stage.base.BaseStage;

public class HelpStage extends BaseStage {

    public HelpStage(Monopoly mainGame, Viewport viewport){
        super(mainGame,viewport);
        init();
    }

    private void init(){
        //空白图片做背景
        Image bgImage = new Image(getMainGame().getTextureAtlas().findRegion(Res.Atlas.BG_BLACK));
        bgImage.setColor(new Color(0, 0, 0, 0.6f));//60%黑色
        //bgImage.setOrigin(0,0);
        bgImage.setSize(getWidth(),getHeight());
        bgImage.setPosition(0,0);
        addActor(bgImage);

        Image image = new Image(getMainGame().getTextureAtlas().findRegion(Res.Atlas.HELP));
        image.setScale(getHeight() / image.getHeight(), getHeight() / image.getHeight());
        image.setPosition(25, 0);
        addActor(image);

        addListener(new InputListener() {
            @Override
            public boolean keyUp(InputEvent event, int keycode) {
                if (keycode == Input.Keys.BACK) {
                    //捕获系统返回键
                    //关闭游戏帮助显示游戏舞台
                    getMainGame().getGameScreen().setShowHelpStage(false);
                }
                return super.keyUp(event, keycode);
            }
        });

        addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //关闭游戏帮助，显示游戏舞台
                getMainGame().getGameScreen().setShowHelpStage(false);
            }
        });
    }
}
