package cqupt.match.game.group;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.ScaleToAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;

import cqupt.match.game.Monopoly;
import cqupt.match.game.group.base.BaseGroup;
import cqupt.match.game.resource.Res;

public class Player extends BaseGroup {

    private Image image;
    private String name = "";
    private float x = 0,y=0;
    private int index = -1;
    private int currentPosition = 0;
    private boolean isFirst = true;

    public Player(Monopoly mainGame, String name, int index){
        super(mainGame);
        this.name = name;
        this.index = index;
    }

    public String getName() { return name; }

    public void setImage(Image image,float y) {
        this.image = image;
        //this.image.setSize(Res.PLAYER_WIDTH,Res.PLAYER_HEIGHT);
        this.image.setPosition(-40,y);
        x = -40;
        this.y = y;
        this.image.setBounds(x,y,Res.PLAYER_WIDTH,Res.PLAYER_HEIGHT);
        image.setOrigin(Align.center);
        addActor(image);
    }

    public Image getImage() {
        return image;
    }

    public void move(CardGroup cardGroup){
        System.out.println("SL: x="+getX()+"to x="+cardGroup.getX()+" y="+getY()+"to y="+cardGroup.getY()+" width="+cardGroup.getWidth()+"height="+cardGroup.getHeight());
        MoveToAction move = Actions.moveTo(cardGroup.getX()+cardGroup.getWidth()+3,cardGroup.getY()+cardGroup.getHeight()/2-15,1.0f);
        addAction(move);
        System.out.println("SL: x="+getX()+"to x="+cardGroup.getX()+" y="+getY()+"to y="+cardGroup.getY()+" width="+cardGroup.getWidth()+"height="+cardGroup.getHeight());
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }

    public boolean isFirst() {
        boolean temp = isFirst;
        isFirst = false;
        return temp;
    }
}
