#language: pt 

Funcionalidade: Testes de API
  
  @PetSimples
  Cenario: Realizar a venda de um dog
    Dado A criação de um usuario chamado "Maria""Assunção" com id "1"
    E A criação de um pet chamado "Brutus" do tipo "dog" com id "1"
    Quando Realizar a venda do pet "1" para o usuario "1"
    E Mudar o status da ordem de venda para "delivered"
    Então Consultar se a ordem gerada esta correta
     
  @PetComMassa
  Cenario: Realizar venda com massa de dados
  Esquema do Cenário: Cenario com massa
    Dado A criação de um usuario chamado "<primeiroNome>""<sobrenome>" com id "<idUser>"
    E A criação de um pet chamado "<dog>" do tipo "<tipo1>" com id "<idPetDog>"
    E A criação de um pet do tipo cat chamado "<cat>" do tipo "<tipo2>" com id "<idPetCat>"
    Quando Realizar a venda do pet "<idPetDog>" para o usuario "<idUser>"
    Quando Realizar a venda do pet "<idPetCat>" para o usuario "<idUser>"
    E Mudar o status da ordem de venda para "<status1>"
    E Mudar o status da ordem de venda do cat para "<status2>"
    Então Consultar se a ordem gerada esta correta
    
    Exemplos:
    	| primeiroNome	| sobrenome	| cat | dog	| tipo1	| tipo2	| status1	| status2 | idUser	| idPetDog	| idPetCat |
    	| Maria |Macedo |Doris |Marley	|cachorro	|gato	|delivered	|approved	|2 | 2 | 3 |
    	| Luiz	|Alves |Gatuti	|Brutus	|cachorro	|gato |delivered	|approved	| 3 | 4 | 5 |
    	| Gerson |Vieira |Mili |Dic |cachorro |gato | delivered | approved | 4 | 6 | 7 |
    	| Jessica | Figueiredo | Mingau | Xuxa | cachorro | gato | delivered | approved | 5 | 8 | 9 |
    	| Natalie | Cavalcanti | Chiquinho | Luna | cachorro | gato | delivered | approved | 5 | 10 | 11 |