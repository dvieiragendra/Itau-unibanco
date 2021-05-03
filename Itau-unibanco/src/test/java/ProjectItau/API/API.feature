#language: pt


Funcionalidade: Testes de API
  
  @Pet
  Cenario: Realizar a venda de um dog
    Dado A criação de um usuario chamado "Maria""Assunção" com id "1"
    E A criação de um pet chamado "Brutus" do tipo "dog" com id "1"
    Quando Realizar a venda do pet para o usuario
    E Mudar o status da ordem de venda para "delivered"
    Então Consultar se a ordem gerada esta correta