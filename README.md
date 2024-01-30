## Resumo do projeto
- O Objetivo deste desafio foi desenvolver uma aplicação web de streaming de videos utilizando as tecnologias Spring Framework, Spring WebFlux, Spring Boot e Spring Data. A aplicação deve permitir o gerenciamento e a exibição de videos.

## 🔨 Funcionalidades do projeto

- `Funcionalidade 1` <br><br>
`API de cadastro de usuario`: A API tem como objetivo permitir o gerenciamento de informações sobre os usuarios da plataforma de streaming.
#### 
- `Funcionalidade 2` <br><br> 
`API de gestão dos videos`: A API tem como objetivo permitir o gerenciamento de informações sobre os vídeos. 

## 🛠️ Arquitetura utilizada
![image](https://github.com/lucasr-conceicao/monitoramento-consumo-energia/assets/64719344/962b3549-c2de-47b8-89da-b09065d59ef6) <br>
O código foi desenvolvido utilizando o clean arch (A ideia de utilizar o clean foi desenvolver o projeto na estrutura que utilizo no dia a dia no trabalho). <br>
TODAS as APIs criadas se encontram na camada VERDE. Todas as requisições feitas pelo insomnia representam a camada AZUL. Quando nosso controller é chamada para cadastrar uma pessoa por exemplo, chamamos o Usecase na camada VERMELHA (camada esse que fica a regra de negócio.) para salvar no banco de dados, fazemos o caminho inverso atráves comunicando os pacotes através de interfaces.

## 🛠️ Exemplo Json/Rotas de cada API

###  API Usuarios

- `(POST) API de cadastro de usuarios`: http://localhost:9080/usuarios/cadastrar
```JSON
{
	"nome": "lucas",
	"email": "teste@email",
	"telefone": "1196666-77777"
}
```
exemplo de entrada e saída: <br>
![image](https://github.com/lucasr-conceicao/streaming-videos/assets/64719344/597f31e1-b915-4779-b8d0-5f0a8a97f263)

- `(PUT) API de atualizar usuario`: http://localhost:9080/videos/d0a510c4-fda1-4fdb-96d3-8f021125802c
```JSON
{
	"nome": "lucas Rocha",
	"email": "lucas@email.com",
	"telefone": "1196666-77777"
}
```
exemplo de entrada e saída: <br>
![image](https://github.com/lucasr-conceicao/streaming-videos/assets/64719344/056aa2b6-e20b-46dc-80c4-0b016c44a85f)

- `(GET) API de buscar usuario`: http://localhost:9080/usuarios/d0a510c4-fda1-4fdb-96d3-8f021125802c
  
exemplo de saída: <br>
![image](https://github.com/lucasr-conceicao/streaming-videos/assets/64719344/8d7acc5f-cea6-489b-9d63-d772723c6b97)
   <br>
   <br>
- `(DELETE) API de deletar usuario`: http://localhost:9080/usuarios/d0a510c4-fda1-4fdb-96d3-8f021125802c
  
exemplo de saída: <br>
![image](https://github.com/lucasr-conceicao/streaming-videos/assets/64719344/186ff3c3-8574-44ed-b7a5-518a2adb5603)

### API Videos
- `(POST) API de cadastrar video`: http://localhost:9080/videos/cadastrar
```JSON
{
	"titulo": "O estudante em apuros",
	"descricao": "Estudante tenta no ultimo semestre passar na faculdade",
	"url": "########",
	"favoritar": "N",
	"usuario": "9d623a26-115d-4cf8-bdf1-6f8e94db8363"
}
```
exemplo de entrada e saída: <br>
![image](https://github.com/lucasr-conceicao/streaming-videos/assets/64719344/97bd97c0-55a7-4d2d-bf4f-870c77978006)


- `(PUT) API de atualizar video`: http://localhost:9080/videos/d2275e42-85c5-4c22-a594-efb91ee0dd7b
```JSON
{
	"titulo": "O estudante em apuros",
	"descricao": "Estudante tenta no ultimo semestre passar na faculdade",
	"url": "########",
	"favoritar": "S",
	"usuario": "9d623a26-115d-4cf8-bdf1-6f8e94db8363"
}
```
exemplo de entrada e saída: <br>
![image](https://github.com/lucasr-conceicao/streaming-videos/assets/64719344/1235e920-4e2e-4b40-9121-befe06451e26)

- `(GET) API de buscar video`: http://localhost:9080/videos/d2275e42-85c5-4c22-a594-efb91ee0dd7b

exemplo de saída: <br>
![image](https://github.com/lucasr-conceicao/streaming-videos/assets/64719344/83d89609-9f36-446d-9e16-f0aeb77aa897)
  <br>
  <br>
  
- `(DELETE) API de deletar video`: http://localhost:9080/videos/d2275e42-85c5-4c22-a594-efb91ee0dd7b

exemplo de saída: <br>
![image](https://github.com/lucasr-conceicao/streaming-videos/assets/64719344/bc549b48-205c-431a-a0f1-7f1963e3650b)

<br>

#### Exemplo do registro no banco de dados na tabela usuário
![image](https://github.com/lucasr-conceicao/streaming-videos/assets/64719344/c274b4c3-0356-46cb-b7d9-98ba078ceabb)

#### Exemplo de registro no banco de dados na tabela video
![image](https://github.com/lucasr-conceicao/streaming-videos/assets/64719344/d66942eb-16b6-40b3-add3-25057eebebec)
