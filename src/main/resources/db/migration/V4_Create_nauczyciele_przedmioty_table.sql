CREATE TABLE nauczyciele_przedmioty (
    id_nauczyciela INT,
    id_przedmiotu INT,
    PRIMARY KEY (id_nauczyciela, id_przedmiotu),
    FOREIGN KEY (id_nauczyciela) REFERENCES nauczyciele(id_nauczyciela),
    FOREIGN KEY (id_przedmiotu) REFERENCES przedmioty(id_przedmiotu)
) engine=InnoDB;
