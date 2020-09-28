package com.bookingManagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "SHOW_SEAT")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShowSeat extends AbstractEntity<Seat>{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "SHOW_ID")
    private Long showId;

    @Column(name = "SEAT_ID")
    private Long SeatId;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private SeatStatus status;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, name = "MODIFY_DATE")
    private Date modifyDate;
}
