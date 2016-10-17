Livrabile:
----------

1. Un fisier readme in care sa argumentezi solutiile tehnice pe care le-ai folositsi care sa includa de asemenea instructiuni privind executia livrabilului
R: documentul curent

2. Fisierul .war ce contine aplicatia web (inclusiv codul sursa).
R: user-administration-demoapp.war
Source code: https://github.com/bogdan-ardeleanu/user-administration-demoapp

3. Fisierul ddl ce contine definitia bazei de date
R: sql/create-db.sql

4. Fisierul sql pentru incarcarea bazei de date.
R: sql/insert-data.sql


Argumentare solutii tehnice:
----------------------------

Stiva tehnologica conform cerintelor:
 * Spring4 (mvc, security, orm)
 * JPA (hibernate4)
 * Database (H2, filesystem)
 * JSP, JSTL
 * AngularJS 1.4, Bootstrap3
 
 Intrarea in aplicatie este conditionata de cunoasterea ID Client. Fara introducerea ID Client nu se pot accesa paginile aplicatiei. Introducere spring-security cu autentificare dupa ID Client.
 Pagina de login este decuplata de restul paginilor in sensul ca implementeaza submit la server. 
 Dupa autentificare utilizatorul acceseaza o aplicatie cu design SPA (Single Page Application) folosind framework-ul AngularJS.
 Pentru validarile structurale ale datelor transmise s-a folosit JSR303 validator.
  
 Layerele aplicatiei sunt:
* server-side
	- controller: include logica de navigare
	- service: incapsuleaza business-ul necesar si ofera operatii tranzactionale
	- repository: EntityManager & spring-data-repository
	- ORM: JPA
* client-side:
	- controller
	- service
	- view
	
	
Mod de rulare:
1. Jetty9 - maven plugin
Folosind sursele aplicatiei (git clone https://github.com/bogdan-ardeleanu/user-administration-demoapp.git) in root se ruleaza: run mvn jetty:run -Pjetty9
Se acceseaza aplicatia la url-ul: http://localhost:8080

2. Container (ex: Tomcat7)
Se ruleaza: mvn install
Se deployaza arhiva in server container: user-administration-demoapp.war
Se acceseaza aplicatia la url-ul: http://localhost:8080/user-administration-demoapp

ID Client de test: 12345678

 
 
