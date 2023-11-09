/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package javaplane.Graphics;

import javax.swing.*;

import javaplane.Decorators.App;
import javaplane.Event.BBClickListener;
import javaplane.Event.RepaintListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

//основна панель для малювання
public class AppCanvas extends JPanel {
    Boolean state = false;
    private LayerManager layerManager = null;//шари зображень
    private App app = null;//головний клас
    public AppCanvas(LayerManager lm, App app){
        this.app = app;
        layerManager = lm;
        //resize to bg image size
        Image bg = layerManager.background;
        setPreferredSize(new Dimension(bg.getWidth(null),bg.getHeight(null)));
        setBackground(Color.BLACK);
        setDoubleBuffered(true);
        setFocusable(true);
        requestFocus();
    }

    //перемальовуємо панель
    @Override
    protected void paintComponent(Graphics g){
        g.setColor(Color.red);
        // Perform your rendering operations on the off-screen buffer
        layerManager.paint(g);
        //call all repaint listeners of app
        for (RepaintListener listener : app.repaintListeners) {
            listener.repaintRequested(g);
        }
        
        g.dispose();
    }
}
