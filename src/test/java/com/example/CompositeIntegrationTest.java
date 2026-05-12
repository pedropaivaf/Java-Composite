package com.example;

import com.example.ativo.*;
import com.example.carteira.CarteiraInvestimentos;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Portfólio — cenários de consolidação patrimonial")
class CompositeIntegrationTest {

    @Test @DisplayName("Portfólio consolidado de dois clientes deve ser independente")
    void portFoliosIndependentes() {
        CarteiraInvestimentos pedro = new CarteiraInvestimentos("Pedro", "Pedro");
        CarteiraInvestimentos ana   = new CarteiraInvestimentos("Ana",   "Ana");
        pedro.adicionar(new AtivoIndividual("PETR4", "ACAO", 200, 38.0));
        ana.adicionar(new AtivoIndividual("VALE3", "ACAO", 100, 62.0));
        assertNotEquals(pedro.getValorTotal(), ana.getValorTotal(), 0.001);
    }

    @Test @DisplayName("Adição sequencial deve acumular valor corretamente")
    void adicaoSequencialAcumula() {
        CarteiraInvestimentos c = new CarteiraInvestimentos("C", "X");
        c.adicionar(new AtivoIndividual("A", "ACAO", 1, 1000.0));
        assertEquals(1000.0, c.getValorTotal(), 0.001);
        c.adicionar(new AtivoIndividual("B", "FII",  1,  500.0));
        assertEquals(1500.0, c.getValorTotal(), 0.001);
    }

    @Test @DisplayName("Ativo com preço atualizado reflete no total")
    void precoAtualizadoNovaCriacao() {
        CarteiraInvestimentos c = new CarteiraInvestimentos("C", "X");
        c.adicionar(new AtivoIndividual("PETR4", "ACAO", 100, 38.0));
        c.adicionar(new AtivoIndividual("PETR4", "ACAO", 100, 42.0)); // novo lote com preco diferente
        assertEquals(8000.0, c.getValorTotal(), 0.001);
    }

    @Test @DisplayName("Hierarquia de 4 níveis deve somar recursivamente")
    void quatroNiveisRecursivo() {
        CarteiraInvestimentos n1 = new CarteiraInvestimentos("N1","X");
        CarteiraInvestimentos n2 = new CarteiraInvestimentos("N2","X");
        CarteiraInvestimentos n3 = new CarteiraInvestimentos("N3","X");
        n3.adicionar(new AtivoIndividual("DEEP", "CRIPTO", 1, 1000.0));
        n2.adicionar(n3);
        n1.adicionar(n2);
        assertEquals(1000.0, n1.getValorTotal(), 0.001);
    }

    @Test @DisplayName("Portfólio misto deve retornar nome correto")
    void nomePortfolioMisto() {
        CarteiraInvestimentos c = new CarteiraInvestimentos("Aposentadoria 2050", "Pedro");
        assertEquals("Aposentadoria 2050", c.getNome());
    }
}
