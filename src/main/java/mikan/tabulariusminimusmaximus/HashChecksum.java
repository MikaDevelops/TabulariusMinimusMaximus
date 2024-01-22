package mikan.tabulariusminimusmaximus;

import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import jakarta.xml.bind.DatatypeConverter;

/**
 *
 * @author mika
 */
public class HashChecksum<T> {
    
    private T rowObject;
    private String previousHash;
    
    public HashChecksum(T rowObject, String previousHash){
        this.rowObject = rowObject;
        this.previousHash = previousHash;        
    }
    
    public String countHash() throws IllegalArgumentException, IllegalAccessException, NoSuchAlgorithmException{
        
        String line = "";
        Field[] listOfFields = this.rowObject.getClass().getDeclaredFields();
        
        for(int i = 0; i < listOfFields.length; i++){
            Field field = listOfFields[i];
            String type = field.getType().toString();
            String nameOfField = field.getName();
            if(type.equalsIgnoreCase("class java.lang.String")){
                line += field.get(this.rowObject).toString();
            }
            if(type.equalsIgnoreCase("int")){
                line += Integer.toString((int) field.get(this.rowObject));
            } 
            if(type.equalsIgnoreCase("long")){
                line += Long.toString((long) field.get(this.rowObject));
            }
        }
        
        line += this.previousHash;
        
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(line.getBytes());
        byte[] digest = messageDigest.digest();
        String newHash = DatatypeConverter.printHexBinary(digest).toUpperCase();
        
        return newHash;
    }
    
}
