import bagel.Image;
import bagel.util.Point;

import java.util.ArrayList;

/**
 * Extends GameEntity to represent objects that can move
 */
public abstract class MovingEntity extends GameEntity {
    /**
     * Object's direction - RIGHT in radian value
     */
    protected static final double RIGHT = 0;
    /**
     * Object's direction - DOWN in radian value
     */
    protected static final double DOWN = Math.PI/2;
    /**
     * Object's direction - LEFT in radian value
     */
    protected static final double LEFT = Math.PI;
    /**
     * Object's direction - UP in radian value
     */
    protected static final double UP = 3*Math.PI/2;

    private double direction;
    private double speed;
    private final double NORMAL_SPEED;
    private final double FRENZY_SPEED;
    private final Point spawnLocation;
    private boolean isFrenzy;

    /**
     * Create MovingEntity according to parameters.
     * Record spawnLocation as topLeft at creation.
     * isFrenzy is set to false on creation.
     *
     * @param image image representing the object
     * @param topLeft top left coordinate of object
     * @param speed object's speed (at creation, this is NORMAL_SPEED)
     * @param frenzySpeed object's FRENZY_SPEED
     * @param direction object's starting direction
     */
    public MovingEntity(Image image, Point topLeft, double speed, double frenzySpeed, double direction) {
        super(image, topLeft);
        this.speed = speed;
        NORMAL_SPEED = speed;
        FRENZY_SPEED = frenzySpeed;
        this.direction = direction;
        spawnLocation = topLeft;
        isFrenzy = false;
    }

    /**
     * Calculate new sprite's centre and get new collision box
     */
    public void updateCollisionBox() {
        Image sprite = getSprite();
        setCentre(calculateCentre());
        setCollisionBox(sprite.getBoundingBoxAt(getCentre()));
    }

    /**
     *
     * @return object's current speed
     */
    public double getSpeed() {
        return speed;
    }

    /**
     *
     * @return object's current direction
     */
    public double getDirection() {
        return direction;
    }

    /**
     *
     * @return object's spawn location
     */
    public Point getSpawnLocation() {
        return spawnLocation;
    }

    /**
     * Method called when object goes into frenzy state
     */
    public abstract void goFrenzy();

    /**
     * Method called when object goes back to normal
     */
    public abstract void goNormal();

    /**
     * Set isFrenzy to either true or false
     *
     * @param bool true or false
     */
    public void setFrenzy(boolean bool) {
        isFrenzy = bool;

        if (isFrenzy) {
            goFrenzy();
            speed = FRENZY_SPEED;
        }
        else {
            goNormal();
            speed = NORMAL_SPEED;
        }
    }

    /**
     * Set direction of object to new direction
     *
     * @param direction new direction
     */
    public void setDirection(double direction) {
        this.direction = direction;
    }

    /**
     * Handle collision with wall
     */
    public abstract void wallCollision();

    /**
     * Handle collision with Player or Ghost
     */
    public abstract void playerGhostCollision();

    /**
     * Handle collision with Edibles
     * @param edibles Edible object
     * @param edible ArrayList of Edibles
     * @return true if edible is a Pellet, false otherwise
     */
    public abstract boolean edibleCollision(ArrayList<Edible> edibles, Edible edible);
}
