# LeBanking API - Gerenciamento de Transações Bancárias

Esta é uma API REST desenvolvida para o gerenciamento de transações bancárias.  A API permite a criação de depósitos e saques,  clientes e empresas, além de listar todas as transações realizadas.

## Tecnologias Utilizadas

- Java 17
- Spring Boot 6.1.3
- Spring Data JPA
- Springdoc OpenAPI para documentação
- Lombok para otimização de código
- Spring Validation para validar input de requisições
- Spring Cloud OpenFeign para integração com serviço de Webhook
- Spring Mail para envio de e-mails
- h2 database

## Endpoints

### Entidades

- **Criar Cliente**: `POST http://localhost:8080/lebanking/clients`
- **Criar Empresa**: `POST http://localhost:8080/lebanking/companys`

### Transações

- **Criar Depósito**: `POST http://localhost:8080/lebanking/transactions/deposit`
- **Criar Saque**: `POST http://localhost:8080/lebanking/transactions/withDrawal`
- **Listar Todas as Transações**: `GET http://localhost:8080/lebanking/transactions`

### Acessar console H2

- **Para acessar o console do h2 dataBase:** `http://localhost:8080/h2-console`

## Como Utilizar a API

1. Certifique-se de ter o Java 17 instalado no seu sistema.
2. Clone este repositório do GitHub.
3. Abra o projeto em sua IDE favorita.
5. Execute a aplicação Spring Boot.
6. Acesse a documentação da API no seu navegador através de `http://localhost:8080/swagger-ui.html`.
7. Coloque seu email quando cadastrar o cliente, para receber o email de transações
8. Utilize algum testador de requisição (POSTMAN / INSOMNIA) para realizar requisições aos endpoints.


### Exemplo de Requisição para criar uma Empresa (POST)

```json
{
  "name": "LeBanking",
  "cnpj": "43880633000144",
  "contact": "email@empresa.com",
  "balance": 1000.00,
  "feeInputDTO": {
    "withdrawalFeeRate": 0.02,
    "depositFeeRate": 0.01
  }
}
```

### Exemplo de Requisição para criar um Cliente (POST)

```json
{
  "name": "João da Silva",
  "cpf": "27136193869",
  "birthDate": "1990-01-01",
  "tel": "11947165215",
  "mail": "leandroluz201616@gmail.com",
  "balance": 1000.0
}
```

### Exemplo de Requisição para fazer um Depósito (POST)

```json
{
  "cnpj": "43880633000144",
  "cpf": "27136193869",
  "amount": 1000.0
}
```

### Exemplo de Requisição para fazer um Saque (POST)

```json
{
  "cnpj": "43880633000144",
  "cpf": "27136193869",
  "amount": 300.0
}
```

### Exemplo de body Request quando solicitamos todas as transaçœs (POST)

```json
{
    "content": [
        {
            "id": "d2b8f7a1-db16-4a37-bced-bfe445286618",
            "timestamp": "2024-02-04T02:56:48.565805",
            "amount": 10000.00,
            "totalAmount": 9900.00,
            "transactionType": "DEPOSIT"
        },
        {
            "id": "a4faa795-3d26-4604-90cc-0307b2db1670",
            "timestamp": "2024-02-04T02:56:54.349883",
            "amount": 1500.00,
            "totalAmount": 1470.00,
            "transactionType": "WITHDRAWAL"
        },
        {
            "id": "de7ab83e-5713-45b2-b52b-9fed3fa77ad2",
            "timestamp": "2024-02-04T02:58:02.669341",
            "amount": 1500.00,
            "totalAmount": 1470.00,
            "transactionType": "WITHDRAWAL"
        }
    ],
    "pageable": {
        "pageNumber": 0,
        "pageSize": 10,
        "sort": [],
        "offset": 0,
        "paged": true,
        "unpaged": false
    },
    "totalPages": 1,
    "totalElements": 3,
    "last": true,
    "first": true,
    "size": 10,
    "number": 0,
    "sort": [],
    "numberOfElements": 3,
    "empty": false
}
```



