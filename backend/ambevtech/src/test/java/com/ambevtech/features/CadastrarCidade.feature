#language: pt

@cadastrarCidade @smoke
Funcionalidade: Cadastro de uma nova cidade

  Como usuário eu preciso cadastrar uma nova cidade para poder visualizar os dados do tempo.

  Esquema do Cenário: Cadastro de uma nova cidade
    Dado que eu desejo cadastrar uma nova cidade "<codigoCidade>", "<nome>", "<latitude>", "<longitude>", "<pais>"
    Quando enviar os dados para a API de cadastro
    Então deve-se criar uma nova cidade e retornar os dados "<statusCode>", "<mensagem>"

    Exemplos:
      | codigoCidade  | nome  | latitude | longitude | pais | statusCode | mensagem                    |
      | 3471374       | Assis | -22.6617 | -50.4122  | BR   | 200        |                             |
      | 3471374       | Assis | -22.6617 | -50.4122  | BR   | 409        | A Cidade já esta Cadastrada |