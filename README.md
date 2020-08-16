# Desafio java - Banco Inter

Definimos um dígito único de um inteiro usando as seguintes
regras:
Dado um inteiro, precisamos encontrar o dígito único do inteiro. 
Se x tem apenas um dígito, então o seu dígito único é x.
Caso contrário, o dígito único de x é igual ao dígito único da soma dos dígitos de x

**CRUD Usuário**

- Deverá ser criado um CRUD para usuários.
- Um usuário possui nome, email e uma lista de resultados de digitoUnicos já calculados.
- Cada objeto da lista de resultados deverá conter quais os parâmetros de entrada do cálculo e qual o resultado

**Criptografia**

- As informações do usuário nome e email devem ser criptografadas com uma chave assimétrica(RSA) de tamanho 2048.
- Cada usuário poderá possuir uma chave distinta para criptografia.
- As informações serão criptografadas com a chave pública do cliente e este irá descriptografar utilizando a sua chave privada.

**APIS**
- Deverá ser disponibilizado endpoints para o CRUD de usuários.
- Deverá ser disponibilizado um endpoint para cálculo do dígito, este cálculo pode ser associado de forma opcional a um usuário.
- Deverá ser criado um endpoint que recupera todos os cálculos para um determinado usuário.
- Deverá ser criado um endpoint para enviar a chave pública do usuário que será utilizada para a criptografia. Esta API deverá receber uma string que conterá a chave.

**Como rodar a aplicação**
Entre no diretório onde está aplicação: 
`mvn install`
`mvn spring-boot:run`

**Como rodar os testes**
Entre no diretório onde está aplicação: 
`mvn test`

**Acessar Swagger**
http://localhost:8080//swagger-ui.html
