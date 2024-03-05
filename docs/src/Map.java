import bagel.util.Point;

import java.util.ArrayList;

/**
 * Class represents the game's map
 */
public class Map {
    private final ArrayList<Wall> walls;
    private final ArrayList<Ghost> ghosts;
    private final ArrayList<Edible> edibles;
    private int numDots;

    /**
     * Create a map with 3 ArrayLists that store the Walls, Ghosts and Edibles
     */
    public Map() {
        walls = new ArrayList<>();
        ghosts = new ArrayList<>();
        edibles = new ArrayList<>();
    }

    /**
     * Add a new object to the map
     *
     * @param objType type of the object
     * @param topLeft top left coordinate of object
     */
    public void addObject(String objType, Point topLeft) {

        if (objType.equals("Ghost")) {
            ghosts.add(new RedGhost(topLeft));
        }
        else if (objType.equals("GhostRed")) {
            Ghost ghost = new RedGhost(topLeft, 1);
            ghosts.add(ghost);
            edibles.add(ghost);
        }
        else if (objType.equals("GhostBlue")) {
            Ghost ghost = new BlueGhost(topLeft);
            ghosts.add(ghost);
            edibles.add(ghost);
        }
        else if (objType.equals("GhostGreen")) {
            Ghost ghost = new GreenGhost(topLeft);
            ghosts.add(ghost);
            edibles.add(ghost);
        }
        else if (objType.equals("GhostPink")) {
            Ghost ghost = new PinkGhost(topLeft);
            ghosts.add(ghost);
            edibles.add(ghost);
        }

        else if (objType.equals("Wall")) {
            walls.add(new Wall(topLeft));
        }
        else if (objType.equals("Dot")) {
            edibles.add(new Dot(topLeft));
            numDots += 1;
        }
        else if (objType.equals("Cherry")) {
            edibles.add(new Cherry(topLeft));
        }
        else if (objType.equals("Pellet")) {
            edibles.add(new Pellet(topLeft));
        }
    }

    /**
     *
     * @return ArrayList of Wall
     */
    public ArrayList<Wall> getWalls() {
        return walls;
    }

    /**
     *
     * @return ArrayList of Ghost
     */
    public ArrayList<Ghost> getGhosts() {
        return ghosts;
    }

    /**
     *
     * @return ArrayList of Edibles (Dot, Cherry, Pellet, Ghost)
     */
    public ArrayList<Edible> getEdibles() {
        return edibles;
    }

    /**
     *
     * @return total number of Dot
     */
    public int getNumDots() {
        return numDots;
    }

    /**
     * Display objects on the Map
     */
    public void showMap() {

        for (Wall wall : walls) {
            wall.show();
        }

        for (Edible edible : edibles) {
            if (edible instanceof Ghost) {
                continue;
            }
            edible.show();
        }

        for (Ghost ghosts : ghosts) {
            if (ghosts.getHidden()) {
                continue;
            }
            ghosts.show();
            ghosts.move();
        }

    }
}
