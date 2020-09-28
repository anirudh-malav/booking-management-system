package com.bookingManagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Theatre")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Theatre extends AbstractEntity<Theatre> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "THEATRE_ID")
    private Long theatreId;

    @Column(name = "THEATRE_NAME")
    private String theatreName;

    @ManyToOne
    private City city;

    @OneToMany
    private List<Show> showList;


}
