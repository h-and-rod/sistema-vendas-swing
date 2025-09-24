package sistemaVendas.model.dao;

import sistemaVendas.model.ItemNota;
import sistemaVendas.model.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Henrique Andrade (h-and-rod)
 */
public class ItensNotaDAO {
    private Conexao conexao;
    private Connection conn;

    public ItensNotaDAO() {
        conexao = new Conexao();
        conn = conexao.getConexao();
    }

    public boolean inserirItemNota(ItemNota item) {
        String sql = "INSERT INTO ItensNota (quantidade, FK_Nota, FK_Produtos, valorUnitario) VALUES (?, ?, ?, ?)";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, item.getQuantidade());
            stmt.setInt(2, item.getFkNota());
            stmt.setInt(3, item.getFkProduto());
            stmt.setDouble(4, item.getValorUnitario());
            
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
            
        } catch (SQLException ex) {
            System.out.println("Erro ao inserir item da nota: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }

}