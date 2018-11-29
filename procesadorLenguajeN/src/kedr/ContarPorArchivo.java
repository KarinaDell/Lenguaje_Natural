/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kedr;

/**
 *
 * @author 5e
 */
public class ContarPorArchivo {
    
    public String nombre;
    public int palabrasRep;
    public int numeroRep;
    
    public ContarPorArchivo(String nom,int prep, int nrep){
        this.nombre=nom;
        this.numeroRep=prep;
        this.palabrasRep=nrep;
    }
    
  
    @Override
    public String toString() { 
        return "Nombre: " + nombre + " Palabras repetidas: "+ palabrasRep + " Palabras no repetidas: " + numeroRep; 
    } 
}
