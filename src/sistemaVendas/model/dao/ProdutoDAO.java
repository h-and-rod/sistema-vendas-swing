
package sistemaVendas.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import sistemaVendas.model.Produto;
import sistemaVendas.model.Conexao;

/**
 *
 * @author Henrique Garcia
 */
public class ProdutoDAO {
    private Conexao conexao;
    private Connection conn;

    public ProdutoDAO() {
        conexao = new Conexao();
        conn = conexao.getConexao();
    }

    public void inserir (Produto produto) {
        String sql = "INSERT INTO Produto (nome, descricao, precovenda, quantidade) VALUES (?,?,?,?);";

        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);

            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getDescricao());
            stmt.setDouble(3, produto.getPrecoVenda());
            stmt.setInt(4, produto.getQuantidade());

            stmt.execute();

        } catch (SQLException ex) {
            System.out.println("Erro ao inserir produto\nErro: " + ex.getMessage());
        }


    }

    public Produto getProduto(int id) {
    String sql = "SELECT * FROM pessoa WHERE id = ?";
        try {
            
            PreparedStatement stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                        
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            Produto p = new Produto();

            rs.first();
            
            p.setIdProduto(id);
            p.setNome(rs.getString("nome"));
            p.setDescricao(rs.getString("descricao"));
            p.setPrecoVenda(rs.getDouble("precoVenda"));
            p.setQuantidade(rs.getInt("quantidade"));

            return p;
            
        } catch (SQLException ex) {
            System.out.println("Erro ao consultar produto: " + ex.getMessage());
            return null;
        }
    }
}

