CREATE TABLE oceny (
    id_oceny INT AUTO_INCREMENT PRIMARY KEY,
    id_ucznia INT,
    id_przedmiotu INT,
    id_nauczyciela INT,
    ocena INT NOT NULL,
    data_oceny DATE NOT NULL,
    FOREIGN KEY (id_ucznia) REFERENCES uczniowie(id_ucznia),
    FOREIGN KEY (id_przedmiotu) REFERENCES przedmioty(id_przedmiotu),
    FOREIGN KEY (id_nauczyciela) REFERENCES nauczyciele(id_nauczyciela)
) engine=InnoDB;
