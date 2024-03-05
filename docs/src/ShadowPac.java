import bagel.*;

/**
 * SWEN20003 Project 2, Semester 1, 2023
 *
 * Pacman game
 * @author Duc Hang Giang Vu
 */
public class ShadowPac extends AbstractGame  {
    private final static int WINDOW_WIDTH = 1024;
    private final static int WINDOW_HEIGHT = 768;
    private final static String GAME_TITLE = "SHADOW PAC";
    private final Image BACKGROUND_IMAGE = new Image("res/background0.png");

    private final static int GS_TITLE_PAGE = 0;
    private final static int GS_PLAYING = 1;
    private final static int GS_LOSE = 2;
    private final static int GS_WIN = 3;
    private final static int GS_NEXT_LEVEL = 4;

    private final static int DEFAULT_WIN_CONDITION = 0;
    private final static int[] WIN_CONDITION = {DEFAULT_WIN_CONDITION, 800};
    private final static String[] LEVEL_FILE = {"res/level0.csv", "res/level1.csv"};
    private final static int MAX_NUM_LEVEL = 2;

    private final Screens SCREEN;
    private Player pacman;
    private Level level;
    private CollisionHandler collisionHandler;

    private int gameState;
    private int levelChangeCount;
    private int currentLevel;

    /**
     * Create ShadowPac with defined width, height and game title
     * Instantiate Screen object
     * Set gameState, levelChangeCount and currentLevel to starting values
     */
    public ShadowPac(){
        super(WINDOW_WIDTH, WINDOW_HEIGHT, GAME_TITLE);
        SCREEN = new Screens(this);
        gameState = GS_TITLE_PAGE;
        levelChangeCount = -1;
        currentLevel = 0;

    }


    /**
     * The entry point for the program.
     */
    public static void main(String[] args) {
        ShadowPac game = new ShadowPac();
        game.run();
    }

    /**
     * Performs a state update.
     * Allows the game to exit when the escape key is pressed.
     */
    @Override
    protected void update(Input input) {

        BACKGROUND_IMAGE.draw(Window.getWidth()/2.0, Window.getHeight()/2.0);

        if (input.wasPressed(Keys.ESCAPE)){
            Window.close();
        }

        if (input.wasPressed(Keys.SPACE) && gameState == GS_TITLE_PAGE) {
            startLevel();
        }

        /* For debugging: press W to go to next level

        if (input.wasPressed(Keys.W)) {
            // Loop back to level 0 if is currently at the final level
            if (currentLevel+1 == MAX_NUM_LEVEL) {
                currentLevel = 0;
                gameState = GS_TITLE_PAGE;
            }
            // Go to next level
            else {
                currentLevel += 1;
                gameState = GS_NEXT_LEVEL;
            }
        }

         */

        // At title screen
        if (gameState == GS_TITLE_PAGE) {
            SCREEN.showTitleScreen();
        }

        // Gameplay started
        if (gameState == GS_PLAYING) {
            level.showLevel();
            pacman.update(input);
            collisionHandler.checkCollisions();
            pacman.show();

            checkPlayerScore();
            checkPlayerLives();
        }

        // Health is 0 -> lost
        if (gameState == GS_LOSE) {
            SCREEN.showLoseScreen();
        }

        // Cleared all levels
        if (gameState == GS_WIN) {
            SCREEN.showWinScreen();
        }

        // One level cleared -> go to next one
        if (gameState == GS_NEXT_LEVEL) {
            nextLevel(input);
        }

    }

    /**
     *
     * @return title of the game
     */
    public String getGameTitle() {
        return GAME_TITLE;
    }

    /**
     *
     * @return width of game window
     */
    public double getWindowWidth() {
        return WINDOW_WIDTH;
    }

    /**
     *
     * @return height of game window
     */
    public double getWindowHeight() {
        return WINDOW_HEIGHT;
    }

    /**
     * Get new level, new player and new collision handler
     * Update win condition
     */
    private void startLevel() {
        // Instantiate new objects
        level = new Level(LEVEL_FILE[currentLevel]);
        pacman = level.getPacman();
        collisionHandler = new CollisionHandler(pacman, level.getMap());

        // Check and update win condition to level's max score
        if (WIN_CONDITION[currentLevel] == DEFAULT_WIN_CONDITION) {
            WIN_CONDITION[currentLevel] = level.getMaxScore();
        }

        gameState = GS_PLAYING;
    }

    /**
     * Show level complete screen for 300 frames, then wait for player's
     * input to start the next level
     *
     * @param input check if input is SPACE before showing the next level
     */
    private void nextLevel(Input input) {
        // Start timer for showing next level screen
        if (levelChangeCount < 0) {
            levelChangeCount = 300;
            return;
        }

        // Show next level screen and update timer
        if (levelChangeCount > 0) {
            levelChangeCount -= 1;
            SCREEN.showLevelComplete();
            return;
        }

        // Countdown finished, go to next level
        if (levelChangeCount == 0) {
            // Instruction screen for level 1
            SCREEN.showLevel1StartScreen();
            if (input.wasPressed(Keys.SPACE)) {
                startLevel();
                levelChangeCount = -1;
            }
        }
    }

    /**
     * Check for win conditions
     * Level is cleared when level's win condition is met
     * Wins the game when all levels have been cleared
     */
    private void checkPlayerScore() {
        int score = pacman.getPlayerScore();
        // Check if win condition is reached
        if (!(score >= WIN_CONDITION[currentLevel])) {
            return;
        }
        // Check if this is the final level
        if (currentLevel+1 == MAX_NUM_LEVEL) {
            gameState = GS_WIN;
        }
        // Not final level, go to next level
        else {
            currentLevel += 1;
            gameState = GS_NEXT_LEVEL;
        }
    }

    /**
     * Check for lose condition (0 heart)
     */
    private void checkPlayerLives() {
        int lives = pacman.getLives();
        if (lives > 0) {
            return;
        }
        gameState = GS_LOSE;
    }
}
