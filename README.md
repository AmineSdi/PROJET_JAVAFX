# inf5153-ete2022-projet-equipe3  

## Introduction  

Ceci est une application Maven/JavaFX qui permet à un médecin de modifier le dossier médical de patients vus lors de rendez-vous médicaux. Les fonctionnalités de cette application sont décrites dans ce document.   

À la racine du projet, il y a aussi une courte vidéo (`INF5153_Équipe3.mp4`) de disponible contenant une démonstration de l'application en question.  

Le dossier `Conception` contient les diagrammes pour les patrons de conception implémentés. Le fichier `partie2.md` à la racine du projet contient une description de ces patrons. 


## Spécifications  

Afin de compiler et d'exécuter ce logiciel, il faut avoir une machine avec `JUnit5`, `Maven` (version 3.x), et `Java` (version 11 ou supérieure) d'installés. 

Cette application utilise la base de données `SQLite3` pour le stockage des informations concernant les dossiers des patients et les médecins dans le système. Les informations se trouvent dans la base de données `MedicalSystem.db` qui se trouve à la racine du projet. Cette base de données est déjà populée de données pour les médecins et les patients. Le script SQLite pour la création des tables et des données de tests se trouve dans le fichier `script.sql`, aussi à la racine du projet.    


## Compilation et exécution

Afin de faire compiler le projet, il faut ouvrir un terminal et utiliser la commande `mvn clean package` pour compiler le projet et exécuter les tests unitaires.  

Par la suite, la commande `mvn exec:java` permettra d'exécuter l'application.  

Cette dernière commande ouvrira une fenêtre de connexion pour le médecin. 

### Page de connexion

Afin de se connecter au compte d'un médecin, il faut utiliser les informations qui se trouvent dans la base de données. Pour facilité d'usage, voici 3 comptes de médecin (nom d'utilisateur et mot de passe) :  

| Utilisateur | Mot de passe |
| --- | --- |
| houseMD | aaa |
| wilsonMD | aaa |
| coxMD | aaa |

### Page de recherche de patients par code RAMQ

Une fois le médecin connecté, une page de recherche de patient par code RAMQ s'ouvrira. Pour facilité d'usage, coici des codes RAMQ qui existent déjà dans la base de données : 

| Code RAMQ | 
| --- |
| ALLA60050501 |
| BAKB68120102 |
| CHAC70110503 | 

### Page du dossier du patient

Cette page affiche les informations contenues dans le dossier du patient. 
On peut voir les informations suivantes :   

- Ses coordonnées et informations de contact
- Les informations sur ses visites médicales
- Les informations sur ses antécédents médicaux (*Medical History*)

À partir de cette page il est possible d'effectuer les actions suivantes :  

-Ajouter une visite médicale (bouton `Add Medical Visit`)  
-Ajouter un antécédent médical (bouton `Add Medical History`)  
-Modifier la date de fin d'un antécédent médical si elle n'existe pas déjà (bouton `Update History`)  
-Soumettre les changements, c'est-à-dire d'écrire les informations de la visite et/ou de l'antécédent dans la base de données (bouton `Submit`). Après cette opération, il est possible de remplir une autre visite ou antécédent pour le même patient.   
-Sortir du dossier du patient (bouton `Back`).

### Page d'ajout d'une visite médicale  

Afin d'ajouter une visite médicale, il faut remplir les champs `Diagnosis`, `Treatment`, `Notes` et `Summary`. 

En cliquant sur le bouton `Clear`, il est possible d'annuler les changements apportés dans les champs. Ce bouton reviendra aussi à la page du dossier du patient. 

En cliquant sur le bouton `Save`, il est possible de sauvegarder localement la visite médicale du patient. Tant que le bouton `Submit` de la page du dossier du patient n'est pas cliqué, aucun changement ne sera sauvegardé dans la base de données.  

Il est à noter que l'utilisateur n'a pas besoin d'entrer la date de la visite médicale, car la date de visite est automatiquement mise à la date actuelle.  

### Page d'ajout d'un antécédent médical  

Afin d'ajouter un antécédent médical, il faut remplir les champs `Start date`, `Diagnosis`, `Treatment` et optionnellement `End date`.  

Le champ `End date` est optionnel car on ne connaît pas nécessairement la date de fin pour une maladie chronique. Alors il est possible de laisser ce champ vide et de le mettre à jour à un autre moment. 

En cliquant sur le bouton `Clear`, il est possible d'annuler les changements apportés dans les champs. Ce bouton reviendra aussi à la page du dossier du patient.  

En cliquant sur le bouton `Save`, il est possible de sauvegarder localement l'antécédent médical du patient. Tant que le bouton `Submit` de la page du dossier du patient n'est pas cliqué, aucun changement ne sera sauvegardé dans la base de données.

### Page de modification de la date de fin d'un antécédent médical  

Tel que mentionné ci-haut, il est possible de modifier une date de fin d'un antécédent médical si elle n'existe pas déjà.  Cette page contient simplement un menu déroulant contenant les antécédents sans date de fin. Il suffit de lui attribuer une date en utilisant le calendrier `End date`. Une fois le bouton `Update` cliqué, les changements sont tout de suite sauvegardés dans la base de données. Le bouton `Cancel` permet de revenir à la page du dossier du patient sans sauvegarder de changement.   

