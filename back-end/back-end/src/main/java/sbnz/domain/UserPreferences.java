package sbnz.domain;

import javax.persistence.*;

@Entity
@Table(name="user_preferences", schema = "isa")
public class UserPreferences {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "destinations")
    private String destinations;

    @Column(name = "trips")
    private String trips;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDestinations() {
        return destinations;
    }

    public void setDestinations(String destinations) {
        this.destinations = destinations;
    }

    public String getTrips() {
        return trips;
    }

    public void setTrips(String trips) {
        this.trips = trips;
    }
}
