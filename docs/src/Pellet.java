import bagel.Image;
import bagel.util.Point;

/**
 * Class representing Pellet
 */
public class Pellet extends GameEntity implements Edible {
    /**
     * Create a Pellet at specified coordinate
     *
     * @param topLeft top left coordinate of Pellet
     */
    public Pellet(Point topLeft) {
        super(new Image("res/pellet.png"), topLeft);
    }

    /**
     *
     * @return point value of Pellet when eaten by Player (0 point)
     */
    @Override
    public int getPoint() {
        return 0;
    }
}
