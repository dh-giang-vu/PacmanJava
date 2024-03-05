import bagel.*;
import bagel.util.Point;

import java.util.ArrayList;

/**
 * Class represents Player
 */
public class Player extends MovingEntity {
    private final Image PAC_CLOSED = new Image("res/pac.png");
    private final Image PAC_OPEN = new Image("res/pacOpen.png");
    private static final double NORMAL_SPEED = 3;
    private static final double FRENZY_SPEED = 4;
    private static final DrawOptions ROTATION = new DrawOptions();

    private static final Point SCORE_COORDINATE = new Point(25, 25);
    private final Font SCORE_WRITER = new Font("res/FSO8BITR.TTF", 20);
    private int playerScore;

    private static final int HEART_OFFSET = 30;
    private static final int NUM_HEART = 3;
    private int lives;
    private Heart[] hearts;

    private int frameCount;

    // Brief time period where pacman takes
    // no input after he's resurrected
    private int recoveryTime;

    /**
     * Create player facing right with default pacman image and speeds
     * Player's spawn location depends on level file
     *
     * @param topLeft top left spawn coordinate from level file
     */
    public Player(Point topLeft) {
        super(new Image("res/pac.png"), topLeft, NORMAL_SPEED, FRENZY_SPEED, RIGHT);
        frameCount = 0;
        playerScore = 0;
        createHearts(NUM_HEART);
        recoveryTime = 0;
    }

    /**
     * Move and animate pacman according to
     * keyboard input from user
     *
     * @param input user input: arrow keys
     */
    public void update(Input input) {
        // Get pacman current location
        double x = getTopLeft().x;
        double y = getTopLeft().y;

        // Check if pacman recently died
        if (recoveryTime > 0) {
            recoveryTime -= 1;
        }
        else {
            // Calculate new location base on input
            if (input.isDown(Keys.RIGHT)) {
                x += getSpeed();
                setDirection(RIGHT);
            }
            else if (input.isDown(Keys.LEFT)) {
                x -= getSpeed();
                setDirection(LEFT);
            }
            else if (input.isDown(Keys.UP)) {
                y -= getSpeed();
                setDirection(UP);
            }
            else if (input.isDown(Keys.DOWN)) {
                y += getSpeed();
                setDirection(DOWN);
            }
            ROTATION.setRotation(getDirection());
        }

        // Change Pacman animation every 15 frames
        frameCount++;
        if (frameCount == 15) {
            setSprite(PAC_OPEN);
        }
        else if (frameCount == 30) {
            setSprite(PAC_CLOSED);
            frameCount = 0;
        }

        // Update pacman location
        setTopLeft(new Point(x, y));
        // Move collision box to pacman new location
        updateCollisionBox();
    }

    /**
     * Display player at current location
     */
    @Override
    public void show() {
        getSprite().drawFromTopLeft(getTopLeft().x, getTopLeft().y, ROTATION);
    }

    /**
     * Handle player's collision with wall
     * Stops player from moving forward in the same direction
     */
    @Override
    public void wallCollision() {
        // Get pacman location
        double x = getTopLeft().x;
        double y = getTopLeft().y;
        double currDirection = getDirection();

        // Stop pacman from moving by reversing the effect from
        // user's keyboard input
        if (currDirection == UP) {
            y += getSpeed();
        }
        else if (currDirection == DOWN) {
            y -= getSpeed();
        }
        else if (currDirection == LEFT) {
            x += getSpeed();
        }
        else if (currDirection == RIGHT) {
            x -= getSpeed();
        }

        // Update pacman location
        setTopLeft(new Point(x, y));
    }

    /**
     * Handle player's collision with ghost
     * Lose 1 life, reset pacman to original spawn location facing right
     * Briefly stops evaluating user input: to show that player faces right after the reset
     */
    @Override
    public void playerGhostCollision() {
        setTopLeft(getSpawnLocation());
        updateCollisionBox();
        setDirection(RIGHT);
        ROTATION.setRotation(getDirection());
        recoveryTime = 5;
        lives -= 1;
    }

    /**
     *
     * @param edibles ArrayList of objects that implements Edible interface
     * @param edible An object in edibles ArrayList
     * Function return false if the edible is not Pellet.
     * Function return true if the edible is a Pellet, in which
     * case this will tell the game to go into Frenzy mode.
     *
     */
    @Override
    public boolean edibleCollision(ArrayList<Edible> edibles, Edible edible) {
        // Update score
        playerScore += edible.getPoint();

        if (edible instanceof Ghost) {
            if (edible.getPoint() != 0) {
                // Ghost is in frenzy mode -> hide eaten ghost
                ((Ghost) edible).setHidden(true);
            }
            // Don't remove ghost from edible list since Ghost might be
            // able to be eaten more than once in future levels
            return false;
        }

        boolean isPellet = edible instanceof Pellet;
        edibles.remove(edible);
        return isPellet;
    }

    /**
     * Player's frenzy behaviour
     */
    @Override
    public void goFrenzy() {
        // Nothing to do
    }

    /**
     * Player's normal behaviour
     */
    @Override
    public void goNormal() {
        // Nothing to do
    }

    /**
     *
     * @return current score
     */
    public int getPlayerScore() {
        return playerScore;
    }

    /**
     *
     * @return current number of lives
     */
    public int getLives() {
        return lives;
    }

    /**
     * Display current score
     */
    public void showScore() {
        SCORE_WRITER.drawString("Score " +
                playerScore, SCORE_COORDINATE.x, SCORE_COORDINATE.y);
    }

    /**
     * Create Array of Heart objects
     *
     * @param numHearts number of hearts
     */
    private void createHearts(int numHearts) {
        lives = numHearts;
        hearts = new Heart[lives];
        double heartX = 900;
        double heartY = 10;

        for (int i = 0; i < lives; i++) {
            hearts[i] = new Heart(new Point(heartX+(HEART_OFFSET*i), heartY));
        }
    }

    /**
     * Display hearts
     */
    public void renderHearts() {
        for (int i = 0; i < lives; i++) {
            hearts[i].show();
        }
    }

}
