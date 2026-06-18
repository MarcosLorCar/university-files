# Trabajo Ingeniería del Software, grupo A05

* DAVID CALZADO OLMO
* HATIM BEN AYAD
* LUIS DUEÑAS RECUERO
* MARCOS LORO CARRASCO
* MARCOS SOBRINO GARCÍA-VILLARACO

#### Instrucciones para ejecutar el programa:

1. Montar base de datos con [ISO-A05.sql](ISO-A05.sql)
2. Tener un usuario "admin" en el servidor mysql con contraseña "1234" (Ver abajo)
3. Ejecutar el programa [ISO-A05.jar](ISO-A05.jar)

#### Para crear el usuario admin:
- `DROP USER 'admin'@'localhost';(por si ya existe el usuario)`
- `CREATE USER 'admin'@'localhost' IDENTIFIED BY '1234';`
- `GRANT ALL PRIVILEGES ON . TO 'admin'@'localhost';`
- `FLUSH PRIVILEGES;`