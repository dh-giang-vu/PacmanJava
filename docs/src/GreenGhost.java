import bagel.Image;
import bagel.util.Point;

/**
 * Concrete class representing Green Ghost
 */
public class GreenGhost extends Ghost{
    private static final double NORMAL_SPEED = 4;
    private static final double FRENZY_SPEED = 3.5;

    /**
     * Create a Green Ghost
     * @param topLeft top left coordinate of Green Ghost
     */
    public GreenGhost(Point topLeft) {
        super(new Image("res/ghostGreen.png"), topLeft, NORMAL_SPEED, FRENZY_SPEED, DOWN);
        setRandomDirection();
    }

}
