package cqupt.match.game.stage;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.Random;

import cqupt.match.game.Monopoly;
import cqupt.match.game.resource.Res;
import cqupt.match.game.stage.base.BaseStage;

public class DiceStage extends BaseStage {

    public DiceStage(Monopoly mainGame, Viewport viewport) {
        super(mainGame, viewport);
        init();
    }

    private void init(){
        Image bg = new Image(getMainGame().getTextureAtlas().findRegion(Res.Atlas.BG_BLACK));
        bg.setScale(getMainGame().getWorldWidth()/bg.getImageWidth(),getMainGame().getWorldHeight()/bg.getImageHeight());
        addActor(bg);
    }

    public int getPoint(){
        Random random = new Random(System.currentTimeMillis());
        int point = random.nextInt(6)+1;
        Label.LabelStyle style = new Label.LabelStyle();
        style.font = getMainGame().getBitmapFont();
        style.fontColor = Color.BLACK;
        Label tip = new Label(point+"",style);
        addActor(tip);
        return point;
    }

    public void close(){

    }
}
