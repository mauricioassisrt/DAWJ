<h1> Enunciado do problema </h1>
<h4>
<b>EP2) Considerando as entidades Pessoa, Produto, Pedido e PedidoItem apresentadas abaixo, realiza a implementação de uma aplicação Spring Boot, contendo as seguintes funcionalidades: (1) operações CRUD para Pessoa e Produto, e (2) movimento de realizar pedido. Deve-se realizar a persistência de dados com Spring Data. Além disso, deve-se prover acesso às funcionalidades do sistema por meio de uma interface web (e.g., usando o Thymeleaf). O valor do produto está em Float, mas pode-se usar outra forma mais adequada para valores monetários, tal como o uso de BigDecimal.<br/>
</b>
</h4>
<h1>Passo a passo utilizado para implementar o projeto </h1>
<h4>
Criar o projeto atráves do site https://start.spring.io <br/>

escolher as seguintes dependencias para o MAVEN <br/>
<b>
Spring Web <br/>
MySQL Driver SQL<br/>
Thymeleaf <br/>
Spring Data JPA<br/>
Validation <br/>
Spring Session<br/>
Spring Boot DevTools <br/>
</b>
</hr>
após gerado o arquivo.zip descompactar o mesmo e importar o projeto <br/>
utilizar a IDE Spring Tool Suite 4 <br/>
Após isso clicar sobre o projeto ir na opção maven e clicar UPDATE PROJECT, este comando faz o download das dependencias para <br/>
a máquina <br/>
Agora vamos configurar o arquivo APPLICATION.PROPERTIES, neste arquivo contém informações sobre conexão de database etc <br/>
vamos utilizar as configurações abaixo<br/>
# PORTA ONDE VAI SER EXECUTADO O TOMCAT
server.port = 8090

spring.datasource.url=jdbc:mysql://localhost:3306/banco
spring.datasource.username=root
spring.datasource.password=   
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
spring.jpa.database-platform = org.hibernate.dialect.MySQL5Dialect
spring.jpa.generate-ddl=true
spring.jpa.hibernate.ddl-auto = update

Após isso, clicar no diretório src/main/java/ abrir o pacote minhaloja e dentro dele clicar com o botão direito do mouse e ir em<br/>
RUN AS -> JAVA APLICATION
</h4>
