package com.example.springkurs.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Table
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long countryid;


    @NotNull
    @Size(min = 4,message = "Мало букав")
    private String fullname;
    @NotNull
    @Size(min = 2,message = "Мало букав")
    private String shortname;
    @OneToMany(mappedBy = "country")
    private List<Region> regions;

}
