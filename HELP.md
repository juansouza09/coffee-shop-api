# Coffee Shop API

## Descrição do Projeto

A **Coffee Shop API** é uma aplicação desenvolvida em **Spring Boot**, fornecendo serviços para busca endereços com base no CEP, vendas e produtos em uma cafeteria.

## Tecnologias Utilizadas

- **Linguagem:** Java 21
- **Framework:** Spring Boot 3.4.0
- **Banco de Dados:** PostgreSQL (Amazon RDS)
- **Containerização:** Docker
- **Orquestração de Contêineres:** Amazon ECS (com Fargate)
- **Registro de Imagens Docker:** Amazon ECR
- **Arquitetura:** Clean Architecture
- **Testes:** Spring Test e Mockoon

## Endpoints

### Endereços

- **GET** `/api/address/{cep}`
    - **Descrição:** Retorna o endereço com base no CEP fornecido.
    - **Exemplo de Requisição:**
      ```bash
      GET /api/address/12345678
      ```
      - **Response:**
        ```json
        {
          "cep": "12345-678",
          "logradouro": "Rua Inicial",
          "complemento": "",
          "bairro": "Bairro Inicial",
          "localidade": "São Paulo",
          "uf": "SP"
        }
        ```

### Produtos

- **GET** `/api/products`
    - **Descrição:** Retorna a lista de produtos disponíveis.
    - **Response:**
      ```json
      [
        {
          "id": 1,
          "name": "Café Americano",
          "price": 5.50
        },
        {
          "id": 2,
          "name": "Cappuccino",
          "price": 7.00
        }
      ]
      ```

- **POST** `/api/products`
    - **Descrição:** Adiciona um novo produto.
    - **Request Body:**
      ```json
      {
        "name": "Latte",
        "price": 6.50
      }
      ```
    - **Response:**
      ```json
      {
        "id": 3,
        "name": "Latte",
        "price": 6.50
      }
      ```

- **PUT** `/api/products/{id}`
    - **Descrição:** Atualiza os detalhes de um produto existente.
    - **Request Body:**
      ```json
      {
        "name": "Espresso",
        "price": 4.50
      }
      ```
    - **Response:**
      ```json
      {
        "id": 1,
        "name": "Espresso",
        "price": 4.50
      }
      ```

- **DELETE** `/api/products/{id}`
    - **Descrição:** Remove um produto pelo ID fornecido.
    - **Response:**
      ```json
      {
        "message": "Produto removido com sucesso."
      }
      ```

### Vendas

- **GET** `/api/sales`
    - **Descrição:** Retorna a lista de vendas realizadas.
    - **Response:**
      ```json
      [
        {
          "id": 1,
          "userName": "João Silva",
          "contactNumber": "(11) 91234-5678",
          "productId": 2,
          "total": 14.00,
          "address": {
            "street": "Rua das Palmeiras",
            "city": "São Paulo",
            "state": "SP",
            "zipCode": "12345-678"
          }
        }
      ]
      ```

- **POST** `/api/sales`
    - **Descrição:** Registra uma nova venda.
    - **Request Body:**
      ```json
      {
        "userName": "João Silva",
        "contactNumber": "(11) 91234-5678",
        "productId": 2,
        "total": 14.00,
        "cep": "12345678"
      }
      ```
    - **Response:**
      ```json
      {
        "id": 1,
        "userName": "João Silva",
        "contactNumber": "(11) 91234-5678",
        "productId": 2,
        "total": 14.00,
        "address": {
          "street": "Rua das Palmeiras",
          "city": "São Paulo",
          "state": "SP",
          "zipCode": "12345-678"
        }
      }
      ```

## Como Executar Localmente

### Pré-requisitos

1. **JDK 21** instalado.
2. **Maven** instalado.
3. **Docker** instalado e em execução.
4. Banco de dados PostgreSQL configurado localmente ou em uma plataforma de sua escolha.

### Passo a Passo

1. Clone o repositório:

   ```bash
   git clone https://github.com/juansouza09/coffee-shop-api.git
   cd coffee-shop-api
   ```

2. Configure o arquivo `application.properties` na pasta `src/main/resources` com os detalhes do seu banco de dados:

   ```properties
   spring.datasource.url=jdbc:postgresql://<seu-host>:5432/<seu-banco>
   spring.datasource.username=<seu-usuario>
   spring.datasource.password=<sua-senha>
   spring.jpa.hibernate.ddl-auto=update
   ```

3. Compile e gere o arquivo JAR:

   ```bash
   mvn clean package
   ```

3. Certifique-se de que o arquivo `Dockerfile` está configurado corretamente na raiz do projeto. Um exemplo básico seria:

   ```properties
    FROM openjdk:21-jdk-slim
    VOLUME /tmp
    ARG JAR_FILE=target/coffee-shop-api-0.0.1-SNAPSHOT.jar
    COPY ${JAR_FILE} app.jar
    ENTRYPOINT ["java", "-jar", "/app.jar"]
   ```

4. Execute o contêiner Docker:

   ```bash
   docker build -t coffee-shop-api .
   docker run -p 8080:8080 coffee-shop-api
   ```

5. Acesse a API localmente em: [http://localhost:8080](http://localhost:8080)

