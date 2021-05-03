#language: pt


Funcionalidade: Projeto Website

  @LoginSucesso
  Cenario: Login com sucesso
    Dado Abrir pagina "Login com sucesso"
    Quando Clicar em Sign In
    E Fazer o cadastro na pagina
    E Logar no site "OK" "teste"
    Então Validar se login foi realizado com sucesso
    
  @LoginInvalido
  Cenario: Login Invalido
    Dado Abrir pagina "Login Invalido"
    Quando Clicar em Sign In
    E Logar no site "invalido" "teste"
    Então Validar se login foi realizado sem sucesso
    
  @LoginEmBranco
  Cenario: Login em Branco
    Dado Abrir pagina "Login em Branco"
    Quando Clicar em Sign In
    E Logar no site "" ""
    Então Validar se login foi realizado sem sucesso