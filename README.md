# Activité 1 — Microservice de comptes bancaires

Spring Boot 3.5.7 · Java 21 · JPA/H2 · REST · Spring Data REST · GraphQL.

## Démarrer

```sh
./mvnw verify
python3 dev.py
```

Ouvrir http://localhost:8081/swagger-ui/index.html puis http://localhost:8081/graphiql.
Les données H2 sont réinitialisées à chaque redémarrage. Les comptes sont fictifs.

## Correspondance avec le travail demandé

| Exigence | Implémentation |
|---|---|
| Dépendances Web, JPA, H2, Lombok | `pom.xml` (accesseurs explicites pour faciliter la lecture) |
| Entité Compte / Repository | `Compte.java`, `CompteRepository.java` |
| Test DAO | Test d'intégration vérifiant la persistance réelle après POST |
| API REST CRUD | `/api/comptes` : GET, POST, PUT, DELETE |
| Client REST | Collection Postman et commandes ci-dessous |
| Swagger | `/swagger-ui/index.html`, `/v3/api-docs` |
| Spring Data REST et projections | `/data/comptes?projection=summary` |
| DTO, Mapper, couche métier | `CompteRequest`, `CompteResponse`, `CompteMapper`, `CompteService` |
| GraphQL | `/graphql`, schéma dans `src/main/resources/graphql` |

```sh
curl -s http://localhost:8081/api/comptes
curl -s -X POST http://localhost:8081/api/comptes -H 'Content-Type: application/json' -d '{"balance":1250,"currency":"MAD","type":"CURRENT"}'
curl -s 'http://localhost:8081/data/comptes?projection=summary'
curl -s http://localhost:8081/graphql -H 'Content-Type: application/json' -d '{"query":"{ comptes { id balance currency type } }"}'
```

Dans GraphiQL :

```graphql
mutation {
  createCompte(input: {balance: 500, currency: "MAD", type: SAVING}) {
    id balance currency type
  }
}
```

`BankIntegrationTest` vérifie CRUD, validation, DAO, projection, mutation et requête GraphQL.
Le stockage monétaire utilise BigDecimal ; le Float GraphQL est ici un choix pédagogique imposant des limites de représentation côté client.
Spring Data REST expose directement la couche persistance pour l'exercice ; l'API métier avec validation est `/api/comptes`.

Références du cours : https://www.youtube.com/watch?v=2-qIoZcvhAw et https://www.youtube.com/watch?v=FsdR09jlqaE.
