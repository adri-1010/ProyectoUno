public class Conexion { 
    Connection conn = null;

public Connection conexion() {
    try {
        //cargar nuestro driver
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://192.168.3.40/hgp", "hgp", "aplicaps");
        System.out.println("Conexion establecida");
        //JOptionPane.showMessageDialog(null, "Conexion establecida");   
    } catch (ClassNotFoundException | SQLException e) {
        conn = null;
        JOptionPane.showMessageDialog(null, "Error de conexion con la base de datos, \ncomprueba tu conexión a la red");
        System.out.println(e);
    }

    return conn;
}

}
