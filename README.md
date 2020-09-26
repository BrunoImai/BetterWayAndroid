BetterWay

Introdução

Analisando inúmeros problemas que poderiam ocasionar complicações no trânsito e assim, iniciamos o desenvolvimento de um sistema que visa a facilidade da mobilidade dentro do trânsito urbano com base em dados vindos diretamente de nossos usuários, de forma manual (pelo site) ou de forma automática (pelo aplicativo), para assim reduzir a quantidade de casualidades mecânicas que nossos clientes poderiam vir a ter além de evitar percalços mais leves como a perda de tempo útil.

Aplicativo

O Aplicativo do BetterWay foi criado visando a portabilidade e a acessibilidade do produto ao público pois como o smartphone é extremamente popular no dia de hoje ele acaba englobando um público imenso e com isso traz outras vantagens consigo pois acaba concedendo algumas ferramentas praticas, como um acesso rápido e automático ao GPS e a acessibilidade aos acelerômetros próprios do smartphone.

Mapa e gelocalização

A principal função do aplicativo é fazer reportes para o sistema de forma rápida e eficiente, e um reporte é feito utilizando principalmente a posição do usuário no mapa, e para conseguir acesso a essa informação foi integrado ao aplicativo a API de geolocalização do Google Maps (Criado pelo Google), e com a integração dela foi possível conseguir não só a posição do usuário, as também serviu como uma interface principal da aplicação pois foi trabalhado para que a mesma fosse dinâmica e mostrasse o usuário e a região envolta dele além de apresentar as notas das ruas de forma simples e pratica.
  
Comunicação

Outra função com extrema importância é a comunicação entre multiplataformas (Arduino, servidor, aplicativo e site), pois todos os sistemas devem conversar com o mínimo delay possível entre si e para isso ser feito, utilizamos varias bibliotecas para transformar a comunicação eficiente, mas a utilizada para o aplicativo foi a biblioteca Retrofit (criada pelo grupo Square), essa biblioteca visa o consumo e processo de Web Service no Android, e utilizando ela foi possível fazer inúmeros processos práticos e praticamente em tempo real, como: Validação de usuário, envio e recebimento de dados, solicitação de serviços, controle e tratamento de erros de conexão, etc. Mas a sua principal função é a comunicação com o servidor passando dados e serviços utilizando a formatação padronizada JSON (JavaScript Object Notation) pois além de ser amplamente utilizada no mercado sua formatação é aceita em praticamente todos os sistemas. Como anteriormente dito o Retrofit foi extremamente estudado, explorado e utilizado pela equipe para resolver vários problemas e prevenir futuros.
  
Sensores

Uma ferramenta apresentada antes provinda do smartphone são os acelerômetros embutidos nele, que trazem funcionalidades adicionais que diferenciam o BetterWay de outros aplicativos, pois utilizando esses sensores podemos coletar dados de forma automática para o sistema, dados esses que serão utilizados para identificar colisões e problemas em vias pavimentadas e não pavimentadas durante o percurso do usuário, e isso é possível pois o acelerômetro concede dados como posição espacial (divididos em vetores X, Y e Z com relação ao chão utilizando a gravidade como indicador de direção) e aceleração (dada em metros por segundo), com esses dados a equipe conseguiu fazer com que o próprio smartphone realizasse o processo de filtro, a partir do momento que o acelerômetro indicasse uma aceleração de 5 m/s era reportado automaticamente para o servidor que salvava esse reporte no sistema utilizando a rua em que o usuário está como chave, para assim a rua ter sua nota reduzida, porém se a aceleração for maior que 100 m/s em um intervalo de 2 segundos é instantaneamente reportado para o sistema o acidente sem precisar de um usuário ativamente enviar o reporte para assim registrar e alertar em tempo real o impacto.
  
Conclusão.  
	Apresentando todos esses fatores fica claro que o BetterWay foi pensado para cobrir inúmeros problemas de outros aplicativos semelhantes utilizando uma gama de ferramentas e bibliotecas para contribuir para a melhor utilização possível do usuário e cliente, para poder assim auxiliar a todos no transito não somente poupando seu tempo mas também conseguindo informar problemas na infraestrutura da cidade e no auxilio de acidentes. 
	
