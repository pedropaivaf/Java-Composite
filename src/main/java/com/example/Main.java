package com.example;

import com.example.ativo.AtivoIndividual;
import com.example.carteira.CarteiraInvestimentos;

public class Main {
    public static void main(String[] args) {
        // Sub-carteira de renda variável
        CarteiraInvestimentos rendaVariavel = new CarteiraInvestimentos("Renda Variável", "Pedro");
        rendaVariavel.adicionar(new AtivoIndividual("PETR4", "ACAO", 200, 38.50));
        rendaVariavel.adicionar(new AtivoIndividual("MXRF11", "FII", 100, 9.80));
        rendaVariavel.adicionar(new AtivoIndividual("VALE3", "ACAO", 50, 62.00));

        // Sub-carteira de renda fixa
        CarteiraInvestimentos rendaFixa = new CarteiraInvestimentos("Renda Fixa", "Pedro");
        rendaFixa.adicionar(new AtivoIndividual("Tesouro IPCA+ 2035", "RENDA_FIXA", 5, 1200.00));
        rendaFixa.adicionar(new AtivoIndividual("CDB Bradesco 14%", "RENDA_FIXA", 1, 50000.00));

        // Carteira consolidada (agrupa tudo)
        CarteiraInvestimentos total = new CarteiraInvestimentos("Portfólio Total", "Pedro");
        total.adicionar(rendaVariavel);
        total.adicionar(rendaFixa);

        total.exibir("");
        System.out.printf("%nPatrimônio total: R$%.2f%n", total.getValorTotal());
    }
}
