package com.example;

import com.example.ativo.AtivoIndividual;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Ativo Individual")
class AtivoIndividualTest {

    @Test @DisplayName("Valor total = quantidade × preço unitário")
    void valorTotalCalculadoCorretamente() {
        var a = new AtivoIndividual("PETR4", "ACAO", 100, 38.50);
        assertEquals(3850.0, a.getValorTotal(), 0.001);
    }

    @Test @DisplayName("Deve retornar nome e tipo corretamente")
    void getNomeETipo() {
        var a = new AtivoIndividual("MXRF11", "FII", 50, 10.0);
        assertEquals("MXRF11", a.getNome());
        assertEquals("FII", a.getTipo());
    }

    @Test @DisplayName("Ativo com 0 unidades deve ter valor zero")
    void ativoZeroUnidades() {
        var a = new AtivoIndividual("VALE3", "ACAO", 0, 60.0);
        assertEquals(0.0, a.getValorTotal(), 0.001);
    }

    @Test @DisplayName("Quantidade negativa deve lançar exceção")
    void quantidadeNegativaDeveLancarExcecao() {
        assertThrows(IllegalArgumentException.class,
            () -> new AtivoIndividual("X", "ACAO", -1, 10.0));
    }

    @Test @DisplayName("Preço negativo deve lançar exceção")
    void precoNegativoDeveLancarExcecao() {
        assertThrows(IllegalArgumentException.class,
            () -> new AtivoIndividual("X", "ACAO", 1, -5.0));
    }
}
