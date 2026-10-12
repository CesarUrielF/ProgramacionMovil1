package com.mycompany.calculadora;


import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Calculadora extends JFrame implements ActionListener {

    JButton btnSuma, btnResta, btnDivision, btnMultiplicacion, btnIgual, btnPunto;
    JTextField pantalla;

    Font letra = new Font("Arial", Font.BOLD, 40);

    JPanel pPantalla;

    JButton btnDigitos[];
    JPanel panelDigitos[];

    Double num1, num2;

    
    String operador;

    public Calculadora() {

        setTitle("Mi Calculadora");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 1));

      
        pantalla = new JTextField(10);
        pantalla.setFont(letra);
        pantalla.setHorizontalAlignment(SwingConstants.RIGHT);

        
        pantalla.setEditable(false);

        pPantalla = new JPanel();
        pPantalla.setLayout(new BorderLayout());
        pPantalla.add(pantalla, BorderLayout.CENTER);

        add(pPantalla);

        
        panelDigitos = new JPanel[4];

        for (int i = 0; i < 4; i++) {
            panelDigitos[i] = new JPanel();
            panelDigitos[i].setLayout(new GridLayout(1, 4));
        }

        
        btnDigitos = new JButton[10];

        for (int i = 1; i < 10; i++) {

            btnDigitos[i] = new JButton(i + "");
            btnDigitos[i].setFont(letra);
            btnDigitos[i].addActionListener(this);

            switch (i) {

                case 7:
                case 8:
                case 9:
                    panelDigitos[3].add(btnDigitos[i]);
                    break;

                case 4:
                case 5:
                case 6:
                    panelDigitos[2].add(btnDigitos[i]);
                    break;

                case 1:
                case 2:
                case 3:
                    panelDigitos[1].add(btnDigitos[i]);
                    break;
            }
        }

      
        btnSuma = new JButton("+");
        btnSuma.setFont(letra);

        btnSuma.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (!pantalla.getText().isEmpty()) {

                    num1 = Double.parseDouble(pantalla.getText());

                    operador = "+";

                    pantalla.setText("");
                }
            }
        });

        
        btnResta = new JButton("-");
        btnResta.setFont(letra);

        btnResta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (!pantalla.getText().isEmpty()) {

                    num1 = Double.parseDouble(pantalla.getText());

                    operador = "-";

                    pantalla.setText("");
                }
            }
        });

       

        btnMultiplicacion = new JButton("*");
        btnMultiplicacion.setFont(letra);

        btnMultiplicacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (!pantalla.getText().isEmpty()) {

                    num1 = Double.parseDouble(pantalla.getText());

                    operador = "*";

                    pantalla.setText("");
                }
            }
        });

        
        btnDivision = new JButton("/");
        btnDivision.setFont(letra);

        btnDivision.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (!pantalla.getText().isEmpty()) {

                    num1 = Double.parseDouble(pantalla.getText());

                    operador = "/";

                    pantalla.setText("");
                }
            }
        });

        
        btnIgual = new JButton("=");
        btnIgual.setFont(letra);

        btnIgual.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (!pantalla.getText().isEmpty() && operador != null) {

                    num2 = Double.parseDouble(pantalla.getText());

                    double resultado = 0;

                    switch (operador) {

                        case "+":
                            resultado = num1 + num2;
                            break;

                        case "-":
                            resultado = num1 - num2;
                            break;

                        case "*":
                            resultado = num1 * num2;
                            break;

                        case "/":

                            if (num2 != 0) {
                                resultado = num1 / num2;
                            } else {
                                pantalla.setText("Error");
                                return;
                            }

                            break;
                    }

                    pantalla.setText(String.valueOf(resultado));

                    // Reiniciamos el operador
                    operador = null;
                }
            }
        });

      
        btnPunto = new JButton(".");
        btnPunto.setFont(letra);

        btnPunto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Si la pantalla está vacía,
                // comenzamos con 0.
                if (pantalla.getText().isEmpty()) {

                    pantalla.setText("0.");

                } else if (!pantalla.getText().contains(".")) {

                    // Solo permite un punto decimal
                    pantalla.setText(pantalla.getText() + ".");
                }
            }
        });

        

        panelDigitos[3].add(btnSuma);

        

        panelDigitos[2].add(btnResta);

       
        panelDigitos[1].add(btnMultiplicacion);

        

        add(panelDigitos[3]);
        add(panelDigitos[2]);
        add(panelDigitos[1]);

       

        btnDigitos[0] = new JButton("0");
        btnDigitos[0].setFont(letra);
        btnDigitos[0].addActionListener(this);

        
        panelDigitos[0].add(btnDigitos[0]);
        panelDigitos[0].add(btnPunto);
        panelDigitos[0].add(btnIgual);
        panelDigitos[0].add(btnDivision);

        add(panelDigitos[0]);
    }

   

    @Override
    public void actionPerformed(ActionEvent a) {

        String num = a.getActionCommand();

        pantalla.setText(pantalla.getText() + num);
    }

    
    public static void main(String[] args) {

        Calculadora c = new Calculadora();

        c.setVisible(true);
    }
}