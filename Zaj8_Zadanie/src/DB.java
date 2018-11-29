import java.sql.*;

public class DB{

    static final int MAX_ERROR_ITERATION = 3;

    public static Connection connect(String user, String database, String password){
        Connection conn = null;

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://mysql.agh.edu.pl/" + database,
                            user, password);
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } catch (Exception e) {e.printStackTrace();}

        return conn;
    }

    public static boolean executeInput(Connection conn, TableStage.Book book, int iteration){
        boolean success = true;
        try
        {
            // the mysql insert statement
            String query = " insert into books (isbn, title, author, year)"
                    + " values (?, ?, ?, ?)";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString (1, book.getIsbn());
            preparedStmt.setString (2, book.getTitle());
            preparedStmt.setString (3, book.getAuthor());
            preparedStmt.setString (4, book.getYear());

            // execute the preparedstatement
            preparedStmt.execute();
        }
        catch (Exception e)
        {
            if(iteration < MAX_ERROR_ITERATION) {
                success = executeInput(conn, book, iteration + 1);
            }else{
                new ErrorStage();
                success = false;
            }
        }
        return success;
    }

    public static boolean executeInput(Connection conn, TableStage.Book book){
        boolean success = true;
        try
        {
            // the mysql insert statement
            String query = " insert into books (isbn, title, author, year)"
                    + " values (?, ?, ?, ?)";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString (1, book.getIsbn());
            preparedStmt.setString (2, book.getTitle());
            preparedStmt.setString (3, book.getAuthor());
            preparedStmt.setString (4, book.getYear());

            // execute the preparedstatement
            preparedStmt.execute();
        }
        catch (Exception e)
        {

        }
        return success;
    }

    public static boolean executeQuery(Connection conn, String query, DBQueryFunction function, int iteration){
        Statement stmt = null;
        ResultSet rs = null;
        boolean success = true;

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            function.execute(rs);
        }catch (SQLException ex){
            // handle any errors
            if(iteration < MAX_ERROR_ITERATION) {
                success = executeQuery(conn, query, function, iteration + 1);
            }else{
                new ErrorStage();
                success = false;
            }
        }finally {
            // zwalniamy zasoby, które nie będą potrzebne
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) { } // ignore
                stmt = null;
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) {} // ignore
                rs = null;
            }
        }
        return success;
    }

    public static boolean executeQuery(Connection conn, String query, DBQueryFunction function){
        Statement stmt = null;
        ResultSet rs = null;
        boolean success = true;

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            function.execute(rs);
        }catch (SQLException ex){
            // handle any errors
            success = executeQuery(conn, query, function, 1);
        }finally {
            // zwalniamy zasoby, które nie będą potrzebne
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) { } // ignore
                stmt = null;
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) {} // ignore
                rs = null;
            }
        }
        return success;
    }
}

