package demo.gb.lessons;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        DBHandler dbHandler = new DBHandler();
        dbHandler.conn();
        dbHandler.createDB();
       // dbHandler.writeDB();
        dbHandler.readDB();
        dbHandler.closeDB();
    }
}
