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
	dni varchar(10) primary key, #dni del client
    nomCognom varchar(30), #cognom del client
    edat integer, #edat del client
	telefon varchar(11), #telefon del client
    adreca varchar(100), #adreça del client
    ciutat varchar(30), #ciutat del client
    pais varchar(30), #pais del client
    email varchar(30), #email del client
    permisConduccio varchar(5), #permis de conducció del client
    punts integer #punts dels que disposa el client
);

ALTER TABLE Clients MODIFY permisConduccio varchar(5) not null;

create table Cotxes(
	matricula varchar(10) primary key, #matricula del cotxe
    numBastidor integer, #numero de bastidor del cotxe
    marca varchar(30), #marca del cotxe
    model varchar(30), #model del cotxe
    color varchar(30), #color del cotxe
    places integer, #places del cotxe
    numPortes integer, #nombre de portes del cotxe
    maleter double, #capacitat del maleter del cotxe en litres
    tCombustible varchar(30) #tipus de combustible del cotxe
);

create table Lloguer(
	dni varchar(10), #dni del client
    matricula varchar(10), #matricula del cotxe
	dies integer, #dies que estarà de lloguer
    preu double, #preu per dia del lloguer
    llocDevolucio varchar(100), #lloc de devolució del cotxe
    depositPle boolean, #si es retorna amb el deposit ple
    tAsseguranca varchar(30), #tipus d'assegurança del lloguer
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
	dniMecanic varchar(10) primary key, #dni del mecanic
    nomCognomM varchar(30), #cognom del mecanic
    edatM integer, #edat del mecanic
	telM varchar(11), #telefon del mecanic
    adrecaM varchar(100),#adreça del mecanic
    ciutatM varchar(30),#ciutat del mecanic
    paisM varchar(30),#pais del mecanic
    emailM varchar(30),#email del mecanic
    permisConduccioM varchar(5),  #permis de conducció del mecanic
    puntsM integer,#punts dels que disposa el mecanic
    salari double, #salari del mecanic
    seguretatSocial integer,#numero de la seguretat social del mecanic
    titulacio varchar(30),#la titulació del mecanic
    anysEmp integer #anys en l'empresa del mecanic
);

create table Manteniment(
	dniMecanic varchar(10), #dni del mecanic
    matricula varchar(10), #matricula del mecanic
    dataInici date,#data inici del manteniment
    dataFi date, #data fi del manteniment
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