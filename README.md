<h1 align="center">MailToDo++ :heavy_check_mark: :envelope: </h1> 

<p align="center">
  <img src="https://img.shields.io/static/v1?label=Spring-Boot&message=2&color=green&style=for-the-badge&logo=Spring"/>
  <img src="https://img.shields.io/static/v1?label=Angular&message=11&color=red&style=for-the-badge&logo=Angular"/>
  <img src="http://img.shields.io/static/v1?label=License&message=MIT&color=informational&style=for-the-badge"/>
</p>

## Descrição do projeto :page_with_curl:
Este é um projeto fullstack, para ver mais sobre o frontend da aplicação confira o repositório [MailToDo_2.0-Angular](https://github.com/vrfvitor/MailToDo_2.0-Angular).

MailToDo++ é um gerenciador de tarefas que de tempos em tempos te notifica da existencia de tarefas de alta prioridade ainda não concluídas, via email.

O usuário deve realizar login para ter acesso as suas tarefas e caso ainda não pussua conta, poderá se registrar utilizando seu nome, email e senha. Uma vez logado, o usuário poderá ver, criar, editar e excluir suas tarefas.

As tarefas devem conter um título, uma prioridade e uma descrição. Além disso podem estar completas ou não. Periodicamente, o sistema verificará e irá gerar um email personalizado contendo tarefas de alta prioridade que estão pendentes, notificando o usuário no endereço de email informado em seu cadastro.

O projeto é a continuação do [MailToDo](https://github.com/vrfvitor/MailToDo-JEE_Angular) porém com o backend todo reformulado, deixando de ser em Java EE (JAX-RS) para se tornar um projeto Spring Boot, onde fiz também a implementação de toda a parte de segurança, registro e autenticação com o Spring Security e utilização de JWT, pela natureza stateless da API REST.

## Layout da Aplicação :art:
<p align="center">
  <img  src="/media/mtd_auth.gif" alt="MailToDo++ Preview Auth">
  <img  src="/media/mtd_main.gif"  alt="MailToDo++ Preview em Computador">
  <img  src="/media/email.png" width="600" height="328" alt="MailToDo++ Preview Sent Email">
</p>
<p align="center">
  <img  src="/media/mtd_mobile.gif"  alt="MailToDo++ Preview em Smartphone">
</p>

## Funcionalidades :gear:

:heavy_check_mark: Registro, Autenticação e Autorização

:heavy_check_mark: CRUD de Tarefas

:heavy_check_mark: Notificação periódica via email

## Techs & Tools utilizadas :books:
- Java 8
  - Uso dos recursos mais modernos da linguagem

- Módulos Spring
  - Toda a rica infraestrutura do Spring: IOC; DI; `@Scheduled` jobs; etc.
  - Projeto em Spring Boot 
  - Módulo Web para endpoints REST
  - Spring Security e a library JJWT para gerenciamento da segurança com JWT's
  - Spring Data JPA para acesso ao banco de dados
  - Módulo Spring Mail para as notificações períodicas
  
- Abstração com utilização de DTO's
  
- Persistência com PostgreSQL

- [Frontend](https://github.com/vrfvitor/MailToDo_2.0-Angular)

## Desenvolvedor :computer:

<img src="https://avatars.githubusercontent.com/vrfvitor" width=115 align="left"/>
<h4>Vitor Rodrigues Ferreira</h4>

<h5>Conecte-se pelo <a href="https://www.linkedin.com/in/vrfvitor" target="_blank">LinkedIn</a></h4>

<h5>Siga pelo <a href="https://github.com/vrfvitor" target="_blank">GitHub</a>

## Licença :balance_scale:

<p align="center">
          <a href="https://opensource.org/licenses/MIT">The MIT License</a>
</p>

<p align="center">Copyright :copyright: 2021 - MailToDo++</p>

