package rw.TennisClub.TennisReservationSystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Court {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer court_id;
    private  String court_number;
    private  String court_type;
    private String court_price;

    public Integer getCourt_id() {
        return court_id;
    }

    public void setCourt_id(Integer court_id) {
        this.court_id = court_id;
    }

    public String getCourt_number() {
        return court_number;
    }

    public void setCourt_number(String court_number) {
        this.court_number = court_number;
    }

    public String getCourt_type() {
        return court_type;
    }

    public void setCourt_type(String court_type) {
        this.court_type = court_type;
    }

    public String getCourt_price() {
        return court_price;
    }

    public void setCourt_price(String court_price) {
        this.court_price = court_price;
    }
}
