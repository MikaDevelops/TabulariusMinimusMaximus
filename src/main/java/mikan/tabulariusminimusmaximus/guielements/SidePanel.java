package mikan.tabulariusminimusmaximus.guielements;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

/**
 *
 * @author mika
 */
public class SidePanel {
    public static VBox getSidePanel(){
        
        Button nappi = new Button("test");
        Button nappi2 = new Button("test");
        
        VBox sidemenuBox = new VBox(4);

        sidemenuBox.getChildren().addAll(nappi,nappi2);
        
        return sidemenuBox;
    }
}
