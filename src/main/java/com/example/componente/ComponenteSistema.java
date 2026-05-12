package com.example.componente;

/**
 * Componente — interface comum para Arquivo (folha) e Diretorio (composto).
 */
public interface ComponenteSistema {
    String getNome();
    long getTamanhoBytes();
    void imprimir(String prefixo);
}
