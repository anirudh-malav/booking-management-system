package com.bookingManagement.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Ticket")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ticket extends AbstractEntity<Ticket> {
    @Id
    @Column(name = "TICKET_ID")
    private String ticketId;

    @ManyToOne
    private UserDetails userDetails;

    @ManyToOne
    private City city;

    @ManyToOne
    private Theatre theatre;

    @ManyToOne
    private Show show;

    @OneToMany
    private List<Seat> seatList;
}
