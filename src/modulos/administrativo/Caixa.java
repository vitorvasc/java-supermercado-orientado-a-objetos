package modulos.administrativo;

public class Caixa {
    private double fluxoTotal;
    private int qtdVendas;
    private double[] totalDosPagamentos = new double[3];

    public double getTotalDosPagamentos(int metodoDePagamento) {
        return totalDosPagamentos[metodoDePagamento];
    }

    public void setTotalDosPagamentos(int metodoDePagamento, double totalDosPagamentos) {
        this.totalDosPagamentos[metodoDePagamento] = totalDosPagamentos;
    }

    public double getFluxoTotal() {
        return fluxoTotal;
    }

    public void setFluxoTotal(double fluxoTotal) {
        this.fluxoTotal = fluxoTotal;
    }

    public int getQtdVendas() {
        return qtdVendas;
    }

    public void setQtdVendas(int qtdVendas) {
        this.qtdVendas = qtdVendas;
    }
}
