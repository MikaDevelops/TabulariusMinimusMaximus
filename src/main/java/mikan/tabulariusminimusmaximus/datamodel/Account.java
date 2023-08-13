package mikan.tabulariusminimusmaximus.datamodel;

/**
 * Defines account data
 * @author mika
 */
public class Account {
    public int accountNumber;
    public int categoryID;
    public String name;
    public String description;
    public int vatBase;

    // Constructors
    public Account(){}
    public Account(int accountNumber, int categoryID, String name, String description, int vatBase) {
        this.accountNumber = accountNumber;
        this.categoryID = categoryID;
        this.name = name;
        this.description = description;
        this.vatBase = vatBase;
    }
    
}
