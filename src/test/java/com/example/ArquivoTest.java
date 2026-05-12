package com.example;

import com.example.ativo.AtivoIndividual;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Ativo — cenários adicionais de precificação")
class ArquivoTest {

    @Test @DisplayName("FII com 50 cotas a R$9,80 = R$490")
    void fiiValorCorreto() {
        var fii = new AtivoIndividual("MXRF11", "FII", 50, 9.80);
        assertEquals(490.0, fii.getValorTotal(), 0.001);
    }

    @Test @DisplayName("Tesouro com preço fracionado")
    void tesouroPrecoFracionado() {
        var t = new AtivoIndividual("IPCA+2035", "RENDA_FIXA", 3, 1233.45);
        assertEquals(3700.35, t.getValorTotal(), 0.01);
    }

    @Test @DisplayName("Cripto com 1 unidade")
    void criptoUmaUnidade() {
        var btc = new AtivoIndividual("BTC", "CRIPTO", 1, 350000.0);
        assertEquals(350000.0, btc.getValorTotal(), 0.001);
    }

    @Test @DisplayName("Getters devem retornar valores corretos")
    void gettersCorretos() {
        var a = new AtivoIndividual("VALE3", "ACAO", 30, 65.0);
        assertEquals(30, a.getQuantidade());
        assertEquals(65.0, a.getPrecoUnitario(), 0.001);
        assertEquals("VALE3", a.getNome());
    }
}
