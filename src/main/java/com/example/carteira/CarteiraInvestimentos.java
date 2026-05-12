package com.example.carteira;

import com.example.ativo.Ativo;
import java.util.*;

/** Composto — carteira que pode conter ativos individuais e sub-carteiras. */
public class CarteiraInvestimentos implements Ativo {

    private final String nome;
    private final String responsavel;
    private final List<Ativo> componentes = new ArrayList<>();

    public CarteiraInvestimentos(String nome, String responsavel) {
        this.nome = nome;
        this.responsavel = responsavel;
    }

    public void adicionar(Ativo ativo) { componentes.add(ativo); }
    public void remover(Ativo ativo)   { componentes.remove(ativo); }
    public List<Ativo> getComponentes() { return Collections.unmodifiableList(componentes); }
    public String getResponsavel()      { return responsavel; }
    public int getTotalAtivos()         { return componentes.size(); }

    @Override public String getNome() { return nome; }

    @Override
    public double getValorTotal() {
        return componentes.stream().mapToDouble(Ativo::getValorTotal).sum();
    }

    @Override
    public void exibir(String indentacao) {
        System.out.printf("%s📊 %s (resp: %s) — Total: R$%.2f%n",
            indentacao, nome, responsavel, getValorTotal());
        for (Ativo a : componentes) a.exibir(indentacao + "  ");
    }
}
