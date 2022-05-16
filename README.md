# vestibular
 Esta API contem o CRUD dos dados de vestibular incluindo curso, candidatos e salas.
 
## Indice
- [Rodar Local](#rodar-local)
- [Database Schema](#database-schema)

## Rodar Local
1) Instalar [Docker](https://docs.docker.com/get-docker/); 
2) Instalar [Docker Desktop](https://www.docker.com/products/docker-desktop/) - interface para não precisar usar a linha de comando; 
3) Rodar o docker-compose para criar o container com o banco de dados --> na raiz do projeto: docker-compose run;
4) Criar as tabelas --> na raiz do projeto:  ./gradlew :data:migrateLocal

## Database Schema
Incluir aqui o diagrama