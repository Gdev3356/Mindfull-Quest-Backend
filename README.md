# MindFull Quest - Backend

> "Sua jornada mental com pontos e progresso."

Projeto desenvolvido para a **Global Solution 2025-2** (FIAP). Uma API RESTful gamificada para acompanhamento de saúde mental e bem-estar.

## Integrantes (Turma 1TDSPH)

* **Thiago Andrade Silvano** - RM:562493
* **Gustavo Keiji Okada** - RM:563428
* **Arthur Silveira** - RM:562310

---

## Tecnologias e Arquitetura

O projeto utiliza **Java** com **Quarkus**, seguindo os padrões de **Domain Driven Design (DDD)** e arquitetura em camadas:
* **Model (TO):** Objetos de transferência.
* **DAO:** Persistência manual com JDBC (ConnectionFactory).
* **BO:** Regras de negócio e validações.
* **Resource:** Endpoints REST.

---

## Configuração e Execução

### 1. Pré-requisitos
* JDK 17+
* Maven
* Banco de Dados Oracle acessível

### 2. Variáveis de Ambiente (Obrigatório)
Para que a aplicação se conecte ao banco de dados, você deve configurar as seguintes variáveis no seu sistema ou IDE antes de rodar:

| Variável | Descrição |
| :--- | :--- |
| `DB_URL` | String de conexão JDBC (ex: `jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL`) |
| `DB_USER` | Seu usuário do Oracle |
| `DB_PASSWORD` | Sua senha do Oracle |

### 3. Rodando em Modo de Desenvolvimento
Para rodar a aplicação com *live coding*:

```shell script
./mvnw quarkus:dev
4. Gerando o Build (Empacotamento)Para gerar o arquivo .jar final na pasta target/:Shell./mvnw package
Em seguida, execute:Shelljava -jar target/mindfull-quest-runner.jar
🔌 Endpoints PrincipaisA API possui integração com Swagger/OpenAPI (se ativado) e conta com os seguintes recursos:RecursoMétodoURIDescriçãoUsuáriosGET/usuariosLista usuáriosPOST/usuariosCria novo usuárioRelatóriosPOST/relatorios-diarioRegistra humor/estresseMedidasGET/medidas-cuidadoLista atividades disponíveisHistóricoPOST/hist-atividadesConclui uma atividade (ganha pontos)CosméticosGET/cosmeticosLista itens da lojaConsulte a documentação completa em PDF para detalhes dos JSONs de entrada e saída.☁️ DeployA aplicação está rodando em produção na plataforma Render:Base URL: https://mindfull-quest-backend.onrender.com/
### Por que usar esse modelo?

1.  **Instrução de Variaveis de Ambiente[cite: 149, 150]:** No seu PDF, você explicou que usa `DB_URL`, `DB_USER`, etc. O README padrão do Quarkus não sabe disso. Se o professor tentar rodar sem configurar isso, vai dar erro de conexão e você pode perder pontos em "Deploy/Execução".
2.  **Identificação[cite: 7, 8, 9, 10]:** Já deixa claro os RMs e Nomes logo de cara.
3.  **Arquitetura[cite: 41, 44, 57, 67, 73]:** Mostra que você sabe o que construiu (Camadas BO, DAO, TO), valorizando a nota de "Boas Práticas".

**Resumo:** O README é a "capa" do seu código no GitHub. Ele deve refletir o conte