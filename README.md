# 🚀 Teste Prático - Iniflex (Projedata)

## 📌 Descrição

Este projeto foi desenvolvido como parte de um teste prático de programação, com o objetivo de aplicar conceitos de **Java**, **Programação Orientada a Objetos** e **boas práticas de desenvolvimento**.

A aplicação realiza o gerenciamento de funcionários, executando diversas operações como:

* leitura de dados via JSON
* manipulação de listas
* agrupamentos com `Map`
* cálculos com `BigDecimal`
* formatação de dados
* exibição em tabelas no console

---

## 🛠️ Tecnologias Utilizadas

* Java 17
* Maven
* Jackson (JSON)
* Stream API
* BigDecimal
* Programação Orientada a Objetos (POO)

---

## 📂 Estrutura do Projeto

```
src/
 ├── main/
 │   ├── java/com/projedata/
 │   │   ├── model/
 │   │   ├── service/
 │   │   ├── exception/
 │   │   └── Main.java
 │   └── resources/
 │       └── funcionarios.json
```

---

## ⚙️ Funcionalidades Implementadas

### ✔️ Requisitos do Teste

* [x] Classe `Pessoa` com nome e data de nascimento
* [x] Classe `Funcionário` herdando de `Pessoa`
* [x] Inserção dos funcionários via JSON
* [x] Remoção do funcionário "João"
* [x] Impressão formatada (datas e valores)
* [x] Aplicação de aumento salarial (10%)
* [x] Agrupamento por função (`Map`)
* [x] Impressão agrupada por função
* [x] Filtro de aniversariantes (meses 10 e 12)
* [x] Identificação do funcionário mais velho
* [x] Ordenação alfabética
* [x] Cálculo total de salários
* [x] Cálculo de salários mínimos por funcionário

---

## 📊 Exemplo de Saída

```
+-----------------+------------+-----------------+
| Nome            | Idade      | Função          |
+-----------------+------------+-----------------+
| Maria           | 23         | Operador        |
| Carlos          | 35         | Gerente         |
+-----------------+------------+-----------------+
```

---

## ▶️ Como Executar o Projeto

### Pré-requisitos:

* Java 17 instalado
* Maven instalado

### Passos:

```bash
# Clonar o repositório
git clone https://github.com/devCarlosSantoss/teste-iniflex-projedata.git

# Entrar na pasta
cd teste-iniflex-projedata

# Compilar o projeto
mvn clean install

# Executar
mvn exec:java -Dexec.mainClass="com.projedata.Main"
```

---

## 💡 Diferenciais do Projeto

* ✅ Leitura de dados via JSON (simulando fonte externa)
* ✅ Uso de `Stream API` para manipulação de dados
* ✅ Tratamento de exceções customizadas
* ✅ Código organizado em camadas (Service)
* ✅ Saída formatada em tabela (melhor visualização)
* ✅ Boas práticas com uso de `BigDecimal`

---

## 👨‍💻 Autor

Desenvolvido por **Carlos Vinícius**
🔗 GitHub: https://github.com/devCarlosSantoss

---

## 📢 Observação

O projeto foi desenvolvido com foco não apenas em atender os requisitos, mas também em demonstrar organização, clareza e boas práticas de desenvolvimento, visando um padrão profissional.

---
