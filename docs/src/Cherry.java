import bagel.Image;
import bagel.util.Point;

/**
 * Class represents Cherry
 */
public class Cherry extends GameEntity implements Edible {
    /**
     * Create a Cherry at specified coordinate
     *
     * @param topLeft top left coordinate of Cherry
     */
    public Cherry(Point topLeft) {
        super(new Image("res/cherry.png"), topLeft);
    }

    /**
     *
     * @return point value of Cherry when eaten by Player
     */
    @Override
    public int getPoint() {
        return 20;
    }
}
