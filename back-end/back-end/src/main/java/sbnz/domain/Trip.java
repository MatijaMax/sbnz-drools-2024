package sbnz.domain;

import javax.persistence.*;

@Entity
@Table(name="trips", schema = "isa")
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "price", nullable = false)
    private Integer price;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "arrangementId", nullable = true)
    private Arrangement arrangement;

    public Trip() {
        // TODO Auto-generated constructor stub
        super();
    }

    public Trip(Integer id, String name, String description, Integer price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Trip(Integer id, String name, String description, Integer price, Arrangement arrangement) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.arrangement = arrangement;
    }

    public Trip(String name, String description, Integer price) {
        this.name = name;
        this.description = description;
        this.price = price;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Arrangement getArrangement() {
        return arrangement;
    }

    public void setArrangement(Arrangement arrangement) {
        this.arrangement = arrangement;
    }
}
