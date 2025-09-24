package sistemaVendas.model;


/**
 *
 * @author Henrique Andrade (h-and-rod)
 */
public class ItemNota {
    private int id;
    private int quantidade;
    private int fkNota;
    private int fkProduto;
    private double valorUnitario;
    private double valorTotal;

    public ItemNota() {
    }

    public ItemNota(int quantidade, int fkNota, int fkProduto, double valorUnitario) {
        this.quantidade = quantidade;
        this.fkNota = fkNota;
        this.fkProduto = fkProduto;
        this.valorUnitario = valorUnitario;
        this.valorTotal = valorUnitario * quantidade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
        this.valorTotal = this.valorUnitario * quantidade;
    }

    public int getFkNota() {
        return fkNota;
    }

    public void setFkNota(int fkNota) {
        this.fkNota = fkNota;
    }

    public int getFkProduto() {
        return fkProduto;
    }

    public void setFkProduto(int fkProduto) {
        this.fkProduto = fkProduto;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
        this.valorTotal = valorUnitario * this.quantidade;
    }

    public double getValorTotal() {
        return valorTotal;
    }

}