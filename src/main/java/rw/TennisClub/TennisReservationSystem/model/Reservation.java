package rw.TennisClub.TennisReservationSystem.model;

import jakarta.persistence.*;

import java.sql.Time;
import java.util.Date;

@Entity
@Table(name = "reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reservation_id;
    private String courtnumber;
    private Date date;
    private Time time;

    public Integer getReservation_id() {
        return reservation_id;
    }

    public void setReservation_id(Integer reservation_id) {
        this.reservation_id = reservation_id;
    }

    public String getCourtnumber() {
        return courtnumber;
    }

    public void setCourtnumber(String courtnumber) {
        this.courtnumber = courtnumber;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }
}
