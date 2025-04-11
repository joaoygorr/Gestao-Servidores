# Gestão de Servidores

Este projeto é a solução de um teste técnico que implementa uma API com operações de CRUD para gerenciamento de servidores efetivos e temporários, pessoas, fotos, unidades e lotações. Além disso, a aplicação inclui um sistema de autenticação baseado em JWT para proteger as rotas da API. O projeto também utiliza o [Minio](https://min.io/) para o armazenamento e gerenciamento de arquivos, como fotos dos usuários.
---

## 🚀 Pré-requisitos

- [Java 21 (JDK)](https://www.oracle.com/br/java/technologies/downloads/#jdk21-windows)
- [Maven](https://maven.apache.org/download.cgi)
- [Docker](https://www.docker.com/products/docker-desktop)
- [Git](https://git-scm.com/downloads) 

---

## 📥 Clonando o repositório

```bash
git clone https://github.com/joaoygorr/Gestao-Servidores
cd gestaoServidores
```

## 🖥️ Executando Manualmente

1. Compile o projeto:

    No diretório do projeto, execute:
   ```bash
   mvn clean install
   ```
2. Execute o servidor Spring Boot:

   Após a compilação, rode:
    ```bash
    mvn spring-boot:run
    ```
3. Acesse a API:

   A API estará disponível em http://localhost:8080/api

## 🐳 Executando com Docker

1. Certifique-se de ter o Docker instalado.

    Rode o comando na pasta raíz da aplicação: 
    ```bash
    docker-compose up -d
    ```

## 📖 Swagger (Documentação da API)
A documentação da API está disponível via **Swagger**. Após iniciar o servidor acesse:
[Documentação](http://localhost:8080/api/swagger-ui.html)

# Guia de Uso da API

Este documento descreve os requisitos necessários para utilização dos endpoints da API.

## Autenticação

Para utilizar os endpoints da API, é necessário realizar o login. Apenas usuários autenticados poderão acessar os recursos.

## Cadastro de Recursos

### 1. Cadastro de Pessoas
Antes de cadastrar outros recursos, é necessário cadastrar uma pessoa na API.

### 2. Cadastro de Servidores
Para cadastrar um servidor, é obrigatório que uma pessoa já esteja cadastrada.

### 3. Cadastro de Lotação
O cadastro de uma lotação requer:
- Uma pessoa previamente cadastrada
- Uma unidade cadastrada

### 4. Cadastro de Foto da Pessoa
Para associar uma foto a um registro, a pessoa deve estar previamente cadastrada.

## Fluxo de Cadastro
1. **Realizar o login**
2. **Cadastrar uma pessoa**
3. **Cadastrar um servidor (se aplicável)**
4. **Cadastrar uma unidade**
5. **Cadastrar uma lotação vinculando a pessoa e a unidade**
6. **Cadastrar a foto da pessoa (opcional)**

Certifique-se de seguir essa ordem para evitar erros durante o processo de cadastro.

---
Este guia tem como objetivo facilitar a compreensão do funcionamento da API e garantir que os cadastros sejam realizados corretamente.

