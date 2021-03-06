package brickdestroyer.ball;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.RectangularShape;

/**
 * This represents the ball
 * <br>Change:
 * <ul>
 *     <li>up, down , left and right declared as a private variable</li>
 * </ul>
 */
abstract public class Ball {
    private Shape ballFace;
    private Point2D center;

    private Point2D up;
    private Point2D down;
    private Point2D left;
    private Point2D right;

    private Color border;
    private Color inner;

    private int speedX;
    private int speedY;

    /**
     * Set the four points of the ball and creates the ball.
     * <br>Changes:
     * <ul>
     *     <li>Removed radiusA and radiusB and added radius</li>
     *     <li>Replaced duplicate code to set ball's location with the existing method setPoints</li>
     *     <li>setPoints method has only one argument</li>
     * </ul>
     * @param center The coordinates of the center of the ball
     * @param radius The radius of the ball
     * @param inner The colour of the inner part of the ball
     * @param border The colour of the border of the ball
     */
    public Ball(Point2D center,int radius, Color inner,Color border){
        this.center = center;

        up = new Point2D.Double();
        down = new Point2D.Double();
        left = new Point2D.Double();
        right = new Point2D.Double();

        setPoints(radius);
        ballFace = makeBall(center,radius);

        this.border = border;
        this.inner  = inner;
        speedX = 0;
        speedY = 0;
    }

    /**
     * Creates the shape of the ball
     * <br>Changes:
     * <ul>
     *     <li>Removed radiusA and radiusB and added radius</li>
     * </ul>
     * @param center The coordinates of the center of the ball
     * @param radius The radius of the ball
     * @return The shape of the ball
     */
    protected abstract Shape makeBall(Point2D center,int radius);

    /**
     * Moves the position of the ball based on the speed
     * <br>Changes:
     * <ul>
     *     <li>Removed code to set the coordinates of the ball and instead called method moveBall</li>
     * </ul>
     */
    public void move(){
        center.setLocation((center.getX() + speedX),(center.getY() + speedY));
        moveBall();
    }

    /**
     * Sets the speed of the ball
     * @param x The speed of the ball along the x-axis
     * @param y The speed of the ball along the y-axis
     */
    public void setSpeed(int x,int y){
        speedX = x;
        speedY = y;
    }

    /**
     * Sets the speed of the ball along the x-axis
     * @param s The speed of the ball along the x-axis
     */
    public void setXSpeed(int s){
        speedX = s;
    }

    /**
     * Sets the speed of the ball along the y-axis
     * @param s The speed of the ball along the y-axis
     */
    public void setYSpeed(int s){
        speedY = s;
    }

    /**
     * Reverses the horizontal direction of the ball
     */
    public void reverseX(){
        speedX *= -1;
    }

    /**
     * Reverses the vertical direction of the ball
     */
    public void reverseY(){
        speedY *= -1;
    }

    /**
     * Gets the colour of the border of the ball
     * @return The colour of the border of the ball
     */
    public Color getBorderColor(){
        return border;
    }

    /**
     * Gets the colour of the inner part of the ball
     * @return The colour of the inner part of the ball
     */
    public Color getInnerColor(){
        return inner;
    }

    /**
     * Gets the position of the ball
     * @return The coordinates of the center of the ball
     */
    public Point2D getPosition(){
        return center;
    }

    /**
     * Gets the shape of the ball
     * @return The shape of the ball
     */
    public Shape getBallFace(){
        return ballFace;
    }

    /**
     * Resets the position of the ball to the initial position
     * <br>Changes:
     * <ul>
     *     <li>Changed method name from moveTo to reset</li>
     *     <li>Removed code to set the coordinates of the ball and instead called method moveBall</li>
     * </ul>
     * @param p The coordinates of the center of the ball at the initial position
     */
    public void reset(Point p){
        center.setLocation(p);
        moveBall();
    }

    /**
     * Sets the coordinates of the four points of the ball
     * <br>Change:
     * <ul>
     *     <li>Parameter accepts a radius instead of width and height</li>
     *     <li>Variable half created since radius/2 is always calculated</li>
     * </ul>
     * @param radius The radius of the ball
     */
    private void setPoints(double radius){
        double half = radius/2;
        up.setLocation(center.getX(),center.getY()-half);
        down.setLocation(center.getX(),center.getY()+half);

        left.setLocation(center.getX()-half,center.getY());
        right.setLocation(center.getX()+half,center.getY());
    }

    /**
     * Gets the speed of the ball along the x-axis
     * @return The speed of the ball along the x-axis
     */
    public int getSpeedX(){
        return speedX;
    }

    /**
     * Gets the speed of the ball along the y-axis
     * @return The speed of the ball along the y-axis
     */
    public int getSpeedY(){
        return speedY;
    }

    /**
     * New Method - Changes the coordinates of the ball to move its position
     * <br>Changes:
     * <ul>
     *     <li>Created this method to reduce duplication in code in method move and moveTo</li>
     *     <li>Get only width as height and width of tmp will be the same</li>
     * </ul>
     */
    public void moveBall(){
        RectangularShape tmp = (RectangularShape) ballFace;
        double l = tmp.getWidth();

        tmp.setFrame((center.getX() -(l / 2)),(center.getY() - (l / 2)),l,l);
        setPoints(l);
        ballFace = tmp;
    }

    /**
     * New Method - Gets the position of the top of the ball
     * @return The coordinate of the top of the ball
     */
    public Point2D getUp() {
        return up;
    }

    /**
     * New Method - Gets the position of the bottom of the ball
     * @return The coordinate of the bottom of the ball
     */
    public Point2D getDown() {
        return down;
    }

    /**
     * New Method - Gets the position of the left of the ball
     * @return The coordinate of the left of the ball
     */
    public Point2D getLeft() {
        return left;
    }

    /**
     * New Method - Gets the position of the right of the ball
     * @return The coordinate of the right of the ball
     */
    public Point2D getRight() {
        return right;
    }

}