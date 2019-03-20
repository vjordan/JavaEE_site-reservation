Informations pour mettre en place l'application

Jspf  :

	Il existe des erreurs selon eclipse dans les JSP mais ce n'est pas a prendre en compte
	et ne pose pas de probleme pour faire fonctionner l'application


Base de données : 

	Nous utilisons une base H2.
	Il suffit de créer une base avec ce nom : jdbc:h2:tcp://localhost/~/asso
	Ensuite dans H2 console inscrivez et executer cette commande pour importer le SQL : RUNSCRIPT FROM 'ChemimDuFicher\save.sql'
	
	Dans le cas où vous souhaitez utiliser un autre type de base de données il faut modifier le fichier persistence.xml 
	et importer le sql qui se trouve dans src/main/resources/META-INF/save.sql

Il existe un utilisateur dans la base de données qui est admin et qui a le droit supplémentaire de pourvoir ajouter un produit dans la base de données.

Il a pour identifiant : admin@mail.fr
 	  et mot de passe : admin1234
 	  
Contact en cas de problème : david.martin.jall@gmail.com
