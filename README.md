# Courier Tracking System

A multimodule project based on microservice architecture. Most of the logical details had given as comments in related code block.

## Prerequisites:

-Project SDK is java 20.

-Docker should be installed on your testing machine. Because there is a db instance, management client and rabbit-mq server configuration in docker-compose.yml file.

## Modules Explanation

A courier tracking multimodule project which consist of 3 microservices and one common module.

eureka-server: A microservice registry server. Used for registering other microservices, and handles server side load balancing. Services registered on this server could be seen on **localhost:8761**

courier: Manages courier db operations. Also it is rabbitmq client for logging store entrance. (Because there is no need to manage logging requests syncronously.)

worker: Manages application entire business logic. Interacts with courier microservice over Feign client and/or message queue.

-Mock location messages could be sent to queue over **MessageSender**.sendLocation() method and **Processor**.receive() method is the consumer of these messages. Designed for real world scenario.


# Installation Steps

In main project directory run "docker-compose up -d" command. This will create 3 container and will create **courier** database also.

**db-client:** For accessing container database server and querying purposes

localhost:5050, hostname:postgres, username:sezer, password: password

**db-server:**

**rabbitmq-server:**

First run eureka-server, and than other 2 microservices.

When all service started successfully, database tables should be created run command to create a courier for testing purposes.

```
curl -L -X POST 'localhost:8083/api/v1/courier' \
-H 'Content-Type: application/json' \
-d '{
    "id": 1,
    "firstName": "sezer",
    "lastName": "malak"
}'
```

Finally push test dataset into queue with request.

```
curl -L -X POST 'localhost:8087/api/v1/worker-test/push-stream' \
-H 'Content-Type: application/json' \
-d '[
{
	"courier": 1,
	"lat":  43.9672453,
	"lng": 29.0630918,
	"time": 1691839194
},
      {
	"courier": 1,
	"lat":  43.9672463,
	"lng": 29.0630908,
	"time": 1691840194
},

      {
	"courier": 1,
	"lat":  43.9672263,
	"lng": 29.0630918,
	"time": 1691841194
},
  {
	"courier": 1,
	"lat":  43.9672363,
	"lng": 29.0630918,
	"time": 1691842194
},
    {
	"courier": 1,
	"lat":  43.9672463,
	"lng": 29.0630928,
	"time": 1691843194
},
    {
	"courier": 1,
	"lat":  43.9672463,
	"lng": 29.0630938,
	"time": 1691844194
},
{
	"courier": 1,
	"lat":  42.9672463,
	"lng": 29.0630918,
	"time": 1691844295
},
{
	"courier": 1,
	"lat":  41.9672444,
	"lng": 29.0630908,
	"time": 1691844296
},
{
	"courier": 1,
	"lat":  40.9672443,
	"lng": 29.0630908,
	"time": 1691844297
},
{
	"courier": 1,
	"lat":  40.9652451,
	"lng": 29.0630908,
	"time": 1691844298
},
{
	"courier": 1,
	"lat":  40.9642453,
	"lng": 29.0630908,
	"time": 1691845206
},
{
	"courier": 1,
	"lat":  40.9635463,
	"lng": 29.0630908,
	"time": 1691845207
},
{
	"courier": 1,
	"lat":  40.9642453,
	"lng": 29.0630908,
	"time": 1691845208
},
{
	"courier": 1,
	"lat":  40.9642463,
	"lng": 29.0630908,
	"time": 1691845209
},
{
	"courier": 1,
	"lat":  40.9635463,
	"lng": 29.0630908,
	"time": 1691845210
}
]'
```


# Solution

## Courier entrance

When a location data has arrived, we don't need to check distance to all store every time. I defined a maximum_speed threshold(200km/h) and checked distance between courier_location and store_location first. With that result i can decide not check for a while for some stores.

After entrance i saved log to courier_log table over rabbitmq asynchronously.

Check entrances list over api

```
curl -L -X GET 'localhost:8083/api/v1/courier-log/1'
```

## Querying total distance

I calculated distance for every consecutive location data and stored distance calculation into memory. After 10 data i wrote the sum into courier_total_distance table over courier microservice. Thanks to minimize io operations system will be performed well and total distance query could done instantly.

Total distance for courier over api

```
curl -L -X GET 'localhost:8087/api/v1/worker-test/total-distance/1'
```
