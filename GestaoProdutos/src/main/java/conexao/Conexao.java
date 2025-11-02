package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static final String url = "jdbc:mysql://localhost:3306/exemplobd";
    private static final String user = "root";
    private static final String password = "Stellio@1";

    private static Connection conn;

    public static Connection getConexao(){
        try{
            //Verifica se o conn é nulo(não tem um banco criado) se não tiver
            //cria um novo banco de dados e retorn
            if(conn == null){
                conn = DriverManager.getConnection(url, user, password);
                return conn;
            }else{
                //retorna o banco existente
                return conn;
            }
        }catch (SQLException e){
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            return null;
        }
    }

}
