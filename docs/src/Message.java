import bagel.Font;
import bagel.util.Point;

/**
 * Class represents text messages on the game screen
 */
public class Message {
    private final static int DEFAULT_SIZE = 64;
    private final static String DEFAULT_FONT = "res/FSO8BITR.TTF";
    private final String text;
    private Font font;
    private int size;
    private Point bottomLeft;

    /**
     * Create a message with default size and font
     *
     * @param text the message's text
     */
    public Message(String text) {
        size = DEFAULT_SIZE;
        font = new Font(DEFAULT_FONT, size);
        this.text = text;
    }

    /**
     * Display message at its coordinate
     */
    public void showMessage() {
        font.drawString(text, bottomLeft.x, bottomLeft.y);
    }

    /**
     * Show message centred to screen
     *
     * @param windowWidth width of game screen
     * @param windowHeight height of game screen
     */
    public void showMessageCentred(double windowWidth, double windowHeight) {
        double xCoordinate = (windowWidth - font.getWidth(text))/2;
        double yCoordinate = (windowHeight - size)/2 + size;
        font.drawString(text, xCoordinate, yCoordinate);
    }

    /**
     * Show message centred below another message
     *
     * @param msg the other message
     */
    public void showMessageCentredBelow(Message msg) {
        if (msg.bottomLeft == null) {
            System.out.println("Message coordinate is null");
        }

        double thisWidth = font.getWidth(text);
        double otherWidth = msg.font.getWidth(msg.text);

        double xCoordinate = msg.bottomLeft.x + (otherWidth - thisWidth)/2;
        double yCoordinate = msg.bottomLeft.y + size + 5;
        this.bottomLeft = new Point(xCoordinate, yCoordinate);

        font.drawString(text, xCoordinate, yCoordinate);
    }

    /**
     * Set size of message to a specified size
     *
     * @param size the specified size
     */
    public void setSize(int size) {
        this.size = size;
        font = new Font(DEFAULT_FONT, this.size);
    }

    /**
     * Set bottom left coordinate of message to a specified point
     *
     * @param bottomLeft the specified point
     */
    public void setBottomLeft(Point bottomLeft) {
        this.bottomLeft = bottomLeft;
    }
}
