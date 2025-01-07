INSERT INTO przedmioty (nazwa_przedmiotu) VALUES
('Matematyka');
INSERT INTO przedmioty (nazwa_przedmiotu) VALUES
('Biologia');
INSERT INTO przedmioty (nazwa_przedmiotu) VALUES
('Historia');
INSERT INTO przedmioty (nazwa_przedmiotu) VALUES
('Język Polski');
INSERT INTO przedmioty (nazwa_przedmiotu) VALUES
('Geografia');

insert into role ( name)
values ("ROLE_UCZEN");
insert into role (name)
values ("ROLE_NAUCZYCIEL");
insert into role (name)
values ("ROLE_ADMIN");

-- Dodanie administratora
INSERT INTO user (email, password) VALUES ("admin@gmail.com", "$2y$10$TEqNXNz30HulGYYs.uax3ufepEwI84DjX4fZ9h3vDZ6z31n3pZ0QG");
INSERT INTO user_role (user_id, role_id) VALUES (LAST_INSERT_ID(), (SELECT id FROM role WHERE name = "ROLE_ADMIN"));
-- Tworzenie użytkownika i przypisanie do nauczyciela
INSERT INTO user (email, password) VALUES ("annanowak@gmail.com", "$2y$10$TEqNXNz30HulGYYs.uax3ufepEwI84DjX4fZ9h3vDZ6z31n3pZ0QG");
INSERT INTO user_role (user_id, role_id) VALUES (LAST_INSERT_ID(), (SELECT id FROM role WHERE name = "ROLE_NAUCZYCIEL"));
INSERT INTO nauczyciele (imie, nazwisko, user_id) VALUES ('Anna', 'Nowak', LAST_INSERT_ID());

INSERT INTO user (email, password) VALUES ("jankowalski@gmail.com", "$2y$10$TEqNXNz30HulGYYs.uax3ufepEwI84DjX4fZ9h3vDZ6z31n3pZ0QG");
INSERT INTO user_role (user_id, role_id) VALUES (LAST_INSERT_ID(), (SELECT id FROM role WHERE name = "ROLE_NAUCZYCIEL"));
INSERT INTO nauczyciele (imie, nazwisko, user_id) VALUES ('Jan', 'Kowalski', LAST_INSERT_ID());

INSERT INTO user (email, password) VALUES ("katarzynawisniewska@gmail.com", "$2y$10$TEqNXNz30HulGYYs.uax3ufepEwI84DjX4fZ9h3vDZ6z31n3pZ0QG");
INSERT INTO user_role (user_id, role_id) VALUES (LAST_INSERT_ID(), (SELECT id FROM role WHERE name = "ROLE_NAUCZYCIEL"));
INSERT INTO nauczyciele (imie, nazwisko, user_id) VALUES ('Katarzyna', 'Wiśniewska', LAST_INSERT_ID());

INSERT INTO user (email, password) VALUES ("jaroslawnoga@gmail.com", "$2y$10$TEqNXNz30HulGYYs.uax3ufepEwI84DjX4fZ9h3vDZ6z31n3pZ0QG");
INSERT INTO user_role (user_id, role_id) VALUES (LAST_INSERT_ID(), (SELECT id FROM role WHERE name = "ROLE_NAUCZYCIEL"));
INSERT INTO nauczyciele (imie, nazwisko, user_id) VALUES ('Jarosław', 'Noga', LAST_INSERT_ID());

INSERT INTO user (email, password) VALUES ("jankomar@gmail.com", "$2y$10$TEqNXNz30HulGYYs.uax3ufepEwI84DjX4fZ9h3vDZ6z31n3pZ0QG");
INSERT INTO user_role (user_id, role_id) VALUES (LAST_INSERT_ID(), (SELECT id FROM role WHERE name = "ROLE_NAUCZYCIEL"));
INSERT INTO nauczyciele (imie, nazwisko, user_id) VALUES ('Jan', 'Komar', LAST_INSERT_ID());

-- Tworzenie użytkownika i przypisanie do ucznia
INSERT INTO user (email, password) VALUES ("piotrkowalski@gmail.com", "$2y$10$TEqNXNz30HulGYYs.uax3ufepEwI84DjX4fZ9h3vDZ6z31n3pZ0QG");
INSERT INTO user_role (user_id, role_id) VALUES (LAST_INSERT_ID(), (SELECT id FROM role WHERE name = "ROLE_UCZEN"));
INSERT INTO uczniowie (imie, nazwisko, id_klasy, user_id) VALUES ('Piotr', 'Kowalski', 1, LAST_INSERT_ID());

INSERT INTO user (email, password) VALUES ("martanowakowska@gmail.com", "$2y$10$TEqNXNz30HulGYYs.uax3ufepEwI84DjX4fZ9h3vDZ6z31n3pZ0QG");
INSERT INTO user_role (user_id, role_id) VALUES (LAST_INSERT_ID(), (SELECT id FROM role WHERE name = "ROLE_UCZEN"));
INSERT INTO uczniowie (imie, nazwisko, id_klasy, user_id) VALUES ('Marta', 'Nowakowska', 2, LAST_INSERT_ID());

INSERT INTO user (email, password) VALUES ("jakubkot@gmail.com", "$2y$10$TEqNXNz30HulGYYs.uax3ufepEwI84DjX4fZ9h3vDZ6z31n3pZ0QG");
INSERT INTO user_role (user_id, role_id) VALUES (LAST_INSERT_ID(), (SELECT id FROM role WHERE name = "ROLE_UCZEN"));
INSERT INTO uczniowie (imie, nazwisko, id_klasy, user_id) VALUES ('Jakub', 'Kot', 1, LAST_INSERT_ID());

INSERT INTO user (email, password) VALUES ("rakotako@gmail.com", "$2y$10$TEqNXNz30HulGYYs.uax3ufepEwI84DjX4fZ9h3vDZ6z31n3pZ0QG");
INSERT INTO user_role (user_id, role_id) VALUES (LAST_INSERT_ID(), (SELECT id FROM role WHERE name = "ROLE_UCZEN"));
INSERT INTO uczniowie (imie, nazwisko, id_klasy, user_id) VALUES ('Rako', 'Tako', 2, LAST_INSERT_ID());

INSERT INTO user (email, password) VALUES ("torbor@gmail.com", "$2y$10$TEqNXNz30HulGYYs.uax3ufepEwI84DjX4fZ9h3vDZ6z31n3pZ0QG");
INSERT INTO user_role (user_id, role_id) VALUES (LAST_INSERT_ID(), (SELECT id FROM role WHERE name = "ROLE_UCZEN"));
INSERT INTO uczniowie (imie, nazwisko, id_klasy, user_id) VALUES ('Tor', 'Bor', 3, LAST_INSERT_ID());

INSERT INTO user (email, password) VALUES ("bontgon@gmail.com", "$2y$10$TEqNXNz30HulGYYs.uax3ufepEwI84DjX4fZ9h3vDZ6z31n3pZ0QG");
INSERT INTO user_role (user_id, role_id) VALUES (LAST_INSERT_ID(), (SELECT id FROM role WHERE name = "ROLE_UCZEN"));
INSERT INTO uczniowie (imie, nazwisko, id_klasy, user_id) VALUES ('Bont', 'Gon', 3, LAST_INSERT_ID());

-- Tworzenie klasy i przypisanie w wychowawcy
INSERT INTO klasy (nazwa_klasy, wychowawca_id) VALUES
('1A', 1);
INSERT INTO klasy (nazwa_klasy, wychowawca_id) VALUES
('1B', 2);
INSERT INTO klasy (nazwa_klasy, wychowawca_id) VALUES
('3A', 4);

INSERT INTO nauczyciele_przedmioty (id_nauczyciela, id_przedmiotu) VALUES
(1, 1); -- Anna Nowak uczy Matematyki
INSERT INTO nauczyciele_przedmioty (id_nauczyciela, id_przedmiotu) VALUES
(1, 2); -- Anna Nowak uczy Biologii
INSERT INTO nauczyciele_przedmioty (id_nauczyciela, id_przedmiotu) VALUES
(2, 2); -- Jan Kowalski uczy Biologii
INSERT INTO nauczyciele_przedmioty (id_nauczyciela, id_przedmiotu) VALUES
(3, 3); -- Katarzyna Wiśniewska uczy Historii
INSERT INTO nauczyciele_przedmioty (id_nauczyciela, id_przedmiotu) VALUES
(1, 3);
INSERT INTO nauczyciele_przedmioty (id_nauczyciela, id_przedmiotu) VALUES
(4, 3);
INSERT INTO nauczyciele_przedmioty (id_nauczyciela, id_przedmiotu) VALUES
(4, 4);
INSERT INTO nauczyciele_przedmioty (id_nauczyciela, id_przedmiotu) VALUES
(5, 3);

INSERT INTO oceny (id_ucznia, id_przedmiotu, id_nauczyciela, ocena, data_oceny) VALUES
(1, 1, 1, 5, '2024-12-01');
INSERT INTO oceny (id_ucznia, id_przedmiotu, id_nauczyciela, ocena, data_oceny) VALUES
(2, 2, 2, 4, '2024-12-02');
INSERT INTO oceny (id_ucznia, id_przedmiotu, id_nauczyciela, ocena, data_oceny) VALUES
(6, 2, 1, 5, '2024-12-09');
INSERT INTO oceny (id_ucznia, id_przedmiotu, id_nauczyciela, ocena, data_oceny) VALUES
(5, 3, 4, 6, '2025-01-02');