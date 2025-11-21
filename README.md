cl<div align="center">

![Instituto Infnet](https://img.shields.io/badge/Instituto-Infnet-red?style=for-the-badge)
![Curso](https://img.shields.io/badge/Curso-Engenharia_de_Software-blue?style=for-the-badge)
![Disciplina](https://img.shields.io/badge/Disciplina-Clean_Code-green?style=for-the-badge)

# 🧼 Projeto DR2 - TP2
## Teste de Performance 2 - Clean Code

[![Autor](https://img.shields.io/badge/Autor-André_Becker-blue)](https://github.com/andrebecker84)
[![LinkedIn](https://img.shields.io/badge/LinkedIn-André_Becker-0077B5?logo=linkedin)](https://linkedin.com/in/andrebecker)

</div>

---

## 📖 Sobre

Este projeto implementa 12 exercícios práticos de refatoração de código, aplicando princípios fundamentais de Clean Code e SOLID. Desenvolvido como trabalho acadêmico da disciplina de Engenharia de Software, demonstra a evolução de código problemático para código limpo, legível e manutenível.

Cada exercício apresenta um problema real de qualidade de código, seguido de uma solução refatorada que aplica boas práticas de engenharia de software. O projeto utiliza Java 21, Spring Boot 3, e ferramentas modernas de teste como JUnit 5, Hamcrest e Jqwik.

> **Estatísticas**: 12 exercícios | 130 testes | 42 classes | Cobertura >70%

---

## 🎯 Objetivos

- ✅ Aplicar princípios **SOLID** (SRP, OCP, LSP, ISP, DIP)
- ✅ Implementar práticas de **Clean Code** (nomeação, funções, comentários)
- ✅ Utilizar **Design Patterns** (Null Object, Factory, Strategy)
- ✅ Seguir princípios **DRY**, **KISS**, **YAGNI**
- ✅ Aplicar **CQS** (Command Query Separation)
- ✅ Desenvolver testes unitários robustos com JUnit 5, Hamcrest e Jqwik
- ✅ Garantir cobertura de código adequada com JaCoCo

---

## 📊 Badges do Projeto

![Status](https://img.shields.io/badge/Status-Concluído-success?style=flat-square)
[![Build](https://github.com/andrebecker84/projetoDR2_TP2/actions/workflows/maven.yml/badge.svg)](https://github.com/andrebecker84/projetoDR2_TP2/actions/workflows/maven.yml)
![Tests](https://raw.githubusercontent.com/andrebecker84/projetoDR2_TP2/master/.github/badges/tests.svg)
![Coverage](https://raw.githubusercontent.com/andrebecker84/projetoDR2_TP2/master/.github/badges/jacoco.svg)
![Branches](https://raw.githubusercontent.com/andrebecker84/projetoDR2_TP2/master/.github/badges/branches.svg)
![Quality](https://img.shields.io/badge/Quality-A+-blue?style=flat-square)
![Java](https://img.shields.io/badge/Java-21-orange?style=flat-square)
[![Last Commit](https://img.shields.io/github/last-commit/andrebecker84/projetoDR2_TP2?style=plastic)](https://github.com/andrebecker84/projetoDR2_TP2)
[![Repo Size](https://img.shields.io/github/repo-size/andrebecker84/projetoDR2_TP2?style=plastic)](https://github.com/andrebecker84/projetoDR2_TP2)
[![License](https://img.shields.io/github/license/andrebecker84/projetoDR2_TP2?style=plastic&logo=readme&logoColor=white)](LICENSE)

---

## 🚀 Tecnologias Utilizadas

| Tecnologia  | Versão  | Finalidade                            |
|-------------|---------|---------------------------------------|
| Java        | 21      | Linguagem de programação              |
| Maven       | 3.9+    | Gerenciamento de dependências e build |
| Spring Boot | 3.4.1   | Framework base da aplicação           |
| JUnit 5     | 5.11.4  | Framework de testes unitários         |
| Hamcrest    | 3.0     | Matchers expressivos para assertions  |
| Jqwik       | 1.9.3   | Property-based testing                |
| JaCoCo      | 0.8.12  | Análise de cobertura de código        |
| Mockito     | 5.14.2  | Framework de mocking                  |
| Lombok      | 1.18.38 | Redução de boilerplate code           |

---

## ⚙️ Como Executar

### Pré-requisitos
- ☕ **Java 21** ou superior ([Download](https://www.oracle.com/java/technologies/downloads/))
- 📦 **Maven 3.9** ou superior ([Download](https://maven.apache.org/download.cgi))
- 🔧 **Git** ([Download](https://git-scm.com/downloads))

### Comandos

```bash
# Clonar o repositório
git clone https://github.com/andrebecker84/projetoDR2_TP2.git
cd projetoDR2_TP2

# Compilar o projeto
mvn clean compile

# Executar todos os testes
mvn test

# Executar testes com verificação de cobertura
mvn verify

# Gerar relatório JaCoCo de cobertura
mvn jacoco:report
# Relatório disponível em: target/site/jacoco/index.html

# Executar a aplicação Spring Boot
mvn spring-boot:run

# 🧪 MENU INTERATIVO DE TESTES
# Executar menu para escolher quais testes rodar
mvn test-compile exec:java

# Ou usando os scripts auxiliares:
# Windows:
./executar-testes.bat

# Linux/Mac:
./executar-testes.sh
```

### 🎯 Menu Interativo de Testes

O projeto inclui um **menu interativo** que permite executar testes de forma seletiva:

**Recursos:**
- 📋 Listar todos os 12 exercícios com descrições
- 🎯 Executar testes de um exercício específico
- 🚀 Executar todos os testes de uma vez
- 🔍 Executar testes de verificação automática
- 📊 Ver estatísticas detalhadas do projeto
- ✅ Resultados em tempo real com status visual

**Como usar:**
1. Execute: `mvn test-compile exec:java` ou `./executar-testes.bat` (Windows) ou `./executar-testes.sh` (Linux/Mac)
2. Escolha uma opção do menu (1-15)
3. Veja os resultados detalhados de cada teste
4. Opção 13: Executa TODOS os testes
5. Opção 14: Executa testes de verificação automática
6. Opção 15: Exibe estatísticas completas

---

## 📊 Estatísticas do Projeto

```
📁 Exercícios:        12
📝 Classes Java:      42
🧪 Testes Unitários:  130 (124 exercícios + 6 verificação)
✅ Testes Passando:   130
📈 Cobertura:         >70%
📄 Linhas de Código:  ~4.200
🎯 Falhas:            0
```

---

## 📁 Estrutura do Projeto

```
projetoDR2_TP2/
├── 📂 doc/
│   └── 📄 DOCUMENTACAO_TP2.md      # Documentação técnica detalhada
├── 📂 src/
│   ├── 📂 main/java/infnet/andre/tp2/
│   │   ├── 📄 Aplicacao.java                # Classe principal Spring Boot
│   │   ├── 📂 exercicio01/                  # Nomeação de Variáveis
│   │   │   ├── CalculadoraAntes.java
│   │   │   └── CalculadoraDepois.java
│   │   ├── 📂 exercicio02/                  # Valores Mágicos
│   │   │   ├── DescontoAntes.java
│   │   │   └── DescontoDepois.java
│   │   ├── 📂 exercicio03/                  # Null Object Pattern
│   │   │   ├── Cliente.java
│   │   │   ├── ClienteReal.java
│   │   │   ├── ClienteNulo.java
│   │   │   ├── PedidoAntes.java
│   │   │   └── PedidoDepois.java
│   │   ├── 📂 exercicio04/                  # Imutabilidade
│   │   │   ├── Produto.java
│   │   │   ├── ProdutoImutavel.java
│   │   │   ├── AjusteAntes.java
│   │   │   └── AjusteDepois.java
│   │   ├── 📂 exercicio05/                  # Complexidade Ciclomática
│   │   │   ├── TipoCliente.java
│   │   │   ├── ClienteClassificadorAntes.java
│   │   │   └── ClienteClassificadorDepois.java
│   │   ├── 📂 exercicio06/                  # Strategy Pattern
│   │   │   ├── Nationality.java
│   │   │   ├── Color.java
│   │   │   ├── BandeiraStrategy.java
│   │   │   ├── PersonalizacaoAntes.java
│   │   │   └── PersonalizacaoDepois.java
│   │   ├── 📂 exercicio07/                  # Abstract Factory
│   │   │   ├── TipoRelatorio.java
│   │   │   ├── Relatorio.java
│   │   │   ├── RelatorioPDF/CSV/JSON.java
│   │   │   ├── RelatorioFactory.java
│   │   │   ├── RelatorioServiceAntes.java
│   │   │   └── RelatorioServiceDepois.java
│   │   ├── 📂 exercicio08/                  # CQS - Conta Bancária
│   │   │   ├── ContaBancariaAntes.java
│   │   │   └── ContaBancariaDepois.java
│   │   ├── 📂 exercicio09/                  # CQS - Fila Mensagens
│   │   │   ├── FilaMensagensAntes.java
│   │   │   └── FilaMensagensDepois.java
│   │   ├── 📂 exercicio10/                  # CQS - Monitoramento
│   │   │   ├── MonitoramentoAntes.java
│   │   │   └── MonitoramentoDepois.java
│   │   ├── 📂 exercicio11/                  # Switch Exaustivo - Pedidos
│   │   │   ├── StatusPedido.java
│   │   │   ├── PedidoServiceAntes.java
│   │   │   └── PedidoServiceDepois.java
│   │   └── 📂 exercicio12/                  # Switch Exaustivo - Notificações
│   │       ├── TipoNotificacao.java
│   │       ├── NotificacaoServiceAntes.java
│   │       └── NotificacaoServiceDepois.java
│   └── 📂 test/java/infnet/andre/tp2/
│       ├── 📂 exercicio01/
│       │   └── CalculadoraTest.java
│       ├── 📂 exercicio02/
│       │   └── DescontoTest.java
│       └── ... (testes para todos os exercícios)
├── 📄 .gitignore
├── 📄 LICENSE                     # Licença MIT
├── 📄 pom.xml                     # Configuração Maven
└── 📄 README.md                   # Este arquivo
```

---

## 🧼 Princípios de Clean Code Aplicados

### SOLID

| Princípio | Descrição | Aplicado | Exercícios |
|-----------|-----------|----------|------------|
| **S**RP | Single Responsibility Principle | ✅ | Todos |
| **O**CP | Open/Closed Principle | ✅ | 6, 7 |
| **L**SP | Liskov Substitution Principle | ✅ | 3, 6, 7 |
| **I**SP | Interface Segregation Principle | ✅ | 3, 7 |
| **D**IP | Dependency Inversion Principle | ✅ | 3, 7 |

### Outros Princípios Fundamentais

| Princípio | Descrição | Exercícios |
|-----------|-----------|------------|
| **DRY** | Don't Repeat Yourself | 2, 5, 6, 7 |
| **KISS** | Keep It Simple, Stupid | Todos |
| **YAGNI** | You Aren't Gonna Need It | Todos |
| **CQS** | Command Query Separation | 8, 9, 10 |

---

## 📚 Exercícios Implementados

### 🔹 Exercício 1 - Nomeação de Variáveis e Funções
**Objetivo**: Refatorar código com nomes indecifráveis para nomes descritivos e reveladores de intenção.

**Problema**: Código original usava nomes genéricos (A, a, x, y, z) que não expressavam significado.

**Solução**: Implementação com nomes claros (`calcularDobroDaSoma`, `primeiroNumero`, `segundoNumero`).

**Princípios**: Clean Code - Nomes reveladores de intenção.

---

### 🔹 Exercício 2 - Código Autoexplicativo e Valores Mágicos
**Objetivo**: Eliminar valores mágicos e tornar o código autoexplicativo.

**Problema**: Valores literais (1000, 0.9) sem contexto, operador de comparação incorreto.

**Solução**: Constantes nomeadas (`VALOR_MINIMO_PARA_DESCONTO`, `PERCENTUAL_DESCONTO`), correção lógica (>= ao invés de >).

**Princípios**: Clean Code - Evite números mágicos.

---

### 🔹 Exercício 3 - Evitando NullPointerException
**Objetivo**: Implementar Null Object Pattern para eliminar verificações de null.

**Problema**: Código lançava NullPointerException quando cliente não cadastrado.

**Solução**: Interface `Cliente` com implementações `ClienteReal` e `ClienteNulo`.

**Princípios**: Design Pattern - Null Object, Clean Code - Evite retornar/passar null.

---

### 🔹 Exercício 4 - Evitando Mutação de Parâmetros
**Objetivo**: Aplicar imutabilidade para prevenir efeitos colaterais.

**Problema**: Método alterava diretamente o objeto passado como parâmetro.

**Solução**: `ProdutoImutavel` com método que retorna nova instância ao invés de modificar original.

**Princípios**: Functional Programming - Imutabilidade, Clean Code - Evite efeitos colaterais.

---

### 🔹 Exercício 5 - Código com Muitos ifs Aninhados
**Objetivo**: Reduzir complexidade ciclomática.

**Problema**: IFs aninhados até 3 níveis dificultando leitura e manutenção.

**Solução**: Guard clauses, métodos auxiliares (`isSenior`, `isBaixaRenda`).

**Princípios**: Clean Code - Evite aninhamento profundo, SRP.

---

### 🔹 Exercício 6 - Personalização da Interface do Cliente
**Objetivo**: Refatorar switch extenso usando configuração centralizada.

**Problema**: Switch/case para cada nacionalidade violando Open/Closed Principle.

**Solução**: `EnumMap` com configuração de cores por nacionalidade, Strategy Pattern.

**Princípios**: SOLID - OCP, Design Pattern - Strategy.

---

### 🔹 Exercício 7 - Switch com Abstract Factory
**Objetivo**: Implementar Abstract Factory para criação de objetos.

**Problema**: IFs encadeados para instanciar diferentes tipos de relatórios.

**Solução**: Interface `Relatorio`, implementações concretas, `RelatorioFactory` com switch exaustivo.

**Princípios**: Design Pattern - Abstract Factory, SOLID - SRP/DIP.

---

### 🔹 Exercício 8 - Command Query Separation (CQS) - Conta Bancária
**Objetivo**: Separar métodos de consulta (query) de métodos de comando (command).

**Problema**: Método `podeComprar` retornava boolean E modificava saldo.

**Solução**: `podeComprar()` apenas consulta (sem alterar estado), `realizarCompra()` apenas altera (void).

**Princípios**: CQS - Command Query Separation.

---

### 🔹 Exercício 9 - CQS - Fila de Mensagens
**Objetivo**: Separar operações de leitura e modificação em fila.

**Problema**: `poll()` retornava E removia mensagem simultaneamente.

**Solução**: `peek()` para consulta (não remove), `poll()` para remoção (void).

**Princípios**: CQS - Command Query Separation.

---

### 🔹 Exercício 10 - CQS - Monitoramento
**Objetivo**: Eliminar efeitos colaterais em getters.

**Problema**: `getTemperatura()` incrementava contador (efeito colateral oculto).

**Solução**: `getTemperatura()` apenas retorna valor, `incrementarAcessos()` explícito.

**Princípios**: CQS - Queries devem ser puras.

---

### 🔹 Exercício 11 - Switch Exaustivo - Status de Pedidos
**Objetivo**: Garantir tratamento explícito de todos os casos de enum.

**Problema**: Switch com `default` mascarava novos status não tratados.

**Solução**: Switch SEM default tratando todos os casos de `StatusPedido`.

**Princípios**: Fail-Fast, Type Safety, Clean Code.

---

### 🔹 Exercício 12 - Switch Exaustivo - Tipos de Notificações
**Objetivo**: Implementar switch exaustivo para tipos de notificação.

**Problema**: Default escondia tipos não implementados.

**Solução**: Switch SEM default para `TipoNotificacao` (EMAIL, SMS, PUSH).

**Princípios**: Compilador garante cobertura completa.

---

## 🧪 Testes Unitários

### Estratégia de Testes

O projeto utiliza uma abordagem multi-facetada para garantir qualidade:

1. **Testes Tradicionais (JUnit 5)**: Casos de teste específicos para cenários conhecidos
2. **Matchers Expressivos (Hamcrest)**: Assertions mais legíveis e descritivas
3. **Property-Based Testing (Jqwik)**: Validação de propriedades com dados aleatórios
4. **Cobertura de Código (JaCoCo)**: Análise de cobertura com threshold mínimo

### Executar Testes

```bash
# Executar todos os testes
mvn test

# Executar testes com cobertura
mvn verify

# Visualizar relatório JaCoCo
# Abrir: target/site/jacoco/index.html
```

### Distribuição de Testes por Exercício

| Exercício | Testes | JUnit | Jqwik | Status |
|-----------|--------|-------|-------|--------|
| Exercício 1 - Nomeação | 10 | 7 | 3 | ✅ PASS |
| Exercício 2 - Valores Mágicos | 10 | 10 | 0 | ✅ PASS |
| Exercício 3 - Null Object | 3 | 3 | 0 | ✅ PASS |
| Exercício 4 - Imutabilidade | 4 | 4 | 0 | ✅ PASS |
| Exercício 5 - Complexidade | 13 | 13 | 0 | ✅ PASS |
| Exercício 6 - Personalização | 9 | 9 | 0 | ✅ PASS |
| Exercício 7 - Abstract Factory | 6 | 6 | 0 | ✅ PASS |
| Exercício 8 - CQS Conta | 6 | 6 | 0 | ✅ PASS |
| Exercício 9 - CQS Fila | 11 | 11 | 0 | ✅ PASS |
| Exercício 10 - CQS Monitor | 17 | 17 | 0 | ✅ PASS |
| Exercício 11 - Switch Pedidos | 18 | 18 | 0 | ✅ PASS |
| Exercício 12 - Switch Notific. | 17 | 17 | 0 | ✅ PASS |
| **TOTAL** | **124** | **121** | **3** | **✅ 100%** |

---

## 📄 Documentação

Para análise técnica detalhada de cada exercício, consulte:

📘 **[Documentação Técnica Completa](doc/DOCUMENTACAO_TP2.md)**

A documentação inclui:
- Análise detalhada de cada exercício
- Código original vs. código refatorado
- Explicação dos princípios aplicados
- Benefícios mensuráveis das refatorações
- Diagramas e exemplos de uso

---

## 📚 Referências Bibliográficas

### Livros Fundamentais

- **MARTIN, Robert C.** *Código Limpo: Habilidades Práticas do Agile Software*. Rio de Janeiro: Alta Books, 2009.
- **MARTIN, Robert C.** *Arquitetura Limpa: O Guia do Artesão para Estrutura e Design de Software*. Rio de Janeiro: Alta Books, 2019.
- **BLOCH, Joshua.** *Java Efetivo: As Melhores Práticas para a Plataforma Java*. 3ª Edição. Rio de Janeiro: Alta Books, 2018.
- **FOWLER, Martin.** *Refatoração: Aperfeiçoando o Design de Códigos Existentes*. 2ª Edição. São Paulo: Novatec, 2020.
- **GAMMA, Erich et al.** *Padrões de Projetos: Soluções Reutilizáveis de Software Orientado a Objetos*. Porto Alegre: Bookman, 2000.

### Documentação Técnica

- **Oracle** - Documentação Java SE 21 - https://docs.oracle.com/en/java/javase/21/
- **Spring** - Spring Boot 3.4 Reference - https://docs.spring.io/spring-boot/docs/3.4.x/reference/html/
- **JUnit Team** - JUnit 5.11 User Guide - https://junit.org/junit5/docs/5.11.4/user-guide/
- **JaCoCo** - Java Code Coverage Library 0.8.12 - https://www.jacoco.org/jacoco/trunk/doc/
- **Maven** - Apache Maven 3.9 Documentation - https://maven.apache.org/ref/3.9.6/

### Materiais Acadêmicos

- **Instituto Infnet** - Engenharia de Software - Materiais Didáticos. Rio de Janeiro, 2025.
- **Clean Coders** - Clean Code Video Series - https://cleancoders.com/

---

## ⚖️ Licença

Este projeto está licenciado sob a **Licença MIT** - consulte o arquivo [LICENSE](LICENSE) para detalhes.

```
MIT License - Copyright (c) 2025 André Becker

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction...
```

---

## 👨‍💻 Autor

<div align="center">

**André Luis Becker**

[![GitHub](https://img.shields.io/badge/GitHub-andrebecker84-181717?style=for-the-badge&logo=github)](https://github.com/andrebecker84)
[![LinkedIn](https://img.shields.io/badge/LinkedIn-André_Becker-0077B5?style=for-the-badge&logo=linkedin)](https://linkedin.com/in/andrebecker)

*Engenheiro de Software | Instituto Infnet*

</div>

---

## 🙏 Agradecimentos

- **Instituto Infnet** - pela excelente formação em Engenharia de Software
- **Professor da Disciplina** - pelas orientações sobre Clean Code e boas práticas
- **Robert C. Martin (Uncle Bob)** - pelos ensinamentos fundamentais de Clean Code
- **Comunidade Open Source** - pelas ferramentas e bibliotecas utilizadas

---

<div align="center">

**Desenvolvido com dedicação e boas práticas** 🧼✨

*Instituto Infnet - Engenharia de Software - 2025*

**⭐ Se este projeto foi útil, considere dar uma estrela no repositório!**

</div>
