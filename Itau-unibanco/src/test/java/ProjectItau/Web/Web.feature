#language: pt


Funcionalidade: Projeto Website

  @PetzSimples
  Cenario: Petz teste simples
    Dado Produto "ração"
    Quando clicar no terceiro item
    E Pegar informações do produto
    E clicar adicionar no carrinho
    Então validar se os dados continuam identicos
    
    
    @PetzMassaDeDados
  Cenario: Petz teste com massa
  Esquema do Cenário: Cenario com massa
    Dado Produto "<produto>"
    Quando clicar no terceiro item
    E Pegar informações do produto
    E clicar adicionar no carrinho
    Então validar se os dados continuam identicos
    
    Exemplos:
    | produto |
    | ração |
    | coleira |
    | guia |
    | bifinho |
    | brinquedos |
    | ossos |
    | tapetes |
    | camas |
    | roupas|