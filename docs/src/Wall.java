import bagel.Image;
import bagel.util.Point;

/**
 * Class represents Wall
 */
public class Wall extends GameEntity {

    /**
     * Create a Wall at specified coordinate
     * @param topLeft top left coordinate of Wall
     */
    public Wall(Point topLeft) {
        super(new Image("res/wall.png"), topLeft);
    }
}
