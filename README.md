#  🇧🇷 Projeto Remédios 
Associação com o curso de Enfermagem para o desenvolvimento de uma aplicação web e mobile para listar, adicionar e editar medicamentos. Feito em equipe com [Estevão](https://github.com/estevaofrancisco867), [Victor](https://github.com/VictorAkiyama) e João Otávio no IFPR Londrina.

## links de acesso:
Acesso professor/teacher: http://localhost/enfermagemProjeto/arquivosPHP/paginaDosRemedios_AcessoProfessor.php

Acesso aluno/student: http://localhost/enfermagemProjeto/arquivosPHP/paginaDosRemedios_AcessoAluno.php

Cadastro/Sign up: http://localhost/enfermagemProjeto/frontend/cadastro.html

Mostrar pastas do projeto/Projects Folder: https://localhost/enfermagemProjeto/

Mostrar JSON da API: http://localhost:8080/remedios/api/remedios

**(apertar Enter ao adicionar o remédio preencherá quase tudo automaticamente)**

# Como importar o projeto no Eclipse
Após fazer o download do arquivo **remedios.zip** e extrair, abrir o Eclipse ir em: File > Import... > Maven > Existing Maven Projects > Browse. Encontre o arquivo **remedios** e o selecione. Deixe o **pom.xml** marcado e clique em Finish.

# Como abrir e executar o Banco de Dados
Abrir o **remedios_db.sql** no MySQL, descomentar a linha 5: `CREATE USER`... para criar o usuário `appuser@localhost` com uma senha simples apenas para o desenvolvimento.

Em seguida, execute o script para configurar o banco de dados (um usuário de teste será criado para realizar login na aplicação).

Observe que o usuário do banco de dados deve ser o mesmo definido no arquivo `persistence.xml` do projeto **remedios.zip** Caso você altere o usuário do banco, será necessário atualizá-lo tanto no `persistence.xml` quanto no **remedios_db.sql**.

# Como inserir valores usando o projeto
No Eclipse, vá nas pastas dentro do projeto **remedios:** Java Resources/src/main/java/com.projeto e, clicando com o botão direito no **TesteJPA.java**, vá em Run As > Java Aplication. Depois das INFOS e dos WARNINGS, deve aparecer no console a mensagem: `Salvo com relacionamento!`. Assim vão ser inseridos alguns valores nas tabelas REMEDIO e DESCRICAO.

# Como executar o servidor Apache Tomcat no Eclipse
Primeiro, certifique-se da aba **Servers** estar aparecendo no Eclipse, se não estiver, vá em Window > Show View > Other e pesquise Servers.
Depois clique em **No servers are available. ...**, Clique na pasta Apache e selecione **Tomcat v11.0 Server** instale caso não esteja ainda e selecione a pasta do Apache Tomcat. Clique em Next e no projeto **remedios** e o passe para o outro lado e depois Finish.

# Como executar o Servidor XAMP
No painel de controle do XAMP, com o servidor Apache rodando, clique em **Start** no módulo do Apache e verifique se fica verde. Se funcionar sem erros, clique no botão **Explorer** e vá na pasta htdocs e coloque o zip **enfermagemProjeto.zip** e descompacte

#  🇺🇸 Medicine Project 
Nursing course association for mobile and web app development for medicines and their descriptions listing, adding and edit. Made in team with [Estevao](https://github.com/estevaofrancisco867), [Victor](https://github.com/VictorAkiyama) and Joao Otavio in IFPR Londrina.

# How to Import the Project into Eclipse
After downloading the **remedios.zip** file and extracting it, open Eclipse and go to: File > Import... > Maven > Existing Maven Projects > Browse. Find the **remedios** folder and select it. Make sure **pom.xml** is checked, then click Finish.

# How to Open and Run the Database

Open **remedios_db.sql** in MySQL and uncomment line 5 (CREATE USER...) to create the user `appuser@localhost` with a simple password intended only for development.

Then execute the script to set up the database (a test user will be created for logging into the application).

Note that the database user must match the one defined in the `persistence.xml` file of the **remedios.zip** project. If you change the database user, you must also update it in both `persistence.xml` and **remedios_db.sql**.

# How to insert values using the project

In Eclipse, navigate through the project folders **remedios**: Java Resources/src/main/java/com.projeto and, by right-clicking on **TesteJPA.java**, go to Run As > Java Application. After the INFO and WARNING messages, the following message should appear in the console: `Salvo com relacionamento!`. This means that some values have been inserted into the REMEDIO and DESCRICAO tables.

# How to Run the Apache Tomcat Server
First, make sure the **Servers** tab is visible in Eclipse. If it is not, go to Window > Show View > Other and search for "Servers".  
Then click on **No servers are available. ...**, open the Apache folder, and select **Tomcat v11.0 Server**. Install it if it is not already installed, and select the Apache Tomcat folder.  
Click Next, select the **remedios** project and move it to the configured side, then click Finish.

# How to run the XAMP Server
In the XAMPP control panel, with the Apache server running, click **Start** in the Apache module and check if it turns green. If it runs without errors, click the **Explorer** button, go to the htdocs folder, place the **enfermagemProjeto.zip** file there, and extract it.



