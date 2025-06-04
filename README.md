SAÉ 2.01/2.02 – Gestion de livraisons de cartes Pokémon

Objectif du projet:
  Ce projet consiste à simuler un système de livraisons de cartes Pokémon entre plusieurs membres répartis dans différentes villes. 
  Chaque scénario représente un ensemble de transactions.
  Ces échanges sont modélisés sous forme d’un graphe orienté, permettant d’analyser les flux, les dépendances et les zones de concentration de demandes.

Structure du projet :
  modele/ : contient la logique métier
  LectureScenario.java : lit les fichiers de scénario, construit le graphe sous forme de tableau de voisins
  Graphe.java : structure de graphe orienté, avec calculs de degrés entrants
  IDException.java : exception personnalisée pour la gestion des identifiants
  vue/ : contient l’interface utilisateur JavaFX
  ChoixScenarioHBox.java : comboBox pour choisir un fichier de scénario et bouton de validation
  VBoxRoot.java : conteneur principal pour l’affichage JavaFX
  controleur/ : contient la logique de contrôle (connexion entre la vue et le modèle)
  Scenario/ : dossier contenant les fichiers de scénarios d’échanges

Fonctionnalités principales :
  Lecture dynamique d’un scénario à partir d’un fichier texte
  Construction automatique du graphe des échanges
  Affichage console du graphe et des degrés entrants pour chaque ville
  Interface JavaFX simple pour le choix de scénario



Fonctionnalités non réalisées :
  Affichage visuel du graphe dans une vue JavaFX
  Tests unitaires pour valider les classes du modèle



Lancement de l’application:
  Ouvrir le projet dans IntelliJ IDEA (ou tout IDE compatible JavaFX)
  Vérifier que le dossier Scenario contient bien des fichiers valides
  Lancer la classe principale de l’application (par exemple via un fichier Main.java)
  Sélectionner un scénario dans l’interface et cliquer sur "Valider"



Technologies utilisées :
Java 
JavaFX pour l’interface graphique
Architecture modèle-vue-contrôleur (MVC)

Auteurs :
Julian JUBAULT--JEAN
Alexandre MEIRA

Projet réalisé dans le cadre de la SAÉ 2.01 / 2.02 – BUT Informatique – Vélizy

