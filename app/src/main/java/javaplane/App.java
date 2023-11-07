/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package javaplane;

import javax.swing.*;

import org.checkerframework.common.value.qual.BoolVal;

import javaplane.Graphics.LayerManager;
import javaplane.Graphics.shit;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

class AppCanvas extends JPanel {
    private Timer gameThread;
    Boolean state = false;
    private LayerManager layerManager = null;
    public AppCanvas(LayerManager lm){
        layerManager = lm;
        //resize to bg image size
        Image bg = layerManager.background;
        setPreferredSize(new Dimension(bg.getWidth(null),bg.getHeight(null)));
        setBackground(Color.BLACK);
        //setDoubleBuffered(true);
        setFocusable(true);
        requestFocus();
    }

    @Override
    protected void paintComponent(Graphics g){
        // Perform your rendering operations on the off-screen buffer
        layerManager.paint(g);
        g.dispose();
    }
}

public class App extends JFrame implements ActionListener {
    private boolean isRedState = true;
    private LayerManager layerManager = new LayerManager();
    public AppCanvas canvas;
    public App() {
        super("JavaPlane");
        // Set up the UI and buttons.
        JButton changeStateButton = new JButton("Change Lamp Color");
        changeStateButton.addActionListener(this);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(changeStateButton);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        //resize to bg image size
        setSize(layerManager.background.getWidth(null), layerManager.background.getHeight(null));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //add canvas
        canvas = new AppCanvas(layerManager);
        add(canvas);
        pack();

    }
    public void repaint(){
        super.repaint();
        canvas.revalidate();
        canvas.repaint();
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Change Lamp Color")) {
            isRedState = !isRedState;
            layerManager.toggleLayerState("1.png");
            repaint();
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            App app = new App();
            app.setVisible(true);
        });
    }
}
