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
     * 世界的宽度
     */
    float FIX_WORLD_WIDTH = 480;

    /**
     * 位图字体路径
     */
    String BITMAP_FONT_PATH = "font/font.fnt";

    /**
     * 纹理图集路径
     */
    String ATLAS_PATH = "";
    /**
     * 输入框的ui
     */
    String UI_JSON = "json/inputUI.json";
    /**
     * 格子的属性值
     */
    interface Proper{
        int COMMON = 0;             /**普通格*/
        int QUESTION = 1;          /**问题*/
        int BLOOD = 2;             /**加血包*/
        int MINE = 3;              /**地雷*/
        int PROP = 4;              /**道具框*/
    }

    /**
     * 纹理图集的小图名称常量
     */
    interface Atlas {

        String GAME_LOGO = "logo";
        String COMMON_BOARD = "common_board";
        String QUESTION_BOARD = "question_board";
        String BLOOD_BOARD = "blood_board";
        String MINE_BOARD = "mine_board";
        String PROP_BOARD = "prop_board";   //道具格
        String START_BOARD = "start_board";
    }

}
