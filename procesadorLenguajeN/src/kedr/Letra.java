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
public class Letra {
    
    public char car;
    public int cant;
    
    public Letra(char caracter){
        this.car=caracter;
        this.cant=1;
    }
    
    public void aumentar(){
        cant++;
    }
    
    @Override
    public String toString(){
      return car+": "+cant;  
    }
}
