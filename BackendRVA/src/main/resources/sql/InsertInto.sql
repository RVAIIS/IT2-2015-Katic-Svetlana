INSERT INTO "nacionalnost"("id", "naziv", "skracenica")
VALUES(nextval('nacionalnost_seq'), 'Srbija', 'SRB');
INSERT INTO "nacionalnost"("id", "naziv", "skracenica")
VALUES(nextval('nacionalnost_seq'), 'Rusija', 'RUS');
INSERT INTO "nacionalnost"("id", "naziv", "skracenica")
VALUES(nextval('nacionalnost_seq'), 'Francuska', 'FRA');
INSERT INTO "nacionalnost"("id", "naziv", "skracenica")
VALUES(nextval('nacionalnost_seq'), 'Amerika', 'USA');
INSERT INTO "nacionalnost"("id", "naziv", "skracenica")
VALUES(nextval('nacionalnost_seq'), 'Spanija', 'SPA');

INSERT INTO "nacionalnost"("id", "naziv", "skracenica")
VALUES(-100, 'NACIONALNOST NAZIV', 'NACIONALNOST SKRACENICA');

INSERT INTO "liga"("id", "naziv", "oznaka")
VALUES(nextval('liga_seq'), 'Nacionalna kosarkaska asocijacija', 'NBA');
INSERT INTO "liga"("id", "naziv", "oznaka")
VALUES(nextval('liga_seq'), 'Evroliga', 'EU');
INSERT INTO "liga"("id", "naziv", "oznaka")
VALUES(nextval('liga_seq'), 'Kup Radivoja Koraca', 'KRK');
INSERT INTO "liga"("id", "naziv", "oznaka")
VALUES(nextval('liga_seq'), 'Jadranska kosarkaska liga', 'ABA Liga');

INSERT INTO "liga"("id", "naziv", "oznaka")
VALUES(-100, 'LIGA NAZIV', 'LIGA SKRACENICA');

INSERT INTO "tim"("id", "naziv", "osnovan", "sediste", "liga")
VALUES(nextval('tim_seq'), 'LA Lakers', to_date('01.11.1947.', 'dd.mm.yyyy.'), 'Los Andeles', 1);
INSERT INTO "tim"("id", "naziv", "osnovan", "sediste", "liga")
VALUES(nextval('tim_seq'), 'KK Crvena Zvezda', to_date('04.03.1945.', 'dd.mm.yyyy.'), 'Beograd', 4);
INSERT INTO "tim"("id", "naziv", "osnovan", "sediste", "liga")
VALUES(nextval('tim_seq'), 'KK Partizan', to_date('04.10.1945.', 'dd.mm.yyyy.'), 'Beograd', 4);
INSERT INTO "tim"("id", "naziv", "osnovan", "sediste", "liga")
VALUES(nextval('tim_seq'), 'KK Barselona', to_date('24.08.1926.', 'dd.mm.yyyy.'), 'Barselona', 2);
INSERT INTO "tim"("id", "naziv", "osnovan", "sediste", "liga")
VALUES(nextval('tim_seq'), 'Sakramento Kings', to_date('11.02.1948.', 'dd.mm.yyyy.'), 'Sakramento', 1);
INSERT INTO "tim"("id", "naziv", "osnovan", "sediste", "liga")
VALUES(nextval('tim_seq'), 'Klipers', to_date('11.02.1970.', 'dd.mm.yyyy.'), 'Los Andeles', 1);
INSERT INTO "tim"("id", "naziv", "osnovan", "sediste", "liga")
VALUES(nextval('tim_seq'), 'CSKA Moskva', to_date('23.04.1923.', 'dd.mm.yyyy.'), 'Moskva', 2);

INSERT INTO "tim"("id", "naziv", "osnovan", "sediste", "liga")
VALUES(-100, 'TIM NAZIV', to_date('01.01.1900.', 'dd.mm.yyyy.'), 'TIM SEDISTE', 1);


INSERT INTO "igrac"("id", "ime", "prezime", "broj_reg", "datum_rodjenja", "nacionalnost", "tim")
VALUES(nextval('igrac_seq'), 'Bogdan', 'Bogdanovic', '0123', to_date('18.08.1992.', 'dd.mm.yyyy.'), 1, 5);
INSERT INTO "igrac"("id", "ime", "prezime", "broj_reg", "datum_rodjenja", "nacionalnost", "tim")
VALUES(nextval('igrac_seq'), 'Miloš', 'Teodosic', '0124', to_date('19.03.1987.', 'dd.mm.yyyy.'), 1, 6);
INSERT INTO "igrac"("id", "ime", "prezime", "broj_reg", "datum_rodjenja", "nacionalnost", "tim")
VALUES(nextval('igrac_seq'), 'Branko', 'Lazic', '0125', to_date('12.01.1989.', 'dd.mm.yyyy.'), 1, 2);
INSERT INTO "igrac"("id", "ime", "prezime", "broj_reg", "datum_rodjenja", "nacionalnost", "tim")
VALUES(nextval('igrac_seq'), 'Nando', 'De Kolo', '0126', to_date('23.06.1987.', 'dd.mm.yyyy.'), 3, 7);

INSERT INTO "igrac"("id", "ime", "prezime", "broj_reg", "datum_rodjenja", "nacionalnost", "tim")
VALUES(-100, 'IGRAC IME', 'IGRAC PREZIME', '0127', to_date('23.06.1987.', 'dd.mm.yyyy.'), 3, 7);






