This project has been created with the intention of learning more about Quarkus and its extensions, focusing on the authentication extensions.

The extensions used in this project are:

### Parsing JSON to Java Object and Vice Versa
- **RestEasy Jackson**

### Request Body Validation
- **Hibernate Validation**: Used to validate request body sended from client.

### Documentation
- **OpenAPI**: Used to genetare documentation of this API.

### Database
- **Hibernate ORM**: Used to create database entities based on Java classes;
- **Hibernate ORM Panache**: Used to create a Panache repository;
- **H2 JDBC**: Used as a development database that stores data in memory instead of on disk.

### Authentication
- **Smallrye JWT**: Used to consume JWT tokens during the authorization stage;
- **Smallrye JWT Build**: Used for creating JWT tokens;
- **Security JPA**: Used for the creation of endpoints protected by authentication.
