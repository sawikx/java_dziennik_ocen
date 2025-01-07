CREATE TABLE klasy (
    id_klasy INT AUTO_INCREMENT PRIMARY KEY,
    nazwa_klasy VARCHAR(10) NOT NULL,
    wychowawca_id INT,
    FOREIGN KEY (wychowawca_id) REFERENCES nauczyciele(id_nauczyciela)
) engine=InnoDB;
