package sistemaVendas.model;
import java.util.Date;

/**
 *
 * @author Henrique Andrade (h-and-rod)
 */
public class Nota {
    private int id;
    private Date dataVenda;
    private int fkCliente;
    private double valorTotal;

    public Nota() {
    }

    public Nota(Date dataVenda, int fkCliente, double valorTotal) {
        this.dataVenda = dataVenda;
        this.fkCliente = fkCliente;
        this.valorTotal = valorTotal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    public int getFkCliente() {
        return fkCliente;
    }

    public void setFkCliente(int fkCliente) {
        this.fkCliente = fkCliente;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }
}