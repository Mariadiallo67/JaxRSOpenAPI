# **Template de projet pour le TP JPA: API Vente de Tickets de Concert**

## **Description**

Ce projet est une API REST développée en Java utilisant JPA (Hibernate) et RESTEasy (JAX-RS) permettant de gérer une plateforme de vente de tickets de concert.

L’application permet :

* la gestion des événements (concerts)
* la gestion des utilisateurs (clients, organisateurs, administrateurs)
* l’achat et la gestion des tickets
* le suivi des commandes
* le transfert de tickets
* la gestion des notifications

## **Architecture du projet**
Le projet suit une architecture en couches :

Controller (REST)
     ↓
Service (logique métier)
     ↓
DAO (accès aux données)
     ↓
JPA / Hibernate
     ↓
Base de données (HSQLDB / MySQL)

## **Modèle métier**

**### Héritage**
Utilisateur (abstrait)
    ── Client
    ── Organisateur
    ── Administrateur

### **Entités principales**
* Utilisateur
* Client
* Organisateur
* Administrateur
* Evenement
* Lieu
* Artiste
* GenreMusical
* CategorieTicket
* Ticket
* Commande
* Notification
* Transfert

### **Relations importantes**
* Organisateur → Evenement (1 → N)
* Evenement → Lieu (N → 1)
* Evenement → Artiste (N → N)
* Evenement → CategorieTicket (1 → N)
* CategorieTicket → Ticket (1 → N)
* Commande → Ticket (1 → N)
* Client → Commande (1 → N)
* Ticket → Client (propriétaire)
* Ticket → Transfert (1 → 0..1)

## **Documentation API (Swagger)**
Pour la documentation API (Swagger) j'ai crée une classe Rest SwaggerUiResource pour avoir l'interface du swagger.
http://localhost:8081/api/docs

## **Endpoints principaux**

Chaque entité du modèle métier dispose de son propre controller REST, dans lequel les opérations CRUD (Create, Read, Update, Delete) ont été implémentées.

En complément, j'ai ajoutée des endpoints métier sur certaines ressources: 
### **Evenements**

| Méthode | URL                                           | Description                                          |
|---------|-----------------------------------------------|------------------------------------------------------|
| GET     | /api/evenements/capacite/{min}                | Rechercher les événements avec une capacité minimale |
| GET     | /api/evenements/organisateur/{organisateurId} | Lister les evenements d'un organisateur              |
| GET     | /api/evenements/avenir                        | Événements à venir                                   |
| GET     | /api/evenements/ville/{ville}                 | Recherche evenement par ville                        |
| GET     | /api/evenements/valides                       | Événements validés                                   |
| GET     | /api/evenements/statut/{statut}               | Rechercher les événements par statut                 |

### **### Commandes**

| Méthode | URL                                            | Description                             |
|---------|------------------------------------------------|-----------------------------------------|
| GET     | /api/commandes/client/{clientId}               | Liste des commandes d'un client         |
| PUT     | /api/commandes/{id}/{annuler}                  | Annuler une commande                    |
| GET     | /api/commandes/confirmees                      | Événements à venir                      |
| GET     | /api/commandes/client/{clientId}/montant-total | Montant total des commandes d'un client |
| GET     | /api/commandes/client/{clientId}/count         | Nombre de commande client               |
| GET     | /api/commandes/statut/{statut}                 | Rechercher les commandes par statut     |

### **Tickets**

| Méthode | URL                                        | Description                              |
|---------|--------------------------------------------|------------------------------------------|
| GET     | /api/tickets/evenement/{evenementId}/count | Compter les ticket vendus pour evenement |
| GET     | /api/tickets/categorie/{categorieId}       | Tickets par catégorie                    |
| GET     | /api/tickets/proprietaire/{id}             | Tickets d’un client                      |
| GET     | /api/tickets/code/{codeQR}                 | Ticket par codeQR                        |
| PUT     | /api/tickets/{id}/utiliser                 | Utiliser un ticket                       |
| PUT     | /api/tickets/{id}/transferer/{clientId}    | Transférer un ticket                     |

## **DAO**
Chaque entité du modèle métier dispose de sa propre **DAO**, dans laquelle les opérations classiques de **CRUD** ont été implémentées.

Les DAO de **Evenement**, **Commande** et **Ticket** ont été enrichies afin de répondre aux exigences du projet et contiennent :

* des requêtes JPQL
* des requêtes nommées (*Named Queries*)
* des Criteria Queries
* plusieurs méthodes métier spécifiques à l’application