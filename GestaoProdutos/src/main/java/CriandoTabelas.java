import conexao.Conexao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CriandoTabelas {
    public static void main(String[] args){
        try(Connection conexao = Conexao.getConexao();
            Statement stmt = conexao.createStatement();){
                //Definindo o comando para criar a tabela
                String comandoSQL = "CREATE TABLE produtos("+
                        "id_produtos INTEGER PRIMARY KEY," +
                        "nome_produto TEXT NOT NULL," +
                        "quantidade INTEGER,"+
                        "preco REAL,"+
                        "status TEXT" +
                        ");";
                System.out.println(comandoSQL);
                stmt.execute(comandoSQL);
                System.out.println("Tabela 'produtos' criada com sucesso!");
        } catch (SQLException e){
            System.err.println("Erro ao criar a tabela: " + e.getMessage());
            //informa a sequencia de chamada de métodos dos erros
            e.printStackTrace();
        }

    }
}
