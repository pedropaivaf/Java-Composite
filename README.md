# Gestão de Portfólio de Investimentos

Projeto da faculdade sobre o padrão **Composite**. Usei o contexto de portfólio de investimentos porque faz todo sentido: você tem ativos individuais (ações, FIIs, cripto, renda fixa) e carteiras que agrupam outros ativos ou sub-carteiras, e quer calcular o valor total de qualquer nível da hierarquia do mesmo jeito.

A interface `Ativo` é implementada tanto pelo `AtivoIndividual` quanto pela `CarteiraInvestimentos`, então o código que soma tudo não precisa saber se está lidando com um ativo simples ou com uma carteira inteira.

## O que tem aqui

- `Ativo` — interface comum com `getValorTotal()` e `exibir()`
- `AtivoIndividual` — folha: ticker, tipo, quantidade e preço unitário
- `CarteiraInvestimentos` — nó: agrupa ativos e sub-carteiras, soma recursivamente

## Como rodar

```bash
mvn test
```

31 testes, todos passando.
