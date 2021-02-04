# BackEnd - Java


## Tecnologias
- MYSQL 8.0.22
- SpringBoot
- Flyway
- Cucumber

## Subir o projeto
- Para subir o backend é necessário mudar as credenciais do banco de dados localizado no arquivo application.yml;
- É necessário alterar o profile para 'prod';
- Ao subir a aplicação será executado o flyway criando a estrutura da tabela de cidades;
- O Projeto será iniciado na porta 8080;

## Cache
- A comunicação com a API Open Weather no método de consultar a previsão do tempo possui um Cache de 15 minutos.

## Teste
- Ao iniciar os testes será criado um novo banco de dados com o nome 'ambevtech_teste';
- Foram criados 2 testes. Teste de Cadastro de Cidade e teste de busca da cidade na API externa.

## Documentação da API - Swagger
Abra seu browser e acesse: `http://localhost:8080/swagger-ui.html`
