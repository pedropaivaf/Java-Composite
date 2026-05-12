package com.example.componente;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Composto (Composite) — representa um diretório que pode conter arquivos e subdiretórios.
 */
public class Diretorio implements ComponenteSistema {

    private final String nome;
    private final List<ComponenteSistema> filhos = new ArrayList<>();

    public Diretorio(String nome) {
        this.nome = nome;
    }

    public void adicionar(ComponenteSistema componente) {
        filhos.add(componente);
    }

    public void remover(ComponenteSistema componente) {
        filhos.remove(componente);
    }

    public List<ComponenteSistema> getFilhos() {
        return Collections.unmodifiableList(filhos);
    }

    @Override
    public String getNome() { return nome; }

    @Override
    public long getTamanhoBytes() {
        return filhos.stream().mapToLong(ComponenteSistema::getTamanhoBytes).sum();
    }

    @Override
    public void imprimir(String prefixo) {
        System.out.println(prefixo + "📁 " + nome + "/ (" + getTamanhoBytes() + " bytes)");
        for (ComponenteSistema filho : filhos) {
            filho.imprimir(prefixo + "  ");
        }
    }
}
