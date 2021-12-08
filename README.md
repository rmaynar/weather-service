# Weather service

API REST para proveer información metereológica haciendo uso de Spring Boot.

## Endpoints

Entrada de datos, consulta y eliminación de todos los datos 

```bash
http://localhost:8080/weather-data
```

Filtrado por fecha
```bash
http://localhost:8080/weather-data?date=2015-12-11
```

Filtrado por fecha mín y máx. (Pendiente de añadir funcionalidad de mostrar temperatura mínima y máxima por localidad)
```bash
http://localhost:8080/weather-data/temp?startDate=2015-12-11&endDate=2016-12-11
```

Formato del JSON de entrada (ejemplo):

```bash
{
    "id": 37892,
    "date": "2020-09-15",
    "location": {
        "lat": 2.0223,
        "lon": 2.3349,
        "city": "Dallas",
        "state": "Texas"
    },
    "temperature": [
        30.0,
        2.1,
        11.2,
        110.1,
        21412.7,
        1873.0,
        182.9,
        1821.2,
        209.2,
        39.2,
        52.9,
        2121.0,
        240.3,
        52.3,
        344.1,
        1023.2,
        1241.2,
        12.1,
        124.4,
        124.6,
        18.6,
        183.2,
        224.9,
        3423.3
    ]
}
```

## Datos de carga

En la carpeta resources está disponible el fichero data.sql con datos para la precarga de 5  recursos.

## Consola H2

http://localhost:8080/h2-console
```bash
connection url: jdbc:h2:mem:testdb
user: sa
password: password
```

## License
[MIT](https://choosealicense.com/licenses/mit/)