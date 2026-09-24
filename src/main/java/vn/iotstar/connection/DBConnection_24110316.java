package vn.iotstar.connection;
import java.sql.*;
public final class DBConnection_24110316 {
 private DBConnection_24110316() {}
 public static Connection open() throws SQLException {
  String url = System.getenv().getOrDefault("DB_URL", "jdbc:sqlserver://localhost:1433;databaseName=KTQT_De4;encrypt=true;trustServerCertificate=true");
  String user = System.getenv().getOrDefault("DB_USER", "sa");
  String password = System.getenv("DB_PASSWORD");
  if (password == null || password.isBlank()) throw new SQLException("Thiếu biến môi trường DB_PASSWORD");
  return DriverManager.getConnection(url,user,password);
 }
}
