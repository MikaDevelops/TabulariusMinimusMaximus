package mikan.tabulariusminimusmaximus.datamodel;

import java.util.HashMap;

/**
 *
 * @author mikaw
 */
public enum TableIDfield {

        TOSITE          ("tosite", "tositeID"),
        ALVKANNAT       ("alvkannat", "prosentti"),
        YLAKATEGORIA    ("ylakategoria", "ylakategoriaID"),
        TILIKATEGORIA   ("tilikategoria", "kategoriaID"),
        TILIKARTTA      ("tilikartta", "tilinumero"),
        PAAKIRJA        ("paakirja", "riviID"),
        TAPAHTUMARIVI   ("tapahtumarivi", "riviID");
        
        private final String table;
        private final String idField;
        
        TableIDfield(String table, String idField){
            this.table = table;
            this.idField = idField;
        }
        
        public String tableName(){
                return this.table;
        }

        public String idFieldName(){
                return this.idField;
        }
        
}