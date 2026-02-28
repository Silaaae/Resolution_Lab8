# Resolution_Lab8
Partie 1 — Création de la base de données MySQL
1.1 Démarrage de l'environnement
Pour commencer, nous avons lancé le panneau de contrôle XAMPP et activé les deux services nécessaires : Apache (serveur web) et MySQL (serveur de base de données). Une fois les deux services en état "Running", nous avons accédé à l'interface d'administration via le navigateur à l'adresse http://localhost/phpmyadmin.
1.2 Création de la base de données
<img width="1851" height="371" alt="image" src="https://github.com/user-attachments/assets/423256f8-2f68-4d5e-bb12-4b06050cedc0" />

Nous avons créé une base de données nommée school1 qui servira à stocker toutes les données relatives aux étudiants. Cette base constitue le socle de notre application web service.
<img width="1918" height="666" alt="image" src="https://github.com/user-attachments/assets/5a388393-81eb-41e5-8c8e-1f9504bb0c74" />
Après exécution des requêtes SQL, la table Etudiant est visible dans phpMyAdmin avec les deux enregistrements correctement insérés. La base de données est maintenant prête à être utilisée par le web service PHP développé en Partie 2.
Voici le rapport complet pour les Parties 2 et 3 :

Partie 2 — Développement du Web Service PHP 8
2.1 Structure du projet
Nous avons créé un projet PHP dans le répertoire C:\Xamp\htdocs\Projet_Lab5\ avec l'arborescence suivante :
Projet_Lab5/
    classes/        → Contient la classe métier Etudiant
    connexion/      → Contient la classe de connexion PDO
    dao/            → Contient l'interface IDao
    service/        → Contient la logique métier EtudiantService
    ws/             → Contient les web services PHP exposés
Cette organisation en couches permet une séparation claire des responsabilités et facilite la maintenance du code.
<img width="1917" height="720" alt="image" src="https://github.com/user-attachments/assets/e5d73afa-6be3-4d70-849a-d3cfa0afc6d6" />


<img width="1065" height="717" alt="image" src="https://github.com/user-attachments/assets/20ddb695-52e2-43ed-a6f4-d2d680999c46" />

<img width="1915" height="651" alt="image" src="https://github.com/user-attachments/assets/767ace95-c170-4447-8a8e-2b8831a77542" />

<img width="1917" height="367" alt="image" src="https://github.com/user-attachments/assets/c0fccc17-8c47-42b2-b5aa-92dd82325363" />

<img width="1550" height="950" alt="image" src="https://github.com/user-attachments/assets/983a6044-b51e-43ba-b233-d46e1d1f3430" />


<img width="1557" height="593" alt="image" src="https://github.com/user-attachments/assets/ec4dfa07-d046-4558-8eed-dc05ca3ddcf1" />

2.6 Web Services PHP
Deux web services ont été développés :
ws/createEtudiant.php : accepte une requête HTTP POST contenant les données d'un étudiant, les insère en base via EtudiantService, puis retourne la liste complète des étudiants au format JSON.
ws/loadEtudiant.php : accepte une requête HTTP GET et retourne directement la liste complète des étudiants au format JSON sans aucune modification des données.
2.7 Test avec Advanced REST Client
Les web services ont été testés avec l'extension Advanced REST Client dans Google Chrome avant leur intégration dans l'application Android.
Test du service createEtudiant (POST) :
<img width="1917" height="955" alt="image" src="https://github.com/user-attachments/assets/792b1176-ee08-47ee-9e20-65f7d93fe428" />

URL : http://localhost/Projet_Lab5/ws/createEtudiant.php
Méthode : POST
Content-Type : application/x-www-form-urlencoded
Body : nom=Dupont&prenom=Sara&ville=Casablanca&sexe=femme
Résultat : réponse JSON avec la liste mise à jour, statut 200 OK

<img width="1885" height="888" alt="image" src="https://github.com/user-attachments/assets/31e9a21f-4a3d-4f39-995b-f72442e7b1c2" />


Partie 3 — Application Android (Volley + Gson)
3.1 Création du projet Android
Nous avons créé un nouveau projet Android Studio de type Empty Views Activity nommé projetws, en choisissant Java comme langage de programmation et API 26 comme niveau minimum. Ce type d'activité permet de travailler avec des fichiers XML de layout, contrairement à Empty Activity qui utilise Jetpack Compose.
3.2 Permission Internet
La permission Internet a été ajoutée dans AndroidManifest.x


<img width="1917" height="982" alt="image" src="https://github.com/user-attachments/assets/c6b6b775-5f37-4f1d-b959-05f3176218bb" />



etape 4.8: 
<img width="307" height="643" alt="image" src="https://github.com/user-attachments/assets/98037f62-0f2a-4659-86cb-a373eec65526" />

<img width="952" height="995" alt="image" src="https://github.com/user-attachments/assets/27d58030-0a98-4fbd-a5f7-2a5964c05fb6" />

Conclusion
Ce laboratoire nous a permis de mettre en place une architecture complète de type Client-Serveur en intégrant trois technologies complémentaires.
Côté serveur, nous avons développé un Web Service RESTful en PHP 8 structuré en couches (connexion, modèle, DAO, service) communiquant avec une base de données MySQL via PDO. Les services exposent des endpoints HTTP retournant des données au format JSON.
Côté client, nous avons développé une application Android en Java utilisant la bibliothèque Volley pour les requêtes HTTP asynchrones et Gson pour la désérialisation des réponses JSON en objets Java.
Les fonctionnalités suivantes ont été implémentées et testées avec succès :

Ajout d'un étudiant via formulaire Android → insertion en base MySQL
Chargement et affichage de la liste des étudiants
Modification du nom d'un étudiant
Suppression avec confirmation


<img width="1881" height="671" alt="image" src="https://github.com/user-attachments/assets/2cf5a8d9-7ed9-4987-a368-4161428d1ebf" />
<img width="301" height="648" alt="image" src="https://github.com/user-attachments/assets/3df9107a-af69-48df-a715-231af97db05b" />


Exemple de suppression avec une alerte qui s'affiche 
 <img width="310" height="637" alt="image" src="https://github.com/user-attachments/assets/9fc71588-05ca-44a7-9b34-6a8036ea166e" />

