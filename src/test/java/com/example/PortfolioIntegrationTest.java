package com.example;

import com.example.ativo.AtivoIndividual;
import com.example.carteira.CarteiraInvestimentos;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Portfólio — integração multi-nível")
class PortfolioIntegrationTest {

    @Test @DisplayName("Portfólio de 3 níveis deve somar corretamente")
    void tresNiveisSomamCorreto() {
        CarteiraInvestimentos raiz = new CarteiraInvestimentos("Total", "Ana");
        CarteiraInvestimentos rv = new CarteiraInvestimentos("Renda Variável", "Ana");
        CarteiraInvestimentos rf = new CarteiraInvestimentos("Renda Fixa", "Ana");

        rv.adicionar(new AtivoIndividual("PETR4", "ACAO", 100, 40.0)); // 4000
        rf.adicionar(new AtivoIndividual("CDB",   "RENDA_FIXA", 1, 10000.0)); // 10000

        raiz.adicionar(rv);
        raiz.adicionar(rf);
        raiz.adicionar(new AtivoIndividual("BTC", "CRIPTO", 1, 300000.0)); // 300000

        assertEquals(314000.0, raiz.getValorTotal(), 0.001);
    }

    @Test @DisplayName("Sub-carteira deve ser tratada como Ativo")
    void subCarteiraEhAtivo() {
        CarteiraInvestimentos sub = new CarteiraInvestimentos("Sub", "X");
        sub.adicionar(new AtivoIndividual("A", "ACAO", 10, 50.0));
        CarteiraInvestimentos pai = new CarteiraInvestimentos("Pai", "X");
        pai.adicionar(sub);
        assertEquals(500.0, pai.getValorTotal(), 0.001);
    }

    @Test @DisplayName("Carteira vazia aninhada nao altera total")
    void carteiraVaziaNaoAltera() {
        CarteiraInvestimentos pai = new CarteiraInvestimentos("Pai", "X");
        pai.adicionar(new AtivoIndividual("A", "FII", 5, 100.0));
        pai.adicionar(new CarteiraInvestimentos("Vazia", "X"));
        assertEquals(500.0, pai.getValorTotal(), 0.001);
    }

    @Test @DisplayName("AtivoIndividual e CarteiraInvestimentos devem implementar Ativo")
    void ambosImplementamAtivo() {
        com.example.ativo.Ativo a = new AtivoIndividual("X", "ACAO", 1, 1.0);
        com.example.ativo.Ativo c = new CarteiraInvestimentos("C", "Y");
        assertNotNull(a.getNome());
        assertNotNull(c.getNome());
    }

    @Test @DisplayName("Dois portfólios independentes nao interferem")
    void portfoliosIndependentes() {
        CarteiraInvestimentos p1 = new CarteiraInvestimentos("P1", "A");
        CarteiraInvestimentos p2 = new CarteiraInvestimentos("P2", "B");
        p1.adicionar(new AtivoIndividual("X", "ACAO", 100, 10.0));
        assertEquals(1000.0, p1.getValorTotal(), 0.001);
        assertEquals(0.0,    p2.getValorTotal(), 0.001);
    }
}
