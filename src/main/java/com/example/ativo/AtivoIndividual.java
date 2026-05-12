package com.example.ativo;

/** Folha — representa um ativo isolado: ação, FII, tesouro, etc. */
public class AtivoIndividual implements Ativo {

    private final String nome;
    private final String tipo;       // ACAO | FII | RENDA_FIXA | CRIPTO
    private final int quantidade;
    private final double precoUnitario;

    public AtivoIndividual(String nome, String tipo, int quantidade, double precoUnitario) {
        if (quantidade < 0) throw new IllegalArgumentException("Quantidade nao pode ser negativa");
        if (precoUnitario < 0) throw new IllegalArgumentException("Preco nao pode ser negativo");
        this.nome = nome;
        this.tipo = tipo;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
    }

    @Override public String getNome() { return nome; }
    @Override public double getValorTotal() { return quantidade * precoUnitario; }
    public String getTipo() { return tipo; }
    public int getQuantidade() { return quantidade; }
    public double getPrecoUnitario() { return precoUnitario; }

    @Override
    public void exibir(String indentacao) {
        System.out.printf("%s[%s] %s — %d x R$%.2f = R$%.2f%n",
            indentacao, tipo, nome, quantidade, precoUnitario, getValorTotal());
    }
}
