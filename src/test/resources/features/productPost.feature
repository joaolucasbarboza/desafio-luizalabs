# language: pt
Funcionalidade: Criar um produto
    Como um cliente da API
    Quero criar um novo produto
    Para que eu possa adicionar produtos à lista de desejos

    Cenário: Criar um produto com sucesso
      Dado que eu tenho os detalhes do produto a ser criado
      | id                       | name           |
      | 66bbb8f66990481657a3e64p | Mesa gamer      |
      Quando eu faço uma requisição do tipo POST para criar o produto
      Entao a resposta deve retornar um status code 201 Created
      E a resposta deve confirmar que o produto foi criado com sucesso