package sistemaVendas.model.dao;

import sistemaVendas.model.Cliente;
import sistemaVendas.model.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteDAO {
    private Conexao conexao;
    private Connection conn;

    public ClienteDAO() {
        conexao = new Conexao();
        conn = conexao.getConexao();
    }

    public void inserir (Cliente cliente) {
        String sql = "INSERT INTO Cliente (nome, endereco, email, telefone) VALUES (?,?,?,?);";

        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            
            stmt.setString(1, cliente.getNomeCliente());
            stmt.setString(2, cliente.getEnderecoCliente());
            stmt.setString(3, cliente.getEmailCliente());
            stmt.setString(4, cliente.getTelefoneCliente());

            stmt.execute();

        } catch (SQLException ex) {
            System.out.println("Erro ao inserir cliente\nErro: " + ex.getMessage());
        }


    }

    public Cliente getCliente(int id) {
    String sql = "SELECT * FROM pessoa WHERE id = ?";
        try {
            
            PreparedStatement stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                        
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            Cliente c = new Cliente();

            rs.first();

            c.setIdCliente(id); 
            c.setNomeCliente(rs.getString("nome"));
            c.setEnderecoCliente(rs.getString("endereco"));
            c.setEmailCliente(rs.getString("email"));
            c.setTelefoneCliente(rs.getString("telefone"));

            return c;
            
        } catch (SQLException ex) {
            System.out.println("Erro ao consultar cliente: " + ex.getMessage());
            return null;
        }
    }
}
