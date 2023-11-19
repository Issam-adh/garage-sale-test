Veuillez trouver ci-joint un lien pour un tutoriel D'application (video): https://we.tl/t-CCkLrpc3CW <br>

Script for the database:
<br>
CREATE TABLE cars (
    id INT AUTO_INCREMENT PRIMARY KEY,
    make VARCHAR(255),
    model VARCHAR(255),
    date DATE,
    price FLOAT,
    picture VARCHAR(255),
    mileage VARCHAR(255),
    fuel ENUM('DIESEL', 'ELECTRIC', 'HYBRID'),
    transmission ENUM('MANUAL', 'SEMI_AUTOMATIC', 'AUTOMATIC')
);
<br>

1- <b>Git clone</b> https://github.com/Issam-adh/garage-sale-test.git </br>
2- <b>cd garageSales</b> </br>
3- <b>mvn clean install </b> </br>
4- <b>mvn spring-boot:run</b> </br>
<br>
<br>
<b> <u>S'il y a un problème avec les enums (fuel, transmission), veuillez exécuter ce script.</u></b> <br>
-- Drop existing columns<br>
ALTER TABLE cars <br>
DROP COLUMN fuel,<br>
DROP COLUMN transmission;<br>
<br><br>
-- Add enum columns<br>
ALTER TABLE cars<br>
ADD COLUMN fuel ENUM('DIESEL', 'ELECTRIC', 'HYBRID'),<br>
ADD COLUMN transmission ENUM('MANUAL', 'SEMI_AUTOMATIC', 'AUTOMATIC');<br>

 
