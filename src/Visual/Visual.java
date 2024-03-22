/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Visual;

import Logica.Trophy;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.basic.BasicProgressBarUI;

/**
 *
 * @author jenniferbueso
 */
public class Visual {
    private static Font font;
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("Examen II");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        //Font
        font = new Font("Avenir Next Condensed", Font.BOLD, 24);
        
        //Label
        JLabel titulo = new JLabel("TROPHY - PSNUSERS");
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setFont(font);
        panel.add(titulo, BorderLayout.NORTH);

        // Botones
        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        JButton agregarUsuario = new JButton("Agregar Usuario");
        JButton desactivarUsuario = new JButton("Desactivar Usuario");
        JButton agregarTrofeo = new JButton("Agregar Trofeo");
        JButton playerInfo = new JButton("Player Info");
        
        agregarUsuario.setCursor(new Cursor(Cursor.HAND_CURSOR));
        desactivarUsuario.setCursor(new Cursor(Cursor.HAND_CURSOR));
        agregarTrofeo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        playerInfo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        agregarUsuario.setFont(font);
        desactivarUsuario.setFont(font);
        agregarTrofeo.setFont(font);
        playerInfo.setFont(font);
        
        //Agregar al Panel
        buttonPanel.add(agregarUsuario);
        buttonPanel.add(desactivarUsuario);
        buttonPanel.add(agregarTrofeo);
        buttonPanel.add(playerInfo);

        //Action Listeners
        agregarUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarUsuario();
            }
        });
        
        desactivarUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                desactivarUsuario();
            }
        });
        
        agregarTrofeo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarTrofeo();
            }
        });
        
        playerInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playerInfo();
            }
        });
        
        panel.add(buttonPanel, BorderLayout.CENTER);
        frame.add(panel);
        frame.setVisible(true);
    }
    
    private static void agregarUsuario() {
        JPanel tituloPanel = new JPanel(new FlowLayout());
        JPanel agregarUsuarioPanel = new JPanel(new FlowLayout());
        
        tituloPanel.setLayout(new BorderLayout());
        JLabel titulo = new JLabel("AGREGAR USUARIO");
        JLabel usuarioLabel = new JLabel("Usuario:");
        JButton botonAgregar = new JButton("Agregar");
        botonAgregar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setFont(font);
        JTextField usuarioField = new JTextField(15);

        tituloPanel.add(titulo);
        agregarUsuarioPanel.add(usuarioLabel);
        agregarUsuarioPanel.add(usuarioField);
        agregarUsuarioPanel.add(botonAgregar);

        JFrame agregarUsuarioFrame = new JFrame();
        agregarUsuarioFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        agregarUsuarioFrame.setSize(300, 130);
        agregarUsuarioFrame.setLocationRelativeTo(null);

        agregarUsuarioFrame.add(tituloPanel, BorderLayout.NORTH);
        agregarUsuarioFrame.add(agregarUsuarioPanel);
        agregarUsuarioFrame.setVisible(true);
        
        botonAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usuarioField.getText();
                
                if (username.isBlank() || username.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Complete el campo de Username", "Campo Incompleto", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                try {
                    boolean siguiente = Main.psnUsers.addUser(username);

                    if (siguiente) {
                        agregarUsuarioFrame.setVisible(false);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Visual.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
    public static void desactivarUsuario() {
        JPanel tituloPanel = new JPanel(new FlowLayout());
        JPanel desactivarUsuarioPanel = new JPanel(new FlowLayout());
        
        tituloPanel.setLayout(new BorderLayout());
        JLabel titulo = new JLabel("DESACTIVAR USUARIO");
        JLabel usuarioLabel = new JLabel("Usuario:");
        JButton botonDesactivar = new JButton("Desactivar");
        botonDesactivar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setFont(font);
        JTextField usuarioField = new JTextField(15);

        tituloPanel.add(titulo);
        desactivarUsuarioPanel.add(usuarioLabel);
        desactivarUsuarioPanel.add(usuarioField);
        desactivarUsuarioPanel.add(botonDesactivar);

        JFrame desactivarUsuarioFrame = new JFrame();
        desactivarUsuarioFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        desactivarUsuarioFrame.setSize(300, 130);
        desactivarUsuarioFrame.setLocationRelativeTo(null);

        desactivarUsuarioFrame.add(tituloPanel, BorderLayout.NORTH);
        desactivarUsuarioFrame.add(desactivarUsuarioPanel);
        desactivarUsuarioFrame.setVisible(true);
        
        botonDesactivar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usuarioField.getText();
                
                if (username.isBlank() || username.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Complete el campo de Username", "Campo Incompleto", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                try {
                    boolean siguiente = Main.psnUsers.deactivateUser(username);

                    if (siguiente) {
                        desactivarUsuarioFrame.setVisible(false);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Visual.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
    public static void agregarTrofeo() {
       JFrame agregarTrofeoFrame = new JFrame();
       agregarTrofeoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
       agregarTrofeoFrame.setSize(400, 200);
       agregarTrofeoFrame.setLocationRelativeTo(null);

       JPanel panel = new JPanel();
       panel.setLayout(new BorderLayout());

       // Título centrado
       JLabel titulo = new JLabel("AGREGAR TROFEO", SwingConstants.CENTER);
       titulo.setFont(new Font("Avenir Next Condensed", Font.BOLD, 24));
       panel.add(titulo, BorderLayout.NORTH);

       // Panel para campos de entrada y botón
       JPanel inputPanel = new JPanel();
       inputPanel.setLayout(new GridLayout(4, 2, 5, 5));

       JLabel usuarioLabel = new JLabel("Usuario:");
       JTextField usuarioField = new JTextField(15);
       JLabel tipoLabel = new JLabel("Tipo de Trofeo:");
       JComboBox<Trophy> TrofeosTiposBox = new JComboBox<>();
       JLabel juegoLabel = new JLabel("Trophy Game:");
       JTextField TrophyGameField = new JTextField(15);
       JLabel nombreLabel = new JLabel("Trophy Name:");
       JTextField TrophyNameField = new JTextField(15);
       
        for (Trophy trophy : Trophy.values()) {
            TrofeosTiposBox.addItem(trophy);
        }
       
       inputPanel.add(usuarioLabel);
       inputPanel.add(usuarioField);
       inputPanel.add(tipoLabel);
       inputPanel.add(TrofeosTiposBox);
       inputPanel.add(juegoLabel);
       inputPanel.add(TrophyGameField);
       inputPanel.add(nombreLabel);
       inputPanel.add(TrophyNameField);

       panel.add(inputPanel, BorderLayout.CENTER);

       // Botón Agregar
       JButton botonAgregar = new JButton("Agregar");
       panel.add(botonAgregar, BorderLayout.SOUTH);
       botonAgregar.setCursor(new Cursor(Cursor.HAND_CURSOR));

       agregarTrofeoFrame.add(panel);
       agregarTrofeoFrame.setVisible(true);
       
       botonAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usuarioField.getText();
                Trophy tipoTrofeo = (Trophy) TrofeosTiposBox.getSelectedItem();
                String trophyGame = TrophyGameField.getText();
                String trophyName = TrophyNameField.getText();

                if (username.isBlank() || username.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Complete el campo de Username.", "Campo Incompleto", JOptionPane.ERROR_MESSAGE);
                    return;
                } else if (trophyGame.isBlank() || trophyGame.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Complete el campo de Trophy Game.", "Campo Incompleto", JOptionPane.ERROR_MESSAGE);
                    return;
                } else if (trophyName.isBlank() || trophyName.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Complete el campo de Trophy Name", "Campo Incompleto", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                try {
                    boolean siguiente = Main.psnUsers.addTrophieTo(username, trophyGame, trophyName, tipoTrofeo);

                    if (siguiente) {
                        agregarTrofeoFrame.setVisible(false);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Visual.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
    public static void playerInfo() {
        JFrame playerInfoFrame = new JFrame();
        playerInfoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        playerInfoFrame.setSize(400, 260);
        playerInfoFrame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Título centrado
        JLabel titulo = new JLabel("PLAYER INFO", SwingConstants.CENTER);
        titulo.setFont(new Font("Avenir Next Condensed", Font.BOLD, 24));
        panel.add(titulo, BorderLayout.NORTH);

        // Panel para campos de entrada y botón
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());

        JLabel usuarioLabel = new JLabel("Usuario:");
        JTextField usuarioField = new JTextField(15);
        JButton botonBuscar = new JButton("Buscar");
        botonBuscar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        inputPanel.add(usuarioLabel);
        inputPanel.add(usuarioField);
        inputPanel.add(botonBuscar);

        panel.add(inputPanel, BorderLayout.CENTER);

        // JTextArea dentro de JScrollPane
        JTextArea infoArea = new JTextArea(10, 30);
        JScrollPane scrollPane = new JScrollPane(infoArea);
        panel.add(scrollPane, BorderLayout.SOUTH);

        playerInfoFrame.add(panel);
        playerInfoFrame.setVisible(true);

        botonBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usuarioField.getText();

                if (username.isBlank() || username.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Complete el campo de Username", "Campo Incompleto", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                infoArea.setText(Main.psnUsers.playerinfo(username));
            }
        });
    }
}