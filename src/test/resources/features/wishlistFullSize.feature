# language: pt
Funcionalidade: Exceção de lista cheia

  Como um cliente da API
  Quero receber uma exceção ao tentar adicionar mais produtos do que a capacidade máxima da Wishlist
  Para ser informado que minha lista de desejos está cheia e evitar adições inválidas

  Cenario: Tentar cadastrar um produto na Wishlist quando a lista está cheia
    Dado que a lista de desejos esteja cheia
      | id                       | name                  |
      | 66bbb8f66990481657a3e537 | Cadeira gamer         |
      | 66bbb8f66990481657a3e76p | Notebook gamer        |
      | 66bbb8f66990481657a3e532 | Fone gamer            |
      | 66bbb8f66990481657a3e538 | Mouse gamer           |
      | 66bbb8f66990481657a3e539 | Teclado mecânico      |
      | 66bbb8f66990481657a3e540 | Monitor 4K            |
      | 66bbb8f66990481657a3e541 | Headset gamer         |
      | 66bbb8f66990481657a3e542 | Mesa gamer            |
      | 66bbb8f66990481657a3e543 | Cadeira de escritório |
      | 66bbb8f66990481657a3e544 | Placa de vídeo        |
      | 66bbb8f66990481657a3e545 | Memória RAM           |
      | 66bbb8f66990481657a3e546 | SSD 1TB               |
      | 66bbb8f66990481657a3e547 | Processador i7        |
      | 66bbb8f66990481657a3e548 | Fonte 750W            |
      | 66bbb8f66990481657a3e549 | Gabinete ATX          |
      | 66bbb8f66990481657a3e550 | Water Cooler          |
      | 66bbb8f66990481657a3e551 | Kit Ventoinha RGB     |
      | 66bbb8f66990481657a3e552 | Teclado sem fio       |
      | 66bbb8f66990481657a3e553 | Controle Xbox         |
      | 66bbb8f66990481657a3e554 | Cadeira ergonômica    |
    Quando eu faço uma requisição do tipo POST para adicionar um novo produto na Wishlist
    Entao a resposta deverá ser uma exceção de lista cheia
    E a resposta deve conter uma mensagem de erro informando que a lista de desejos está cheia