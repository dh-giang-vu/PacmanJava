import java.util.ArrayList;

/**
 * Class that checks for collision in game
 */
public class CollisionHandler {
    private final static int FRENZY_TIME = 1000;
    private final Player player;
    private final ArrayList<Wall> walls;
    private final ArrayList<Ghost> ghosts;
    private final ArrayList<Edible> edibles;
    private boolean isFrenzy;
    private int frenzyCounter;

    /**
     * Create a CollisionHandler for specified Map and Player
     *
     * @param player specified Player
     * @param map spcified Map
     */
    public CollisionHandler(Player player, Map map) {
        this.player = player;
        ghosts = map.getGhosts();
        walls = map.getWalls();
        edibles = map.getEdibles();
        isFrenzy = false;
        frenzyCounter = 0;
    }


    /**
     * Check for object's collision with Wall
     */
    private void checkWallCollision() {

        // Check if any ghosts are colliding with wall
        for (Wall wall : walls) {
            for (Ghost ghost : ghosts) {
                if (ghost.getCollisionBox().intersects(wall.getCollisionBox())) {
                    ghost.wallCollision();
                }
            }
        }

        // Check if player is colliding with wall
        for (Wall wall : walls) {
            if (player.getCollisionBox().intersects(wall.getCollisionBox())) {
                player.wallCollision();
                break;
            }
        }
    }

    /**
     * Check for collision between Player and Ghost
     */
    private void checkPlayerGhostCollisions() {
        for (Ghost ghost : ghosts) {
            if (player.getCollisionBox().intersects(ghost.getCollisionBox())) {
                player.playerGhostCollision();
                ghost.playerGhostCollision();
            }
        }
    }

    /**
     * Check for Player's collision with Edibles
     */
    private void checkEdibleCollision() {
        for (Edible edible : edibles) {
            if (!player.getCollisionBox().intersects(edible.getCollisionBox())) {
                continue;
            }
            boolean isPellet = player.edibleCollision(edibles, edible);

            if (isPellet) {
                goFrenzy();
            }
            break;
        }
    }

    /**
     * Go into frenzy mode
     */
    private void goFrenzy() {
        isFrenzy = true;
        frenzyCounter = FRENZY_TIME;

        player.setFrenzy(true);
        for (Ghost ghost : ghosts) {
            ghost.setFrenzy(true);
        }
    }

    /**
     * Go back to normal
     */
    private void goNormal() {
        isFrenzy = false;

        player.setFrenzy(false);
        for (Ghost ghost : ghosts) {
            ghost.setFrenzy(false);
            if (ghost.getHidden()) {
                ghost.respawn();
            }
        }
    }

    /**
     * Check for all collisions in game
     */
    public void checkCollisions() {
        checkWallCollision();
        checkEdibleCollision();

        if (!isFrenzy) {
            checkPlayerGhostCollisions();
        }

        if (isFrenzy) {
            if (frenzyCounter > 0) {
                frenzyCounter -= 1;
            }
            if (frenzyCounter == 0) {
                goNormal();
            }
        }
    }

}
