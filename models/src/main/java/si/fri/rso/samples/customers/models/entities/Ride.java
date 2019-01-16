package si.fri.rso.samples.customers.models.entities;

import org.eclipse.persistence.annotations.UuidGenerator;
import si.fri.rso.samples.customers.models.dtos.Order;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.sql.Date;
import java.time.Instant;
import java.util.List;

@Entity(name = "ride")
@NamedQueries(value =
        {
                @NamedQuery(name = "Ride.getAll", query = "SELECT c FROM ride c")
        })
public class Ride {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "initial_departure_time")
    private Instant initialDepartureTime;

    @Column(name = "initial_payment")
    private Integer initialPayment;


    @Column(name = "initial_passengers_num")
    private Integer initialPassengersNum;


    @Column(name="status")
    private String status;

    @Column(name = "driver_id")
    private Integer driverId;

    @Column(name = "initial_latitute")
    private Double initialLatitute;

    @Column(name = "initial_longitude")
    private Double initialLongitude;




    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Instant getInitialDepartureTime() {
        return initialDepartureTime;
    }

    public void setInitialDepartureTime(Instant initialDepartureTime) {
        this.initialDepartureTime = initialDepartureTime;
    }

    public Integer getInitialPayment() {
        return initialPayment;
    }
    public void setInitialPayment(Integer initialPayment) {
        this.initialPayment = initialPayment;
    }


    public Integer getInitialPassengersNum() {
        return initialPassengersNum;
    }
    public void setInitialPassengersNum(Integer initialPassengersNum) {
        this.initialPassengersNum = initialPassengersNum;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }



    public Integer getDriverId() {
        return driverId;
    }
    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }


    public Double getInitialLatitute() {
        return initialLatitute;
    }

    public void setInitialLatitute(Double initialLatitute) {
        this.initialLatitute = initialLatitute;
    }

    public Double getInitialLongitude() {
        return initialLongitude;
    }

    public void setInitialLongitude(Double initialLongitude) {
        this.initialLongitude = initialLongitude;
    }
}