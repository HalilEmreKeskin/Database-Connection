
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Connection {
    
    private String user_name = "root";
    private String Password ="********";
    
    private String db_name = "demo";
    private String host ="127.0.0.1";
    private int port = 3306;
    
    private java.sql.Connection connection = null;
    
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    
    public void getPreparedStatement(int id){
        String sorgu ="Select * From emp where id >?";
        
        try {
            preparedStatement=connection.prepareStatement(sorgu);
            preparedStatement.setInt(1,id);
            
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                String firstname =rs.getString("firstname");
                String lastname =rs.getString("lastname");
                String email =rs.getString("email");
                
                System.out.println("Name: "+firstname+" lastname: "+lastname+" email: "+email);
            }
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    public void deletePreparedEmp(){
        
        
        try {    
            
            statement = connection.createStatement();
            String querry ="Delete From emp where id = ?";
           
            statement.executeUpdate(querry);
            
        } catch (SQLException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    
    public void addPreaparedEmp(String firstname,String lastname,String email){
        
        
        
        try {
            String sorgu= "Insert Into emp (firstname,lastname,email) VALUES("+"'"+firstname+"',"+"'"+lastname+"',"+"'"+email+"')";
            statement =connection.prepareStatement(sorgu);
            
             
            statement.execute(sorgu);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void addEmployee(){
        try {
            statement = connection.createStatement();
            String firstname = "Halil Emre";
            String lastName ="Keskin";
            String email = "dasdasd@gmail.com ";
            
            
            
            String sorgu= "Insert Into emp (firstname,lastname,email) VALUES("+"'"+firstname+"',"+"'"+lastName+"',"+"'"+email+"')";
            
            statement.executeUpdate(sorgu);
            
        } catch (SQLException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void takeEmployee(){
        String query ="Select * From emp";
        
        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            
            while(rs.next()){
                int id =rs.getInt("id");
                String name = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                String email = rs.getString("email");
                
                System.out.println("Id:"+id+" Name:"+name+" LastName:"+lastname+" Email:"+email);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public Connection() {
        
        String url ="jdbc:mysql://"+host+":"+port+"/"+db_name;
        
        try{
            Class.forName("com.myql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Not Found Driver");
        }
       
        try {
            connection = DriverManager.getConnection(url,user_name,Password);
            System.out.println("Connected..");
            
        } catch (SQLException ex) {
            System.out.println("Connection is failed...");
        }
    }
    
    public static void main(String[] args) {
        Connection baglanti = new Connection();
       
        //baglanti.getPreparedStatement(1);
       // baglanti.addPreaparedEmp("jAVA", "Learning", "jjjj@gmail.com");
        //baglanti.addPreaparedEmp("c++", "Learning", "cccc@gmail.com");
        //baglanti.addPreaparedEmp("pyton", "Learning", "pppp@gmail.com");
        //baglanti.addPreaparedEmp("PHP", "Learning", "lllll@gmail.com");
       // baglanti.deletePreparedEmp();
       
        
        
        baglanti.takeEmployee();
       
      //  baglanti.addEmployee();
        
        //baglanti.takeEmployee();
         
         
        
    }
    
    
}

    

