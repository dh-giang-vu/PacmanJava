import bagel.Image;
import bagel.util.Point;

/**
 * Class represents Hearts
 */
public class Heart extends GameEntity {
    /**
     * Create a Heart at specified coordinate
     * @param topLeft top left coordinate of Heart
     */
    public Heart(Point topLeft) {
        super(new Image("res/heart.png"), topLeft);
    }
}
