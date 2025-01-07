package projekt.dziennik_ocen.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name = "uczniowie")
public class Uczniowie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ucznia", unique = true, nullable = false)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user; // Powiązanie z modelem User

    @Column(name = "imie", nullable = false, length = 50)
    private String imie;

    @Column(name = "nazwisko", nullable = false, length = 50)
    private String nazwisko;

    @ManyToOne
    @JoinColumn(name = "id_klasy")
    private Klasy klasa;

    @Override
    public String toString() {
        return String.format("%s %s (%s)", imie, nazwisko, klasa != null ? klasa.getNazwaKlasy() : "---");
    }
}
