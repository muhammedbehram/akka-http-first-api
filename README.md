# akka-http-first-api #

### GET /people

Tüm insanların id isim ve soyisim bilgileri ile geri döner.
### Response:
```json
[
    {
      "id": 1,
      "name": "muhammed",
      "surname": "behram"
    }
]
```
### POST /person

Yeni bir birey kaydı alır.
### Request Body:
```json
[
    {
      "name": "muhammed",
      "surname": "behram"
    }
]
```
### PUT /person

Var olan birey kaydını günceller.
### Request Body:
```json
[
    {
      "id": 1,
      "name": "muhammed osman",
      "surname": "behram"
    }
]
```
