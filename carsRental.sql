INSERT INTO clients values("46465746W", "Judith Barrajon", 27, "607652294","calle Castelao num 24 ","Hospitalet","Espanya","prueba@prueba.com",'A2',12);
/*delete from clients where dni = "46465746W";
UPDATE clients set permisConduccio = 'A2' where nomCognom = "Judith Barrajon";*/
UPDATE Clients SET nomCognom = "pepe" WHERE dni = "1234";
select * from clients;
SELECT * FROM clients WHERE UPPER(dni) LIKE '46465746w';
/*----------------------------------------------*/
CREATE DATABASE carsRental;
use carsRental;

create table Clients(
	dni varchar(10) primary key,
    nomCognom varchar(30),
    edat integer,
	telefon varchar(11),
    adreca varchar(100),
    ciutat varchar(30),
    pais varchar(30),
    email varchar(30),
    permisConduccio varchar(5),
    punts integer
);

ALTER TABLE Clients MODIFY permisConduccio varchar(5) not null;

create table Cotxes(
	matricula varchar(10) primary key,
    numBastidor integer,
    marca varchar(30),
    model varchar(30),
    color varchar(30),
    places integer,
    numPortes integer,
    maleter double,
    tCombustible varchar(30)
);

create table Lloguer(
	dni varchar(10),
    matricula varchar(10),
	dies integer,
    preu double,
    llocDevolucio varchar(100),
    depositPle boolean, 
    tAsseguranca varchar(30),
    FOREIGN KEY (dni) REFERENCES Clients(dni),
    FOREIGN KEY (matricula) REFERENCES Cotxes(matricula),
    PRIMARY KEY (dni,matricula)
);

create table Mecanics(
	dniMecanic varchar(10) primary key,
    nomCognomM varchar(30),
    edatM integer,
	telM varchar(11),
    adrecaM varchar(100),
    ciutatM varchar(30),
    paisM varchar(30),
    emailM varchar(30),
    permisConduccioM varchar(5),
    puntsM integer,
    salari double,
    seguretatSocial integer,
    titulacio varchar(30),
    anysEmp integer
);

create table Manteniment(
	dniMecanic varchar(10),
    matricula varchar(10),
    dataInici date,
    dataFi date,
    FOREIGN KEY (dniMecanic) REFERENCES Mecanics(dniMecanic),
    FOREIGN KEY (matricula) REFERENCES Cotxes(matricula),
    PRIMARY KEY (dniMecanic,matricula)
);
