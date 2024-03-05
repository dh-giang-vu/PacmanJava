import bagel.Image;
import bagel.util.Point;

import java.util.ArrayList;
import java.util.Random;
import java.lang.Math;

/**
 * Abstract class representing all ghosts
 * Implements Edible since ghost can be eaten during frenzy mode
 */
public abstract class Ghost extends MovingEntity implements Edible {
    private final Image GHOST_NORMAL;
    private final Image GHOST_FRENZY = new Image("res/ghostFrenzy.png");
    private final Random rand = new Random();
    private boolean hidden;
    private int point;

    /**
     * Create a ghost according to the parameters
     *
     * @param image image representing ghost
     * @param topLeft initial top left coordinate of ghost
     * @param speed speed of ghost at creation - NORMAL_SPEED
     * @param frenzySpeed FRENZY_SPEED
     * @param direction direction of ghost at creation
     */
    public Ghost(Image image, Point topLeft, double speed, double frenzySpeed, double direction) {
        super(image, topLeft, speed, frenzySpeed, direction);
        GHOST_NORMAL = image;
        hidden = false;
        point = 0;
    }

    /**
     * Move ghost in its current direction, according to its speed
     * Update collision box after movement
     */
    public void move() {
        double x = getTopLeft().x;
        double y = getTopLeft().y;

        if (getDirection() == RIGHT) {
            x += getSpeed();
        }
        else if (getDirection() == LEFT) {
            x -= getSpeed();
        }
        else if (getDirection() == UP) {
            y -= getSpeed();
        }
        else if (getDirection() == DOWN) {
            y += getSpeed();
        }

        setTopLeft(new Point(x, y));
        updateCollisionBox();
    }

    /**
     * Ghost's behaviour during frenzy
     */
    @Override
    public void goFrenzy() {
        // Ghost has points in Frenzy mode + change sprite
        point = 30;
        setSprite(GHOST_FRENZY);
    }

    /**
     * Normal ghost's behaviour
     */
    @Override
    public void goNormal() {
        // Ghost has no points + change sprite
        point = 0;
        setSprite(GHOST_NORMAL);
    }

    /**
     * Reverse direction of ghost: left -> right, up -> down, etc.
     */
    private void reverseDirection() {
        double newDirection = getDirection();
        newDirection += Math.PI;
        if (newDirection > 3*Math.PI/2) {
            newDirection -= 2*Math.PI;
        }
        setDirection(newDirection);
    }

    /**
     * Move ghost back to its spawn location
     */
    private void resetGhost() {
        setTopLeft(getSpawnLocation());
        updateCollisionBox();
    }

    /**
     * Randomly set direction of ghost
     */
    public void setRandomDirection() {
        setDirection(rand.nextInt(3)*Math.PI/2);
    }

    /**
     * Respawn a ghost that was eaten during frenzy mode
     */
    public void respawn() {
        resetGhost();
        setHidden(false);
    }

    /**
     * Ghost goes in opposite direction when collide with wall, except for PinkGhost.
     */
    @Override
    public void wallCollision() {
        reverseDirection();
    }

    /**
     * Ghost reset to spawn location when collide with player during normal mode
     */
    @Override
    public void playerGhostCollision() {
        resetGhost();
    }

    /**
     * Ghost does not interact with edibles
     */
    @Override
    public boolean edibleCollision(ArrayList<Edible> edibles, Edible edible) {
        // Do nothing
        return false;
    }

    /**
     *
     * @return point value when ghost is eaten by player
     */
    @Override
    public int getPoint() {
        return point;
    }

    /**
     *
     * @return hidden: true, when ghost was eaten
     *                 false, otherwise
     */
    public boolean getHidden() {
        return hidden;
    }

    /**
     * Set hidden to true or false
     *
     * @param bool true or false
     */
    public void setHidden(boolean bool) {
        hidden = bool;
        if (hidden) {
            point = 0;
        }
    }
}
