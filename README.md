# 💇‍♀️ Sistema de Agendamentos - Salão da Dona Inês

![Status do Projeto](https://img.shields.io/badge/status-entregue-green)
![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.7-brightgreen)


## 📋 Sobre o Projeto

Projeto desenvolvido como parte do Projeto Integrador III da Universidade Virtual do Estado de São Paulo (UNIVESP). O sistema consiste em uma plataforma web para gerenciamento de agendamentos do Salão de Beleza da Dona Inês, facilitando o processo de marcação de horários e gestão de serviços.


### 🎯 Objetivo
Desenvolver uma solução digital que modernize e otimize o processo de agendamentos do salão, proporcionando maior organização e melhor experiência tanto para os clientes quanto para os profissionais.

## 🚀 Funcionalidades
- 📅 Agendamento online de serviços
- 🔄 Reagendamento e cancelamento
- 💇‍♀️ Histórico de serviços realizados

## 🛠️ Tecnologias Utilizadas

Hospedado na Digital Ocean: http://67.205.131.238:8080/

### Backend
- **Java 17**
- **Spring Boot 3.2.7**
    - Spring Data JPA
    - Spring MVC
- **Jakarta EE**
    - Jakarta Persistence (JPA)
- **Banco de Dados**
    - MySQL

### Ferramentas de Desenvolvimento
- **Lombok** - Redução de boilerplate
- **Maven** - Gerenciamento de dependências
- **Git** - Controle de versão

## 🏃‍♂️ Como Executar o Projeto

### Pré-requisitos
- Java JDK 17
- Maven
- MySQL
- IDE de sua preferência (recomendamos IntelliJ IDEA)

### Passo a Passo

1. **Clone o repositório**

2. **Configure o banco de dados**
    - Crie um banco de dados MySQL
    - Atualize o arquivo `application.properties` com suas configurações:


3. **Execute o projeto: bash mvn spring-boot:run**


4. **Acesse a aplicação:**
    - Frontend: `http://localhost:8080`
    - API: `http://localhost:8080/api`

## 📚 Documentação da API

### Endpoints Disponíveis:

- **POST** `/agendamentos` - Cria um novo agendamento
- **GET** `/agendamentos` - Lista todos os agendamentos
- **GET** `/agendamentos/{id}` - Busca um agendamento específico
- **PUT** `/agendamentos/{id}` - Atualiza um agendamento existente
- **DELETE** `/agendamentos/{id}` - Remove um agendamento (requer senha)


## 👥 Equipe

- Cleyton Ongaratto
- Gabriel Martins Melo
- Altieres Moreira Servulo
- Naomi
- Luis Gabrie
- Jhonatan
- Aline

## 🎓 Instituição

Universidade Virtual do Estado de São Paulo - UNIVESP

---
