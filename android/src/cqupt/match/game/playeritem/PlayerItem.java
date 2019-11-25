package cqupt.match.game.playeritem;

public class PlayerItem {
    private String name;
    private int index;

    public PlayerItem(String name, int index){
        this.name = name;
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }
}
