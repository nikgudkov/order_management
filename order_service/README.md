
Swagger:

http://localhost:8081/swagger-ui/

Development tips:

To run application locally in dev mode add to VM arguments following line substituting the stubs:

`-Dspring.profiles.active=dev -DCLIENT_ID={client-id} -DCLIENT_SECRET={client_secret} -DAZURE_AD_TENANT_ID={tenant_id}`

Run mysql and activemq locally in a docker:

`docker run -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=orders mysql:8.0.22`

`docker run -p 61616:61616 -p 8161:8161 rmohr/activemq:5.15.9`

or run `docker-compose up`

Build an image:

`mvn clean package`

`docker build --tag ngudkov/order_service:1.1 .`