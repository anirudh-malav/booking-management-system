package com.bookingManagement.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Table(name = "USER_DETAILS", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"USER_ID","EMAIL_ID"})
})
public class UserDetails implements Serializable {
    private static final long serialVersionUID = 12153334345L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "USER_ID")
    public Long userId;
    @Column(nullable = false, name = "EMAIL_ID")
    public String emailId;
    @Column(nullable = false, name = "PASSWORD")
    private String password;
}
