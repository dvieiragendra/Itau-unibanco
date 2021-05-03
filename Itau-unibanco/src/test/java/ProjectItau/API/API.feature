#language: pt


Funcionalidade: Testes de API
  
  @CriarEmpregado
  Cenario: Criar um empregado
    Dado Realizar response create
    Então Gerar relatorio "Criar registro" "create"
    
  @ConsultaEmpregado
  Cenario: Consultar empregado criado
    Dado Realizar response consulta
    Então Gerar relatorio "Consultar registro" "consulta"
    
  @DeletaEmpregado
  Cenario: Deletar empregado criado
    Dado Realizar response delete
    Então Gerar relatorio "Deletar registro" "delete"