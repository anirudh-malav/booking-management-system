package com.bookingManagement.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "Show")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Show  extends AbstractEntity<Show>{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "SHOW_ID")
    private Long showId;

    @Column(name = "SHOW_NAME")
    private String showName;

}
