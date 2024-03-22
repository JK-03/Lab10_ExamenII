/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import javax.swing.JOptionPane;

/**
 *
 * @author jenniferbueso
 */
public class HashTable {
    private Entry inicio;
    
    public HashTable() {
        inicio = null;
    }
    
    public void add(String username, long pos) {
        Entry entrada = new Entry(username, pos);
        
        if (inicio == null) {
            inicio = entrada;
        } else {
            Entry actual = inicio;
            
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            
            actual.siguiente = entrada;
        }
    }
    
    public void remove(String nombreUsuario) {
        if (inicio == null) {
            return;
        }
        
        if (inicio.username.equals(nombreUsuario)) {
            inicio = inicio.siguiente;
            return;
        }
        
        Entry actual = inicio;
        while (actual.siguiente != null) {
            if (actual.siguiente.username.equals(nombreUsuario)) {
                actual.siguiente = actual.siguiente.siguiente;
                return;
            }
            actual = actual.siguiente;
        }
    }
    
    public long search(String username) {
        Entry actual = inicio;
        
        while (actual != null) {
            if (actual.username.equals(username)) {
                return actual.posicion;
            }
            actual = actual.siguiente;
        }
        return -1;
    }
}
