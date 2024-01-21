package mikan.tabulariusminimusmaximus.guielements;

/**
 *
 * @author mika
 */
class IndexCalculator {
    
    int index = 12;
    
    protected void increment(){
        this.index += 7;
        System.out.println(this.index);
    }
    
    protected int value(){
        return this.index;
    }
    
    protected void reset() {
        this.index=12;
    }
   
}
