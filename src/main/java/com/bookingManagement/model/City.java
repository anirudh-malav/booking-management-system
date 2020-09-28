package com.bookingManagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "city")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class City extends AbstractEntity<City>{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CITY_ID")
    private Long cityID;

    @Column(name = "CITY_NAME")
    private String cityName;

}
