CREATE TABLE nauczyciele (
    id_nauczyciela INT AUTO_INCREMENT PRIMARY KEY,
    imie VARCHAR(50) NOT NULL,
    nazwisko VARCHAR(50) NOT NULL,
    user_id INT UNIQUE,
    FOREIGN KEY (user_id) REFERENCES user(id)
) engine=InnoDB;
