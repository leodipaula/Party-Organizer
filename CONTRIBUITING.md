# Contribuindo para o Projeto PartyOrganizer

Obrigado por querer contribuir! Este guia tem como objetivo facilitar o processo de colaboração.

## 🧰 Requisitos

Antes de contribuir, certifique-se de ter instalado:

- Java 21+
- Maven
- PostgreSQL
- Redis (para cache)
- Docker (opcional)

## 🗂️ Estrutura do Projeto

O projeto segue as práticas padrão do Spring Boot com R2DBC e WebFlux. Os arquivos de migração estão em `resources/db/migration` com Flyway. 

## 📥 Como contribuir

1. **Fork o repositório**
2. **Crie uma branch** com um nome descritivo
3. **Implemente sua alteração**
4. **Adicione testes** se possível
5. **Faça commit das mudanças**
6. **Suba sua branch**
7. **Abra um Pull Request** no GitHub

## 🔍 Boas práticas

- Siga o padrão de código já estabelecido no projeto.
- Escreva commits claros e concisos.
- Prefira funções puras e código testável.
- Utilize migrations Flyway para qualquer alteração no banco de dados.
- Use `application.properties` e evite hardcoded de configurações sensíveis.

## 📚 Convenções de commits (opcional, mas recomendado)

Use os prefixos do Conventional Commits:

- `feat:` Nova funcionalidade
- `fix:` Correção de bug
- `docs:` Documentação
- `refactor:` Refatoração sem alterar funcionalidades
- `test:` Adição ou melhoria em testes
- `chore:` Tarefas de manutenção


## 🧪 Rodando localmente

1. Crie o banco no PostgreSQL:
   ```sql
   CREATE DATABASE party_organizer_db;
2. Suba o Redis caso esteja usando.
3. Execute o projeto pela IDE ou via terminal.

## ✍️ Material para estudar

- Exemplo de CRUD básico caso você não tenha familiaridade com Web Flux: [Clique aqui](https://github.com/leodipaula/Loja-de-games).
- Playlist explicando código reativo: [Clique aqui](https://www.youtube.com/watch?v=j3Ik6iscbu8&list=PL62G310vn6nG3sBMCIEoZBK3r3E_4aKW5).
- Playlist Web Flux: [Clique aqui](https://www.youtube.com/watch?v=uLFU5Uou5t4&list=PL62G310vn6nH5Tgcp5q2a1xCb6CsZJAi7).

## 🤔💬 Dúvidas?

Abra uma issue ou entre em contato por [email](leofernandes9@gmail.com).

##

**Obrigado por contribuir!!!** ❤️
