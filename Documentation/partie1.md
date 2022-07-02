# PAGE PRÉSENTATION
<p style="text-align: center;">Rapport de conception projet de session

<p style="text-align: center;">Présenté au professeur Gagnely Serge Dogny

<p style="text-align: center;">dans le cadre du cours INF5153 Génie Logiciel: Conception

<p style="text-align: center;"> par

<p style="text-align: center;">Steven Chia Ah-Lan - AHLS12109105 
<p style="text-align: center;">Alexandre Filion - FILA09128609 
<p style="text-align: center;">Mohand Amine Saïdi - TODO
<p style="text-align: center;">Nicolas Goulet - GOUN12109401
<p style="text-align: center;">Travail remis le dimanche 3 juillet 2022
<div style="page-break-after: always"></div>

# TABLE DES MATIÈRES
1. Page Présentation...........................................1
2. Table des matières..........................................2
3. Introduction................................................3
4. Diagramme de classes........................................4
5. Diagramme de cas d'utilisations
6. Les diagrammes de séquence

    6.1 Login

    6.2 ConsultPatientFile

    6.3 ModifyPatientFile

   6.4 ReconstructPatientFile

    6.5 modifyContactInfo
7. Diagramme de package
8. Diagramme de composantes
9. Diagramme de déploiement
<div style="page-break-after: always"></div>


# Introduction



# Diagramme de classes
EXPLICATION DIAGRAMME DE CLASSE TODO

![Diagramme de classes](../Models/pngs/class-Centralized_medical_management_system___Class_diagram.png)

## Justfications GRASP 
### PatientFile

#### Justification d'instanciation :
Nous utilisons le patron de *Spécialiste de l'information* pour justifier l'instanciation de la classe PatientFile. Cette classe a pour spécialité d'aller récupérer toutes les informations pertinentes et relatives au dossier d'un patient, notament les visites et antécédents médicaux de ce dernier. Nous évitons ainsi un gros BLOB.

#### Justification de la méthode public X
Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.

### Doctor

### PatientID

### ContactInformation

### MedicalHistory

### MedicalVisit

###Date

# Diagramme des cas d'utilisation 

# Diagrammes de séquences 

## Login