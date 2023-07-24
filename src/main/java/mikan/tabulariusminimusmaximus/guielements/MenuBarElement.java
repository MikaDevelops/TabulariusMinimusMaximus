package mikan.tabulariusminimusmaximus.guielements;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

/**
 *
 * @author mika
 */
public class MenuBarElement {
    public static MenuBar getMenuBar(){
    
        MenuItem item = new MenuItem("testItem");
        Menu testMenu = new Menu("test menu");
        testMenu.getItems().add(item);
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().add(testMenu);
        
        return menuBar;
    }
}
