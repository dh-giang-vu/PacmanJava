import bagel.Image;
import bagel.util.Point;
import bagel.util.Rectangle;

/**
 * Abstract class representing all objects on the map
 */
public abstract class GameEntity {
    private Image sprite;
    private Point topLeft;
    private Point centre;
    private Rectangle collisionBox;

    /**
     * Create game entity with given image and top left coordinate
     *
     * @param image image representing the object
     * @param topLeft top left coordinate of object
     */
    public GameEntity(Image image, Point topLeft) {
        sprite = image;
        this.topLeft = topLeft;

        centre = calculateCentre();
        collisionBox = sprite.getBoundingBoxAt(centre);
    }

    /**
     * Calculate centre coordinate of this object
     *
     * @return centre coordinate
     */
    public Point calculateCentre() {
        return new Point(topLeft.x + (sprite.getWidth()/2), topLeft.y + (sprite.getHeight()/2));
    }

    /**
     * Display object
     */
    public void show() {
        sprite.drawFromTopLeft(topLeft.x, topLeft.y);
    }

    /**
     *
     * @return object's image
     */
    public Image getSprite() {
        return sprite;
    }
    /**
     *
     * @return object's centre coordinate
     */
    public Point getCentre() {
        return centre;
    }

    /**
     *
     * @return object's top left coordinate
     */
    public Point getTopLeft() {
        return topLeft;
    }

    /**
     *
     * @return object's collision box
     */
    public Rectangle getCollisionBox() {
        return collisionBox;
    }

    /**
     * Set image that represents this object to a different image
     *
     * @param spriteImage new image
     */
    public void setSprite(Image spriteImage) {
        this.sprite = spriteImage;
    }

    /**
     * Set object's centre to a new point
     *
     * @param centre new point
     */
    public void setCentre(Point centre) {
        this.centre = centre;
    }

    /**
     * Set object's top left coordinate to new point
     *
     * @param topLeft new point
     */
    public void setTopLeft(Point topLeft) {
        this.topLeft = topLeft;
    }

    /**
     * Set collision box to a new box
     *
     * @param collisionBox new collision box
     */
    public void setCollisionBox(Rectangle collisionBox) {
        this.collisionBox = collisionBox;
    }

}
