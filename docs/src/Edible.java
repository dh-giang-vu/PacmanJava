import bagel.util.Rectangle;

/**
 * Interface for objects of classes that can be eaten by Player
 */
public interface Edible {
    /**
     *
     * @return point value of Edible
     */
    int getPoint();

    /**
     *
     * @return collisionBox of Edible
     */
    Rectangle getCollisionBox();

    /**
     * Display Edible
     */
    void show();
}
