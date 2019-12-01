package cqupt.match.game.resource;

/**
 * 常量，资源文件目录
 */
public interface Res {

    /**
     * 默认服务器端口号,
     */
    int PORT = 1999;

    //xml文件
    String XML_PATH = "DATA";
    String NAME = "name";
    String IP = "ip";

    /**
     * 玩家人数
     */
    String PLAYER_NUMBER = "player_number";

    /**
     * 人物的宽度，正方形
     */
    float PLAYER_WIDTH = 30;
    float PLAYER_HEIGHT = PLAYER_WIDTH;

    /**
     * 世界的宽度
     */
    float FIX_WORLD_WIDTH = 420;

    /**
     * 位图字体路径
     */
    String BITMAP_FONT_PATH = "font/font.fnt";

    /**
     * 纹理图集路径
     */
    String ATLAS_PATH = "atlas/image_atlas.atlas";

    /**
     * 格子的属性值
     */
    interface Proper{
        int FIRST = 1;
        int START = 2;              /*开始格*/
        int COMMON = 4;             /*普通格*/
        int QUESTION = 8;           /*问题*/
        int BLOOD = 16;             /*加血包*/
        int MINE = 32;              /*地雷*/
        int RANDOM = 64;            /*随机道具框*/
    }

    /**
     * 纹理图集的小图名称常量
     */
    interface Atlas {

        //背景图片
        String BG_LOGO = "bg_small";
        String BG_BLACK = "bg_black";
        String HELP = "39308A78971D7A438A0E4DAA605126AE";

        String COMMON_BOARD = "board_common";
        String QUESTION_BOARD = "board_question";
        String BLOOD_BOARD = "board_blood";
        String MINE_BOARD = "board_mine";
        String RANDOM_BOARD = "board_random";   //道具格
        String START_BOARD = "board_start";
        String FIRST_BOARD = "board_first";

        //玩家头像
        String PLAYER_ONE = "player_one";
        String PLAYER_TWO = "player_two";
        String PLAYER_THREE = "player_three";
        String PLAYER_FOUR = "player_four";
        String PLAYER_FIVE = "player_five";
        String PLAYER_SIX = "player_six";
        String PLAYER_SEVEN = "player_seven";
    }

}
