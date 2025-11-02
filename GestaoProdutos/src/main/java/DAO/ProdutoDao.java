package DAO;

import produto.Produto;

import java.sql.Connection;
import java.sql. PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDao {
    private final Connection CONEXAO_DB;
    //construtor que inicializa a conexão com o bd

    public ProdutoDao(Connection conexao){
        this.CONEXAO_DB = conexao;
    }

    //Metodo para inserir um novo produto
    public void inseri(Produto produto){

        String sql = "INSERT INTO produto (nome_produto, quantidade, preco,"+
                "status) VALUES (?, ?, ?, ?)";


        try(PreparedStatement stmt = CONEXAO_DB.prepareStatement(sql)){
            stmt.setString(1, produto.getNome());
            stmt.setInt(2,produto.getQuantidade());
            stmt.setDouble(3,produto.getPreco());
            stmt.setString(4,produto.getStatus());

            stmt.executeUpdate();

        }catch (SQLException e){
            System.err.println("Erro ao imprimir produto: " + e.getMessage());
        }
    }

    //Metodo para excluir todos os produtos
    public void excluirTodos(){
        String sql = "DELETE FROM produto";
        try(PreparedStatement stmt = CONEXAO_DB.prepareStatement(sql)){
            stmt.executeUpdate();
        }catch (SQLException e){
            System.err.println("Erro ao excluir todos os produtos: " + e.getMessage());
        }
    }

    //Consultando por id
    public Produto consultarPorId( int id){
        String sql = "SELECT * FROM produto WHERE id_produto = ?";

        try(PreparedStatement stmt = CONEXAO_DB.prepareStatement(sql)){
            //Definindo o parâmetro do ID antes de executar a consulta
            stmt.setInt(1, id);
            try(ResultSet rs = stmt.executeQuery()){
                //se houver um resultado entra no if
                if(rs.next()){
                    //retornando a consulta
                    Produto produto = new Produto();
                    produto.setId(rs.getInt("id_produto"));
                    produto.setNome(rs.getString("nome_produto"));
                    produto.setQuantidade(rs.getInt("quantidade"));
                    produto.setPreco(rs.getDouble("preco"));
                    produto.setStatus(rs.getString("status"));
                    return produto;
                }
            }
        }catch (SQLException e){
            System.err.println("Erro ao consultar produto por ID: " + e.getMessage());
        }
        return null;
    }

    //Metodo atualizar informações no BD
    public void atualizar(Produto produto){
        String sql = "UPDATE produto SET nome_produto = ?,quantidade = ?, preco = ?, status = ? " +
                "WHERE id_produto = ?";

        try(PreparedStatement stmt = CONEXAO_DB.prepareStatement(sql)){
            stmt.setString(1, produto.getNome());
            stmt.setInt(2, produto.getQuantidade());
            stmt.setDouble(3, produto.getPreco());
            stmt.setString(4, produto.getStatus());
            stmt.setInt(5, produto.getId());
            stmt.executeUpdate();
        }catch(SQLException e){
            System.err.println("Erro ao atulizar produto: " + e.getMessage());
        }
    }
    //Metodo para excluir um unico produto
    public void excluir(int id){
        String sql = "DELETE FROM produto WHERE id_produto = ?";
        try(PreparedStatement stmt = CONEXAO_DB.prepareStatement(sql)){
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }catch (SQLException e){
            System.err.println("Erro ao excluir produto: " + e.getMessage());
        }
    }

    //Metodo para listar todos os produtos no BD
    public List<Produto> listarTodos(){
        List<Produto> produto = new ArrayList<>();
        String sql = "SELECT * FROM produto";

        try(PreparedStatement stmt = CONEXAO_DB.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery()){
            //enquanto tiver produto dentro da tabela
            while(rs.next()){
                Produto produtos = new Produto();
                produtos.setId(rs.getInt("id_produto"));
                produtos.setNome(rs.getString("nome_produto"));
                produtos.setQuantidade(rs.getInt("quantidade"));
                produtos.setPreco(rs.getDouble("preco"));
                produtos.setStatus(rs.getString("status"));
                //adicionando os produtos
                produto.add(produtos);
            }

        }catch (SQLException e){
            System.err.println("Erro ao listar produtos: " + e.getMessage());
        }
        return produto;
    }

}
