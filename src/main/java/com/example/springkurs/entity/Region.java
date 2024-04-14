package com.example.springkurs.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;


@Data
@Table
@Entity
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long regionid;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="countryidfk")
    private  Country country;

    @OneToMany(mappedBy = "region")
    private List<City> cities;

    @NotNull
    @Size(min = 4,message = "Мало букав")
    @Size(max = 30,message = "Много букав")
    private String nameregion;

}
