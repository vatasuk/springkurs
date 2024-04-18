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
    private Long addressid;
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="cityidfk")
    private  City city;

    @NotNull
    @Size(min = 4,message = "Мало букав")
    @Size(max = 40,message = "Много букав")
    private String person;

    @NotNull
    @Size(min = 6,message = "Мало букав")
    @Size(max = 45,message = "Много букав")
    private String street;

    @NotNull
    @Size(min = 1,message = "Мало букав")
    @Size(max = 45,message = "Много букав")
    private String building;

    @NotNull
    @Size(min = 2,message = "Мало букав")
    @Size(max = 45,message = "Много букав")
    private String office;


}
