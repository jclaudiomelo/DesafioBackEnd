# DesafioBackEnd

Desafio Back End Java, Spring Boot, PostgreSql, Postman
Cenário:
Um Cliente de uma transportadora gostaria de fazer o controle de sua Frota de
Caminhões e de suas entregas,dessa forma gostaria de saber quantos caminhões tem em
sua frota e os horários e entregas que estão fazendo como também quais os tipos de
cargas da entrega e os valores totais por dia.
Regras:

1. Umcaminhão só pode estar associado a uma entrega;
2. Entregas com valores maiores que 30 mil devem receber um indicador de valiosa;
3. Entregas do Tipo eletrônicos devem ter um indicador se tem seguro ou nao;
4. Entregas do Tipo Combustível devem ter um indicador de perigosa;
5. UmCaminhão só pode fazer 4 entregas por mês;
6. UmMotorista de um caminhão só pode fazer duas entregas por mês;
7. Entregas para o Nordeste tem um taxa de 20% no valor do frete;
8. Entregas para Argentina tem um taxa de 40% no valor do frete;
9. Entregas para a Amazônia tem uma taxa de 30% no valor do frete;
10. Um Motorista só pode fazer um entrega para o Nordeste;

Tecnologias

● Java
● Spring Boot ● PostgreSql ● Postman

# 🚛 Sistema de Gestão de Frotas e Entregas

Este projeto é um sistema backend desenvolvido em **Java com Spring Boot**, que permite o gerenciamento de **caminhões,
motoristas e entregas**. Ele oferece **relatórios detalhados** com filtros por período, motorista e caminhão, além de
totalizações por dia.

---

## ✅ Regras de Negócio

1. Um caminhão só pode estar associado a **uma entrega por vez**;
2. Entregas com valor superior a **R$ 30.000,00** devem ser marcadas como **valiosas**;
3. Entregas do tipo **Eletrônicos** devem indicar se possuem **seguro**;
4. Entregas do tipo **Combustível** devem ser marcadas como **perigosas**;
5. Um caminhão pode fazer no máximo **4 entregas por mês**;
6. Um motorista pode fazer no máximo **2 entregas por mês**;
7. Entregas para o **Nordeste** têm uma taxa adicional de **20%**;
8. Entregas para a **Argentina** têm uma taxa adicional de **40%**;
9. Entregas para a **Amazônia** têm uma taxa adicional de **30%**;
10. Um motorista só pode realizar **uma entrega para o Nordeste** por mês.

---

## 🛠️ Tecnologias Utilizadas

- Java 22
- Spring Boot
- Spring Data JPA
- Hibernate
- PostgreSQL
- Maven
- Lombok
- JPA Specifications (consulta dinâmica)
- Postman
- Swagger (Springdoc OpenAPI) 

## 🚧 Funcionalidades Implementadas

- ✅ CRUD completo para:
    - Caminhões
    - Motoristas
    - Entregas

- ✅ Relatórios:
    - Total de caminhões na frota
    - Entregas realizadas por período
    - Totais por dia
    - Entregas por motorista
    - Entregas por caminhão

- ✅ Documentação com Swagger:
    - Acessível em: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

- ✅ Tratamento de erros com **Custom Exceptions**:
    - Retorno em JSON com `message` e `statusCode`

- ✅ Estrutura seguindo princípios **SOLID**
- ✅ Uso de **DTOs** e **Records** para respostas otimizadas
- ✅ Uso de **Enums** para tipo de carga e região (mais escalável)

---

## conexão com o banco
- //localhost:5432/transportadora

- username=postgres

- password=postgres


