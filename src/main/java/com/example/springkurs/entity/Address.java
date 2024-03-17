package com.example.springkurs.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;



@Data
@Table
public class Address {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "city_id")
    private int city_id;
    @NotNull
    @Size(min = 4,message = "Мало букав")
    @Size(max = 30,message = "Много букав")
    @Column(name = "person")
    private String person;
    @NotNull
    @Size(min = 4,message = "Мало букав")
    @Size(max = 15,message = "Много букав")
    @Column(name = "street")
    private String street;
    @NotNull
    @Size(min = 1,message = "Мало букав")
    @Size(max = 15,message = "Много букав")
    @Column(name = "building")
    private String building;
    @NotNull
    @Size(min = 1,message = "Мало букав")
    @Size(max = 15,message = "Много букав")
    @Column(name = "office")
    private String office;


}
