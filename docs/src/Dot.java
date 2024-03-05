import bagel.Image;
import bagel.util.Point;

/**
 * Class represents Dots that can be eaten by Player
 */
public class Dot extends GameEntity implements Edible {
    /**
     * Create dot at specified coordinate
     *
     * @param topLeft top left coordinate of Dot
     */
    public Dot(Point topLeft) {
        super(new Image("res/dot.png"), topLeft);
    }

    /**
     *
     * @return point value of Dot when eaten by Player
     */
    @Override
    public int getPoint() {
        return 10;
    }
}
