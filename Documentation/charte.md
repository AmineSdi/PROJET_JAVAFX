# Charte de l'équipe 3

## Répartition des tâches
À établir lorsqu'on connaîtra mieux le projet.

## Méthode de travail que vous prévoyez utiliser

### STYLE

#### Langage
- Le noms des variables, classes, méthodes et constantes seront en anglais.
- La documentation, comme l'entête des fonctions, sera en anglais. 
- Les rapports et les commits seront écrits en français.

#### Typographie
- camelCase: noms de variables, noms de méthodes
- PascalCase: noms de classes
- SNAKE_CASE: noms des constantes

#### Noms des méthodes
- Le noms de toutes les méthodes commencent par un verbe.
- Si la méthode retourne un booléen, elle doit commencer par ''is'' (ex. isPatient).
- Sinon, la méthode commence par un verbe à l'infinitif (ex. createConnection)

#### Indentation
- Utilisation de 4 espaces au lieu du tab (nous allons modifier le comportement de nos IDEs)
- L'accolade ouvrante est mis à la fin de la ligne où commence un bloc concerné.
- L'accolade fermante est mis sur une autre ligne.
- L'indentation est faite au niveau de l'instruction qui a entrainé l'ouverture du bloc.
- Pour les méthodes à corps vide, l'accolade fermante suis immédiatement l'accolade ouvrante. 
- Si un bloc contient seulement une instruction, il n'y aura pas d'accolades. 

#### Taille maximale d'une ligne
- Chaque ligne aura une longueur maximale de 100 caractères.

### BONNES PRATIQUES

#### Comportements des fonctions
- Une méthode doit faire une chose.
- Une méthode fait un maximum de 25 lignes. Si une méthode dépasse 25 lignes, ça doit être justifié, sinon elle devra être ré-usinée.

#### Bonnes pratiques des commits
- La ligne de sujet ne doit pas dépasser 50 caractères.
- La ligne de sujet doit commencer par un verbe à l'indicatif, au présent et à la 3ième personne du singulier.
- Si on veut juste un court message, on utilisera git commit -m ""
- Si on veut ajouter plus de détails, on utilisera git commit en séparant la ligne de sujet des explications à l'aide d'un saut de ligne
- Si on fait du peer coding, on essaye d'ajouter (peer-coding) dans la ligne de sujet. Sinon, il faut l'écrire dans le corps du commit (si par git commit sans -m).

#### Gestion des branches
- Les noms des branches représentent les fonctionnalités qu'elles ajoutent et/ou implémentent.
- Un code est considéré approuvé s'il a été lu et revu par un autre membre.
- Une branche peut être "merged" si le merge est approuvé par un autre membre.

#### Méthode de travail
- Notre méthode sera inspirée des méthodes Scrum et Agile.
- Rencontres régulières avec explications du travail réalisé. 
- Prioriser en premier ce qui ajoute le plus de valeur au projet.  


## Méthode de résolution de conflit

### Règles générales et sanctions
- Si un membre de l'équipe de remplit pas ses engagements, à la première remarque un simple rappel sera fait par le chef d'équipe.
- Si le membre ne remplit toujours pas ses engagements, un avertissement formel sera émis. 
- Si le problème persiste, la personne sera rencontrée par l'équipe. 
- S'il n'y a toujours pas d'amélioration, l'enseignant ou le correcteur sera rencontré. 
- S'il n'y a pas d'amélioration, le membre de l'équipe sera expulsé de l'équipe. 
- Si le chef d'équipe est la personne en litige, les autres membres décideront de la personne qui les représentera pour les avertissements.

### Définition d'un engagement
- Une tâche ou une fonctionnalité devant être remise avant une date prévue. La date du commit sera utilisée pour vérifier si l'engagement a été respectée ou non. 

## Définition de "Terminé"
- Une méthode est considérée "terminée" lorsqu'elle respecte les règles de bonnes pratiques citées ci-haut, a des tests unitaires qui fonctionnent pour les cas critiques, a été approuvée par un membre de l'équipe et a été ajouté au dépôt Git.
- Une classe est considérée "terminée" lorsqu'elle respecte les règles de bonnes pratiques pour les classes et ses méthodes sont "terminées".
* Une fonctionalitée est "terminée" lorsqu'elle permet à l'utilisateur d'accomplir la tâche désirée. De plus, la branche dans laquelle elle se trouve devra avoir été mergée dans la branche main. 
- Le projet est considéré "terminé" lorsque toutes les fonctionnalités sont terminées, la documentation est terminée, et les diagrammes ont été approuvées par les 4 membres.
