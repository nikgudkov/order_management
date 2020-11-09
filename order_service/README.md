
Swagger:

http://localhost:8081/swagger-ui/

Development tips:

To run application in dev mode add to VM arguments:
`-Dspring.profiles.active=dev `

Run mysql and activemq locally in docker:

`docker run -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=orders mysql:8.0.22`
`docker run -p 61616:61616 -p 8161:8161 rmohr/activemq:5.15.9`


Build an image:

`mvn clean package`

`docker build --tag ngudkov/order_service:1.1 .`