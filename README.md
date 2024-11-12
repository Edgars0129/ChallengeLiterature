# Library Management System

Este proyecto es una aplicación Java/Spring Boot que integra con la API de Gutendex para gestionar una biblioteca digital. Permite buscar, almacenar y gestionar información sobre libros y autores.

## Características 🚀

- Búsqueda de libros por título
- Listado de libros registrados en el sistema
- Listado de autores y sus obras
- Búsqueda de autores por período histórico
- Filtrado de libros por idioma
- Integración con API externa (Gutendex)
- Almacenamiento local de datos consultados

## Tecnologías Utilizadas 💻

- Java 17
- Spring Boot
- Spring Data JPA
- Base de datos H2/MySQL
- Maven
- Jackson (para manejo de JSON)

## Estructura del Proyecto 📁

```
src/main/java/com/alurachallenge/demo
├── Config/
│   └── RestTemplateConfig.java
├── Exception/
│   ├── APIException.java
│   ├── ConversionException.java
│   └── ValidacionException.java
├── Model/
│   ├── Autor.java
│   ├── Categoria.java
│   ├── FormatoLibro.java
│   └── Libro.java
├── Principal/
│   └── Principal.java
├── RecordDTO/
│   ├── DatosAutor.java
│   ├── DatosFormato.java
│   ├── DatosLibro.java
│   └── DatosRespuestaAPI.java
├── Repository/
│   ├── AutorRepository.java
│   └── LibroRepository.java
├── Service/
│   ├── AutorService.java
│   ├── ConsumoAPI.java
│   ├── ConvierteDatos.java
│   ├── IConvierteDatos.java
│   └── LibroService.java
└── Util/
    ├── Convertidor.java
    └── URLBuilder.java
```

## Requisitos Previos 📋

- Java 17 o superior
- Maven 3.6 o superior
- IDE compatible con Spring Boot (recomendado: IntelliJ IDEA, Eclipse, VS Code)

## Configuración ⚙️

1. Clona el repositorio:
```bash
git clone [URL-del-repositorio]
```

2. Navega al directorio del proyecto:
```bash
cd library-management-system
```

3. Configura las variables de entorno en `application.properties`:
```properties
api.gutendex.baseurl=https://gutendex.com
# Otras configuraciones necesarias
```

4. Ejecuta el proyecto con Maven:
```bash
mvn spring-boot:run
```

## Uso 📖

Una vez iniciada la aplicación, se presentará un menú con las siguientes opciones:

1. Buscar libro por título
2. Listar libros registrados
3. Listar autores registrados
4. Listar autores vivos en un determinado año
5. Listar libros por idioma
6. Salir

## Documentación de la API 📚

La aplicación integra con la API de Gutendex, que proporciona acceso a una extensa biblioteca de libros digitales. Las principales endpoints utilizados son:

- `/books`: Búsqueda general de libros
- `/books?search=query`: Búsqueda por título
- `/books?languages=lang`: Filtrado por idioma

## Contribución 🤝

1. Haz un Fork del proyecto
2. Crea tu rama de características (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abre un Pull Request

## Contacto 📧

Link del proyecto: https://github.com/Edgars0129/ChallengeLiterature

## Licencia 📄

Este proyecto está bajo la Licencia MIT - mira el archivo [LICENSE.md](LICENSE.md) para detalles

## Agradecimientos 🎁

- API Gutendex por proporcionar acceso a su biblioteca digital
- Comunidad de Spring Boot por la documentación y recursos
- [Nombre del curso o institución] por la inspiración y guía
