# Operacion Fuego de Quasar

API que permite decodificar mensajes interceptados de una nave imperial pidiendo auxilio.

### Tecnologias utilizadas🛠️

* [JAVA](https://www.java.com/es/)
* [Maven](https://maven.apache.org/)
* [Spring Boot](https://spring.io/projects/spring-boot)
* [JUnit 5](https://junit.org/junit5/)
* [AWS](https://aws.amazon.com/)


### Como probarlo?

La solucion se encuentra hosteada en Amazon Web Services, para consumir la api se debera tener en cuenta la siguiente informacion:
-  **Endpoint:** 
```
   http://ec2-3-19-141-132.us-east-2.compute.amazonaws.com:8080
```   
- **Serivicios disponibles:**

- POST /api/topsecret   ->  Decodifica el mensaje y la ubicacion segun informacion obtenida en Satelites
	
**Ejemplo Request**	
```json
[
        {
            "name": "Kenobi",
            "distance": 100.0,
            "message": ["", "este", "es", "un", "mensaje","","largo","","","comun"]
        },
        {
            "name": "Skywalker",
            "distance": 115.5,
            "message": ["Hola", "", "", "", "","mas","largo","","","comun"]
        },
        {
            "name": "Sato",
            "distance": -200,
            "message": ["", "", "es", "", "mensaje","mas","","de","lo"]
        }
]
```
- POST /api/topsecret_split/{satelite_name}  ->  Se inserta un mensaje y distancia en cada Satelite de a uno a la vez.

**Ejemplo Request**		
```json
{
    "distance": 100.0,
    "message": [
        "",
        "este",
        "es",
        "un",
        "mensaje"
    ]
}
```		
- GET /api/topsecret_split/  ->  Se obtiene lo instertado en el servicio anterior.
		
- GET /api/{satelite_name}/  ->  Obtiene un Satelite segun nombre
		
- PUT /api/updatePosition  ->  Actualiza la posicion original de un Satelite

**Ejemplo Request**		
```json
{
    "name" :"Kenobi",
    "position": {
                "x":7,
                "y":3
          }
}
```	


Se podra encontrar el archivo **FuegoQuasar.postman_collection.json** con ejemplos de cada request.

![Alt text](/assets/PostmanScreenshot.jpg?raw=true "Diagrama")

### Diagrama de clases

A continuacion se detalla el diagrama de clases de la solucion, indicando las clases involucradas y sus metodos mas significativos:

![Alt text](/assets/fuegoquasarclases.jpg?raw=true "Diagrama")

### Diagramas de flujo

A continuacion se detalla los diagramas de flujos de los requerimientos principales del ejercicio:

![Alt text](/assets/_api_topsecret.png?raw=true "Diagrama")


![Alt text](/assets/_api_topsecret_split_satelitename.png?raw=true "Diagrama")


![Alt text](/assets/gettopsecret_split.png?raw=true "Diagrama")
