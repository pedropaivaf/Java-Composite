package com.example.componente;

/**
 * Folha (Leaf) — representa um arquivo que não contém outros componentes.
 */
public class Arquivo implements ComponenteSistema {

    private final String nome;
    private final long tamanhoBytes;

    public Arquivo(String nome, long tamanhoBytes) {
        this.nome = nome;
        this.tamanhoBytes = tamanhoBytes;
    }

    @Override
    public String getNome() { return nome; }

    @Override
    public long getTamanhoBytes() { return tamanhoBytes; }

    @Override
    public void imprimir(String prefixo) {
        System.out.println(prefixo + "📄 " + nome + " (" + tamanhoBytes + " bytes)");
    }
}
