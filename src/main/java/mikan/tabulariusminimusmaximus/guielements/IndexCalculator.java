package mikan.tabulariusminimusmaximus.guielements;

/**
 *
 * @author mika
 */
class IndexCalculator {
    
    int index = 12;
    int idIndex = 1;
    
    protected void increment(){
        this.index += 7;
        this.idIndex += 1;
    }
    
    protected int value(){
        return this.index;
    }
    
    protected int idIndex(){
        return this.idIndex;
    }
    
    protected void reset() {
        this.index=12;
    }
   
}
