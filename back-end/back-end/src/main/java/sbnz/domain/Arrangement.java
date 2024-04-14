package sbnz.domain;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="arrangements", schema = "isa")
public class Arrangement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private Integer price;

    @OneToMany(mappedBy = "arrangement", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Trip> trips = new HashSet<Trip>();

    public Arrangement() {
        super();
    }

    public Arrangement(Integer id, String name, Integer price) {
        super();
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Arrangement(Integer id, String name, Integer price, Set<Trip> trips) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.trips = trips;
    }

    public Set<Trip> getTrips() {
        return trips;
    }

    public void setTrips(Set<Trip> trips) {
        this.trips = trips;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*
     * Pri implementaciji equals and hashCode metoda treba obratiti paznju da se
     * one razlikuju kada se koristi ORM (Hibernate) i kada se klase posmatraju kao
     * obicne POJO klase. Hibernate zahteva da entitet mora biti jednak samom sebi kroz sva
     * stanja tog objekta (tranzijentni (novi objekat), perzistentan (persistent), otkacen (detached) i obrisan (removed)).
     * To znaci da bi dobar pristup bio da se za generisanje equals i hashCode metoda koristi podatak
     * koji je jedinstven a poznat unapred (tzv. business key) npr. index studenta, isbn knjige, itd.
     * U slucaju da takvog obelezja nema, obicno se implementacija svodi na proveri da li je id (koji je kljuc) isti.
     * Posto u velikom broju slucajeva id baza generise, to znaci da u tranzijentnom stanju objekti nisu jednaki.
     * Postoji nekoliko resenja za ovaj problem:
     * 1. Naci neko jedinstveno obelezje
     * 2. Koristiti prirodne kljuceve
     * 3. Pre cuvanja na neki nacin saznati koja je sledeca vrednost koju ce baza generisati pa pozvati setId metodu da se kompletira objekat cak i pre cuvanja
     * 4. Na drugi nacin implementirati equals i hashCode - primer u klasi Teacher
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Arrangement s = (Arrangement) o;
        if (s.name == null || name == null) {
            return false;
        }
        return Objects.equals(name, s.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public String toString() {
        return "Arrangement [id=" + id + ", firstName=" + name + "]";
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
