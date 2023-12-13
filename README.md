# Projeto Backend E-Diaristas em Spring Boot

## Dependências de Produção

- Spring Boot
- Spring Web MVC
- Spring Data JPA
- Spring Security
- Bean Validation
- Thymeleaf
- Thymeleaf Spring Security 5
- MySQL Connector
- WebJars AdminLTE

## Dependências de Desenvolvimento

- Lombok
- MapStruct
- Spring Boot Devtools

## Requisitos

- Java 17
- Maven 3.8

## Como testar esse projeto na minha máquina?

Clone este repositório e ente na pasta do projeto

```sh
git clone https://github.com/CleysonPH/ediaristas-spring.git
cd ediaristas-spring
```

Atualize as configurações de acesso ao banco de dados no arquivo
[application.properties](src/main/resources/application.properties).

```properties
# Datasource de conexão com o banco de dados
spring.datasource.url=jdbc:mysql://localhost:3306/ediaristas?useTimezone=true&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=root

# Configuração do JPA e Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# Configuração do Thymeleaf
spring.thymeleaf.cache=false
```

Execute o servidor imbutido do Tomcat

```sh
mvn spring-boot:run
```

E então acessar a aplicação em http://localhost:8000/