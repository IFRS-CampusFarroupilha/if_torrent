<img src="http://web.farroupilha.ifrs.edu.br/paginas/~temp1/imagens/IFTorrent.png" width="600">

# IFTorrent - Compartilhando Arquivo e Ideias 
<img src="http://web.farroupilha.ifrs.edu.br/paginas/~temp1/imagens/versao.png" height="20"> [<img src="http://web.farroupilha.ifrs.edu.br/paginas/~temp1/imagens/wiki.png" height="20">](http://web.farroupilha.ifrs.edu.br/iftorrentwiki/index.php/P%C3%A1gina_principal) [<img src="http://web.farroupilha.ifrs.edu.br/paginas/~temp1/imagens/contato.png" height="20">](mailto:iftorrent@farroupilha.ifrs.edu.br)

O projeto IFTorrent tem por finalidade a criação de um cliente torrent para o compartilhamento de arquivos nos _campi_ da rede federal de institutos de educação, ciência e tecnologia.

A aplicação foi desenvolvida em linguagem *__Java__*, mas há a utilização de outras linguagens, como *__CSS__* e *__FXML__* para a estruturação da interface gráfica. Além disso, foi desenvolvido um site para _download_ de _arquivos torrents_, este em *__HTML__*, *__CSS__*, *__PHP__* e *__MySQL__*.

Para mais detalhes, acesse a nossa documentação [aqui](http://web.farroupilha.ifrs.edu.br/iftorrentwiki//docs/javadoc/javadoc/).

### Download 

Sinta-se livre para baixar o código-fonte da nossa aplicação no [link abaixo](https://github.com/IFRS-CampusFarroupilha/-if_torrent). *Obs: necessita da IDE _NetBeans_ e do _Java Development Kit_ instalados.

	https://github.com/IFRS-CampusFarroupilha/-if_torrent
	
Ou, se preferir, baixe apenas o cliente (sem instalação) no [seguinte link](http://link-para-download-do-cliente.com):

	http://web.farroupilha.ifrs.edu.br/paginas/~temp1/IFTorrent.rar

### Requisitos de Sistema

Requisito | Mínimo | Recomendado
------------ | ------------- | -------------
Sistema Operacional | Windows | Windows
Processador | Intel Core i7-5500U | Intel Core i9-7900x
Memória RAM | 2GB | 8GB
Espaço em Disco | 9MB | 9MB
Placa de Vídeo | nenhum | nenhum
Softwares adicionais | _Java Runtime Environment_ | _Java Runtime Environment_
Hardwares adicionais | nenhum | nenhum
 
### Como usar

Após o download do cliente, basta abrir a pasta e dar um duplo clique no arquivo __IFTorrent__ (arquivo com extensão _.jar_). Caso tenha optado pelo download do projeto, o arquivo __IFTorrent__ se encontrará dentro da pasta __dist__.

Em caso de algum erro ou dúvida, o cliente torrent possui a opção para contato no menu __"Ajuda"__.

### Desenvolvimento

O projeto foi desenvolvido principalmente em Java através da **IDE NetBeans**. Para a implementação da parte _Web_ não houve um editor fixo, permitindo que os integrantes da equipe escolhessem qual usar.

Foram usadas também algumas ferramentas para auxiliar o desenvolvimento do projeto:

* __*Wiki*__ (para a documentação do projeto);
* __*Bugzilla*__ (para a organização de bugs do projeto);
* __*Repositório SVN*__ (para o controle de versões do software e para organizar o desenvolvimento em equipe);
* __*Java Scene Builder*__ (para a construção da interface gráfica do cliente);
* __*Balsamiq Mockups 3*__ (para a prototipação do cliente);
* __*Google Drive*__ (para a organização de documentos gerados durante o projeto);
* __*Google Planilhas*__ (para a organização das tarefas do projeto).

Além disso, foram usadas algumas bibliotecas para dispensar a necessidade de desenvolver recursos desejáveis:

* _**bt**_ - biblioteca que implementa o protocolo BitTorrent desenvolvida pelo __Andrei Tomashpolskiy__;
* _**Commons Email**_ - biblioteca para envio de e-mails desenvolvida pela __Apache__ e que, por sua vez, necessita das bibliotecas __*mail*__ e __*activation*__, ambas desenvolvidas pela __Oracle__;
* _**Bouncy Castle**_ - biblioteca para criptografia desenvolvida pela __The Legion of the Bouncy Castle__;
* _**MySQL Connector/J**_ - biblioteca para realizar a conexão com o banco de dados desenvolvida pela __MySQL__.

A equipe optou pela __Metodologia Ágil__ como forma de desenvolvimento do cliente por ser mais eficiente no quesito adaptação. 

### Possíveis implementações

- [ ] Permitir as ações _pausar_ e _reiniciar_ no download de um arquivo;
- [ ] Permitir a alteração da estrutura da interface gráfica (como ocultar alguns componentes);
- [ ] Melhorar o gráfico referente as velocidades de _download_ e _upload_;
- [ ] Tornar o cliente compatível com o sistema Linux.

### Sobre o cliente

Para facilitar, no próprio cliente é disponibilizado um pequeno tutorial de uso do programa. Para acessá-lo, basta clicar na opção __"Ajuda"__ no menu superior e após selecionar a opção __"Fazer um Tour"__. Como maneira alternativa, também é possível iniciar o _tour_ através do conjunto de teclas _**"Ctrl + F1"**_.

<img src="http://web.farroupilha.ifrs.edu.br/paginas/~temp1/imagens/cliente.png" width="700">

_Interface do cliente Torrent_

### Sobre o site

O site para _download_  de _arquivos torrents_ se encontra no [link abaixo](http://web.farroupilha.ifrs.edu.br/paginas/~iftorrent/):

	http://web.farroupilha.ifrs.edu.br/paginas/~iftorrent/

<img src="http://web.farroupilha.ifrs.edu.br/paginas/~temp1/imagens/site.png" width="700">

_Interface do site_

### Licença

O nosso cliente torrent é distribuído segundo a licença  [**The MIT License**](https://mit-license.org/) (Licença MIT). Abaixo segue a tradução literal da licença:

	Copyright © 2018 <detentores de direitos autorais>
	
	A permissão é concedida gratuitamente a qualquer pessoa que obtenha uma cópia deste 
	software e arquivos de documentação associados (o “Software”), para lidar com o 
	Software sem restrições, incluindo, sem limitação, os direitos de uso, cópia, 
	modificação, fusão, publicação, distribuição, sublicenciar e/ou vender cópias do 
	Software, e permitir que pessoas a quem o Software esteja fornecido para tal, 
	sujeito às seguintes condições:
	
	O aviso de copyright acima e este aviso de permissão devem ser incluídos em todas 
	as cópias ou partes substanciais do Software.
	
	O SOFTWARE É FORNECIDO “COMO ESTÁ”, SEM GARANTIA DE QUALQUER TIPO, EXPRESSA OU 
	IMPLÍCITA, INCLUINDO NÃO LIMITADA ÀS GARANTIAS DE COMERCIALIZAÇÃO, ADEQUAÇÃO A UM 
	FIM ESPECÍFICO E NÃO INFRAÇÃO. EM NENHUMA CIRCUNSTÂNCIA, OS AUTORES OU OS TITULARES 
	DOS DIREITOS AUTORAIS SERÃO RESPONSÁVEIS POR QUALQUER RECLAMAÇÃO, DANOS OU OUTRAS 
	RESPONSABILIDADES, SEJA EM AÇÃO DE CONTRATO, DELITO OU OUTRO, DECORRENTE DE, FORA OU 
	RELACIONADA COM O SOFTWARE OU O USO OU OUTRAS CONCESSÕES NO SOFTWARE.
	
Para acessar o conteúdo original da licença (em inglês) acesse o [seguinte link](https://mit-license.org/):
	
	https://mit-license.org/

### Equipe

A nossa equipe é composta, atualmente, por nove integrantes: 

* __*[Rafael Vieira Coelho](https://github.com/rafael-vieira-coelho)*__ (professor orientador do projeto);
* __*Gustavo Rodrigo Tausendfreund*__ (profissional de T.I.);
* __*Eduardo Balbinot*__ (profissional de T.I.);
* __*[Eduardo Toffolo](https://github.com/EToffolo)*__ (aluno voluntário);
* __*Leonardo Bortolini*__ (aluno voluntário);
* __*[Gabriel Muller](https://github.com/Gabriel2501)*__ (aluno voluntário);
* __*[Garrenlus de Souza](https://github.com/GarrenSouza)*__ (aluno voluntário);
* __*[Guilherme Giordani](https://github.com/GuilhermeDaBolsa)*__ (aluno voluntário);
* __*[Otávio Farinon](https://github.com/ovfarinon)*__ (aluno voluntário).

Há alguns membros que não integram mais a equipe, mas que merecem créditos:

* __*Sérgio Brunetta*__ (aluno voluntário);
* __*Cléber Masieski*__ (aluno voluntário);

### Contate-nos

Para qualquer problema que nosso cliente ou nosso site possa vir a gerar, pedimos que nos avise. Ademais, estamos abertos para novas ideias e impressões sobre o programa. Seu _feedback_ é muito importante!

E-mail: [iftorrent@farroupilha.ifrs.edu.br](mailto:iftorrent@farroupilha.ifrs.edu.br)

<img src="http://web.farroupilha.ifrs.edu.br/paginas/~temp1/imagens/ifrs.png" width="1000">
