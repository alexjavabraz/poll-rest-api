########################################################################
# poll-rest-api
## Poll rest api java 1.8 repository
Esta API serve para criação de votações.
Este projeto é um dos teste para o cargo de desenvolvedor Sr.
> Você pode obter mais informações sobre o projeto  **poll-rest-api** , seus componentes e motivação [aqui](https://github.com/alexjavabraz/poll-rest-api).

## Tecnologias
- Java 1.8
- Spring Boot 2.3.2.RELEASE
- MySQL (Neste projeto utilizamos o serviço AWS de banco de dados chamado RDS)
> Você pode obter mais informações sobre o RDS da **AWS**, [aqui](https://aws.amazon.com/pt/rds/)
- JPA
- Lombok

## Requisitos dev
- Eclipse ou Intellij IDEA
- JDK 1.8


### Key dependencies
Abaixo uma lista das principais dependências deste projeto.

| Plugin | README |
| ------ | ------ |
| SpringBoot | [aqui][PlDb] |
| Lombok | [aqui][PlGh] |
| Mysql | [aqui][PlGd] |

## Criando uma nova Branch
- Na pasta raiz, sempre que for criar uma nova feature
```bash
git checkout -b feature/featureName develop
```
-- Close Branchs after merge with develop

## Variáveis de ambiente necessárias

- AWS_REGION (Região da AWS utilizada)
- AWS_ACCESS_KEY_ID (Acesso aos serviços da AWS)
- AWS_SECRET_ACCESS_KEY (Acesso aos serviços da AWS)
- AWS_MYSQL-URL (Acesso ao RDS na Amazon)
- AWS_mysql-USER (Usuário para acesso ao RDS)
- AWS_MYSQL-PASS (Senha de acesso ao RDS)

## Configuração MYSQL 8+ para amiente de desenvolvimento
- Exemplo de criação de um usuário: newuser
- Exemplo de criação de uma senha: user_password
- Usuário e senha devem ser adicionados no arquivo application.properties (se você utilizar o profile=default) application-local.properties (se você utilizar o profile=local)

#### Criando schema e um usuário
```bash
mysql -u root -p
CREATE USER 'newuser'@'localhost' IDENTIFIED BY 'user_password';
CREATE USER 'newuser'@'%' IDENTIFIED BY 'user_password';
CREATE DATABASE financial;
GRANT ALL PRIVILEGES ON poll.* to 'newuser'@'%';
ALTER USER 'newuser'@'%' IDENTIFIED WITH mysql_native_password BY 'user_password';
FLUSH PRIVILEGES;
```

#### Modelo de Dados
Este projeto utilizará inicialmente a base de dados Dynamo DB, considerando o seguinte modelo de dados:

|INDICE_PK       |INDICE_SK|ACCOUNT_ID|CARD_ID|CONTRACT_ID|EXTERNAL_CODE|MAIN_ACCOUNT_ID|
|----------------|---------|----------|----------|----------|----------|----------|
|card_id|`"529960"`|1272|529960|486|`"xyz"`|846|
|card_id|`"529963"`|1281|529963|474|`"abc"`|780|
|external_code|`"abc"`|1281|529963|474|`"abc"`|780|
|external_code|`"xyz"`|1272|529960|486|`"xyz"`|846|

## Como usar (ambiente de desenvolvimento)
### Como gerar os testes e o html de cobertura
- Na pasta raiz, executar:
```bash
./mvnw verify
```
- Abrir o arquivo target/site/jacoco/index.html
#### Executando o maven
- Na pasta raiz, executar com profile LOCAL:
- Certifique-se de incluir as configurações necessárias no arquivo application-local.properties.
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=local -Dspring-boot.run.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005"
```

#### Utilizando o Docker
- Na pasta raiz, executar:

```bash
docker-compose up --build
```
- Ou ainda:
```sh
docker build -t bjbraz/poll-api:${package.json.version} .
```
- Então para rodar a aplicação
```sh
docker run -d -p 8000:8080 --restart="always" bjbraz/poll-api:${package.json.version}
```
Utilize o seu browser preferido para acessar o endereço:
```sh
127.0.0.1:8000
```
## Como usar (server side)



## Endpoints ativos
- [GET] /poll/:id
- [PATCH] /accounts
- /accounts
- /actuator/health
- /card/{cardId}

## Acessar o swagger
```bash
http://localhost:8080/swagger-ui.html
```
## Como acessar o HealthCheck
- Esta é a URL configurada para o Actuator Healthcheck:
```bash
http://localhost:8080/actuator/health
```
