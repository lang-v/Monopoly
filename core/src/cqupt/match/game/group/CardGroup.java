package cqupt.match.game.group;

import com.badlogic.gdx.scenes.scene2d.ui.Image;

import cqupt.match.game.Monopoly;
import cqupt.match.game.group.base.BaseGroup;
import cqupt.match.game.resource.Res;

public class CardGroup extends BaseGroup {

    private float x = 0, y = 0;
    private int proper = 0;
    private Image bgImage = null;
    public static float width = 0, height = 0;
    private boolean isShow = false;

    public CardGroup(Monopoly mainGame, float x, float y, float width, float height, int proper) {
        super(mainGame);
        this.x = x;
        this.y = y;
        this.proper = proper;
        this.width = width;
        this.height = height;
        setProper(proper);
    }

    public int getProper() {
        return proper;
    }

    public void setProper(int proper) {
        this.proper = proper;
        setBgImage(proper);
    }

    private void setBgImage(int proper) {
        //初始地板
        if (bgImage == null) {
            proper = Res.Proper.FIRST;
        } else {
            removeActor(bgImage);
        }
        switch (proper) {
            case Res.Proper.FIRST:
                bgImage = new Image(getMainGame().getTextureAtlas().findRegion(Res.Atlas.FIRST_BOARD));
                break;
            case Res.Proper.START:
                bgImage = new Image(getMainGame().getTextureAtlas().findRegion(Res.Atlas.START_BOARD));
                break;
            case Res.Proper.COMMON:
                bgImage = new Image(getMainGame().getTextureAtlas().findRegion(Res.Atlas.COMMON_BOARD));
                break;
            case Res.Proper.BLOOD:
                bgImage = new Image(getMainGame().getTextureAtlas().findRegion(Res.Atlas.BLOOD_BOARD));
                break;
            case Res.Proper.MINE:
                bgImage = new Image(getMainGame().getTextureAtlas().findRegion(Res.Atlas.MINE_BOARD));
                break;
            case Res.Proper.QUESTION:
                bgImage = new Image(getMainGame().getTextureAtlas().findRegion(Res.Atlas.QUESTION_BOARD));
                break;
            case Res.Proper.RANDOM:
                bgImage = new Image(getMainGame().getTextureAtlas().findRegion(Res.Atlas.RANDOM_BOARD));
                break;
        }
        bgImage.setSize(width, height);
        bgImage.setOrigin(width / 2, height / 2);//原点移动到中间
        bgImage.setPosition(x, y);
        addActor(bgImage);
    }

    public void setShow() {
        if (isShow) {
            return;
        }
        isShow = true;
        setBgImage(proper);
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }

    @Override
    public float getHeight() {
        return height;
    }

    @Override
    public float getWidth() {
        return width;
    }

    public boolean isShow() {
        return isShow;
    }
}
