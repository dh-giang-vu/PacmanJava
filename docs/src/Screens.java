import bagel.util.Point;

/**
 * Class represents non-playing Screens
 */
public class Screens {
    private final ShadowPac GAME;

    private final static int SMALL_INS_SIZE = 24;
    private final static int BIG_INS_SIZE = 40;

    private final static Point TITLE_COORDINATE = new Point(260, 250);
    private final Message TITLE_MESSAGE;

    private final static String INS_1 = "PRESS SPACE TO START";
    private final Message INS_1_MESSAGE;

    private final static String INS_2 = "USE ARROW KEYS TO MOVE";
    private final Message INS_2_MESSAGE;

    private final static String INS_3 = "EAT THE PELLET TO ATTACK";
    private final Message INS_3_MESSAGE;

    private final static String LVL_WIN = "LEVEL COMPLETE!";
    private final Message LVL_WIN_MESSAGE;

    private final static String WIN = "WELL DONE!";
    private final Message WIN_MESSAGE;

    private final static String LOSE = "GAME OVER!";
    private final Message LOSE_MESSAGE;


    /**
     * Create a Screen and instantiate all of its Message
     *
     * @param game object used to get the game's title and window size
     */
    public Screens(ShadowPac game) {
        GAME = game;

        TITLE_MESSAGE = new Message(GAME.getGameTitle());
        TITLE_MESSAGE.setBottomLeft(TITLE_COORDINATE);

        INS_1_MESSAGE = new Message(INS_1);
        INS_2_MESSAGE = new Message(INS_2);
        INS_3_MESSAGE = new Message(INS_3);

        LVL_WIN_MESSAGE = new Message(LVL_WIN);
        WIN_MESSAGE = new Message(WIN);
        LOSE_MESSAGE = new Message(LOSE);

    }

    /**
     * Show Game Title and instructions for level 0
     */
    public void showTitleScreen() {
        Point line1Coord = new Point(TITLE_COORDINATE.x + 60, TITLE_COORDINATE.y + 190);
        INS_1_MESSAGE.setBottomLeft(line1Coord);

        INS_1_MESSAGE.setSize(SMALL_INS_SIZE);
        INS_2_MESSAGE.setSize(SMALL_INS_SIZE);

        TITLE_MESSAGE.showMessage();
        INS_1_MESSAGE.showMessage();
        INS_2_MESSAGE.showMessageCentredBelow(INS_1_MESSAGE);
    }

    /**
     * Show instructions for level 1
     */
    public void showLevel1StartScreen() {
        Point line1Coord = new Point(200, 350);
        INS_1_MESSAGE.setBottomLeft(line1Coord);

        INS_1_MESSAGE.setSize(BIG_INS_SIZE);
        INS_2_MESSAGE.setSize(BIG_INS_SIZE);
        INS_3_MESSAGE.setSize(BIG_INS_SIZE);

        INS_1_MESSAGE.showMessage();
        INS_2_MESSAGE.showMessageCentredBelow(INS_1_MESSAGE);
        INS_3_MESSAGE.showMessageCentredBelow(INS_2_MESSAGE);
    }

    /**
     * Display message when a level is cleared
     */
    public void showLevelComplete() {
        LVL_WIN_MESSAGE.showMessageCentred(GAME.getWindowWidth(), GAME.getWindowHeight());
    }

    /**
     * Display congratulatory message when game is won
     */
    public void showWinScreen() {
        WIN_MESSAGE.showMessageCentred(GAME.getWindowWidth(), GAME.getWindowHeight());
    }

    /**
     * Display message when game is lost
     */
    public void showLoseScreen() {
        LOSE_MESSAGE.showMessageCentred(GAME.getWindowWidth(), GAME.getWindowHeight());
    }
}
