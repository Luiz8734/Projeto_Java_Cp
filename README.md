# 📘 WorkForce Manager

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

## 🔍 Complexidade Empresarial

Para o desenvolvimento das regras deste sistema, pesquisamos como grandes corporações gerenciam seus ativos. Abaixo, apresentamos a justificativa de como essas práticas inspiraram nossa implementação:

Inventário e Controle de Ativos: No mercado, utiliza-se a rastreabilidade total para evitar o "Dark IT" (ativos não monitorados).

- Justificativa: Implementamos listas centralizadas em SistemaERS e um histórico de movimentações em Alocacao, permitindo saber exatamente com quem está cada recurso em tempo real.

Políticas de Alocação e Limites de Gasto: Empresas definem níveis de aprovação baseados no valor do ativo (CAPEX).

- Justificativa: Criamos a regra que bloqueia automaticamente a alocação de recursos acima de R$ 5.000,00 sem autorização, refletindo o controle orçamentário corporativo.

Ciclo de Vida do Colaborador (Onboarding e Offboarding): O momento do desligamento é crítico para a segurança; ativos não devolvidos representam riscos de vazamento de dados.

- Justificativa: Implementamos o método demitirColaborador, que automatiza a devolução de todos os recursos vinculados ao ID do funcionário assim que ele é desativado no sistema.

Governança e Segurança: Apenas colaboradores ativos devem possuir acesso a recursos da empresa.

- Justificativa: Inserimos validações que impedem a alocação de novos recursos para colaboradores com status ativo = false.

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

- Novos colaboradores iniciam como ativos. Recursos só podem ser alocados para funcionários ativos.
- Um recurso só pode ser alocado se estiver com o status disponivel = true.
- Recursos com valor estimado > R$ 5.000,00 exigem permissão especial (bloqueio preventivo no sistema).
- Ao demitir um colaborador, o sistema identifica todos os ativos sob sua responsabilidade e os marca como disponíveis automaticamente, gerando um registro de devolução.

---

## 🚀 Inovação

### 💰 Cálculo de custo total por colaborador

O sistema conta com uma funcionalidade de auditoria financeira que percorre todos os ativos alocados a um colaborador específico e soma seus valores estimados.

**Benefícios reais:**
- Permite ao gestor visualizar o "custo de hardware" por funcionário, auxiliando na análise de depreciação e seguros.

---

## 🏢 Aplicação no Mundo Real

Empresas utilizam sistemas semelhantes para:

- Inventário de ativos  
- Controle de equipamentos  
- Segurança da informação  
- Gestão do ciclo de vida do colaborador  

---

## 📊 Diagrama de Classes

    classDiagram {
        class Colaborador {
            -int id
            -String nome
            -String cargo
            -double salario
            -boolean ativo
            -String dataDeAdmissao
            +promover(String novoCargo, double novoSalario)
            +desativar()
        }

        class Recurso {
            -int id
            -String nomeDoRecurso
            -String categoria
            -boolean disponivel
            -double valorEstimado
            +podeSerAlocado() boolean
            +setDisponivel(boolean disponivel)
        }

        class Alocacao {
            -int colaboradorId
            -int recursoId
            -String data
            -String observacao
            +getColaboradorId() int
            +getRecursoId() int
            +getObservacao() String
        }

        class SistemaERS {
            -List~Colaborador~ colaboradores
            -List~Recurso~ recursos
            -List~Alocacao~ alocacoes
            -int proximoColaboradorId
            -int proximoRecursoId
            +cadastrarColaborador(String nome, String cargo, double salario, String data)
            +cadastrarRecurso(String nome, String categoria, double valor)
            +alocarRecurso(int colaboradorId, int recursoId)
            +devolverRecurso(int colaboradorId, int recursoId)
            +promoverColaborador(int id, String novoCargo, double novoSalario)
            +demitirColaborador(int id)
            +calcularCustoTotalAlocadoPorColaborador(int id) double
            +exibirColaboradores()
            +exibirRecursos()
            +exibirAlocacoes()
        }

        class Main {
            +main(String[] args)
        }

        Main ..> SistemaERS : utiliza
        SistemaERS "1" --> "*" Colaborador : gerencia
        SistemaERS "1" --> "*" Recurso : gerencia
        SistemaERS "1" --> "*" Alocacao : registra


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
10. Demitir Colaborador
0. Sair

---

## 📌 Conclusão

O  WorkForce Manager demonstra como a lógica de programação orientada a objetos pode resolver problemas reais de governança. Ao integrar regras de segurança e automação no desligamento, o projeto simula as necessidades de conformidade e eficiência exigidas por grandes corporações no manejo de seus ativos tecnológicos.
