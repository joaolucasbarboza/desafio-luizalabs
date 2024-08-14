# language: pt
Funcionalidade: Exibir wishlist com todos os produtos

    Como um cliente da API
    Quero obter a lista de desejos
    Para visualizar todos os produtos cadastrados na lista de desejos

    Cenário: Exibir a lista de desejos com todos os produtos cadastrados na lista com sucesso
        Dado que existem produtos cadastrados na lista de desejos
            | id                       | name           |
            | 66bbb8f66990481657a3e537 | Cadeira gamer  |
            | 66bbb8f66990481657a3e76p | Notebook gamer |
            | 66bbb8f66990481657a3e532 | Fone gamer     |
        Quando eu faço uma requisição do tipo GET para obter a lista de desejos
        Entao a resposta deve ser um status code 200
        E a resposta deve retornar a lista de desejos com os produtos cadastrados anteriormente