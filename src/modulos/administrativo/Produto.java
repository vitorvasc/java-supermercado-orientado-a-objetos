package modulos.administrativo;

public class Produto {
    private String nome;
    private double valor;
    private int qtdEstoque;

    public Produto(String nome, double valor, int qtdEstoque) {
        this.nome = nome;
        this.valor = valor;
        this.qtdEstoque = qtdEstoque;
    }

    public String getNome() {
        return nome;
    }

    public double getValor() {
        return valor;
    }

    public int getQtdEstoque() {
        return qtdEstoque;
    }

    public void subtrairEstoque(int quantidade) {
        this.qtdEstoque = this.qtdEstoque-quantidade;
    }

    public void setEstoque(int quantidade) {
        this.qtdEstoque = quantidade;
    }
}
