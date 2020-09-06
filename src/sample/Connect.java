package sample;
import java.sql.*;
import java.util.Hashtable;


public class Connect {
    public static Connection connect(){
        Connection conn = null;
        try {
            String url = "jdbc:sqlite:Restaurant.db";
            return DriverManager.getConnection(url);
        }catch (SQLException e) {
            System.out.println(e.getMessage());

        }
        return conn;
    }



    public Hashtable selectAllByID(String table,int id){
        Statement stmt = null;
        ResultSet rs = null;
        Connection conn = null;
        String sql = "SELECT " + table +"_id,name,url FROM "+ table;
        Hashtable<String,String> my_dict = new Hashtable<String, String>();
        try{
            conn = this.connect();
            stmt  = conn.createStatement();
            rs    = stmt.executeQuery(sql);
            while (rs.next()){
                if(rs.getInt(table + "_id") == id){
                    my_dict.put("id" , Integer.toString(rs.getInt(table + "_id")));
                    my_dict.put("name" , rs.getString("name"));
                    my_dict.put("url" ,rs.getString("url"));
                }
            }
            return my_dict;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            try {
                conn.close();
                stmt.close();
                rs.close();
            }catch (SQLException ex){
                System.out.println(ex.getMessage());
            }
        }
        return my_dict;
    }


    public int countRow(String table){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        int count = 0;
        String sql = "SELECT " + table +"_id FROM "+ table;
        try {
            conn = this.connect();
            stmt  = conn.createStatement();
            rs    = stmt.executeQuery(sql);
            while (rs.next()) {
                count++;
            }
            return count;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            try {
                conn.close();
                stmt.close();
                rs.close();
            }catch (SQLException ex){
                System.out.println(ex.getMessage());
            }
        }
        return count;
    }

    public void insertDataIntoOrders(int user_id, int pizza_id, int hamburgur_id, String type_type, Date date_date){
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "INSERT INTO orders(user_id,pizza_id,hamburgur_id,type,date) VALUES(?,?,?,?,?)";
        try {
            conn = this.connect();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,user_id);
            pstmt.setInt(2,pizza_id);
            pstmt.setInt(3,hamburgur_id);
            pstmt.setString(4,type_type);
            pstmt.setDate(5,date_date);
            pstmt.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            try {
                conn.close();
            }catch (SQLException ex){
                System.out.println(ex.getMessage());
            }
        }
    }
}
