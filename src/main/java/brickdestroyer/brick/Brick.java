package brickdestroyer.brick;

import brickdestroyer.ball.Ball;

import java.awt.*;
import java.awt.Point;
import java.awt.geom.Point2D;

/**
 * This represents the brick
 * <br>Change:
 * <ul>
 *     <li>Removed Crack class and placed it in its own class</li>
 *     <li>Removed string "name" as it wasn't being used</li>
 * </ul>
 */
abstract public class Brick  {
    public static final int UP_IMPACT = 100;
    public static final int DOWN_IMPACT = 200;
    public static final int LEFT_IMPACT = 300;
    public static final int RIGHT_IMPACT = 400;

    Shape brickFace;

    private Color border;
    private Color inner;

    private int fullStrength;
    private int strength;
    private boolean broken;

    /**
     * Creates the brick shape and initialises the variables
     * <br>Change:
     * <ul>
     *     <li>Removed variable rnd as it's not used in Brick class</li>
     *     <li>Removed name from the parameters</li>
     * </ul>
     * @param pos The coordinates of the top left corner of the brick
     * @param size The width and height of the brick
     * @param border The colour of the border of the brick
     * @param inner The colour of the inner part of the brick
     * @param strength The number of times the brick has to be hit to be broken
     */
    public Brick(Point pos,Dimension size,Color border,Color inner,int strength){
        broken = false;
        brickFace = makeBrickFace(pos,size);
        this.border = border;
        this.inner = inner;
        this.fullStrength = this.strength = strength;
    }

    /**
     * Creates the shape of the brick
     * @param pos The coordinates of the top left corner of the brick
     * @param size The width and height of the brick
     * @return The shape of the brick
     */
    protected abstract Shape makeBrickFace(Point pos,Dimension size);

    /**
     * Checks if the ball has hit a brick which has not been broken
     * <br>If the brick is not broken, calls method impact
     * @param point The coordinates of the point of the ball which hits a brick
     * @param dir The side of the brick which was hit
     * @return A boolean representing if a brick has been broken
     */
    public  boolean setImpact(Point2D point , int dir){
        if(broken)
            return false;
        impact();
        return  broken;
    }

    /**
     * Gets the shape of the brick
     * @return The shape of the brick
     */
    public abstract Shape getBrick();

    /**
     * Gets the colour of the border of the brick
     * @return The colour of the border of the brick
     */
    public Color getBorderColor(){
        return  border;
    }

    /**
     * Gets the colour of the inner part of the brick
     * @return The colour of the inner part of the brick
     */
    public Color getInnerColor(){
        return inner;
    }

    /**
     * Finds which side of the brick was hit by the ball
     * <br>Change:
     * <ul>
     *     <li>Removed variable out as it was unnecessary</li>
     *     <li>Called get methods for left, right, up, down of ball</li>
     * </ul>
     * @param b The ball object
     * @return The side of the brick which was hit by the ball
     */
    public final int findImpact(Ball b){
        if(broken)
            return 0;
        if(brickFace.contains(b.getRight()))
            return LEFT_IMPACT;
        else if(brickFace.contains(b.getLeft()))
            return RIGHT_IMPACT;
        else if(brickFace.contains(b.getUp()))
            return DOWN_IMPACT;
        else if(brickFace.contains(b.getDown()))
            return UP_IMPACT;
        return 0;
    }

    /**
     * Checks if the brick is broken
     * @return A boolean representing if the brick is broken
     */
    public final boolean isBroken(){
        return broken;
    }

    /**
     * Resets the brick to its initial property
     */
    public void repair() {
        broken = false;
        strength = fullStrength;
    }

    /**
     * Reduces the strength of the brick and decides if it's broken
     */
    public void impact(){
        strength--;
        broken = (strength == 0);
    }

}





