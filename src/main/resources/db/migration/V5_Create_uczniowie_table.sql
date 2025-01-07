CREATE TABLE uczniowie (
    id_ucznia INT AUTO_INCREMENT PRIMARY KEY,
    imie VARCHAR(50) NOT NULL,
    nazwisko VARCHAR(50) NOT NULL,
    id_klasy INT,
    user_id INT UNIQUE,
    FOREIGN KEY (id_klasy) REFERENCES klasy(id_klasy),
    FOREIGN KEY (user_id) REFERENCES user(id)
) engine=InnoDB;


