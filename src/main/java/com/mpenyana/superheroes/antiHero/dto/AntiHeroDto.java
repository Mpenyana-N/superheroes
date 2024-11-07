package com.mpenyana.superheroes.antiHero.dto;


import lombok.Getter;
import lombok.Setter;
import java.util.UUID;

@Getter
@Setter

public class AntiHeroDto {

    private UUID id;
    private String firstName;
    private String LastName;
    private String house;
    private String knownAS;
}
