# language: pt
Funcionalidade: Consultar se um determinado produto está na Wishlist do cliente
    Como um cliente da API
    Quero consultar se um produto específico está na minha lista de desejos
    Para verificar se o produto desejado já foi adicionado à lista

    Cenario: Produto está na Wishlist
      Dado que a lista de desejos contém produtos previamente cadastrados
      | id                       | name           |
      | 66bbb8f66990481657a3e537 | Cadeira gamer  |
      | 66bbb8f66990481657a3e76p | Notebook gamer |
      | 66bbb8f66990481657a3e532 | Fone gamer     |
      Quando eu faço uma requisição do tipo GET para verificar se o produto está na Wishlist
      Entao a resposta deve confirmar que o produto foi encontrado na Wishlist
      E a resposta deve confirmar que o produto está na Wishlist

    Cenario: Produto não está na Wishlist
      Dado que existem produtos cadastrados na lista de desejos
      | id                       | name           |
      | 66bbb8f66990481657a3e537 | Cadeira gamer  |
      | 66bbb8f66990481657a3e76p | Notebook gamer |
      | 66bbb8f66990481657a3e532 | Fone gamer     |
      Quando eu faço uma requisição do tipo GET para verificar se o produto não está na Wishlist
      Entao a resposta deve retornar uma exceção de produto não encontrado na Wishlist
      E a resposta deve retornar uma exceção de produto não encontrado na Wishlist