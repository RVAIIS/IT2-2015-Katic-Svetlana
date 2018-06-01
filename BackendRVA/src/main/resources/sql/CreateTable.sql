DROP TABLE IF EXISTS nacionalnost CASCADE;
DROP TABLE IF EXISTS igrac CASCADE;
DROP TABLE IF EXISTS liga CASCADE;
DROP TABLE IF EXISTS tim CASCADE;
DROP SEQUENCE IF EXISTS nacionalnost_seq CASCADE;
DROP SEQUENCE IF EXISTS igrac_seq CASCADE;
DROP SEQUENCE IF EXISTS liga_seq CASCADE;
DROP SEQUENCE IF EXISTS tim_seq CASCADE;

CREATE TABLE nacionalnost(
	
	id integer NOT NULL,
    naziv varchar(50) NOT NULL,
    skracenica varchar(30)
	
);

CREATE TABLE igrac(

	id integer NOT NULL,
    ime VARCHAR(30) NOT NULL,
    prezime VARCHAR(30) NOT NULL,
    broj_reg INTEGER NOT NULL,
	datum_rodjenja DATE NOT NULL,
	nacionalnost INTEGER NOT NULL,
	tim INTEGER NOT NULL

);

CREATE TABLE tim(

	id INTEGER NOT NULL,
    naziv VARCHAR(50) NOT NULL,
    osnovan DATE NOT NULL,
    sediste VARCHAR(50) NOT NULL,
    liga INTEGER NOT NULL

);

CREATE TABLE liga(

	id integer NOT NULL,
    naziv VARCHAR(50) NOT NULL,
	oznaka VARCHAR(30) NOT NULL

);

ALTER TABLE nacionalnost ADD CONSTRAINT PK_Nacionalnost
	PRIMARY KEY(id);
ALTER TABLE igrac ADD CONSTRAINT PK_Igrac
	PRIMARY KEY(id);
ALTER TABLE tim ADD CONSTRAINT PK_Tim
	PRIMARY KEY(id);
ALTER TABLE liga ADD CONSTRAINT PK_Liga
	PRIMARY KEY(id);

ALTER TABLE igrac ADD CONSTRAINT FK_Igrac_Nacionalnost
	FOREIGN KEY (nacionalnost) REFERENCES nacionalnost (id);
ALTER TABLE igrac ADD CONSTRAINT FK_Igrac_Tim
	FOREIGN KEY (tim) REFERENCES tim (id);
ALTER TABLE tim ADD CONSTRAINT FK_Tim_Liga
	FOREIGN KEY (liga) REFERENCES liga (id);


CREATE INDEX IDXFK_Igrac_Nacionalnost
	ON igrac (nacionalnost);
CREATE INDEX IDXFK_Igrac_Tim
	ON igrac (tim);
CREATE INDEX IDXFK_Tim_Liga
	ON tim (liga);
	
CREATE SEQUENCE nacionalnost_seq
INCREMENT 1;
CREATE SEQUENCE tim_seq
INCREMENT 1;
CREATE SEQUENCE igrac_seq
INCREMENT 1;
CREATE SEQUENCE liga_seq
INCREMENT 1;


