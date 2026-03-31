# 📘 ERS — Employee Resource System

## 👥 Integrantes do Grupo

- Kevin Carvalho — RM 561459  
- Luiz Antonio — RM 562142  
- Nicolas Barnabe — RM 561997  
- Yan Breno — RM 566412  
- Guilherme Sorrilha — RM 563825  

---

## 🌍 Contexto Empresarial

Em empresas modernas — como bancos, empresas de tecnologia, indústrias e consultorias — existe a necessidade de controlar de forma eficiente:

- Colaboradores
- Equipamentos (notebooks, celulares, etc.)
- Recursos internos
- Custos operacionais

Esse controle é essencial para garantir:

- Governança corporativa  
- Segurança da informação  
- Redução de custos  
- Rastreabilidade de ativos  

O sistema desenvolvido simula um núcleo de gestão de recursos corporativos, inspirado em práticas reais de:

- Inventário de ativos (Asset Management)
- Controle de equipamentos de TI
- Políticas de alocação
- Ciclo de vida do colaborador

---

## 🎯 Objetivo do Projeto

Desenvolver um módulo central de um sistema corporativo chamado ERS (Employee Resource System) utilizando apenas conceitos fundamentais de Java:

- Classes e objetos  
- Atributos e métodos  
- Tipos primitivos  
- Estruturas básicas (List, loops, etc.)  
- Sem uso de frameworks ou banco de dados  

---

## 🧩 Estrutura do Sistema

### 👤 Colaborador

**Atributos:**
- id
- nome
- cargo
- salario
- ativo
- dataDeAdmissao

**Regras de negócio:**
- Todo colaborador inicia como ativo = true
- Pode ser promovido

---

### 💻 Recurso

**Atributos:**
- id
- nomeDoRecurso
- categoria
- disponivel
- valorEstimado

**Regras de negócio:**
- Só pode ser alocado se estiver disponível
- Valor acima de 5000 exige autorização especial

---

### 🔄 Alocação

**Atributos:**
- colaboradorId
- recursoId
- data
- observacao

---

### 🧠 SistemaERS

Responsável por:
- Gerenciar listas
- Aplicar regras de negócio
- Controlar o sistema

---

## ⚙️ Funcionalidades

- Cadastro de colaboradores  
- Cadastro de recursos  
- Alocação e devolução  
- Promoção de colaboradores  
- Histórico de alocações  
- Listagem geral  

---

## 🧠 Regras de Negócio

- Colaborador deve estar ativo
- Recurso deve estar disponível
- Recursos > 5000 não são alocados
- Sistema mantém histórico de movimentações

---

## 🚀 Inovação

### 💰 Cálculo de custo total por colaborador

Permite calcular o valor total dos recursos atualmente alocados a um colaborador.

**Benefícios reais:**
- Controle financeiro
- Auditoria
- Gestão de ativos

---

## 🏢 Aplicação no Mundo Real

Empresas utilizam sistemas semelhantes para:

- Inventário de ativos  
- Controle de equipamentos  
- Segurança da informação  
- Gestão do ciclo de vida do colaborador  

---

## 📊 Diagrama de Classes

Colaborador  
Recurso  
Alocacao  
SistemaERS  

---

## 💻 Execução

Menu interativo:

1. Cadastrar Colaborador
2. Cadastrar Recurso
3. Alocar Recurso
4. Devolver Recurso
5. Exibir Colaboradores
6. Exibir Recursos
7. Exibir Alocações
8. Custo Total por Colaborador
9. Promover Colaborador
0. Sair

---

## 📌 Conclusão

O projeto aplica conceitos fundamentais de programação orientada a objetos para simular um sistema corporativo real, podendo evoluir para soluções mais robustas no futuro.
