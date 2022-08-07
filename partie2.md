# PAGE DE PRÉSENTATION
<br><br>
<br><br>
<br><br>
<br><br>
<p style="text-align: center;">Rapport de projet de session</p>

<p style="text-align: center;">Présenté au professeur Gagnely Serge Dogny</p>

<p style="text-align: center;">dans le cadre du cours INF5153 Génie Logiciel: Conception</p>
<br><br>
<br><br>
<br><br>
<br><br>
<p style="text-align: center;"> par
<br><br>
<br><br>
<br><br>
<br><br>
<p style="text-align: center;">Steven Chia Ah-Lan - AHLS12109105</p>
<p style="text-align: center;">Alexandre Filion - FILA09128609</p>
<p style="text-align: center;">Mohand Amine Saïdi - SAIM15029603</p>
<p style="text-align: center;">Nicolas Goulet - GOUN12109401</p>
<br><br>
<br><br>
<br><br>
<br><br>
<br><br>
<br><br>
<br><br>
<br><br>
<p style="text-align: center;">Travail remis le dimanche 7 août 2022
<div style="page-break-after: always"></div>


<br><br>
<br><br>
<br><br>
<br><br>
1. Différences entre l'implémentation et la conception
   <br><br> 
   Dans notre diagramme de classes de la partie 1 du projet, nous avons inclus une classe `HealthProfessional` qui était une classe abstraite englobant les différents professionels de la santé qui peuvent potentiellement utiliser ce système. Par contre, dans lors de l'implémentation, nous nous sommes rendus compte que ce niveau d'indirection supplémentaire (en plus de la classe abstraite `User`) n'était pas nécessaire et complexifiait l'implémentation, et que nous étions en train de prévoir une fonctionnalité future que nous n'aurions pas besoin d'implémenter. Afin de ne pas faire d'over-engineering, nous avons décidé de supprimer la classe `HealthProfessional` de notre implémentation. 

   <br><br>

   


2. Les patrons de conceptions
   <br><br>
    2.1 Singleton  
    Nous avons implémenté le Singleton pour la connexion à la base de données afin de sécuriser la connexion à la base de données. Nous avons effectivement jugé que lors du lancement de l'application, il seraient prudent d'avoir une seule instance de la connexion à la base de données. 
    
   <br><br> 

   2.2 Builder  
   Nous avons implémenté le patron `Builder` pour l'objet `PatientFile`, car nous avons considéré que le `PatientFile` est un objet complexe. Effectivement, afin de construire un `PatientFile`, il faut non seulement avoir les champs habituels comme le code du patient et son nom, mais aussi une collection d'objets `MedicalVisit` et `MedicalHistory`, ainsi qu'un objet `ContactInformation`. Vu que cela fait beaucoup d'objets à construire afin d'avoir un objet `PatientFile` complet, nous avons jugé qu'il est pertinent de déléguer la construction complexe du `PatientFile` à un objet `PatientFileBuilder`.  
   <br><br>
   2.3 Visitor  
   Nous avons implémenté le patron `Visitor` pour la création des objets `MedicalVisit` et `MedicalHistory` lorsque l'utilisateur du système (le médecin) veut ajouter ces informations dans le dossier du patient. Dans ce patron, le `Doctor` est l'objet visiteur et le `MedicalHistory` ou le `MedicalVisit` sont les objets visités. Ceci reflète la réalité du domaine d'affaires dans lequel le médecin est la personne qui écrit les informations des visites et des antécédents dans le dossier du patient.  
   <br><br>
   <br><br>


