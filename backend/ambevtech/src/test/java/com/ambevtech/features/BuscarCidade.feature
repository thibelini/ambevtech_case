#language: pt

@buscarCidade @smoke
Funcionalidade: Busca da Cidade na API da Open Weather

  Como usuário eu preciso buscar uma cidade pelo nome na API da Open Weather para posteriormente cadstrar.

  Esquema do Cenário: Busca de uma cidade pelo nome
    Dado que eu desejo pesquisar uma nova pelo nome "<nome>"
    Quando enviar os dados para a API da Open Weather
    Então deve-se retornar os dados da cidade "<nome>", "<codigoCidade>"

    Exemplos:
      | nome    | codigoCidade |
      | Assis   | 3471374      |
      | Tokyo   | 1850147      |