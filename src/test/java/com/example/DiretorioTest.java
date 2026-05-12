package com.example;

import com.example.ativo.AtivoIndividual;
import com.example.carteira.CarteiraInvestimentos;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Carteira — cenários avançados de gestão")
class DiretorioTest {

    @Test @DisplayName("Carteira com múltiplos FIIs deve somar corretamente")
    void multiplosFlls() {
        CarteiraInvestimentos c = new CarteiraInvestimentos("FIIs", "Ana");
        c.adicionar(new AtivoIndividual("MXRF11", "FII", 100, 9.80));
        c.adicionar(new AtivoIndividual("HGLG11", "FII", 20, 175.0));
        assertEquals(980.0 + 3500.0, c.getValorTotal(), 0.001);
    }

    @Test @DisplayName("Adicionar sub-carteira aumenta total corretamente")
    void adicionarSubCarteira() {
        CarteiraInvestimentos pai = new CarteiraInvestimentos("Pai", "X");
        CarteiraInvestimentos filho = new CarteiraInvestimentos("Filho", "X");
        filho.adicionar(new AtivoIndividual("A", "ACAO", 10, 50.0));
        pai.adicionar(filho);
        assertEquals(500.0, pai.getValorTotal(), 0.001);
    }

    @Test @DisplayName("Nome da carteira deve ser preservado")
    void nomeCarteiraPreservado() {
        CarteiraInvestimentos c = new CarteiraInvestimentos("Reserva de Emergência", "Carlos");
        assertEquals("Reserva de Emergência", c.getNome());
    }

    @Test @DisplayName("Carteira com 0 componentes tem totalAtivos = 0")
    void totalAtivoZero() {
        assertEquals(0, new CarteiraInvestimentos("Vazia", "X").getTotalAtivos());
    }

    @Test @DisplayName("Remover ativo inexistente não altera a lista")
    void removerInexistenteNaoAltera() {
        CarteiraInvestimentos c = new CarteiraInvestimentos("C", "X");
        var a = new AtivoIndividual("A", "ACAO", 1, 10.0);
        c.remover(a); // nao está na lista
        assertEquals(0, c.getTotalAtivos());
    }

    @Test @DisplayName("getComponentes deve ser imutável")
    void listaImutavel() {
        CarteiraInvestimentos c = new CarteiraInvestimentos("C", "X");
        assertThrows(UnsupportedOperationException.class,
            () -> c.getComponentes().add(new AtivoIndividual("X", "ACAO", 1, 1.0)));
    }
}
