/*
 *  Brick Destroy - A simple Arcade video game
 *   Copyright (C) 2017  Filippo Ranza
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package brickdestroyer.debug;

import brickdestroyer.game.GameSystem;
import brickdestroyer.wall.Wall;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionListener;


/**
 * This represents the debug panel
 */
public class DebugPanel extends JPanel {
    private static final Color DEF_BKG = Color.WHITE;

    private JButton skipLevel;
    private JButton resetBalls;

    private JSlider ballXSpeed;
    private JSlider ballYSpeed;

    /**
     * Calls for the methods to make the button and the slider
     * <br>Change:
     * <ul>
     *     <li>Added a GameSystem object parameter</li>
     *     <li>Edited method calls for methods which have been moved from Wall to GameSystem</li>
     *     <li>Wall and GameSystem converted to a local variable</li>
     * </ul>
     * @param wall The wall object
     * @param gameSystem The gameSystem object
     */
    public DebugPanel(Wall wall, GameSystem gameSystem){
        initialize();

        skipLevel = makeButton("Skip Level",e -> wall.nextLevel());
        resetBalls = makeButton("Reset Balls",e -> gameSystem.resetBallCount());

        ballXSpeed = makeSlider(-4,4,e -> gameSystem.setBallXSpeed(ballXSpeed.getValue()));
        ballYSpeed = makeSlider(-4,4,e -> gameSystem.setBallYSpeed(ballYSpeed.getValue()));

        this.add(skipLevel);
        this.add(resetBalls);

        this.add(ballXSpeed);
        this.add(ballYSpeed);
    }

    /**
     * Sets the background colour of the panel and its grid layout
     */
    private void initialize(){
        this.setBackground(DEF_BKG);
        this.setLayout(new GridLayout(2,2));
    }

    /**
     * Makes the buttons in the panel
     * @param title The text displayed on the button
     * @param e The object which listens for an event and performs an action when an event occurs
     * @return The button object
     */
    private JButton makeButton(String title, ActionListener e){
        JButton out = new JButton(title);
        out.addActionListener(e);
        return out;
    }

    /**
     * Makes a slider in the panel
     * @param min The minimum value
     * @param max The maximum value
     * @param e The object which listens for a change in the slider and performs an action when a change occurs
     * @return The slider object
     */
    private JSlider makeSlider(int min, int max, ChangeListener e){
        JSlider out = new JSlider(min,max);
        out.setMajorTickSpacing(1);
        out.setSnapToTicks(true);
        out.setPaintTicks(true);
        out.addChangeListener(e);
        return out;
    }

    /**
     * Sets the initial position of the slider based on the current speed of the ball
     * @param x The speed of the ball along the x-axis
     * @param y The speed of the ball along the y-axis
     */
    public void setValues(int x,int y){
        ballXSpeed.setValue(x);
        ballYSpeed.setValue(y);
    }

}
