/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.galeria;

/**
 *
 * @author cesar
 */
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class Galeria extends JFrame {

    JButton btnAtras, btnAdelante, btnPlay;
    JPanel panelBotones, panelCentro;
    ImageIcon[] imagenes;
    String[] nombres; 
    int imagenActual;
    JLabel etiquetaImagen, etiquetaTitulo; 

    Timer timer;
    boolean reproduciendo;

    public Galeria() {
        setTitle("Galeria 1");
        setSize(400, 550); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panelBotones = new JPanel();
        panelCentro = new JPanel(); 
        panelCentro.setLayout(new BorderLayout()); 

        btnAtras = new JButton("Atras");
        btnAdelante = new JButton("Adelante");
        btnPlay = new JButton("Reproducir");

        // Arreglo de 10 imágenes
        imagenes = new ImageIcon[10];
        imagenes[0] = new ImageIcon(getClass().getResource("/batman.jpg"));
        imagenes[1] = new ImageIcon(getClass().getResource("/superman.jpg"));
        imagenes[2] = new ImageIcon(getClass().getResource("/flash.jpg"));
        imagenes[3] = new ImageIcon(getClass().getResource("/mujermaravilla.jpg"));
        imagenes[4] = new ImageIcon(getClass().getResource("/linternaverde.jpeg"));
        imagenes[5] = new ImageIcon(getClass().getResource("/aquaman.jpg"));
        imagenes[6] = new ImageIcon(getClass().getResource("/lobo.jpg"));
        imagenes[7] = new ImageIcon(getClass().getResource("/flechaverde.jpg"));
        imagenes[8] = new ImageIcon(getClass().getResource("/robin.jpg"));
        imagenes[9] = new ImageIcon(getClass().getResource("/cyborg.jpg"));

        // Arreglo de los 10 títulos correspondientes
        nombres = new String[10];
        nombres[0] = "Batman";
        nombres[1] = "Superman";
        nombres[2] = "Flash";
        nombres[3] = "Mujer Maravilla";
        nombres[4] = "Linterna Verde";
        nombres[5] = "Aquaman";
        nombres[6] = "Lobo";
        nombres[7] = "Green Arrow";
        nombres[8] = "Robin";
        nombres[9] = "Cyborg";

        etiquetaImagen = new JLabel();
        etiquetaImagen.setHorizontalAlignment(SwingConstants.CENTER); 

        // Configuración de la etiqueta del título
        etiquetaTitulo = new JLabel();
        etiquetaTitulo.setHorizontalAlignment(SwingConstants.CENTER); 
        etiquetaTitulo.setFont(new Font("Arial", Font.BOLD, 20)); 

       
        panelCentro.add(etiquetaImagen, BorderLayout.CENTER);
        panelCentro.add(etiquetaTitulo, BorderLayout.SOUTH);

        mostrarImagen();

        btnAtras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (imagenActual == 0) {
                    imagenActual = 9;
                } else {
                    imagenActual--;
                }
                mostrarImagen();
            }
        });

        btnAdelante.addActionListener(e -> {
            if (imagenActual == 9) { 
                imagenActual = 0;
            } else {
                imagenActual++;
            }
            mostrarImagen();
        });

        btnPlay.addActionListener(e -> {
            if (!reproduciendo) {
                btnPlay.setText("Detener");
                reproduciendo = true;
                timer.start();
            } else {
                btnPlay.setText("Reproducir");
                reproduciendo = false;
                timer.stop();
            }
        });

        timer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (imagenActual == 9) { 
                    imagenActual = 0;
                } else {
                    imagenActual++;
                }
                mostrarImagen();
            }
        });

        panelBotones.add(btnAtras);
        panelBotones.add(btnAdelante);
        panelBotones.add(btnPlay);

        
        add(panelCentro, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        Galeria g = new Galeria();
        g.setVisible(true);
        g.setLocationRelativeTo(null); 
    }

    public void mostrarImagen() {
        ImageIcon icono = imagenes[imagenActual];
        
        
        if(icono != null && icono.getImage() != null) {
            Image imagen = icono.getImage();
            Image imagenEscalada = imagen.getScaledInstance(
                    400,
                    400,
                    Image.SCALE_SMOOTH
            );
            etiquetaImagen.setIcon(new ImageIcon(imagenEscalada));
        } else {
           
            etiquetaImagen.setIcon(null);
            etiquetaImagen.setText("Imagen no encontrada");
        }
        
       
        etiquetaTitulo.setText(nombres[imagenActual]);
    }
}