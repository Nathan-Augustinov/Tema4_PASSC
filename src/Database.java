import java.sql.*;
import java.util.ArrayList;

public class Database implements StudentsStorer{
    public Database(){}
    public ArrayList<Student> readStudents(){
        ArrayList<Student>students;
        try {
            Class.forName("org.sqlite.JDBC");
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        deleteDatabaseIfAlreadyExists();
        createNewDatabase();
        createNewTable();
        insert("Nathan",8.9);
        insert("Boji",8.3);
        insert("Paul",7.5);
        insert("Rares",7.2);
        students=adaugareStudenti();
        return students;
    }
    public void createNewDatabase(){
        String url = "jdbc:sqlite:sqlite/students" ;

        try {
            Connection conn = DriverManager.getConnection(url);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteDatabaseIfAlreadyExists(){
        String sql = "DROP TABLE students";

        try{
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void createNewTable(){
        String url = "jdbc:sqlite:sqlite//students.db";

        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS students (\n"
                + " nr_matricol integer PRIMARY KEY,\n"
                + " nume text NOT NULL,\n"
                + " medie real\n"
                + ");";

        try{
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    private Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:sqlite//students.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void insert(String nume, double medie) {
        String sql = "INSERT INTO students(nume, medie) VALUES(?,?)";

        try{
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, nume);
            pstmt.setDouble(2, medie);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Student> adaugareStudenti(){
        String sql = "SELECT * FROM students";
        ArrayList<Student>students=new ArrayList<>();
        try {
            Connection conn = this.connect();
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            // loop through the result set
            while (rs.next()) {
                /*System.out.println(rs.getInt("id") +  "\t" +
                        rs.getString("name") + "\t" +
                        rs.getDouble("capacity"));*/
                Student student = new Student(rs.getInt("nr_matricol"),rs.getDouble("medie"),rs.getString("nume"));
                students.add(student);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return students;
    }
}
