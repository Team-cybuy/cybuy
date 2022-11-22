# Cybuy application setup

### Steps to initialize the application

#### For the Frontend
- Navigate to `~/src/main/frontend`
- Execute `npm install`
<br><br>
#### For the Backend
- Apply the following changes to `~/src/main/resources/application.properties`
  - `spring.datasource.url=` url to your PostgreSQL database - example: *jdbc:postgresql://localhost:5432/cybuy*
  - `spring.datasource.username=` username of your database - PostgreSQL default: *postgres*
  - `spring.datasource.password=` password of your database user - **Remember to *never* submit this value when committing code changes**
