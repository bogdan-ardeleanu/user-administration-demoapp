# user-administration-demoapp
A webapplication demo application that manages customer accounts which are kept in a database

# Descriere (RO)
Aplicatie web care sa se bazeze pe design pattern-ul MVC (Model View Controller).


Interfata grafica este alcatuita din 3 pagini:

1. Selectie ID client
In prima pagina userul poate introduce un ID de client si un buton de submit. Cand se da click pe submit utilizatorului i se afiseaza a doua pagina.

2. Vizualizare conturi
A doua pagina contine numele si conturile clientului ce are ID-ul introdus in prima pagina. Din aceasta pagina se poate selecta un singur cont, ale carui detalii vor fi afisate userului in a treia pagina.

3. Operatiuni bancare
A treia pagina contine numarul si soldul contului selectat in pagina precedenta. Aici userul poate introduce o suma si poate selecta o actiune: retragere sau alimentare. Userului i se reincarca pagina (a treia) in care se poate vizualiza noul sold al contului.

Alte interactiuni:
- Din pagina 2 (Vizualizare conturi) se poate ajunge in pagina 1 (Selectie ID client).
- Din pagina 3 (Operatiuni bancare) se poate ajunge in pagina 2 (Vizualizare conturi).

Informatii utilie:
1. Numerele de cont sunt unice la nivelul intregii baze de date (nu pot exista 2 numere de cont identice pentru acelasi id client, si nici la clienti diferitii).
2. Soldurile conturilor sunt numere intregi si vor fi afisate formatate (cifrele grupate cate 3 si despartite prin '.')
3. ID-urile de client sunt unice si numerice

Validari:
1. Prima pagina:
a. Campul ID client: Validare ca inputul introdus sa fie numeric - daca nu se reincarca pagina cu mesajul de eroare: "ID-ul trebuie sa fie numeric"
b. Campul ID client: Daca nu exista un user cu acel id se reincarca pagina cu mesajul de eroare: "ID client inexistent"
2. Pagina 3:
a. Campul suma: Validare ca inputul introdus sa fie un numar intreg pozitiv - daca nu se reincarca pagina cu mesajul de eroare: "Suma trebuie sa fie un numar intreg pozitiv"
b. Daca se alege Retragere si suma introdusa este mai mare decat soldul contului se reincarca pagina cu mesajul de eroare: "Sold insuficient"

#Requirements
- JDK 1.7+
- Maven 3+

#Tehnology stack
- Spring4 (mvc, security, orm)
- JPA (hibernate4)
- Database (H2, filesystem)
- JSP, JSTL
- AngularJS 1.4
