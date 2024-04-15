package com.example.springkurs.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Table
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long addressid;
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="cityidfk")
    private  City city;

    @NotNull
    @Size(min = 4,message = "Мало букав")
    @Size(max = 30,message = "Много букав")
    private String person;

    @NotNull
    @Size(min = 4,message = "Мало букав")
    @Size(max = 15,message = "Много букав")
    private String street;

    @NotNull
    @Size(min = 1,message = "Мало букав")
    @Size(max = 15,message = "Много букав")
    private String building;

    @NotNull
    @Size(min = 1,message = "Мало букав")
    @Size(max = 15,message = "Много букав")
    private String office;


}
