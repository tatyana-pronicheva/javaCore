package demo.gb.lessons;

import java.sql.*;
import java.util.Date;

public class DBHandler {
    public static Connection conn;
    public static Statement statmt;

    // --------ПОДКЛЮЧЕНИЕ К БАЗЕ ДАННЫХ--------
    public static void conn() throws ClassNotFoundException, SQLException
    {
        conn = null;
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cinemaapp","root","123");

        System.out.println("База Подключена!");
    }

    // --------Создание таблицы--------
    public static void createDB() throws ClassNotFoundException, SQLException
    {
        statmt = conn.createStatement();

        statmt.execute("CREATE TABLE if not exists movies " +
                "(id INTEGER AUTO_INCREMENT PRIMARY KEY, " +
                "name TEXT, " +
                "duration TIME);");
        statmt.execute("CREATE TABLE if not exists timetable " +
                "(id INT AUTO_INCREMENT PRIMARY KEY, " +
                "price INT, " +
                "date DATETIME, " +
                "movie_id INT, " +
                "CONSTRAINT movies_id FOREIGN KEY (movie_id) REFERENCES movies(id));");
        statmt.execute("CREATE TABLE if not exists bought_tickets " +
                "(id INT AUTO_INCREMENT PRIMARY KEY, " +
                "session_id INT, " +
                "CONSTRAINT session_id FOREIGN KEY (session_id) REFERENCES timetable(id));");


        System.out.println("Таблица создана или уже существует.");
    }

    // --------Заполнение таблицы--------
    public static void writeDB() throws SQLException
    {
        statmt.execute("INSERT INTO movies (name, duration) VALUES ('Winnie the Pooh', '01:00:00');");
        statmt.execute("INSERT INTO movies (name, duration) VALUES ('Venom', '01:30:00');");
        statmt.execute("INSERT INTO movies (name, duration) VALUES ('Avatar', '02:00:00');");
        statmt.execute("INSERT INTO movies (name, duration) VALUES ('Buba', '01:00:00');");
        statmt.execute("INSERT INTO movies (name, duration) VALUES ('Hatiko', '01:30:00');");

        statmt.execute("INSERT INTO timetable (movie_id, price, date) VALUES (1, 200, '2022-04-12 12:00:00');");
        statmt.execute("INSERT INTO timetable (movie_id, price, date) VALUES (4, 200, '2022-04-12 14:00:00');");
        statmt.execute("INSERT INTO timetable (movie_id, price, date) VALUES (5, 350, '2022-04-12 15:40:00');");
        statmt.execute("INSERT INTO timetable (movie_id, price, date) VALUES (3, 500, '2022-04-12 16:40:00');");
        statmt.execute("INSERT INTO timetable (movie_id, price, date) VALUES (2, 1000, '2022-04-12 16:50:00');");
        statmt.execute("INSERT INTO timetable (movie_id, price, date) VALUES (1, 1000, '2022-04-12 21:50:00');");


        for (int i = 0; i<40; i++) statmt.execute("INSERT INTO bought_tickets (session_id) VALUES (1);");
        for (int i = 0; i<10; i++) statmt.execute("INSERT INTO bought_tickets (session_id) VALUES (3);");
        for (int i = 0; i<50; i++) statmt.execute("INSERT INTO bought_tickets (session_id) VALUES (4);");
        for (int i = 0; i<200; i++) statmt.execute("INSERT INTO bought_tickets (session_id) VALUES (6);");

        System.out.println("Таблица заполнена");
    }

    // -------- Вывод таблицы--------
    public static void readDB() throws ClassNotFoundException, SQLException
    {
        ResultSet resSet1 = statmt.executeQuery("SELECT m1.name, t1.date, m1.duration, m2.name, t2.date, m2.duration FROM timetable t1 " +
                "JOIN timetable t2 ON t1.id<>t2.id " +
                "JOIN movies m1 ON t1.movie_id = m1.id " +
                "JOIN movies m2 ON t2.movie_id = m2.id " +
                "WHERE t1.date<=t2.date AND (t1.date + m1.duration)>t2.date ORDER BY t1.date;");
        while(resSet1.next())
        {
            String name1 = resSet1.getString(1);
            Date date1 = resSet1.getTimestamp(2);
            Date duration1 = resSet1.getTime(3);
            String name2 = resSet1.getString(4);
            Date date2 = resSet1.getTimestamp(5);
            Date duration2 = resSet1.getTime(6);

            System.out.println( "Название фильма 1 = " + name1 );
            System.out.println( "Время фильма 1 = " + date1 );
            System.out.println( "Длительность фильма 1 = " + duration1 );
            System.out.println( "Название фильма 2 = " + name2 );
            System.out.println( "Время фильма 2 = " + date2 );
            System.out.println( "Длительность фильма 2 = " + duration2 );
            System.out.println();
        }

        ResultSet resSet2 = statmt.executeQuery("SELECT m1.name, t1.date, m1.duration, m2.name, t2.date, m2.duration, " +
                "t2.date-t1.date-m1.duration AS pause " +
                "FROM timetable t1 JOIN timetable t2 ON t1.date<t2.date\n" +
                "JOIN movies m1 ON t1.movie_id = m1.id " +
                "JOIN movies m2 ON t2.movie_id = m2.id " +
                "WHERE t2.date-t1.date-m1.duration>30 " +
                "ORDER BY t2.date-t1.date-m1.duration; ");
        while(resSet2.next())
        {
            String name1 = resSet2.getString(1);
            Date date1 = resSet2.getTimestamp(2);
            Date duration1 = resSet2.getTime(3);
            String name2 = resSet2.getString(4);
            Date date2 = resSet2.getTimestamp(5);
            Date duration2 = resSet2.getTime(6);
            Long pause = resSet2.getLong(7);

            System.out.println( "Название фильма 1 = " + name1 );
            System.out.println( "Время фильма 1 = " + date1 );
            System.out.println( "Длительность фильма 1 = " + duration1 );
            System.out.println( "Название фильма 2 = " + name2 );
            System.out.println( "Время фильма 2 = " + date2 );
            System.out.println( "Длительность фильма 2 = " + duration2 );
            System.out.println( "Пауза между фильмами = " + duration2 );
            System.out.println();
        }

        ResultSet resSet3 = statmt.executeQuery("(SELECT m.name, COUNT(*), SUM(t.price) FROM movies m " +
                "JOIN timetable t ON t.movie_id = m.id " +
                "JOIN bought_tickets bt ON bt.session_id=t.id " +
                "GROUP BY m.id " +
                "ORDER BY SUM(t.price) DESC) " +
                "UNION " +
                "(SELECT 'Итого', COUNT(*), SUM(t.price) FROM timetable t " +
                "JOIN bought_tickets bt ON bt.session_id=t.id);");
        while(resSet3.next())
        {
            String name = resSet3.getString(1);
            Integer tickets_count = resSet3.getInt(2);
            Integer sum_price = resSet3.getInt(3);

            System.out.println( "Название фильма = " + name );
            System.out.println( "Продано билетов = " + tickets_count );
            System.out.println( "Сумма проданных билетов = " + sum_price );
            System.out.println();
        }

        ResultSet resSet4 = statmt.executeQuery("SELECT '9-15', COUNT(*), SUM(t.price) FROM timetable t\n" +
                "JOIN bought_tickets bt ON bt.session_id=t.id\n" +
                "WHERE t.date BETWEEN '2022-04-12 09:00:00' AND '2022-04-12 15:00:00'\n" +
                "UNION\n" +
                "SELECT '15-18', COUNT(*), SUM(t.price) FROM timetable t\n" +
                "JOIN bought_tickets bt ON bt.session_id=t.id\n" +
                "WHERE t.date BETWEEN '2022-04-12 15:00:00' AND '2022-04-12 18:00:00'\n" +
                "UNION\n" +
                "SELECT '18-21', COUNT(*), SUM(t.price) FROM timetable t\n" +
                "JOIN bought_tickets bt ON bt.session_id=t.id\n" +
                "WHERE t.date BETWEEN '2022-04-12 18:00:00' AND '2022-04-12 21:00:00'\n" +
                "UNION\n" +
                "SELECT '21-00', COUNT(*), SUM(t.price) FROM timetable t\n" +
                "JOIN bought_tickets bt ON bt.session_id=t.id\n" +
                "WHERE t.date BETWEEN '2022-04-12 21:00:00' AND '2022-04-13 00:00:00';\n");

        while(resSet4.next())
        {
            String time = resSet4.getString(1);
            Integer tickets_count = resSet4.getInt(2);
            Integer sum_price = resSet4.getInt(3);

            System.out.println( "Время дня = " + time );
            System.out.println( "Продано билетов = " + tickets_count );
            System.out.println( "Сумма проданных билетов = " + sum_price );
            System.out.println();
        }
    }

    // --------Закрытие--------
    public static void closeDB() throws ClassNotFoundException, SQLException
    {
        conn.close();

        System.out.println("Соединения закрыты");
    }
}
