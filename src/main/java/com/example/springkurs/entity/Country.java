package com.example.springkurs.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
@Table
public class Country {
    @Id
    private int Id;
    @NotNull
    @Size(min = 4,message = "Мало букав")
    @Size(max = 30,message = "Много букав")
    private String fullname;
    @NotNull
    @Size(min = 1,message = "Мало букав")
    @Size(max = 10,message = "Много букав")
    private String shortname;
}
