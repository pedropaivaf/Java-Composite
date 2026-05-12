package com.example;

import com.example.ativo.AtivoIndividual;
import com.example.carteira.CarteiraInvestimentos;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Carteira de Investimentos")
class CarteiraInvestimentosTest {

    private CarteiraInvestimentos carteira;

    @BeforeEach
    void setUp() { carteira = new CarteiraInvestimentos("Minha Carteira", "Pedro"); }

    @Test @DisplayName("Carteira vazia deve ter valor zero")
    void carteiraVaziaValorZero() {
        assertEquals(0.0, carteira.getValorTotal(), 0.001);
    }

    @Test @DisplayName("Deve somar o valor de todos os ativos")
    void deveSomarAtivos() {
        carteira.adicionar(new AtivoIndividual("A", "ACAO", 100, 10.0));
        carteira.adicionar(new AtivoIndividual("B", "FII",  200, 5.0));
        assertEquals(2000.0, carteira.getValorTotal(), 0.001);
    }

    @Test @DisplayName("Deve calcular valor com sub-carteira aninhada")
    void somaComSubCarteira() {
        CarteiraInvestimentos sub = new CarteiraInvestimentos("Sub", "Pedro");
        sub.adicionar(new AtivoIndividual("C", "RENDA_FIXA", 1, 5000.0));
        carteira.adicionar(new AtivoIndividual("A", "ACAO", 10, 100.0));
        carteira.adicionar(sub);
        assertEquals(6000.0, carteira.getValorTotal(), 0.001);
    }

    @Test @DisplayName("Deve remover ativo e recalcular valor")
    void removerAtivoRecalculaValor() {
        var a = new AtivoIndividual("A", "ACAO", 100, 20.0);
        carteira.adicionar(a);
        assertEquals(2000.0, carteira.getValorTotal(), 0.001);
        carteira.remover(a);
        assertEquals(0.0, carteira.getValorTotal(), 0.001);
    }

    @Test @DisplayName("Deve retornar responsável corretamente")
    void responsavelCorreto() {
        assertEquals("Pedro", carteira.getResponsavel());
    }

    @Test @DisplayName("getTotalAtivos deve contar componentes diretos")
    void totalAtivosCorreto() {
        carteira.adicionar(new AtivoIndividual("A", "ACAO", 1, 1.0));
        carteira.adicionar(new AtivoIndividual("B", "FII",  1, 1.0));
        assertEquals(2, carteira.getTotalAtivos());
    }
}
