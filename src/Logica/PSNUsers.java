/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import java.io.EOFException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author jenniferbueso
 */
public class PSNUsers {
    private RandomAccessFile RAF, PSN;
    private HashTable users;
    
    public PSNUsers() {
        try {
           RAF = new RandomAccessFile("Users", "rw");
           PSN = new RandomAccessFile("psn", "rw");
           users = new HashTable(); 
           reloadHashTable();
           
        } catch (IOException e) {
           JOptionPane.showMessageDialog(null, "¡Se ha producido un error!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void reloadHashTable() {
        try {
            RAF.seek(0);

            while (RAF.getFilePointer() < RAF.length()) {
                long posicion = RAF.getFilePointer();
                String username = RAF.readUTF();
                int puntos = RAF.readInt();
                int trofeos = RAF.readInt();
                boolean isActive = RAF.readBoolean();

                if (isActive) {
                    users.add(username, posicion);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "¡Se ha producido un error!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /*
    Usuario
    - Usuario
    - Acumulador puntos
    - Contador Trofeos
    - Activo
    */
    public boolean addUser(String username) throws IOException {
        if (users.search(username) != -1) {
            JOptionPane.showMessageDialog(null, "El nombre de usuario ya existe. Por favor, elija otro.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        long posicion = RAF.length();
        RAF.seek(posicion);
        RAF.writeUTF(username);
        RAF.writeInt(0);
        RAF.writeInt(0);
        RAF.writeBoolean(true);
        users.add(username, posicion);

        JOptionPane.showMessageDialog(null, "Usuario agregado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        return true;
    }

    public boolean deactivateUser(String username) throws IOException {
        long pos = users.search(username);

        if (pos != -1) {
            RAF.seek(pos);
            String usuario = RAF.readUTF();
            RAF.readInt();
            RAF.readInt();
            RAF.writeBoolean(false);
            users.remove(usuario);
            JOptionPane.showMessageDialog(null, "Usuario desactivado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "El usuario no existe.", "Usuario Inexistente", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
    }
    
    /*
    Trofeos
    - Usuario
    - Juego del Trofeo
    - Nombre del Trofeo
    - Fecha
    */
    public boolean addTrophieTo(String username, String trophyGame, String trophyName, Trophy type) throws IOException {
        long pos = users.search(username);
        if (pos != -1) {
            RAF.seek(pos);
            String usuario = RAF.readUTF();
            int acumuladorPuntos = RAF.readInt();
            int contadorTrofeos = RAF.readInt();
            boolean isActive = RAF.readBoolean();

            PSN.seek(PSN.length());
            PSN.writeUTF(username);
            PSN.writeUTF(trophyGame);
            PSN.writeUTF(trophyName);
            PSN.writeUTF(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));

            RAF.seek(pos);
            RAF.readUTF();
            RAF.writeInt(acumuladorPuntos + type.points);
            RAF.writeInt(contadorTrofeos + 1);
            RAF.writeBoolean(isActive);

            JOptionPane.showMessageDialog(null, "Trofeo agregado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "El usuario no existe.", "Usuario Inexistente", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
    }

    public String playerinfo(String username) {
        long pos = users.search(username);
        if (pos != -1) {
            try {
                RAF.seek(pos);
                String usuario = RAF.readUTF();
                int acumuladorPuntos = RAF.readInt();
                int contadorTrofeos = RAF.readInt();
                boolean activo = RAF.readBoolean();

                StringBuilder info = new StringBuilder();
                info.append("INFORMACIÓN SOBRE USUARIO").append("\n");
                info.append("Nombre de usuario: ").append(usuario).append("\n");
                info.append("Puntos por trofeos: ").append(acumuladorPuntos).append("\n");
                info.append("Trofeos: ").append(contadorTrofeos).append("\n");
                info.append("Estado: ").append(activo ? "Activo" : "Inactivo").append("\n");

                info.append("\n").append("INFORMACIÓN SOBRE TROFEOS").append("\n");
                PSN.seek(0);
                while (PSN.getFilePointer() < PSN.length()) {
                    String user = PSN.readUTF();
                    String juegoTrofeo = PSN.readUTF();
                    String nombreTrofeo = PSN.readUTF();
                    String fecha = PSN.readUTF();

                    if (user.equals(username)) {
                        info.append(fecha).append(" - ").append(juegoTrofeo).append(" - ").append(nombreTrofeo).append("\n");
                    }
                }
                return info.toString();

            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "¡Error al obtener la información del usuario!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "El usuario no existe.", "Error", JOptionPane.ERROR_MESSAGE);
            return "¡El usuario no existe!";
        }
        return null;
    }
}

