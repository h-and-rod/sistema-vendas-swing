package sistemaVendas.model.dao;

import sistemaVendas.model.Nota;
import sistemaVendas.model.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Henrique Andrade (h-and-rod)
 */
public class NotaDAO {
    private Conexao conexao;
    private Connection conn;

    public NotaDAO() {
        conexao = new Conexao();
        conn = conexao.getConexao();
    }

    public int inserirNota(Nota nota) {
        String sql = "INSERT INTO Nota (dataVenda, FK_Cliente, valorTotal) VALUES (?, ?, ?)";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            // Converte java.util.Date para java.sql.Date
            java.sql.Date dataSql = new java.sql.Date(nota.getDataVenda().getTime());
            stmt.setDate(1, dataSql);
            stmt.setInt(2, nota.getFkCliente());
            stmt.setDouble(3, nota.getValorTotal());
            
            int affectedRows = stmt.executeUpdate();
            
            if (affectedRows > 0) {
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int idGerado = generatedKeys.getInt(1);
                    System.out.println("Nota inserida com ID: " + idGerado);
                    return idGerado;
                }
            }
            
        } catch (SQLException ex) {
            System.out.println("Erro ao inserir nota: " + ex.getMessage());
            ex.printStackTrace();
        }
        
        return -1;
    }

    public Nota getNota(int id) {
        String sql = "SELECT * FROM Nota WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                Nota nota = new Nota();
                nota.setId(rs.getInt("id"));
                nota.setDataVenda(rs.getDate("dataVenda"));
                nota.setFkCliente(rs.getInt("FK_Cliente"));
                nota.setValorTotal(rs.getDouble("valorTotal"));
                return nota;
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao consultar nota: " + ex.getMessage());
        }
        return null;
    }

    public List<Nota> getNotas() {
        String sql = "SELECT * FROM Nota";
        List<Nota> notas = new ArrayList<>();
        
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Nota nota = new Nota();
                nota.setId(rs.getInt("id"));
                nota.setDataVenda(rs.getDate("dataVenda"));
                nota.setFkCliente(rs.getInt("FK_Cliente"));
                nota.setValorTotal(rs.getDouble("valorTotal"));
                notas.add(nota);
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao consultar notas: " + ex.getMessage());
        }
        return notas;
    }
    
        public List<Nota> listarNotasComCliente() {
        List<Nota> lista = new ArrayList<>();
        String sql = "SELECT n.id, n.dataVenda, c.nome AS cliente, n.valorTotal " +
                     "FROM Nota n " +
                     "INNER JOIN Cliente c ON n.FK_Cliente = c.id";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Nota nota = new Nota();
                nota.setId(rs.getInt("id"));
                nota.setDataVenda(rs.getDate("dataVenda"));
                nota.setClienteNome(rs.getString("cliente"));
                nota.setValorTotal(rs.getDouble("valorTotal"));
                lista.add(nota);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}