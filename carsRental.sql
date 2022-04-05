INSERT INTO clients values("46465746W", "Judith Barrajon", 27, "607652294","calle Castelao num 24 ","Hospitalet","Espanya","prueba@prueba.com",'A2',12);
/*delete from clients where dni = "A AA";
UPDATE clients set permisConduccio = 'A2' where nomCognom = "Judith Barrajon";*/
UPDATE Clients SET nomCognom = "pepe" WHERE dni = "1234";
select * from clients;
select * from cotxes;
select * from mecanics;
SELECT * FROM Mecanics WHERE dniMecanic = "00" or nomCognomM = "franky v";
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
    FOREIGN KEY (dni) REFERENCES Clients(dni) on update cascade on delete cascade,
    FOREIGN KEY (matricula) REFERENCES Cotxes(matricula) on update cascade on delete cascade,
    PRIMARY KEY (dni,matricula)
);
INSERT INTO Lloguer values("46465746W", "1234ABC", 7, 10.50,"Estocolmo",1,"Con Franquicia");
select matricula, c.dni, nomCognom, telefon, dies, preu from clients c, lloguer l where l.dni = c.dni; /*PARA LA CONSULTA DE SACAR LOS QUE ESTAN ALQUILADOS Y EL DNI.*/

SELECT l.matricula, llocDevolucio, depositPle, if(dataInici, "Si", "No")
FROM lloguer l
LEFT JOIN manteniment m ON l.matricula = m.matricula; /*muestra los lloguers que han tenido mantenimiento*/

delete from manteniment where matricula = "1234ABC";


UPDATE lloguer SET matricula = "1234ABC" WHERE dni = "46465746w";

select * from lloguer;
select * from cotxes;
select * from manteniment;
select * from Mecanics;
select dni from clients;
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
    FOREIGN KEY (dniMecanic) REFERENCES Mecanics(dniMecanic)  on update cascade on delete cascade,
    FOREIGN KEY (matricula) REFERENCES Cotxes(matricula)  on update cascade on delete cascade,
    PRIMARY KEY (dniMecanic,matricula)
);
INSERT INTO Manteniment values("000", "1234ABC", str_to_date('12-12-2021', '%d-%m-%Y'), str_to_date('1-01-2022', '%d-%m-%Y'));
UPDATE MANTENIMENT SET dataInici = '2021-02-22' WHERE matricula = "1234ABC" and dniMecanic = "001";
								   
select * from mecanics;
select * from manteniment;
select * from lloguer;
select * from cotxes;

delete from manteniment where dniMecanic = "000"; 