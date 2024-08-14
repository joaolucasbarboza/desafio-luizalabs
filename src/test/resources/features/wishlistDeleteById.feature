# language: pt
Funcionalidade: Remover produto da Wishlist

    Como um cliente da API
    Quero remover um produto específico da lista de desejos
    Para manter minha lista de desejos atualizada conforme meu interesse

    Cenário: Remover um produto específico da Wishlist com sucesso
      Dado que existem produtos cadastrados na Wishlist
      | id                       | name           |
      | 66bbb8f66990481657a3e537 | Cadeira gamer  |
      | 66bbb8f66990481657a3e76p | Notebook gamer |
      | 66bbb8f66990481657a3e532 | Fone gamer     |
    Quando eu faço uma requisição do tipo DELETE para remover o produto com id da Wishlist
    Então a resposta deve confirmar que o produto foi removido da Wishlist
    E o retorno deve confirmar que a quantidade de produtos na Wishlist foi atualizada corretamente