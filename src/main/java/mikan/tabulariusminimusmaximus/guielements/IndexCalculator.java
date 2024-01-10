/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mikan.tabulariusminimusmaximus.guielements;

/**
 *
 * @author mika
 */
class IndexCalculator {
    
    int index = 12;
    
    protected void increment(){
        this.index += 7;
    }
    
    protected int value(){
        return this.index;
    }
    
    protected void reset() {
        this.index=12;
    }
   
}
