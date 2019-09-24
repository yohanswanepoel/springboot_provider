# Provider API (Springboot on Wildfly)

## Useful links and information

Health Check end-point
```
provider-api/api/v1/health
```

API endpoint
```
provider-api/api/v1/providers
```

Swagger UI for API
```
swagger-ui.html
```

* Project is built using Springboot and runs on Wildfly
* Deploys using standard OpenShift Wildfly S2I Template
* OpenShift DO commandline was used to develop this with no Java on the workstation
* Runtime requires PostgreSQL in OpenShift Parameters are set using

```java
spring.datasource.url = jdbc:postgresql://${DATABASE_SERVICE_NAME}:5432/${DATABASE_NAME}
spring.datasource.username = ${DATABASE_USER}
spring.datasource.password = ${DATABASE_PASSWORD}
```

## TODO
* Change to use Springboot JAR with standard JDK S2I image