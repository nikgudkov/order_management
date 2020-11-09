
###### Running:

Run mysql activemq locally in a docker:

`docker run -p 61616:61616 -p 8161:8161 rmohr/activemq:5.15.9`

or run `docker-compose up`

###### Build an image:

`mvn clean package`

`docker build --tag ngudkov/log_service:1.1 .`