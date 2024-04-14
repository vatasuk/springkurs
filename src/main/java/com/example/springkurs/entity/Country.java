package com.example.springkurs.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;


@Data
@Table
@Entity
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long countryid;

    @OneToMany(mappedBy = "country")
    private List<Region> regions;

    @NotNull
    @Size(min = 4,message = "Мало букав")
    @Size(max = 30,message = "Много букав")
    private String fullname;
    @NotNull
    @Size(min = 1,message = "Мало букав")
    @Size(max = 10,message = "Много букав")
    private String shortname;


}
