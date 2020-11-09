
Small task showing spring boot integration with Azure Active Directory, MySQL and ActiveMQ.

Order service provides CRUD operations on orders, while log service listens on ActiveMQ queue 
whether there any updates on the orders and logs them.
###### Running:

Substitute following parameters in `docker-compose.yml` with your Active Directory values:

`CLIENT_ID`

`CLIENT_SECRET`

`AZURE_AD_TENANT_ID`

and run 

`docker-compose up`


###### Build an image:

`mvn clean package`

`docker build --tag ngudkov/order_service:1.1 .`