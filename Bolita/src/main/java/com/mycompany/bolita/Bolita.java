/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.bolita;

/**
 *
 * @author cesar
 */
import java.awt.Color;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Bolita extends JPanel {

    int x, y;
    Image img;

    public Bolita() {
        setSize(300, 300);
        x = 50;
        y = 50;

        
        ImageIcon iconoOriginal = new ImageIcon(getClass().getResource("/nombre.jpg"));
        Image imagenGigante = iconoOriginal.getImage();

        
        img = imagenGigante.getScaledInstance(400, 400, Image.SCALE_SMOOTH);

        setBackground(Color.black);

        MouseAdapter mouse = new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                
                x = e.getX() - 50;
                y = e.getY() - 50;
                repaint();
            }
        };

        addMouseMotionListener(mouse);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, x, y, this);
    }

    public static void main(String[] args) {
        Ventana v = new Ventana();
        v.setVisible(true);
    }
}

class Ventana extends JFrame {

    public Ventana() {
        setSize(450, 430);
        setTitle("Bolita 01");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Bolita bolita = new Bolita();
        add(bolita);
    }
}