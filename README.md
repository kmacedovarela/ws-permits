# Permits WS

Two simple endpoints exposed by WildFly Swarm using JPA, JAX-RS and CDI

Developed mainly to be consumed by a BPMN2 process. More info [here](https://github.com/kmacedovarela/advanced-bpmn2-process). 

Electrical and Structural permits can be in status `INPROGRESS`,`APPROVED` or `DENIED.

## Pre reqs
* Maven 
* Java 8
* [Sollar Vilage Model Jar](https://github.com/kmacedovarela/advanced-bpmn2-process/tree/master/sv-model) installed 

## Run

```mvn wildfly-swarm:run```

## Use
APIs will be available at:
	
```GET http//localhost:8080/electricalpermit/{id}
GET http//localhost:8080/structuralpermit/{id}
PUT http//localhost:8080/electricalpermit/{id}/{status}
PUT http//localhost:8080/structuralpermit/{id}/{status}```
