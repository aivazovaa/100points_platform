# 100points (Online School Platform [Java, Spring, Redis, Postgres, Docker])

Микросервисная платформа онлайн‑школы.

## Сервисы
- `gateway-service` - API Gateway на Spring Cloud Gateway с JWT‑аутентификацией и Redis Rate Limiting.
- `auth-service` - регистрация и логин, выдача JWT. PostgreSQL.
- `course-service` - CRUD курсов. PostgreSQL.
- `enrollment-service` - запись на курс. PostgreSQL.
- `postgres` - один инстанс с базами: `100points_auth`, `100points_course`, `100points_enrollment`.
- `redis` - кэш и лимитирование запросов.

## Быстрый старт
```bash
docker compose up --build
```
После старта:
Gateway: http://localhost:8080
Swagger UI (каждый сервис): `/:service/swagger-ui/index.html` (локальные порты: auth 8081, course 8082, enrollment 8083)

## Авторизация
1) Регистрация:
```bash
curl -X POST http://localhost:8080/api/v1/auth/register -H "Content-Type: application/json" -d '{"email":"u@ex.com","password":"secret"}'
```
2) Логин:
```bash
TOKEN=$(curl -s -X POST http://localhost:8080/api/v1/auth/login -H "Content-Type: application/json" -d '{"email":"u@ex.com","password":"secret"}' | jq -r .token)
```
3) Запрос с JWT через gateway:
```bash
curl -H "Authorization: Bearer $TOKEN" http://localhost:8080/api/v1/courses
```

## Переменные окружения
- `JWT_SECRET` общий секрет для подписи токенов
- `RATE_LIMIT_REPLENISH` и `RATE_LIMIT_BURST` лимиты запросов в gateway

## Архитектура (схема)
Gateway - (auth, course, enrollment)
Redis для rate‑limit и кэша. Postgres с тремя БД. JWT выпускается auth‑сервисом и проверяется gateway‑фильтром.

## Локальная разработка
Можно поднимать сервисы отдельно, указав свои строки подключения. Релизы собираются Docker‑файлами сервисов.
