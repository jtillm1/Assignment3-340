# Character Gallery (Web App + REST API)

A Spring Boot app for TV/movie characters. It has a server-rendered web front end (FreeMarker, Track A) and a REST API, both backed by PostgreSQL (Neon) and deployed on Render with Docker.

**Live site (home page):** https://assignment3-340-paxa.onrender.com/
**Live API:** https://assignment3-340-paxa.onrender.com/api/characters
*(Free Render services sleep when idle, so the first request can take about a minute.)*

## Entity: `TvCharacter` (table `characters`)

| Attribute | Type | Rules |
|---|---|---|
| characterId | Long | Unique, auto-generated |
| name | String | Required, not blank |
| description | String | Required, not blank |
| universe | String | Optional (used for filtering) |
| species | String | Optional |
| role | String | Optional |
| firstAppearance | LocalDate | Optional, format `yyyy-MM-dd` |
| imageUrl | String | Optional; empty, an `https://` link, or a path like `/img/name.jpg` |

## Pages

| Route | Description |
|---|---|
| `GET /` | Gallery: cards for every character, with name search and universe filter |
| `GET /about` | About page, including how the app is built |
| `GET /characters/{id}` | Details page for one character (Edit and Delete buttons) |
| `GET /characters/new` | Add form |
| `POST /characters` | Saves a new character, redirects to the gallery |
| `GET /characters/{id}/edit` | Edit form, pre-filled |
| `POST /characters/{id}/edit` | Saves changes, redirects to the details page |
| `POST /characters/{id}/delete` | Deletes (after a confirm dialog), redirects to the gallery |

Unknown IDs show a friendly "Character not found" page (HTTP 404).

## API Endpoints

| Method | Path | Description | Success |
|---|---|---|---|
| GET | `/api/characters` | Get all characters | 200 |
| GET | `/api/characters/{id}` | Get one character | 200 |
| GET | `/api/characters?universe=Marvel` | Filter by universe | 200 |
| GET | `/api/characters?name=abc` | Partial, case-insensitive name search | 200 |
| POST | `/api/characters` | Add a character | 201 |
| PUT | `/api/characters/{id}` | Update a character | 200 |
| DELETE | `/api/characters/{id}` | Delete a character | 204 |

Errors: unknown ID returns **404**; missing `name` or `description` returns **400**.

Example body for POST and PUT:

```json
{
  "name": "Luna",
  "description": "A curious young witch.",
  "universe": "Wizarding World",
  "species": "Human",
  "role": "Hero",
  "firstAppearance": "2003-06-21",
  "imageUrl": "https://example.com/luna.jpg"
}
```

## Running locally

Set `SPRING_DATASOURCE_URL` to your Neon JDBC string, then start from the same terminal:

```
./mvnw spring-boot:run
```