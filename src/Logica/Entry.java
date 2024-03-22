/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

/**
 *
 * @author jenniferbueso
 */
public class Entry {
    public String username;
    public long posicion;
    public Entry siguiente;
    
    Entry(String username, long posicion) {
        this.username = username;
        this.posicion = posicion;
        siguiente = null;
    }
}
