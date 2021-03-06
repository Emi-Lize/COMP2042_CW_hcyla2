package brickdestroyer.homescreen;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * New Class - The design of the components in the starting screen
 */
abstract public class MenuDesign extends JComponent {
    private static final Color BG_COLOR = Color.GREEN.darker();
    private static final Color BORDER_COLOR = new Color(200,8,21);
    private static final Color DASH_BORDER_COLOR = new  Color(255, 216, 0);
    protected static final Color TEXT_COLOR = new Color(16, 52, 166);
    protected static final Color CLICKED_BUTTON_COLOR = BG_COLOR.brighter();
    protected static final Color CLICKED_TEXT = Color.WHITE;
    private static final int BORDER_SIZE = 5;
    private static final float[] DASHES = {12,6};

    protected Rectangle menuFace;

    private BasicStroke borderStoke;
    private BasicStroke borderStoke_noDashes;

    protected Font buttonFont;

    protected Dimension btnDim;

    /**
     * Initialises the size of the menu and sets the font and border
     * @param area The dimension of the menu
     */
    public MenuDesign(Dimension area){
        menuFace = new Rectangle(new Point(0,0),area);
        btnDim = new Dimension(area.width / 3, area.height / 12);

        setBorderDesign();
        setFont();
    }

    /**
     * New Method - Sets the font of each string
     */
    private void setFont(){
        buttonFont = new Font("Monospaced",Font.PLAIN,btnDim.height-2);
    }

    /**
     * New Method - Sets the design of the border
     */
    private void setBorderDesign(){
        borderStoke = new BasicStroke(BORDER_SIZE,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND,0,DASHES,0);
        borderStoke_noDashes = new BasicStroke(BORDER_SIZE,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);
    }

    /**
     * Draws the components in the starting screen
     * @param g2d An object which draws the 2D components
     */
    public void drawMenu(Graphics2D g2d){
        drawContainer(g2d);

        Color prevColor = g2d.getColor();
        Font prevFont = g2d.getFont();

        double x = menuFace.getX();
        double y = menuFace.getY();

        g2d.translate(x,y);

        setText(g2d);
        setButton(g2d);

        g2d.translate(-x,-y);
        g2d.setFont(prevFont);
        g2d.setColor(prevColor);
    }

    /**
     * Draws the border of the home menu frame and sets the background image
     * <br>Change:
     * <ul>
     *     <li>Moved code to draw the border to drawBorder</li>
     *     <li>Puts an image as the background</li>
     * </ul>
     * @param g2d An object which draws the 2D components
     */
    private void drawContainer(Graphics2D g2d){
        Color prev = g2d.getColor();

        Image img = Toolkit.getDefaultToolkit().getImage("brick.jpg");
        g2d.drawImage(img, 0, 0, null);

        Stroke tmp = g2d.getStroke();

        drawBorder(borderStoke_noDashes, DASH_BORDER_COLOR, g2d);
        drawBorder(borderStoke, BORDER_COLOR, g2d);

        g2d.setStroke(tmp);
        g2d.setColor(prev);
    }

    /**
     * New Method - Moved the code to draw the border from drawContainer
     * @param stroke The border outline
     * @param colour The colour of the border
     * @param g2d An object which draws the 2D components
     */
    private void drawBorder(BasicStroke stroke, Color colour, Graphics2D g2d){
        g2d.setStroke(stroke);
        g2d.setColor(colour);
        g2d.draw(menuFace);
    }

    /**
     * Sets the text at correct position
     * @param g2d An object which draws the 2D components
     */
    protected abstract void setText(Graphics2D g2d);

    /**
     * Draws the string of text
     * @param sY The y coordinate of the top left of the text
     * @param rectangle The rectangle around the text
     * @param font The font of the text
     * @param text The text string
     * @param g2d An object which draws the 2D components
     */
    protected abstract void drawText(int sY, Rectangle2D rectangle, Font font, String text, Graphics2D g2d);

    /**
     * Sets the buttons at the correct position
     * @param g2d An object which draws the 2D components
     */
    protected abstract void setButton(Graphics2D g2d);

    /**
     * Draws the shape of the button and the text in the button
     * @param rectangle The rectangle around the text
     * @param g2d An object which draws the 2D components
     * @param clicked A boolean which represents if the button has been clicked
     * @param button A rectangle representing the button
     * @param text The string in the button
     */
    protected abstract void drawButton(Rectangle2D rectangle, Graphics2D g2d, Boolean clicked, Rectangle button, String text);

}
