# LiterAlura

**LiterAlura** é um catálogo de livros interativo desenvolvido em Java, utilizando a API Gutendex para buscar livros e armazenar informações em um banco de dados PostgreSQL. O projeto foi criado como parte do desafio LiterAlura e segue os princípios de orientação a objetos.

## Funcionalidades

- **Buscar livro por título**: O usuário pode buscar um livro pelo título e obter informações como autor, idioma e número de downloads. O livro é registrado no banco de dados.
- **Listar livros registrados**: Exibe todos os livros cadastrados no banco de dados, mostrando título e autor.
- **Listar nossos autores**: Exibe todos os autores com livros registrados no banco de dados, listando também os livros de cada autor.
- **Listar autores em determinado ano**: O usuário pode inserir um ano e o sistema retorna os autores que estavam vivos naquele período.
- **Listar livros em determinado idioma**: O usuário pode listar os livros registrados no banco de dados em um idioma específico (como português, inglês, espanhol, etc.).

## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot**: Framework para construção de aplicações Java.
- **PostgreSQL**: Banco de dados utilizado para armazenar as informações.
- **Maven**: Gerenciador de dependências.
- **Gutendex API**: API externa utilizada para buscar informações sobre livros.

## Requisitos

- JDK 17 ou superior
- PostgreSQL instalado e configurado
- Maven instalado

## Como Rodar o Projeto

1. **Clonar o repositório:**

```bash
https://github.com/RafaelaCabral/ChallengeONET7-LiterAlura.git
cd literalura
```
2. **Configurar o banco de dados:**
Crie um banco de dados PostgreSQL com as credenciais adequadas e configure as variáveis no arquivo src/main/resources/application.properties:
```bash
spring.datasource.url=jdbc:postgresql://localhost:5432/literalura
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
```
3. **Instalar dependências:**
Execute o seguinte comando para instalar as dependências do projeto:
```bash
mvn install
```
4. **Rodar o projeto:**
Após a configuração e instalação, execute a aplicação com o comando:
```bash
mvn spring-boot:run
```


