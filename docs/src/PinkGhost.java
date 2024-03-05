import bagel.Image;
import bagel.util.Point;
import java.util.Random;
import java.lang.Math;

/**
 * Concrete class representing Pink Ghost
 */
public class PinkGhost extends Ghost{
    private static final double NORMAL_SPEED = 3;
    private static final double FRENZY_SPEED = 2.5;
    Random rand = new Random();

    /**
     * Create a Pink Ghost
     *
     * @param topLeft top left coordinate of Pink Ghost
     */
    public PinkGhost(Point topLeft) {
        super(new Image("res/ghostPink.png"), topLeft, NORMAL_SPEED, FRENZY_SPEED, LEFT);
    }

    /**
     * Pink Ghost need a new random direction after colliding with wall
     */
    @Override
    public void wallCollision() {
        /*
         * Set new random direction that is different from
         * the old direction
         */
        int randNum = rand.nextInt(3) + 1;
        double oldDirection = getDirection();
        double newDirection = oldDirection + (randNum * Math.PI/2);

        if (newDirection > 3*Math.PI/2) {
            newDirection -= 2*Math.PI;
        }
        setDirection(newDirection);

        /*
         * This section of code moves the ghost
         * away from the wall that it's touching so that
         * collisionHandler doesn't think that this ghost
         * is constantly colliding with the wall
         */
        double x = getTopLeft().x;
        double y = getTopLeft().y;

        if (oldDirection == UP) {
            y += getSpeed();
        }
        else if (oldDirection == DOWN) {
            y -= getSpeed();
        }
        else if (oldDirection == LEFT) {
            x += getSpeed();
        }
        else if (oldDirection == RIGHT) {
            x -= getSpeed();
        }
        setTopLeft(new Point(x, y));
    }

    /**
     * Reset Pink Ghost to spawn location
     * Choose a random direction
     */
    @Override
    public void playerGhostCollision() {
        super.playerGhostCollision();
        setRandomDirection();
    }

}
