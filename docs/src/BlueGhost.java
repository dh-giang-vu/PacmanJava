import bagel.Image;
import bagel.util.Point;

/**
 * Concrete class representing Blue Ghost
 */
public class BlueGhost extends Ghost{
    private static final double NORMAL_SPEED = 2;
    private static final double FRENZY_SPEED = 1.5;

    /**
     * Create a Blue Ghost
     *
     * @param topLeft top left coordinate of Blue Ghost
     */
    public BlueGhost(Point topLeft) {
        super(new Image("res/ghostBlue.png"), topLeft, NORMAL_SPEED, FRENZY_SPEED, DOWN);
    }


}
