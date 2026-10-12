/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.dibujo;

/**
 *
 * @author cesar
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Dibujo extends JFrame {

    JComboBox<String> comboColores;
    JPanel panelDibujo, panelSuperior;
    JButton btnLimpiar;

    BufferedImage imagen;
    BufferedImage imagenFondo;
    Graphics2D g2;

    int xAnterior, yAnterior;

    public Dibujo() {

        setTitle("Paint");
        setSize(630, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

       
        comboColores = new JComboBox<>();

        comboColores.addItem("Rojo");
        comboColores.addItem("Verde");
        comboColores.addItem("Azul");

        
        // BOTÓN LIMPIAR
        
        btnLimpiar = new JButton("Limpiar");

       
        // PANEL SUPERIOR
        
        panelSuperior = new JPanel();
        panelSuperior.add(comboColores);
        panelSuperior.add(btnLimpiar);

        add(panelSuperior, BorderLayout.NORTH);

        
        // CARGAR IMAGEN DE FONDO
       
        try {

            InputStream entrada = getClass().getResourceAsStream("/fondo.jpg");

            if (entrada == null) {
                throw new IOException("No se encontró la imagen fondo.jpg");
            }

            imagenFondo = ImageIO.read(entrada);

        } catch (IOException e) {

            System.out.println("Error al cargar la imagen:");
            e.printStackTrace();

            // Crear fondo blanco en caso de error
            imagenFondo = new BufferedImage(
                    584,
                    584,
                    BufferedImage.TYPE_INT_RGB
            );

            Graphics2D fondo = imagenFondo.createGraphics();
            fondo.setColor(Color.WHITE);
            fondo.fillRect(0, 0, 584, 584);
            fondo.dispose();
        }

        
        // CREAR IMAGEN PARA DIBUJAR
      
        imagen = new BufferedImage(
                imagenFondo.getWidth(),
                imagenFondo.getHeight(),
                BufferedImage.TYPE_INT_RGB
        );

        // Copiar el fondo original a la imagen de trabajo
        Graphics2D gFondo = imagen.createGraphics();
        gFondo.drawImage(imagenFondo, 0, 0, null);
        gFondo.dispose();

        // Graphics que utilizaremos para dibujar
        g2 = imagen.createGraphics();

        // Color inicial
        g2.setColor(Color.RED);

       
        // PANEL DE DIBUJO
        
        panelDibujo = new JPanel() {

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                // Dibujar la imagen en el panel
                g.drawImage(imagen, 20, 10, null);
            }
        };

        panelDibujo.setBackground(Color.LIGHT_GRAY);

      
        // CAMBIAR COLOR
        
        comboColores.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                int colorSeleccionado = comboColores.getSelectedIndex();

                System.out.println(colorSeleccionado);

                switch (colorSeleccionado) {

                    case 0:
                        g2.setColor(Color.RED);
                        break;

                    case 1:
                        g2.setColor(Color.GREEN);
                        break;

                    case 2:
                        g2.setColor(Color.BLUE);
                        break;
                }
            }
        });

        
        // BOTÓN LIMPIAR
        
        btnLimpiar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                // Copiar nuevamente el fondo original
                g2.drawImage(imagenFondo, 0, 0, null);

                // Actualizar la ventana
                panelDibujo.repaint();
            }
        });

        
        // MOUSE PRESIONADO
      
        panelDibujo.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {

                xAnterior = e.getX() - 20;
                yAnterior = e.getY() - 10;
            }
        });

        
        // MOUSE ARRASTRADO
       
        panelDibujo.addMouseMotionListener(new MouseMotionAdapter() {

            @Override
            public void mouseDragged(MouseEvent e) {

                int x = e.getX() - 20;
                int y = e.getY() - 10;

                // Dibujar línea
                g2.drawLine(
                        xAnterior,
                        yAnterior,
                        x,
                        y
                );

                // Actualizar posición anterior
                xAnterior = x;
                yAnterior = y;

                // Actualizar pantalla
                panelDibujo.repaint();
            }
        });

        add(panelDibujo, BorderLayout.CENTER);
    }

    public static void main(String[] args) {

        Dibujo d = new Dibujo();

        d.setVisible(true);
    }
}