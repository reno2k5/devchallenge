# Store challenge

Comentarios sobre el entregable e instrucciones para el correcto funcionamiento de la app

### Requisitos del sistema
Se debe tener instalado:

* Java JDK 1.8+
* Maven 3+
* Oracle DB 10+
* Postman


### Preparar ambiente
Para tener el ambiente listo, se debe:

1) Importar el dump de sql. El archivo de backup es devchallenge\data\src\main\resources\db_export\STORECH01.DMP.
2) Instalar el driver de oracle dentro de nuestra instalación de maven. Versión del driver es 12.1.0.1, descargable desde oracle.com. Para más referencia en cómo instalarlo, puede encontrar [https://www.mkyong.com/maven/how-to-add-oracle-jdbc-driver-in-your-maven-local-repository/](ayuda acá)
3) El proyecto de Postman de muestra para probar éste projecto se puede ubicar en [https://www.getpostman.com/collections/ac60b1f136e3e4b262bb](éste link)
4) Asegurarse que maven esté dentro del PATH del entorno, o añadirlo

### Compilar
Para compilar el código, abra una línea de comandos, y diríjase hacia la carpeta raíz: devchallenge. Una vez ahí, ejecute el comando "mvn package"

### Ejecutar
Finalmente, la aplicación se ejecuta automáticamente en los puertos 80 y 81

Si ya tiene ocupado el puerto algún puerto en su máquina, diríjase al archivo _\devchallenge\restapp\src\main\resources\application.properties_ para el puerto 80, y al archivo _\devchallenge\service-buy\src\main\resources\application.properties_ para el puerto 81. Dentro de éste archivo, cambie la propiedad "server.port". Si ésto aplica, regrese al paso anterior (compilar)

Para ejecutar, desde la consola ejecutando en el directorio devchallenge, debe entrar el comando:

* Para servicio de productos "restapp\target\devchallenge-service-product-0.0.1-SNAPSHOT.jar"
* Para servicio de compra "java -jar service-buy\target\devchallenge-service-buy-0.0.1-SNAPSHOT.jar"

El servicio estará disponible en breves momentos, y se mostrará un mensaje en la línea de comandos que diga "Started Application in _x_ seconds"

Los archivos de logs, estarán guardados sobre la carpeta devchallenge, y pueden ser abiertos con cualquier programa para texto en modo únicamente lectura