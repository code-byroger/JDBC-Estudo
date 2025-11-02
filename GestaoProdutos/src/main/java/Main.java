
import DAO.ProdutoDao;
import conexao.Conexao;
import produto.Produto;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
public class Main {
    //metodo para mostrar o produto na tela
    private static void mostrarProdutos(ProdutoDao produtoDao){
        List<Produto> todosProdutos = produtoDao.listarTodos();
        if(todosProdutos.isEmpty()){
            System.out.println("Nenhum produto encontrado");
        }else{
            System.out.println("Lista de produtos:");
            for(Produto p : todosProdutos){
                System.out.println(p.getId() + " | " + p.getNome() + " | " + p.getPreco() + " | " +
                        p.getQuantidade() + " | "+ p.getStatus());
            }
        }

    }
    public static void main(String[] args){
       try(Connection conexao = Conexao.getConexao()){
           ProdutoDao produtoDao = new ProdutoDao(conexao);

           //Listar todos os produtos deve está vazio
            mostrarProdutos(produtoDao);

            //Exemplo de inserção
           Produto novoProduto1 = new Produto("Monitor FULL HD 35p", 10, 2.500, "Em estoque");
           Produto novoProduto2 = new Produto("Teclado", 6, 60, "Estoque Baixo");
           Produto novoProduto3 = new Produto("mouse", 2, 30, "Estoque Baixo");

           produtoDao.inseri(novoProduto1);
           produtoDao.inseri(novoProduto2);
           produtoDao.inseri(novoProduto3);

           //Lista todos os produtos após a inserção
           mostrarProdutos(produtoDao);


           //Exemplo consulta por id

           Produto produtoConsultado = produtoDao.consultarPorId(2);
           System.out.println("---------------------------");
           if(produtoConsultado != null){
               //produtoConsultado.setNome("teclado");
               System.out.println("Produto: " + produtoConsultado.getNome());
                //produtoDao.atualizar(produtoConsultado);
                mostrarProdutos(produtoDao);
           }else{
               System.out.println("Produto não encontrado");
           }

            /*
           //  Exclusao por ID
           produtoDao.excluir(5);
           produtoDao.excluir(4);
           produtoDao.excluir(6);


           mostrarProdutos(produtoDao);

           //Limpar banco de dados
           produtoDao.excluirTodos();
           System.out.println("----------LISTA APÓS A LIMPEZA");
           mostrarProdutos(produtoDao);
            */
       }catch (SQLException e){
            System.err.println("Erro geral: " + e.getMessage());
       }
    }
}
