package com.example.ativo;

/** Componente comum — trata Ativos individuais e Carteiras de forma uniforme. */
public interface Ativo {
    String getNome();
    double getValorTotal();
    void exibir(String indentacao);
}
