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
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cityid;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="regionidfk")
    private  Region region;

    @OneToMany(mappedBy = "city")
    private List<Address> addresses;

    @NotNull
    @Size(min = 3,message = "Мало букав")
    @Size(max = 20,message = "Много букав")
    private String namecity;
    @Override
    public String toString(){
        return "1";
    }
}
