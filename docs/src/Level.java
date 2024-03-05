import bagel.util.Point;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Class representing a level in the game
 */
public class Level {
    private final Map map;
    private Player pacman;

    /**
     * Create a Level with objects as specified by the csv file
     *
     * @param csvFileName contains info on game objects
     */
    public Level(String csvFileName) {
        map = new Map();
        readLevel(csvFileName);
    }

    /**
     * Read CSV file and load map objects correspondingly
     * @param filename csv file name
     */
    private void readLevel(String filename) {

        try (BufferedReader br =
                     new BufferedReader(new FileReader(filename))) {
            String text;
            String delimiter = ",";
            String[] inputArr;

            while ((text = br.readLine()) != null) {
                inputArr = text.split(delimiter);
                double inputX = Double.parseDouble(inputArr[1]);
                double inputY = Double.parseDouble(inputArr[2]);

                Point objLocation = new Point(inputX, inputY);

                if (inputArr[0].equals("Player")) {
                    pacman = new Player(objLocation);
                } else {
                    map.addObject(inputArr[0], objLocation);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Display map, heart and score
     */
    public void showLevel() {
        map.showMap();
        pacman.renderHearts();
        pacman.showScore();
    }

    /**
     *
     * @return Player object
     */
    public Player getPacman() {
        return pacman;
    }

    /**
     *
     * @return Level's Map
     */
    public Map getMap() {
        return map;
    }

    /**
     * Note: currently only return score obtainable from eating all dots
     *       but can extend to count Cherry as well if needed.
     *
     * @return Maximum score of current level
     */
    public int getMaxScore() {
        return 10 * map.getNumDots();
    }
}
