# RabbitMQSender
Este proyecto es el que envia los mensajes a una cola de mensajes en RabbitMQ

Este proyecto ha sido desarrollado para ejecutar con jre 8
Gestionando el ciclo de compilacion, empaquetado y test (No tiene JUnit implementado pero lo soportaria) con maven 4.0
Se encuentra implementado con Spring boot y spring mvc para la exposicion de servicios rest que dejan los mensajes como string en la cola de mensajes
Parametrizando tanto el puerto de publicacion de los servicios rest, la ip del servidor de rabbitMQ y el mombre de la cola de mensajes mediante el archivo application.properties
El controlador rest que deja el mensaje en la cola de mensajes es MyRestController, el metodo sendMessage

Una vez se levantan los servicios mediante la clase Main IniciarViaSpringBoot, se puede dejar el mensaje en la cola de mensajes mediante el consumo de la
url http://{ip_publicacion}:{puerto_publicacion}/sendMessage?message={string_mensaje}, por ejemplo http://localhost:8080/sendMessage?message=Andres

NOTA, este proyecto presupone que en la ip que se parametrice en el archivo application.properties como ip.cola.mensajes , se encuentre ejecutando un servicio de
RabbitMQ, por otra parte tanto productos como consumidor deben apuntar a esta misma ip o nombre de maquina y a la misma cola de mensajes parametrizada en el
campo nombre.cola.mensajes del mismo archivo.
