package com.example.springkurs.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
@Table
public class City {
    @Id
    private int id;
    @NotNull
    private int region_id;
    @NotNull
    @Size(min = 3,message = "Мало букав")
    @Size(max = 20,message = "Много букав")
    private String namecity;
}
