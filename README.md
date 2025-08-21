# Login Service

Spring Boot 기반의 사용자 인증 서비스입니다.

## 기능

- 사용자 회원가입
- 사용자 로그인 (ID 또는 이메일로 로그인 가능)
- 이메일 형식 검증
- 사용자 ID/이메일 중복 확인
- Azure PostgreSQL 데이터베이스 연동

## API 엔드포인트

### 1. 로그인
```
POST /api/auth/login
```

**Request Body:**
```json
{
  "identifier": "user123", // userId 또는 email
  "password": "password123"
}
```

**Response:**
```json
{
  "success": true,
  "message": "Login successful",
  "data": {
    "id": 1,
    "userId": "user123",
    "email": "user@example.com",
    "name": "John Doe"
  }
}
```

### 2. 회원가입
```
POST /api/auth/signup
```

**Request Body:**
```json
{
  "userId": "user123",
  "password": "password123",
  "confirmPassword": "password123",
  "email": "user@example.com",
  "name": "John Doe"
}
```

**Response:**
```json
{
  "success": true,
  "message": "Account created successfully",
  "data": {
    "id": 1,
    "userId": "user123",
    "email": "user@example.com",
    "name": "John Doe"
  }
}
```

### 3. 사용자 ID 중복 확인
```
GET /api/auth/check-userid/{userId}
```

**Response:**
```json
{
  "success": true,
  "message": "User ID check completed",
  "data": false
}
```

### 4. 이메일 중복 확인
```
GET /api/auth/check-email/{email}
```

**Response:**
```json
{
  "success": true,
  "message": "Email check completed",
  "data": false
}
```

### 5. 이메일 형식 검증
```
GET /api/auth/validate-email/{email}
```

**Response:**
```json
{
  "success": true,
  "message": "Email validation completed",
  "data": true
}
```

### 6. 헬스 체크
```
GET /api/auth/health
```

**Response:**
```json
{
  "success": true,
  "message": "Login service is running",
  "data": null
}
```

## 데이터베이스 설정

Azure PostgreSQL 데이터베이스를 사용합니다. `application-secret.properties` 파일에 다음 설정이 필요합니다:

```properties
spring.datasource.url=jdbc:postgresql://your-azure-postgresql-server.postgres.database.azure.com:5432/your-database-name
spring.datasource.username=your-username@your-azure-postgresql-server
spring.datasource.password=your-password
spring.datasource.driver-class-name=org.postgresql.Driver
```

## 실행 방법

1. 데이터베이스 설정 파일 생성:
   ```bash
   cp src/main/resources/application-secret.properties.example src/main/resources/application-secret.properties
   ```

2. 데이터베이스 연결 정보 입력

3. 서비스 실행:
   ```bash
   ./gradlew bootRun
   ```

서비스는 기본적으로 `http://localhost:8081`에서 실행됩니다.

## Frontend 연동

Frontend에서는 `web-frontend/lib/api/auth.ts` 파일의 API 함수들을 사용하여 서비스와 연동할 수 있습니다.

## 에러 처리

서비스는 다음과 같은 에러 상황을 처리합니다:

- 이메일 형식이 올바르지 않은 경우
- 비밀번호가 일치하지 않는 경우
- 사용자 ID가 이미 존재하는 경우
- 이메일이 이미 존재하는 경우
- 로그인 정보가 올바르지 않은 경우
- 필수 필드가 누락된 경우

모든 에러는 일관된 형식으로 응답됩니다:

```json
{
  "success": false,
  "message": "Error message",
  "data": null
}
```