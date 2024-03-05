import bagel.Image;
import bagel.util.Point;

/**
 * Concrete class representing Red Ghost
 */
public class RedGhost extends Ghost {
    private static final double NORMAL_SPEED = 1;
    private static final double FRENZY_SPEED = 0.5;

    /**
     * Create a stationary Red Ghost
     * @param topLeft top left coordinate of Red Ghost
     */
    public RedGhost(Point topLeft) {
        super(new Image("res/ghostRed.png"), topLeft, 0, 0, RIGHT);
    }

    /**
     * Create a Red Ghost that can move (has speed)
     *
     * @param topLeft top left coordinate of Red Ghost
     * @param speed indicate that ghost has speed
     */
    public RedGhost(Point topLeft, double speed) {
        super(new Image("res/ghostRed.png"), topLeft, NORMAL_SPEED, FRENZY_SPEED, RIGHT);
    }

}
